package com.jrobertoss.pong


import com.jrobertoss.pong.entities.AbstractGameEntity
import com.jrobertoss.pong.entities.Ball
import com.jrobertoss.pong.entities.Enemy
import com.jrobertoss.pong.entities.Player
import com.jrobertoss.pong.entities.ScoreBoard
import org.reflections.Reflections
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Controls the state of all game entities.
 * Initialize dynamically by reflection, creating an instance of every [AbstractGameEntity] and putting it into the [gameEntityList].
 * [gameEntityList] is a [CopyOnWriteArrayList] to avoid [java.util.ConcurrentModificationException].
 *
 * @see Reflections
 * @see CopyOnWriteArrayList
 * @see java.util.ConcurrentModificationException
 */
object GameState {
    private val reflections = Reflections("com.jrobertoss.pong.entities")
    val gameEntityList = CopyOnWriteArrayList<AbstractGameEntity>()
    lateinit var player: Player
    lateinit var enemy: Enemy
    lateinit var ball: Ball

    fun initEntities() {
        gameEntityList.clear()

        reflections.getSubTypesOf(AbstractGameEntity::class.java).onEach {
            it.constructors.singleOrNull { c -> c.parameterCount == 0 }?.let { constructor ->
                constructor.newInstance().also { newInstance ->
                    if (newInstance is AbstractGameEntity) gameEntityList.add(newInstance)
                    when (newInstance) {
                        is Player -> player = newInstance
                        is Enemy -> enemy = newInstance
                        is Ball -> ball = newInstance
                    }
                }
            }
        }

        // Score is singleton
        gameEntityList.add(ScoreBoard)
    }

}