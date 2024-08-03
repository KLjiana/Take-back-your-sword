package com.kljiana.tbys.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.item.BowItem.getPowerForTime;
import static com.kljiana.tbys.config.Config.*;
@Mixin(BowItem.class)
public abstract class BowCancel {
    @Shadow
    public abstract int getUseDuration(ItemStack pStack);

    @Inject(method = "releaseUsing", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"), cancellable = true)
    public void cancelBow(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft, CallbackInfo ci) {
        int i = this.getUseDuration(pStack) - pTimeLeft;
        float f = getPowerForTime(i);
        if (f < 1.0D && isCancelShot.get()) {
            if (pEntityLiving instanceof ServerPlayer player) {
                MobEffect mobEffect  = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(shotEffect.get()));
                if (mobEffect != null) {
                    MobEffectInstance instance = new MobEffectInstance(mobEffect, shotDuration.get(), shotAmplifier.get(), shotAmbient.get(), shotVisible.get(), shotShowIcon.get());
                    player.addEffect(instance);
                }
            }
            ci.cancel();
        }
    }
}
