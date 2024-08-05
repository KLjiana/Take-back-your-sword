package com.kljiana.tbys.mixin;

import com.kljiana.tbys.network.NetworkHandler;
import com.kljiana.tbys.network.c2s.AttackPacketC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static com.kljiana.tbys.config.Config.*;
@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class AttackCancel {
    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "startAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;attack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V"), cancellable = true)
    private void cancelAttackAnimation(CallbackInfoReturnable<Boolean> cir) {
        if (player != null && player.getAttackStrengthScale(0.0F) < 1.0F && isCancelAttack.get()) {
            NetworkHandler.CHANNEL.sendToServer(AttackPacketC2S.addEffect());
            cir.setReturnValue(false);
        }
    }
}
