package org.fourmur.weri.network;

import java.util.UUID;

import org.fourmur.weri.Weri;
import org.fourmur.weri.init.WeriNetwork;

import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;

public final record CloudMessageClientboundPayload(String message, UUID player)
        implements CustomPayload {
    public static final WeriNetwork.PayloadInfo<CloudMessageClientboundPayload> PAYLOAD_INFO
        = new WeriNetwork.PayloadInfo<>(
            new CustomPayload.Id<>(Weri.id("cloud_message_clientbound")),
            PacketCodec.tuple(
                PacketCodecs.STRING, CloudMessageClientboundPayload::message,
                Uuids.PACKET_CODEC, CloudMessageClientboundPayload::player,
                CloudMessageClientboundPayload::new
            )
        );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PAYLOAD_INFO.id();
    }
}
