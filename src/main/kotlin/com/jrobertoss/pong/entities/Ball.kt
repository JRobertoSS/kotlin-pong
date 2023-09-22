package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import com.jrobertoss.pong.GameState
import com.jrobertoss.pong.PongHelper.getRandomRadianAnglePair
import com.jrobertoss.pong.PongHelper.getRectangle
import java.awt.Color
import java.awt.Graphics

/**
 * The ball of the game.
 * Moves in a random direction/angle, bouncing on horizontal boundaries or by hitting any player bar,
 * then inverting its direction and new random angle
 */
class Ball(
    x: Double,
    y: Double,
    // direction of X axis
    private var dx: Double = 0.0,
    // direction of Y axis
    private var dy: Double = 0.0,
    private var speed: Double = 0.8,
    private val speedModifier: Double = 0.1
) : AbstractGameEntity(x, y, 4, 4) {

    // Used by reflection
    constructor() : this(100.0, GameCanvas.GAME_HEIGHT / 2 - 1.0)

    init {
        setRandomDxDy()
    }

    override fun tick() {
        checkHorizontalBoundaries()

        if (checkVerticalBoundaries()) return

        executeCollisionVerification()

        setNewPosition()
    }

    private fun setNewPosition() {
        x += dx * speed
        y += dy * speed
    }

    private fun executeCollisionVerification() {

        val bounds = getRectangle(
            (x + (dx * speed)),
            (y + (dy * speed)),
            width, height
        )

        val boundsPlayer = getRectangle(
            GameState.player.x,
            GameState.player.y,
            GameState.player.width,
            GameState.player.height
        )

        val boundsEnemy = getRectangle(
            GameState.enemy.x,
            GameState.enemy.y,
            GameState.enemy.width,
            GameState.enemy.height
        )

        if (bounds.intersects(boundsPlayer)) {
            setRandomDxDy()
            if (dy > 0) onAnyPlayerCollision()
        } else if (bounds.intersects(boundsEnemy)) {
            setRandomDxDy()
            if (dy < 0) onAnyPlayerCollision()
        }
    }

    private fun checkVerticalBoundaries(): Boolean {
        // Has the ball passed through upper boundaries?
        if (y >= GameCanvas.GAME_HEIGHT) {
            // Enemy score
            ScoreBoard.addEnemyScore()
            // Reposition everything
            GameState.initEntities()
            return true

        // Or else has the ball passed through lower boundaries?
        } else if (y < 0) {
            // Player score
            ScoreBoard.addPlayerScore()
            // Reposition everything
            GameState.initEntities()
            return true
        }
        return false
    }

    private fun checkHorizontalBoundaries() {
        val futureX = x + (dx * speed)
        if (futureX + width >= GameCanvas.GAME_WIDTH) {
            reverseDx()
        } else if (futureX < 0) {
            reverseDx()
        }
    }

    private fun onAnyPlayerCollision() {
        reverseDy()
        increaseSpeed()
    }

    private fun increaseSpeed() {
        speed += speedModifier
    }

    private fun reverseDy() {
        dy *= -1
    }

    private fun reverseDx() {
        dx *= -1
    }

    private fun setRandomDxDy() = getRandomRadianAnglePair().apply {
        dx = this.first
        dy = this.second
    }

    override fun render(graphics: Graphics) = graphics.run {
        color = Color.yellow
        fillRect(x.toInt(), y.toInt(), width, height)
    }

}