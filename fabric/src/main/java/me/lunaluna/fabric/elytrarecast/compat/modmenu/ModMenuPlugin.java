package me.lunaluna.fabric.elytrarecast.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lunaluna.fabric.elytrarecast.config.Config;
import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuPlugin implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> AutoConfig.getConfigScreen(Config.class, screen).get();
    }
}