package me.lunaluna.fabric.elytrarecast;

import me.lunaluna.fabric.elytrarecast.config.Config;
import me.lunaluna.fabric.elytrarecast.config.ConfigHelper;
import net.fabricmc.api.ClientModInitializer;

import java.io.IOException;
import java.nio.file.Path;

public class Startup implements ClientModInitializer {
    public static Config config = null;
    public static Path configPath = Path.of("config", "elytra-recast.json");
    @Override
    public void onInitializeClient() {
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        try {
            config = ConfigHelper.getOrCreate(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
        System.out.println("HELLO WORLD");
    }
}