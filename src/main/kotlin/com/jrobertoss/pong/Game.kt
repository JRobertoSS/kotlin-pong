package com.jrobertoss.pong

import com.jrobertoss.pong.entities.AbstractGameEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object Game : Runnable {

    private const val FRAME_DELAY_TIME = 1000L / 120

    private var isRunning = true


    init {
        GameState.initEntities()
    }

    @JvmStatic
    fun main(args: Array<String>) = Thread(Game).start()

    /*
        TODO > invoke all game entities tick
     */
    private fun tick() = GameState.gameEntityList.forEach(AbstractGameEntity::tick)

    override fun run() = runBlocking {
        while (isRunning) {
            tick()
            GameCanvas.render(GameState.gameEntityList)
            delay(FRAME_DELAY_TIME)
        }
    }
}