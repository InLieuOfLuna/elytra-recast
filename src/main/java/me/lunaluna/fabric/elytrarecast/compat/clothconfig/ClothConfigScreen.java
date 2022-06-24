package me.lunaluna.fabric.elytrarecast.compat.clothconfig;

import me.lunaluna.fabric.elytrarecast.Startup;
import me.lunaluna.fabric.elytrarecast.config.ConfigHelper;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.io.IOException;

public class ClothConfigScreen {
    public static Screen createScreen(Screen parent) {
        ConfigBuilder configBuilder = ConfigBuilder.create();

        configBuilder.setTitle(translate("title.elytra-recast.config"));
        configBuilder.setSavingRunnable(() -> {
                    try {
                        ConfigHelper.write(Startup.config, Startup.configPath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
        ConfigCategory elytra = configBuilder.getOrCreateCategory(translate("elytra.elytra-recast.config"));
        ConfigCategory jumping = configBuilder.getOrCreateCategory(translate("jumping.elytra-recast.config"));
        elytra.addEntry(
                entryBuilder.startIntField(translate("cooldown.elytra.elytra-cast.config"), Startup.config.getCooldown())
                        .setSaveConsumer(Startup.config::setCooldown)
                        .build()
        );
        elytra.addEntry(
                entryBuilder.startBooleanToggle(translate("enabled.elytra.elytra-cast.config"), Startup.config.isEnabled())
                        .setSaveConsumer(Startup.config::setEnabled)
                        .build()
        );

        jumping.addEntry(
                entryBuilder.startIntField(translate("cooldown.jumping.elytra-cast.config"), Startup.config.getJumpCooldown())
                        .setSaveConsumer(Startup.config::setJumpCooldown)
                        .build()
        );
        jumping.addEntry(
                entryBuilder.startBooleanToggle(translate("enabled.jumping.elytra-cast.config"), Startup.config.isJumpEnabled())
                        .setSaveConsumer(Startup.config::setJumpEnabled)
                        .build()
        );

        return configBuilder.setParentScreen(parent).build();
    }

    private static Text translate(String key) {
        return Text.translatable(key);
    }
}
