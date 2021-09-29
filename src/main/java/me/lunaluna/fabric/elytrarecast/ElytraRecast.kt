package me.lunaluna.fabric.elytrarecast

import fi.dy.masa.malilib.config.ConfigManager
import me.lunaluna.fabric.elytrarecast.config.UserConfig
import net.fabricmc.api.ModInitializer

class ElytraRecast : ModInitializer {
    override fun onInitialize() {
        ConfigManager.getInstance().registerConfigHandler("elytra-recast", UserConfig)
    }
}