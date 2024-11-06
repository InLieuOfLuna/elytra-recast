package me.lunaluna.fabric.elytrarecast.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.loader.api.FabricLoader;

public class ElytraRecastConfig {

	final public static boolean DEFAULT_ENABLED = true;
	final public static boolean DEFAULT_JUMP_ENABLED = true;

	final public static int DEFAULT_COOLDOWN = 4;
	final public static int DEFAULT_JUMP_COOLDOWN = 2;

	public static boolean enabled = true;
	public static boolean jumpEnabled = true;

	public static int jumpCooldown = 2;

	public static void save() {
		File configFile = FabricLoader.getInstance().getConfigDir().resolve("elytra-recast.json").toFile();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("enabled", enabled);
		jsonObject.addProperty("jumpEnabled", jumpEnabled);
		jsonObject.addProperty("jumpCooldown", jumpCooldown);

		try (Writer writer = new FileWriter(configFile)) {
			writer.write(new Gson().toJson(jsonObject));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void load() {
		File configFile = FabricLoader.getInstance().getConfigDir().resolve("elytra-recast.json").toFile();
		if (!configFile.exists())
			return;

		try (Reader reader = new FileReader(configFile)) {
			JsonObject objectFromFile = JsonParser.parseReader(reader).getAsJsonObject();
			if (objectFromFile.has("enabled"))
				enabled = objectFromFile.get("enabled").getAsBoolean();
			if (objectFromFile.has("jumpEnabled"))
				jumpEnabled = objectFromFile.get("jumpEnabled").getAsBoolean();
			if (objectFromFile.has("jumpCooldown"))
				jumpCooldown = objectFromFile.get("jumpCooldown").getAsInt();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			save();
		}
	}
}
