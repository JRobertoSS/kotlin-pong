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

    private const val MIN_ANGLE = 45
    private const val MAX_ANGLE = 120

    /**
     * Get a random int between [MIN_ANGLE] and [MAX_ANGLE]
     */
    fun getRandomAngle() = Random.nextInt(MIN_ANGLE, MAX_ANGLE + 1) // until param is exclusive

    /**
     * Get a pair of random radian angles, used for directional X and directional Y,
     * by converting a random angle generated in degrees by [getRandomAngle] to radians.
     *
     * @see Math.toRadians
     * @see cos
     * @see sin
     */
    fun getRandomRadianAnglePair() = getRandomAngle().let { angle ->
        cos(Math.toRadians(angle.toDouble())) to sin(Math.toRadians(angle.toDouble()))
    }

    /**
     * Get an [Rectangle] instance with given dimensions and position.
     * Used for collision checks with [Rectangle.intersects]
     */
    fun getRectangle(x: Double, y: Double, width: Int, height: Int) = Rectangle(
        x.toInt(), y.toInt(), width, height
    )

    fun getRectangle(x: Double, y: Double, width: Double, height: Double) =
        getRectangle(x, y, width.toInt(), height.toInt())

    /**
     * Constraint any player movement to the screen horizontal area.
     */
    fun AbstractGameEntity.constraintHorizontalBoundaries() {
        if (x + width > GameCanvas.GAME_WIDTH) {
            x = (GameCanvas.GAME_WIDTH - width).toDouble()
        } else if (x < 0) {
            x = 0.0
        }
    }
}