package me.lunaluna.fabric.elytrarecast.config

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import fi.dy.masa.malilib.config.ConfigUtils
import fi.dy.masa.malilib.config.IConfigBase
import fi.dy.masa.malilib.config.IConfigHandler
import fi.dy.masa.malilib.config.options.ConfigBoolean
import fi.dy.masa.malilib.config.options.ConfigInteger
import fi.dy.masa.malilib.util.FileUtils
import fi.dy.masa.malilib.util.JsonUtils
import java.io.File

object UserConfig : IConfigHandler {

    private const val CONFIG_FILE_NAME = "elytra-recast.json"

    object Generic {
        val ENABLED         = ConfigBoolean("enabled", true, "Is the mod enabled?")
        val RECAST_COOLDOWN = ConfigInteger("recastCooldown", 4, "The amount of milliseconds until the next relaunch attempt")

        val OPTIONS = listOf<IConfigBase>(
            ENABLED,
            RECAST_COOLDOWN
        )
    }

    override fun load() {
        val configFile = File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME)
        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            val element: JsonElement? = JsonUtils.parseJsonFile(configFile)
            if (element != null && element.isJsonObject) {
                val root = element.asJsonObject
                ConfigUtils.readConfigBase(root, "Generic", Generic.OPTIONS)
            }
        }
    }

    override fun save() {
        val dir: File = FileUtils.getConfigDirectory()
        if (dir.exists() && dir.isDirectory || dir.mkdirs()) {
            val root = JsonObject()
            ConfigUtils.writeConfigBase(root, "Generic", Generic.OPTIONS)
            JsonUtils.writeJsonToFile(root, File(dir, CONFIG_FILE_NAME))
        }
    }
}