package org.fourmur.weri.init;

import java.util.function.BiFunction;

import org.fourmur.weri.network.CloudMessageClientboundPayload;
import org.fourmur.weri.network.CloudMessageServerReceiver;
import org.fourmur.weri.network.CloudMessageServerboundPayload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public final class WeriNetwork {
    public final record PayloadInfo<T extends CustomPayload>(
        CustomPayload.Id<T> id,
        PacketCodec<RegistryByteBuf, T> packetCodec
    ) {}

    public final record ServerReceiverInfo<T extends CustomPayload, R extends Runnable>(
        PayloadInfo<T> payloadInfo,
        BiFunction<T, ServerPlayNetworking.Context, R> constructor
    ) {}

    public static void initialize() {
        registerClientboundPayload(CloudMessageClientboundPayload.PAYLOAD_INFO);
        registerServerboundPayload(CloudMessageServerboundPayload.PAYLOAD_INFO);

        registerServerReceiver(CloudMessageServerReceiver.RECEIVER_INFO);
    }

    private static <T extends CustomPayload, R extends Runnable> void registerServerReceiver(
            ServerReceiverInfo<T, R> info) {
        ServerPlayNetworking.registerGlobalReceiver(
            info.payloadInfo().id(),
            (payload, context) -> {
                context.server().execute(info.constructor().apply(payload, context));
            }
        );
    }

    private static <T extends CustomPayload> void registerClientboundPayload(PayloadInfo<T> info) {
        registerPayload(info, PayloadTypeRegistry.playS2C());
    }

    private static <T extends CustomPayload> void registerServerboundPayload(PayloadInfo<T> info) {
        registerPayload(info, PayloadTypeRegistry.playC2S());
    }

    private static <T extends CustomPayload> void registerPayload(PayloadInfo<T> info,
            PayloadTypeRegistry<RegistryByteBuf> registry) {
        registry.register(info.id(), info.packetCodec());
    }
}
