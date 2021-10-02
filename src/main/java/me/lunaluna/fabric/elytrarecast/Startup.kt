package me.lunaluna.fabric.elytrarecast

import fi.dy.masa.malilib.config.ConfigManager
import fi.dy.masa.malilib.event.InitializationHandler
import fi.dy.masa.malilib.event.InputEventHandler
import fi.dy.masa.malilib.gui.GuiBase
import me.lunaluna.fabric.elytrarecast.config.UserConfig
import me.lunaluna.fabric.elytrarecast.config.gui.GuiUserConfig
import me.lunaluna.fabric.elytrarecast.hotkeys.InputHandler
import net.fabricmc.api.ClientModInitializer

class Startup : ClientModInitializer {
    override fun onInitializeClient() = InitializationHandler.getInstance().registerInitializationHandler {
        ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID, UserConfig)
        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler)

        UserConfig.Hotkeys.OPEN_CONFIG.keybind.setCallback { _, _ ->
            GuiBase.openGui(GuiUserConfig())
            true
        }
        UserConfig.Hotkeys.TOGGLE_RECAST.keybind.setCallback { _, _ ->
            UserConfig.Recasting.ENABLED.booleanValue = !UserConfig.Recasting.ENABLED.booleanValue
            true
        }
        UserConfig.Hotkeys.TOGGLE_JUMP.keybind.setCallback { _, _ ->
            UserConfig.Jumping.ENABLED.booleanValue = !UserConfig.Jumping.ENABLED.booleanValue
            true
        }
    }
}