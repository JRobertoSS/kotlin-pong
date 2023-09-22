package com.jrobertoss.pong.entities

import com.jrobertoss.pong.GameCanvas
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

/**
 * Controls and renders the score on the UI.
 * Score methods ([addPlayerScore] and [addEnemyScore]) are called by
 * [Ball], accordingly to vertical boundaries check on [Ball.checkVerticalBoundaries]
 */
object ScoreBoard : AbstractGameEntity() {

    private var playerScore = 0
    private var enemyScore = 0
    private const val X_OFFSET = 10
    private const val Y_OFFSET = 15

    fun addPlayerScore() = playerScore++
    fun addEnemyScore() = enemyScore++

    override fun tick() {}

    override fun render(graphics: Graphics) = graphics.run {
        color = Color.white
        drawString(
            enemyScore.toString(),
            X_OFFSET,
            Y_OFFSET)

        drawString(
            playerScore.toString(),
            GameCanvas.GAME_WIDTH - X_OFFSET,
            GameCanvas.GAME_HEIGHT - Y_OFFSET
        )

    }

}