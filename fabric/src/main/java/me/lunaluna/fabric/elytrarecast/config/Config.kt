package me.lunaluna.fabric.elytrarecast.config

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "elytra-recast")
data class Config(
    var enabled: Boolean = false,
    var cooldown: Int = 0,
    var jumpEnabled: Boolean = false,
    var jumpCooldown: Int = 0
) : ConfigData