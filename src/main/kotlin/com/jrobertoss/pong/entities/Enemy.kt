package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameState
import com.jrobertoss.pong.PongHelper.constraintHorizontalBoundaries
import java.awt.Color
import java.awt.Graphics

/**
 * The enemy player AI bar.
 * Tries to chase the ball position, with a certain limitation of speed to make it beatable.
 */
class Enemy(
    x: Double,
    y: Double,
): AbstractGameEntity(x, y, 40, 5) {

    private val enemySpeed = 0.07
    private val xOffset = 6

    // Used by reflection
    constructor() : this(100.0,0.0)

    override fun tick() {
        x += (GameState.ball.x - x - xOffset) * enemySpeed
        constraintHorizontalBoundaries()
    }

    override fun render(graphics: Graphics) = graphics.run {
        color = Color.red
        fillRect(x.toInt(), y.toInt(), width, height)
    }

}
