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

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
public class PlayerJumpCooldownMixin {

    @Shadow private int jumpingCooldown;

    private boolean enabled() {
        return true;
    }
    private int cooldown() {
      return 3;
    }
    private ClientPlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }


    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void reduceCooldown(CallbackInfo ci) {
        if (enabled() && (jumpingCooldown > cooldown()) && equals(player())) {
            jumpingCooldown = cooldown();
        }
    }

}
