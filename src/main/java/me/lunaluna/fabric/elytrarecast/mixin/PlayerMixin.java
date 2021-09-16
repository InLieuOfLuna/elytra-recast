package me.lunaluna.fabric.elytrarecast.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerEntity.class)
@Environment(EnvType.CLIENT)
public class PlayerMixin {

    //TODO: Implement recasting elytra when landing from elytra jumping

}