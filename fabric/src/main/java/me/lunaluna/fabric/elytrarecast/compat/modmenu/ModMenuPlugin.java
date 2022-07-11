package me.lunaluna.fabric.elytrarecast.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lunaluna.fabric.elytrarecast.compat.clothconfig.ClothConfigScreen;
public class ModMenuPlugin implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ClothConfigScreen::createScreen;
    }
}
