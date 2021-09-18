package me.lunaluna.fabric.elytrarecast.mixin

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ElytraItem
import net.minecraft.item.Items
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(LivingEntity::class)
@Environment(EnvType.CLIENT)
abstract class PlayerMixin {

    private var previousElytra = false
    private var previousTime = 0L
    private val recastCooldown = 4
    private var timer: Long = 0

    private val player: ClientPlayerEntity? = MinecraftClient.getInstance().player
    private val isPlayer: Boolean = equals(player)

    @Inject(method = ["isFallFlying"], at = [At("TAIL")], cancellable = true)
    fun recastIfLanded(cir: CallbackInfoReturnable<Boolean>) { if (isPlayer) {
        val time = System.currentTimeMillis()
        if (previousTime != 0L) {
            timer += (time - previousTime)
            val elytra = cir.returnValue
            if (previousElytra && !elytra && timer >= recastCooldown) {
                timer = 0
                cir.returnValue = castElytra(player!!)
            }
            previousElytra = elytra
        }
        previousTime = time
    } }

    private fun castElytra(player: ClientPlayerEntity) = if (checkElytra(player) && checkFallFlyingIgnoreGround(player)) {
        player.networkHandler.sendPacket(
            ClientCommandC2SPacket(player, ClientCommandC2SPacket.Mode.START_FALL_FLYING)
        )
        true
    } else false

    private fun checkElytra(player: ClientPlayerEntity) = if (player.input.jumping && !player.abilities.flying && !player.hasVehicle() && !player.isClimbing) {
        val itemStack = player.getEquippedStack(EquipmentSlot.CHEST)
        itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack)
    } else false

    private fun checkFallFlyingIgnoreGround(player: ClientPlayerEntity) = if (!player.isTouchingWater && !player.hasStatusEffect(StatusEffects.LEVITATION)) {
        val itemStack = player.getEquippedStack(EquipmentSlot.CHEST)
        if (itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack)) {
            player.startFallFlying()
            true
        } else false
    } else false
}