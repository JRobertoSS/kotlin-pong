package com.jrobertoss.pong

import com.jrobertoss.pong.entities.AbstractGameEntity
import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.WindowConstants

object GameCanvas : Canvas() {

    const val gameWidth = 160
    const val gameHeight = 120
    const val gameScale = 3

    val layer: BufferedImage

    init {
        layer = BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_RGB)

        preferredSize = Dimension(gameWidth * gameScale, gameHeight * gameScale)
        addKeyListener(GameKeyListener.instance)

        val frame = JFrame("Pong").apply {
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

        val graphics = this.layer.graphics
        graphics.color = Color.black
        graphics.fillRect(0, 0, gameWidth, gameHeight)

        gameEntityList.forEach {
            it.render(graphics)
        }

        bufferStrategy.drawGraphics.drawImage(
            this.layer,
            0,
            0,
            gameWidth * gameScale,
            gameHeight * gameScale,
            null
        )

        bufferStrategy.show()
    }
}