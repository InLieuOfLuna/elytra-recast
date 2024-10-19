package me.lunaluna.fabric.elytrarecast.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.lunaluna.fabric.elytrarecast.compat.clothconfig.ElytraRecastConfigScreen;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuPlugin implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
			return ElytraRecastConfigScreen::createConfigScreen;
		} else {
			return null;
		}
	}
}
