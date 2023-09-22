package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import com.jrobertoss.pong.PongHelper.constraintHorizontalBoundaries
import java.awt.Color
import java.awt.Graphics

/**
 * The controlled player bar.
 * Moves on horizontal axis accordingly to flags, that are set by [com.jrobertoss.pong.GameKeyListener]
 */
class Player(
    x: Double,
    y: Double
): AbstractGameEntity(x, y, 40, 5) {

    var right: Boolean = false
    var left: Boolean = false

    // Used by reflection
    constructor() : this(100.0,GameCanvas.GAME_HEIGHT - 5.0)

    override fun tick() {
        if (right) {
            x++
        } else if (left) {
            x--
        }

        constraintHorizontalBoundaries()
    }

    override fun render(graphics: Graphics) {
        graphics.color = Color.blue
        graphics.fillRect(x.toInt(), y.toInt(), width, height)
    }
}