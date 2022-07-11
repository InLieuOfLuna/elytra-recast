package me.lunaluna.fabric.elytrarecast.compat.modmenu

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.lunaluna.fabric.elytrarecast.config.Config
import me.shedaniel.autoconfig.AutoConfig

class ModMenuPlugin : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { screen ->
            AutoConfig.getConfigScreen(
                Config::class.java, screen
            ).get()
        }
    }
}