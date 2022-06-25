package me.lunaluna.fabric.elytrarecast.mixin;

import me.lunaluna.fabric.elytrarecast.ElytraHelper;
import me.lunaluna.fabric.elytrarecast.Startup;
import me.lunaluna.fabric.elytrarecast.Timer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
abstract class PlayerMixin {

    private final Timer timer = new Timer(() -> Startup.config.getCooldown());
    private ClientPlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }
    private boolean previousElytra = false;

    @Inject(method = "isFallFlying", at = @At("TAIL"), cancellable = true)
    public void recastIfLanded(CallbackInfoReturnable<Boolean> cir) {
        if (!MinecraftClient.getInstance().isInSingleplayer()) return;
        timer.runOnCooldown(() -> {
            if (Startup.config.getEnabled()) {
                boolean elytra = cir.getReturnValue();
                if (previousElytra && !elytra) {
                    cir.setReturnValue(ElytraHelper.castElytra(player()));
                }
                previousElytra = elytra;
            }
        });
    }
}