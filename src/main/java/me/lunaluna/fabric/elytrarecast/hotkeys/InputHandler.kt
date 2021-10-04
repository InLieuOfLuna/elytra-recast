package me.lunaluna.fabric.elytrarecast.hotkeys

import fi.dy.masa.malilib.hotkeys.IHotkey
import fi.dy.masa.malilib.hotkeys.IKeybindManager
import fi.dy.masa.malilib.hotkeys.IKeybindProvider
import me.lunaluna.fabric.elytrarecast.Reference
import me.lunaluna.fabric.elytrarecast.config.UserConfig

object InputHandler: IKeybindProvider {

    override fun addKeysToMap(manager: IKeybindManager) {
        manager.addKeybindToMap(UserConfig.Hotkey.OPEN_CONFIG.keybind)
        manager.addKeybindToMap(UserConfig.Hotkey.TOGGLE_RECAST.keybind)
        manager.addKeybindToMap(UserConfig.Hotkey.TOGGLE_JUMP.keybind)
    }

    override fun addHotkeys(manager: IKeybindManager) {
        val hotkeys: List<IHotkey> = listOf(
            UserConfig.Hotkey.OPEN_CONFIG,
            UserConfig.Hotkey.TOGGLE_RECAST,
            UserConfig.Hotkey.TOGGLE_JUMP,
        )
        manager.addHotkeysForCategory(Reference.MOD_NAME, "lunaluna.hotkeys.category.generic_hotkeys", hotkeys)
    }
}