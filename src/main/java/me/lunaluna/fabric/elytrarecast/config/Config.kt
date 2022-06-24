package me.lunaluna.fabric.elytrarecast.config

data class Config(
    var enabled: Boolean = false,
    var cooldown: Int = 0,
    var jumpEnabled: Boolean = false,
    var jumpCooldown: Int = 0
)