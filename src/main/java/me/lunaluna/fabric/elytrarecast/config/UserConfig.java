package me.lunaluna.fabric.elytrarecast.config;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class UserConfig implements IConfigHandler {

    private static String CONFIG_FILE_NAME = "${Reference.MOD_ID}.json";

    public static class Recasting {
        public static ConfigBoolean ENABLED = new ConfigBoolean("enabled", true, "Is the recast functionality enabled?");
        public static ConfigInteger COOLDOWN = new ConfigInteger("cooldown", 4, 1, 20, "The amount of milliseconds until the next relaunch attempt");

        public static List<IConfigBase> OPTIONS = Lists.newArrayList(ENABLED, COOLDOWN);
    }

    public static class Jumping {
        public static ConfigBoolean ENABLED = new ConfigBoolean("enabled", true, "Is the jump cooldown reset functionality enabled?");
        public static ConfigInteger COOLDOWN = new ConfigInteger("cooldown", 2, 1, 20, "The amount of ticks until the next auto jump");

        public static List<IConfigBase> OPTIONS = Lists.newArrayList(ENABLED, COOLDOWN);
    }

    public static class Hotkey {
        public static ConfigHotkey OPEN_CONFIG = new ConfigHotkey("Open Config", "R,C", "Hotkey to open the config");
        public static ConfigHotkey TOGGLE_RECAST = new ConfigHotkey("Toggle Recast", "", "Hotkey to toggle the automatic recasting");
        public static ConfigHotkey TOGGLE_JUMP = new ConfigHotkey("Toggle Jump", "", "Hotkey to toggle the jump cooldown reset");
        public static List<IConfigBase> OPTIONS = Lists.newArrayList(OPEN_CONFIG, TOGGLE_RECAST, TOGGLE_JUMP);
    }

    @Override
    public void load() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);
        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "Recasting", Recasting.OPTIONS);
                ConfigUtils.readConfigBase(root, "Jumping", Jumping.OPTIONS);
                ConfigUtils.readConfigBase(root, "Hotkeys", Hotkey.OPTIONS);
            }
        }
    }

    @Override
    public void save() {
        File dir = FileUtils.getConfigDirectory();
        if (dir.exists() && dir.isDirectory() || dir.mkdirs()) {
            JsonObject root = new JsonObject();
            ConfigUtils.writeConfigBase(root, "Recasting", Recasting.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Jumping", Jumping.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Hotkeys", Hotkey.OPTIONS);
            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }
}