package me.lunaluna.fabric.elytrarecast.config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigHelper {

    public static Config getOrCreate(Path filePath) throws IOException {
        File file = filePath.toFile();

        Config result = null;

        try {
            result = new Gson().fromJson(new FileReader(file), Config.class);
        } catch (Exception ignored) {  }

        if (result == null) {
            result = getDefault();
            write(result, filePath);
        }

        return result;
    }

    public static void write(Config config, Path filePath) throws IOException {
        File file = filePath.toFile();
        file.getParentFile().mkdirs();
        file.createNewFile();
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(config, Config.class, writer);
        }
    }

    public static Config getDefault() {
        return new Config(
                true,
                3,
                true,
                3
        );
    }

}