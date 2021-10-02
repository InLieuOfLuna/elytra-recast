package me.lunaluna.fabric.elytrarecast.modmenu

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.lunaluna.fabric.elytrarecast.config.gui.GuiUserConfig
import net.minecraft.client.gui.screen.Screen

class ModMenuImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory<Screen> {
            GuiUserConfig().apply { parent = it }
        }
    }
}