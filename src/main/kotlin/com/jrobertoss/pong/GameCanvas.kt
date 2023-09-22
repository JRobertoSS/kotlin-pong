package com.jrobertoss.pong

import com.jrobertoss.pong.entities.AbstractGameEntity
import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.WindowConstants

object GameCanvas : Canvas() {

    const val GAME_WIDTH = 160
    const val GAME_HEIGHT = 120
    private const val GAME_SCALE = 3
    const val SCALED_WIDTH = GAME_WIDTH * GAME_SCALE
    const val SCALED_HEIGHT = GAME_HEIGHT * GAME_SCALE


    private val layer: BufferedImage = BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB)

    init {

        preferredSize = Dimension(SCALED_WIDTH, SCALED_HEIGHT)
        addKeyListener(GameKeyListener)

        JFrame("Pong").apply {
            this.isResizable = false
            this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            this.add(this@GameCanvas)
            this.pack()
            this.setLocationRelativeTo(null)
            this.isVisible = true
        }

    }

    fun render(gameEntityList: List<AbstractGameEntity>) {
        val bufferStrategy = this.bufferStrategy ?: run {
            this.createBufferStrategy(3)
            return
        }

        this.layer.graphics.apply {
            color = Color.black
            fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT)

            gameEntityList.forEach {
                it.render(this)
            }
        }

        bufferStrategy.drawGraphics.drawImage(
            this.layer,
            0,
            0,
            SCALED_WIDTH,
            SCALED_HEIGHT,
            null
        )

        bufferStrategy.show()
    }
}