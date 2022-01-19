package me.lunaluna.fabric.elytrarecast;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.gui.GuiBase;
import me.lunaluna.fabric.elytrarecast.config.UserConfig;
import me.lunaluna.fabric.elytrarecast.config.gui.GuiUserConfig;
import me.lunaluna.fabric.elytrarecast.hotkeys.InputHandler;
import net.fabricmc.api.ClientModInitializer;

public class Startup implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID, new UserConfig());
        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.INSTANCE);

        UserConfig.Hotkey.OPEN_CONFIG.getKeybind().setCallback((a, b) -> {
            GuiBase.openGui(new GuiUserConfig());
            return true;
        });
        UserConfig.Hotkey.TOGGLE_RECAST.getKeybind().setCallback((a, b) -> {
                UserConfig.Recasting.ENABLED.setBooleanValue(!UserConfig.Recasting.ENABLED.getBooleanValue());
                return true;
        });
        UserConfig.Hotkey.TOGGLE_JUMP.getKeybind().setCallback((a, b) -> {
                UserConfig.Jumping.ENABLED.setBooleanValue(!UserConfig.Jumping.ENABLED.getBooleanValue());
                return true;
        });
    }
}