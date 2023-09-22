package com.jrobertoss.pong

import com.jrobertoss.pong.PongHelper.getRandomAngle
import com.jrobertoss.pong.entities.AbstractGameEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Main game runnable.
 * Executes the game infinite loop with a delay set by [FRAME_DELAY_TIME].
 * With each iteration, invokes [tick] method, which in turn invokes all ticks of all [AbstractGameEntity]
 * instances in [GameState.gameEntityList], and also run [GameCanvas.render].
 */
object Game : Runnable {

    private const val FRAME_DELAY_TIME = 1000L / 120

    private var isRunning = true


    init {
        GameState.initEntities()
    }

    @JvmStatic
    fun main(args: Array<String>) = Thread(Game).start()

    private fun tick() = GameState.gameEntityList.forEach(AbstractGameEntity::tick)

    override fun run() = runBlocking {
        val angles = mutableSetOf<Int>()
        while (isRunning) {
            tick()
            GameCanvas.render()
            angles.add(getRandomAngle())
            println(angles.max())
            println(angles.min())
            delay(FRAME_DELAY_TIME)
        }
    }
}