package me.lunaluna.fabric.elytrarecast.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {
    public boolean enabled;
    public int cooldown;
    public boolean jumpEnabled;
    public int jumpCooldown;
}