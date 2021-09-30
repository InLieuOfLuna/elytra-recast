package me.lunaluna.fabric.elytrarecast

import fi.dy.masa.malilib.config.ConfigManager
import fi.dy.masa.malilib.event.InitializationHandler
import me.lunaluna.fabric.elytrarecast.config.UserConfig
import net.fabricmc.api.ClientModInitializer

class Startup : ClientModInitializer {
    override fun onInitializeClient() = InitializationHandler.getInstance().registerInitializationHandler {
        ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID, UserConfig)
    }
}