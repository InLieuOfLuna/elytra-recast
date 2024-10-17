package me.lunaluna.fabric.elytrarecast.compat.clothconfig;

import me.lunaluna.fabric.elytrarecast.config.ElytraRecastConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ElytraRecastConfigScreen {
	public static Screen createConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.translatable("text.autoconfig.elytra-recast.title"));
		builder.setSavingRunnable(ElytraRecastConfig::save);
		ConfigCategory general = builder
				.getOrCreateCategory(Text.translatable("text.autoconfig.elytra-recast.title"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		// Enabled value
		general.addEntry(entryBuilder
				.startBooleanToggle(Text.translatable("text.autoconfig.elytra-recast.option.enabled"),
						ElytraRecastConfig.enabled)
				.setDefaultValue(ElytraRecastConfig.DEFAULT_ENABLED)
				.setSaveConsumer((replace) -> ElytraRecastConfig.enabled = replace)
				.build());
		// Cooldown value
		general.addEntry(entryBuilder
				.startIntField(Text.translatable("text.autoconfig.elytra-recast.option.cooldown"),
						ElytraRecastConfig.cooldown)
				.setDefaultValue(ElytraRecastConfig.DEFAULT_COOLDOWN)
				.setSaveConsumer((replace) -> ElytraRecastConfig.cooldown = replace).build());
		// Jump Enabled
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Text.translatable("text.autoconfig.elytra-recast.option.jumpEnabled"),
						ElytraRecastConfig.jumpEnabled)
				.setDefaultValue(ElytraRecastConfig.DEFAULT_JUMP_ENABLED)
				.setSaveConsumer((replace) -> ElytraRecastConfig.jumpEnabled = replace).build());
		// Jump Cooldown
		general.addEntry(entryBuilder
				.startIntField(Text.translatable("text.autoconfig.elytra-recast.option.jumpCooldown"),
						ElytraRecastConfig.jumpCooldown)
				.setDefaultValue(ElytraRecastConfig.DEFAULT_JUMP_COOLDOWN)
				.setSaveConsumer((replace) -> ElytraRecastConfig.jumpCooldown = replace).build());
		return builder.build();
	}
}
