package me.lunaluna.fabric.elytrarecast.mixin

class Timer(val cooldown: Long) {

    var previousTime: Long = 0L

    fun runOnCool(runnable: () -> Unit) {
        val time = System.currentTimeMillis()
        val diff = time - previousTime
        if (diff >= cooldown) runnable()
        previousTime = time
    }
}