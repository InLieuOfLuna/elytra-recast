package me.lunaluna.fabric.elytrarecast;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class ElytraHelper {
    public static boolean castElytra(ClientPlayerEntity player) {
        if (checkElytra(player) && checkFallFlyingIgnoreGround(player)) {
            var networkHandler = player.networkHandler;
            if (networkHandler != null)
                networkHandler
                        .sendPacket(new ClientCommandC2SPacket(player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            return true;
        } else
            return false;
    }

    public static boolean checkElytra(ClientPlayerEntity player) {
        if (player.input.playerInput.jump() && !player.getAbilities().flying && !player.hasVehicle() && !player.isClimbing()) {
            var itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
            return itemStack.isOf(Items.ELYTRA) && LivingEntity.canGlideWith(itemStack, EquipmentSlot.CHEST);
        } else
            return false;
    }

    public static boolean checkFallFlyingIgnoreGround(ClientPlayerEntity player) {
        if (!player.isTouchingWater() && !player.hasStatusEffect(StatusEffects.LEVITATION)) {
            var itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
            if (itemStack.isOf(Items.ELYTRA) && LivingEntity.canGlideWith(itemStack, EquipmentSlot.CHEST)) {
                player.startGliding();
                return true;
            } else
                return false;
        } else
            return false;
    }
}
