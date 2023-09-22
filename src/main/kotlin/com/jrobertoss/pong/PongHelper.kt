package com.jrobertoss.pong

import com.jrobertoss.pong.entities.AbstractGameEntity
import java.awt.Rectangle
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Utilitary functions
 */
object PongHelper {

    fun getRandomAngle() = Random.nextInt(120 - 45) + 45 + 1

    fun getRandomDxDyPair() = getRandomAngle().let { angle ->
        cos(Math.toRadians(angle.toDouble())) to sin(Math.toRadians(angle.toDouble()))
    }

    fun getRectangle(x: Double, y: Double, width: Int, height: Int) = Rectangle(
        x.toInt(), y.toInt(), width, height
    )

    fun getRectangle(x: Double, y: Double, width: Double, height: Double) =
        getRectangle(x, y, width.toInt(), height.toInt())

    fun AbstractGameEntity.constraintHorizontalBoundaries() {
        if (x + width > GameCanvas.GAME_WIDTH) {
            x = (GameCanvas.GAME_WIDTH - width).toDouble()
        } else if (x < 0) {
            x = 0.0
        }
    }
}