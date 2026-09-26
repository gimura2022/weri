package org.fourmur.weri.client.init;

import java.util.function.BiFunction;

import org.fourmur.weri.client.network.CloudMessageClientReceiver;
import org.fourmur.weri.init.WeriNetwork;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public final class WeriClientNetwork {
    public static void initialize() {
        registerClientReceiver(CloudMessageClientReceiver.RECEIVER_INFO);
    }

    public final record ClientReceiverInfo<T extends CustomPayload, R extends Runnable>(
        WeriNetwork.PayloadInfo<T> payloadInfo, BiFunction<T, ClientPlayNetworking.Context, R> constructor) {}

    private static <T extends CustomPayload, R extends Runnable> void registerClientReceiver(
            ClientReceiverInfo<T, R> info) {
        ClientPlayNetworking.registerGlobalReceiver(
            info.payloadInfo().id(),
            (payload, context) -> {
                context.client().execute(info.constructor().apply(payload, context));
            }
        );
    }
}
