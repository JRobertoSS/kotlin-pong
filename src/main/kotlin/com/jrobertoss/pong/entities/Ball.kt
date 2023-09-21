package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import com.jrobertoss.pong.GameState
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class Ball(
    x: Double,
    y: Double,
    private var dx: Double = 0.0,
    private var dy: Double = 0.0,
    private var speed: Double = 1.7,
): AbstractGameEntity(x, y, 4, 4) {

    // Used by reflection
    constructor() : this(100.0,GameCanvas.gameHeight / 2 -1.0)

    init {
        val angle = Random().nextInt(120 - 45) + 45 + 1
        dx = cos(Math.toRadians(angle.toDouble()))
        dy = sin(Math.toRadians(angle.toDouble()))
    }


    override fun tick() {
        executeWidthVerification()

        if (executeHeightVerification()) return

        executeCollisionVerification()

        setNewPosition()
    }

    private fun setNewPosition() {
        x += dx * speed
        y += dy * speed
    }

    private fun executeCollisionVerification() {
        val bounds = Rectangle(
            (x + (dx * speed)).toInt(),
            (y + (dy * speed)).toInt(), width, height
        )

        val boundsPlayer = Rectangle(
            GameState.player.x.toInt(),
            GameState.player.y.toInt(), GameState.player.width, GameState.player.height
        )
        val boundsEnemy = Rectangle(
            GameState.enemy.x.toInt(),
            GameState.enemy.y.toInt(), GameState.enemy.width, GameState.enemy.height
        )

        if (bounds.intersects(boundsPlayer)) {
            val angle = Random().nextInt(120 - 45) + 45 + 1
            dx = Math.cos(Math.toRadians(angle.toDouble()))
            dy = Math.sin(Math.toRadians(angle.toDouble()))
            if (dy > 0) dy *= -1.0
        } else if (bounds.intersects(boundsEnemy)) {
            val angle = Random().nextInt(120 - 45) + 45 + 1
            dx = Math.cos(Math.toRadians(angle.toDouble()))
            dy = Math.sin(Math.toRadians(angle.toDouble()))
            if (dy < 0) dy *= -1.0
        }
    }

    private fun executeHeightVerification(): Boolean {
        if (y >= GameCanvas.gameHeight) {
            // Enemy score
            ScoreBoard.addEnemyScore()
            // Reposition everything
            GameState.initEntities()
            return true
        } else if (y < 0) {
            // Player score
            ScoreBoard.addPlayerScore()
            // Reposition everything
            GameState.initEntities()
            return true
        }
        return false
    }

    private fun executeWidthVerification() {
        if (x + (dx * speed) + width >= GameCanvas.gameWidth) {
            dx *= -1.0
        } else if (x + (dx * speed) < 0) {
            dx *= -1.0
        }
    }

    override fun render(graphics: Graphics) {
        graphics.color = Color.yellow
        graphics.fillRect(x.toInt(), y.toInt(), width, height)
    }
}