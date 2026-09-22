package org.fourmur.wathe_extended_rus_improved;

import java.util.UUID;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

public final class CloudMessagePacket {
    public static final Identifier PACKET_ID = WatheExtendedRusImproved.id("recive_cloud_message");

    public static void register() {
        PayloadTypeRegistry.playS2C().register(ClientboundPayload.ID, ClientboundPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(ServerboundPayload.ID, ServerboundPayload.PACKET_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(CloudMessagePacket.ServerboundPayload.ID, (payload, context) -> {
            context.server().execute(new ServerCloudMessageReceiver(payload, context));
        });
    }

    public final record ClientboundPayload(String message, UUID player) implements CustomPayload {
        public static final CustomPayload.Id<ClientboundPayload> ID = new CustomPayload.Id<>(PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, ClientboundPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, ClientboundPayload::message,
            Uuids.PACKET_CODEC, ClientboundPayload::player,
            ClientboundPayload::new
        );

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public final record ServerboundPayload(String message) implements CustomPayload {
        public static final CustomPayload.Id<ServerboundPayload> ID = new CustomPayload.Id<>(PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, ServerboundPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, ServerboundPayload::message,
            ServerboundPayload::new
        );

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
