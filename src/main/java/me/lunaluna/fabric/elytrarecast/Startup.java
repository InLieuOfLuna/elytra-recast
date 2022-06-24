package me.lunaluna.fabric.elytrarecast;

import me.lunaluna.fabric.elytrarecast.config.Config;
import me.lunaluna.fabric.elytrarecast.config.ConfigHelper;
import net.fabricmc.api.ClientModInitializer;

import java.io.IOException;
import java.nio.file.Path;

public class Startup implements ClientModInitializer {
    public static Config config;
    public static final Path configPath = Path.of("config", "elytra-recast.json");
    @Override
    public void onInitializeClient() {
        try {
            config = ConfigHelper.getOrCreate(configPath);
        } catch (IOException e) {
            config = ConfigHelper.getDefault();
        }
    }
}