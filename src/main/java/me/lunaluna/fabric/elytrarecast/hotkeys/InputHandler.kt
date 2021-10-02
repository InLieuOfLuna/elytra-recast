package me.lunaluna.fabric.elytrarecast.hotkeys

import fi.dy.masa.malilib.hotkeys.IHotkey
import fi.dy.masa.malilib.hotkeys.IKeybindManager
import fi.dy.masa.malilib.hotkeys.IKeybindProvider
import me.lunaluna.fabric.elytrarecast.Reference
import me.lunaluna.fabric.elytrarecast.config.UserConfig

object InputHandler: IKeybindProvider {

    override fun addKeysToMap(manager: IKeybindManager) {
        manager.addKeybindToMap(UserConfig.Hotkeys.OPEN_CONFIG.keybind)
        manager.addKeybindToMap(UserConfig.Hotkeys.TOGGLE_RECAST.keybind)
        manager.addKeybindToMap(UserConfig.Hotkeys.TOGGLE_JUMP.keybind)
    }

    override fun addHotkeys(manager: IKeybindManager) {
        val hotkeys: List<IHotkey> = listOf(
            UserConfig.Hotkeys.OPEN_CONFIG,
            UserConfig.Hotkeys.TOGGLE_RECAST,
            UserConfig.Hotkeys.TOGGLE_JUMP
        )
        manager.addHotkeysForCategory(Reference.MOD_NAME, "lunaluna.hotkeys.category.generic_hotkeys", hotkeys)
    }
}