package org.fourmur.weri.network;

import java.util.UUID;

import org.fourmur.weri.Weri;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

public final record CloudMessageClientboundPayload(String message, UUID player) implements CustomPayload {
    public static final Identifier PACKET_ID = Weri.id("cloud_message");
    public static final CustomPayload.Id<CloudMessageClientboundPayload> ID = new CustomPayload.Id<>(PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, CloudMessageClientboundPayload> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING, CloudMessageClientboundPayload::message,
        Uuids.PACKET_CODEC, CloudMessageClientboundPayload::player,
        CloudMessageClientboundPayload::new
    );

    public static void register() {
        PayloadTypeRegistry.playS2C().register(ID, PACKET_CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
