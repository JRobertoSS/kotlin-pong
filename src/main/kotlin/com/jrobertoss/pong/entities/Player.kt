package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import java.awt.Color
import java.awt.Graphics

class Player(
    x: Double,
    y: Double
): AbstractGameEntity(x, y, 40, 5) {

    var right: Boolean = false
    var left: Boolean = false

    // Used by reflection
    constructor() : this(100.0,GameCanvas.gameHeight - 5.0)

    override fun tick() {
        if (right) {
            x++
        } else if (left) {
            x--
        }

        if (x + width > GameCanvas.gameWidth) {
            x = (GameCanvas.gameWidth - width).toDouble()
        } else if (x < 0) {
            x = 0.0
        }
    }

    override fun render(graphics: Graphics) {
        graphics.color = Color.blue
        graphics.fillRect(x.toInt(), y.toInt(), width, height)
    }
}