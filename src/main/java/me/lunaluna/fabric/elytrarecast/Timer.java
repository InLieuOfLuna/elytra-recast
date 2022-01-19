package me.lunaluna.fabric.elytrarecast;

import java.util.function.Supplier;

public class Timer {

    private final Supplier<Integer> getCooldown;
    private long previousTime = 0L;

    public Timer(Supplier<Integer> getCooldown) {
        this.getCooldown = getCooldown;
    }

    public void runOnCooldown(Runnable runnable) {
        long time = System.currentTimeMillis();
        long diff = time - previousTime;
        if (diff >= getCooldown.get()) {
            runnable.run();
            previousTime = time;
        }
    }
}