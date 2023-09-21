package com.jrobertoss.pong

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object Game : Runnable {

    private const val FRAME_DELAY_TIME = 1000L / 60

    private var isRunning = true


    init {
      // TODO > initialize game state
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Thread(Game).start()
    }


    /*
        TODO > invoke all game entities tick
     */
    private fun tick() {

    }

    override fun run() = runBlocking {
        while (isRunning) {
            tick()
            delay(FRAME_DELAY_TIME)
        }
    }
}