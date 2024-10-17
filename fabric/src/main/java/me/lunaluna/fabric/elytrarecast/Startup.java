package me.lunaluna.fabric.elytrarecast;

import me.lunaluna.fabric.elytrarecast.config.ElytraRecastConfig;
import net.fabricmc.api.ClientModInitializer;

public class Startup implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ElytraRecastConfig.load();
	}

}
