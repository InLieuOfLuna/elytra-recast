package me.lunaluna.fabric.elytrarecast.config

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "elytra-recast")
data class Config(
    var enabled: Boolean = true,
    var cooldown: Int = 4,
    var jumpEnabled: Boolean = true,
    var jumpCooldown: Int = 2
) : ConfigData