package com.kljiana.tbys.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftClient {
    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void cancelAttackAnimation(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayer player = mc.player;
        if (player != null && player.getAttackStrengthScale(0.0F) < 1.0F) {
            cir.cancel();
        }
    }
}
