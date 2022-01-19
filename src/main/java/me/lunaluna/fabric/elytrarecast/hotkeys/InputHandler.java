package me.lunaluna.fabric.elytrarecast.hotkeys;

import com.google.common.collect.Lists;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import me.lunaluna.fabric.elytrarecast.Reference;
import me.lunaluna.fabric.elytrarecast.config.UserConfig;

import java.util.List;

public class InputHandler implements IKeybindProvider {

    public static InputHandler INSTANCE = new InputHandler();

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        manager.addKeybindToMap(UserConfig.Hotkey.OPEN_CONFIG.getKeybind());
        manager.addKeybindToMap(UserConfig.Hotkey.TOGGLE_RECAST.getKeybind());
        manager.addKeybindToMap(UserConfig.Hotkey.TOGGLE_JUMP.getKeybind());

    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        List<IHotkey> hotkeys = Lists.newArrayList(
                UserConfig.Hotkey.OPEN_CONFIG,
                UserConfig.Hotkey.TOGGLE_RECAST,
                UserConfig.Hotkey.TOGGLE_JUMP
                );
        manager.addHotkeysForCategory(Reference.MOD_NAME, "lunaluna.hotkeys.category.generic_hotkeys", hotkeys);
    }
}