package org.fourmur.weri.network;

import org.fourmur.weri.Weri;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public final record CloudMessageServerboundPayload(String message) implements CustomPayload {
    public static final Identifier PACKET_ID = Weri.id("cloud_message");
    public static final CustomPayload.Id<CloudMessageServerboundPayload> ID = new CustomPayload.Id<>(PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, CloudMessageServerboundPayload> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING, CloudMessageServerboundPayload::message,
        CloudMessageServerboundPayload::new
    );

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, PACKET_CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
