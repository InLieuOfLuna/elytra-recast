package me.lunaluna.fabric.elytrarecast.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntity.class)
public class PlayerJumpCooldownMixin {
    @Shadow private int jumpingCooldown;

    private boolean hasChecked = false;
    private boolean isPlayer = false;
    private ClientPlayerEntity player = null;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void reduceTickJumping(final CallbackInfo ci) {
        if (player == null) {
            player = MinecraftClient.getInstance().player;
            if (!hasChecked && player != null) {
                hasChecked = true;
                isPlayer = equals(player);
            }
        }
        if (isPlayer && jumpingCooldown > 2) jumpingCooldown = 2;
    }
}
