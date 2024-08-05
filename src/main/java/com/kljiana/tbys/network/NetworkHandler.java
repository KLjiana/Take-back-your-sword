package com.kljiana.tbys.network;

import com.kljiana.tbys.TBYS;
import com.kljiana.tbys.network.c2s.AttackPacketC2S;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class NetworkHandler {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TBYS.ModID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(packetId++, AttackPacketC2S.class, AttackPacketC2S::encode, AttackPacketC2S::decode, AttackPacketC2S::handle);
    }
}
