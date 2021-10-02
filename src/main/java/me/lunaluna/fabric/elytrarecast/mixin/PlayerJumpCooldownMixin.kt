package me.lunaluna.fabric.elytrarecast.mixin

import me.lunaluna.fabric.elytrarecast.config.UserConfig
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(LivingEntity::class)
@Environment(EnvType.CLIENT)
class PlayerJumpCooldownMixin {

    @Shadow private var jumpingCooldown: Int = 0

    private val enabled get() = UserConfig.Jumping.ENABLED.booleanValue
    private val cooldown get() = UserConfig.Jumping.COOLDOWN.integerValue
    private val player get() = MinecraftClient.getInstance().player


    @Inject(method = ["tickMovement"], at = [At("HEAD")])
    fun reduceCooldown(ci: CallbackInfo) {
        if (enabled && (jumpingCooldown > cooldown) && equals(player)) {
            jumpingCooldown = cooldown
        }
    }
}