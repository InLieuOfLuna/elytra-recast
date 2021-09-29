package me.lunaluna.fabric.elytrarecast

class Timer(val getCooldown: () -> Int) {


    var previousTime: Long = 0L
    private val cooldown get() = getCooldown()

    fun runOnCooldown(runnable: () -> Unit) {
        val time = System.currentTimeMillis()
        val diff = time - previousTime
        if (diff >= cooldown) { runnable() }
        previousTime = time
    }
}