package me.lunaluna.fabric.elytrarecast.mixin

import me.lunaluna.fabric.elytrarecast.ElytraHelper
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.LivingEntity
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
    private val elytraHelper: ElytraHelper? = if(isPlayer) ElytraHelper(player!!) else null

    @Inject(method = ["isFallFlying"], at = [At("TAIL")], cancellable = true)
    fun recastIfLanded(cir: CallbackInfoReturnable<Boolean>) { if (isPlayer) {
        val time = System.currentTimeMillis()
        if (previousTime != 0L) {
            timer += (time - previousTime)
            val elytra = cir.returnValue
            if (previousElytra && !elytra && timer >= recastCooldown) {
                timer = 0
                cir.returnValue = elytraHelper!!.castElytra()
            }
            previousElytra = elytra
        }
        previousTime = time
    } }
}