package me.lunaluna.fabric.elytrarecast;

import me.lunaluna.fabric.elytrarecast.config.Config;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class Startup implements ClientModInitializer {
    public static Config config;
    @Override
    public void onInitializeClient() {
        config = AutoConfig.register(Config.class, GsonConfigSerializer::new).getConfig();
    }
}