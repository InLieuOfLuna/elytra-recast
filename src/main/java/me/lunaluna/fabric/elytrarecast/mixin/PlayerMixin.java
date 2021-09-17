package me.lunaluna.fabric.elytrarecast.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
@Environment(EnvType.CLIENT)
public abstract class PlayerMixin {

    private boolean previousElytra = false;
    private long previousTime = 0L;
    private final int recastCooldown = 4;
    private long timer = 0;
    private boolean hasChecked = false;
    private boolean isPlayer = false;
    private ClientPlayerEntity player = null;

    @Inject(method = "isFallFlying", at = @At("TAIL"), cancellable = true)
    public void recastIfLanded(CallbackInfoReturnable<Boolean> cir) {
        if (player == null) {
            player = MinecraftClient.getInstance().player;
            if (!hasChecked && player != null) {
                hasChecked = true;
                isPlayer = equals(player);
            }
        }
        if (isPlayer) {
            assert (player != null);
            long time = System.currentTimeMillis();
            if (previousTime != 0L) {
                timer = timer + (time - previousTime);
                boolean elytra = cir.getReturnValue();
                if (previousElytra && !elytra && timer >= recastCooldown) {
                    timer = 0;
                    cir.setReturnValue(castElytra(player));
                }
                previousElytra = elytra;
            }
            previousTime = time;
        }
    }

    boolean castElytra(ClientPlayerEntity player) {
        if (checkElytra(player) && checkFallFlyingIgnoreGround(player)) {
            player.networkHandler.sendPacket(new ClientCommandC2SPacket(player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            return true;
        }
        return false;
    }

    boolean checkElytra(ClientPlayerEntity player) {
        if (player.input.jumping && !player.getAbilities().flying && !player.hasVehicle() && !player.isClimbing()) {
            ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
            return itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack);
        }
        return false;
    }

    public boolean checkFallFlyingIgnoreGround(ClientPlayerEntity player) {
        if (!player.isTouchingWater() && !player.hasStatusEffect(StatusEffects.LEVITATION)) {
            ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
            if (itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack)) {
                player.startFallFlying();
                return true;
            }
        }
        return false;
    }
}