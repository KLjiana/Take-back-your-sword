package com.kljiana.tbys.event;

import com.kljiana.tbys.TBYS;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.kljiana.tbys.config.Config.*;
import static net.minecraft.world.phys.HitResult.Type.ENTITY;

@Mod.EventBusSubscriber(modid = TBYS.ModID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEvent {
    @SubscribeEvent
    public static void changeAttribute(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (Minecraft.getInstance().hitResult != null && player != null && player.getAttackStrengthScale(0.0F) < 1.0F && isCancelAttack.get() && Minecraft.getInstance().hitResult.getType() == ENTITY && player.swinging) {
            MobEffect mobEffect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(attackEffect.get()));
            if (mobEffect != null) {
                MobEffectInstance instance = new MobEffectInstance(mobEffect, attackDuration.get(), attackAmplifier.get(), attackAmbient.get(), attackVisible.get(), attackShowIcon.get());
                player.addEffect(instance);
            }
        }
    }
}
