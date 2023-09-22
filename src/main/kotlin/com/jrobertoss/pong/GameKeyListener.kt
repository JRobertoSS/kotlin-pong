package com.jrobertoss.pong

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

object GameKeyListener : KeyListener {

    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) = setPlayerDirection(e, true)

    override fun keyReleased(e: KeyEvent?) = setPlayerDirection(e, false)

    private fun setPlayerDirection(e: KeyEvent?, value: Boolean) {
        when (e?.keyCode) {
            KeyEvent.VK_RIGHT -> GameState.player.right = value
            KeyEvent.VK_LEFT -> GameState.player.left = value
        }
    }

}