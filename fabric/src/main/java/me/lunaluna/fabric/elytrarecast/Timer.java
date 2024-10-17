package me.lunaluna.fabric.elytrarecast;

import java.util.function.Supplier;

public class Timer {

    private Supplier<Integer> getCooldown;

    public Timer(Supplier<Integer> supplier) {
        this.getCooldown = supplier;
    }

    private long previousTime = 0;

    public void runOnCooldown(Runnable runnable) {
        var time = System.currentTimeMillis();
        var diff = time - previousTime;
        if (diff >= getCooldown.get()) {
            runnable.run();
            previousTime = time;
        }
    }
}
