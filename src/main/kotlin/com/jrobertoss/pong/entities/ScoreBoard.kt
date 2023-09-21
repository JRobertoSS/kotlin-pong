package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import java.awt.Color
import java.awt.Graphics

object ScoreBoard : AbstractGameEntity() {

    private var playerScore = 0
    private var enemyScore = 0
    private val xOffset = 10
    private val yOffset = 20

    fun addPlayerScore() = playerScore++
    fun addEnemyScore() = enemyScore++

    override fun tick() {

    }

    override fun render(graphics: Graphics) {
        graphics.color = Color.white
        graphics.drawString(enemyScore.toString(), xOffset, yOffset)
        graphics.drawString(
            playerScore.toString(),
            GameCanvas.gameWidth - xOffset,
            GameCanvas.gameHeight - yOffset
        )
    }
}