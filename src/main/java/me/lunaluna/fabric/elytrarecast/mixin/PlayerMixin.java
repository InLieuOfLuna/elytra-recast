package me.lunaluna.fabric.elytrarecast.mixin;

import me.lunaluna.fabric.elytrarecast.ElytraHelper;
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

    private final Timer timer = new Timer(this::cooldown);
    private ClientPlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }
    private boolean previousElytra = false;

    @Inject(method = "isFallFlying", at = @At("TAIL"), cancellable = true)
    public void recastIfLanded(CallbackInfoReturnable<Boolean> cir) {
        timer.runOnCooldown(() -> {
            if (enabled()) {
                boolean elytra = cir.getReturnValue();
                if (previousElytra && !elytra) {
                    cir.setReturnValue(ElytraHelper.castElytra(player()));
                }
                previousElytra = elytra;
            }
        });
    }

    private boolean enabled() {
        return true;
    }
    private int cooldown() {
        return 3;
    }
}