package org.fourmur.weri.network;

import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

import org.fourmur.weri.Weri;
import org.fourmur.weri.init.WeriNetwork;

public final record CloudMessageServerboundPayload(String message) implements CustomPayload {
    // spotless:off
    public static final WeriNetwork.PayloadInfo<CloudMessageServerboundPayload> PAYLOAD_INFO =
            new WeriNetwork.PayloadInfo<>(
                    new CustomPayload.Id<>(Weri.id("cloud_message_serverbound")),
                    PacketCodec.tuple(
                        PacketCodecs.STRING,
                        CloudMessageServerboundPayload::message,
                        CloudMessageServerboundPayload::new
                    )
                );
    // spotless:on

    @Override
    public Id<? extends CustomPayload> getId() {
        return PAYLOAD_INFO.id();
    }
}
