package me.lunaluna.fabric.elytrarecast

import java.util.function.Supplier

class Timer(val getCooldown: Supplier<Int>) {
    private var previousTime = 0L

    fun runOnCooldown(runnable: Runnable) {
        val time = System.currentTimeMillis()
        val diff = time - previousTime
        if (diff >= getCooldown.get()) {
            runnable.run()
            previousTime = time
        }
    }
}