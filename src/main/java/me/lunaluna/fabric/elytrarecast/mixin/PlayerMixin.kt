package me.lunaluna.fabric.elytrarecast.mixin

import me.lunaluna.fabric.elytrarecast.ElytraHelper
import me.lunaluna.fabric.elytrarecast.Timer
import me.lunaluna.fabric.elytrarecast.config.UserConfig
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(LivingEntity::class)
@Environment(EnvType.CLIENT)
abstract class PlayerMixin {

    private val timer = Timer { return@Timer cooldown() }
    private val player get() = MinecraftClient.getInstance().player

    private var previousElytra = false

    @Inject(method = ["isFallFlying"], at = [At("TAIL")], cancellable = true)
    fun recastIfLanded(cir: CallbackInfoReturnable<Boolean>) = timer.runOnCooldown {
        if (enabled()) {
            val elytra = cir.returnValue
            if (previousElytra && !elytra) {
                cir.returnValue = ElytraHelper.castElytra(player!!)
            }
            previousElytra = elytra
        }
    }

    private fun enabled(): Boolean {
        return UserConfig.Recasting.ENABLED.booleanValue
    }
    private fun cooldown(): Int {
        return UserConfig.Recasting.COOLDOWN.integerValue
    }
}