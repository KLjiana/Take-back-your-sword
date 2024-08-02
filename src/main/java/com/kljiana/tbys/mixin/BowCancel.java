package com.kljiana.tbys.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.item.BowItem.getPowerForTime;

@Mixin(BowItem.class)
public abstract class BowCancel {
    @Shadow
    public abstract int getUseDuration(ItemStack pStack);

    @Inject(method = "releaseUsing", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"), cancellable = true)
    public void cancelBow(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft, CallbackInfo ci) {
        int i = this.getUseDuration(pStack) - pTimeLeft;
        float f = getPowerForTime(i);
        if (f < 1.0D) {
            if (pEntityLiving instanceof ServerPlayer player) player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20));
            ci.cancel();
        }
    }
}
