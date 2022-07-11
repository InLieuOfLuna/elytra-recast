package me.lunaluna.fabric.elytrarecast

import me.lunaluna.fabric.elytrarecast.config.Config
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ClientModInitializer

object Startup: ClientModInitializer {
    val config: Config = gsonAutoConfig()
    override fun onInitializeClient() { }
    private inline fun <reified T : ConfigData> gsonAutoConfig(): T = AutoConfig.register(T::class.java) { config, configClass -> GsonConfigSerializer(config, configClass) }.config
}