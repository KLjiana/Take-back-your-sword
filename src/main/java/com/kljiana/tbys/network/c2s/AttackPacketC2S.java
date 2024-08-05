package com.kljiana.tbys.network.c2s;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.kljiana.tbys.config.Config.*;

public record AttackPacketC2S() {
    public static AttackPacketC2S addEffect() {
        return new AttackPacketC2S();
    }
    public static void encode(AttackPacketC2S packet, FriendlyByteBuf friendlyByteBuf) {}

    public static AttackPacketC2S decode(FriendlyByteBuf friendlyByteBuf) {
        return new AttackPacketC2S();
    }

    public static void handle(AttackPacketC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            MobEffect mobEffect  = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(attackEffect.get()));
            if (mobEffect != null && player != null) {
                MobEffectInstance instance = new MobEffectInstance(mobEffect, attackDuration.get(), attackAmplifier.get(), attackAmbient.get(), attackVisible.get(), attackShowIcon.get());
                player.addEffect(instance);
            }
        });
        context.setPacketHandled(true);
    }
}
