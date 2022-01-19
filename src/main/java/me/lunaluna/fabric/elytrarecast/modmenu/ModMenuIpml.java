package me.lunaluna.fabric.elytrarecast.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lunaluna.fabric.elytrarecast.config.gui.GuiUserConfig;

public class ModMenuIpml implements ModMenuApi {

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> {
            var config = new GuiUserConfig();
            config.setParent(parent);
            return config;
        };
    }

}
