package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameState
import java.awt.Color
import java.awt.Graphics

class Enemy(
    x: Double,
    y: Double
): AbstractGameEntity(x, y, 40, 5) {

    // Used by reflection
    constructor() : this(100.0,0.0)

    override fun tick() {
        x += (GameState.ball.x - x - 6) * 0.07
    }

    override fun render(graphics: Graphics) {
        graphics.color = Color.red
        graphics.fillRect(x.toInt(), y.toInt(), width, height)
    }
}