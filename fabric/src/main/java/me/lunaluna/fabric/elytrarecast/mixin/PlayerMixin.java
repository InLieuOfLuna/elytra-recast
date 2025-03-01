package me.lunaluna.fabric.elytrarecast.mixin;

import me.lunaluna.fabric.elytrarecast.ElytraHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
abstract class PlayerMixin {

    private ClientPlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }

    @Unique
    private boolean previousElytra = false;
    @Unique
    private boolean awaitingElytra = false;

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "tickMovement", at = @At("TAIL"))
    public void recastIfLanded(CallbackInfo ci) {
        if (player() == null || !((Object) this instanceof ClientPlayerEntity))
            return;
        boolean elytra = isGliding();
        if (awaitingElytra) {
            if (elytra)
                awaitingElytra = false;
        } else if (!elytra && previousElytra) {
            MinecraftClient.getInstance().getSoundManager().stopSounds(SoundEvents.ITEM_ELYTRA_FLYING.id(),
                    SoundCategory.PLAYERS);
            ElytraHelper.castElytra(player());
            awaitingElytra = ElytraHelper.checkElytra(player());
        }
        previousElytra = elytra;
    }

    @Shadow
    public abstract boolean isGliding();
}
