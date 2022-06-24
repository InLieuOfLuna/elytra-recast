package me.lunaluna.fabric.elytrarecast.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {
    private boolean enabled;
    private int cooldown;
    private boolean jumpEnabled;
    private int jumpCooldown;
}