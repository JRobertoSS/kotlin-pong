package com.jrobertoss.pong.entities


import java.awt.Graphics

/**
 * Default parent of game entities.
 * Defines tick (logic processor) and render (graphics draw) methods to be implemented.
 */
abstract class AbstractGameEntity(
    var x: Double = 0.0,
    var y: Double = 0.0,
    var width: Int = 0,
    var height: Int = 0
) {

    abstract fun tick()
    abstract fun render(graphics: Graphics)

}