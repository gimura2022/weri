package org.fourmur.wathe_extended_rus_improved.client;

import org.fourmur.wathe_extended_rus_improved.CloudMessagePacket;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class WatheExtendedRusImprovedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(CloudMessagePacket.ClientboundPayload.ID, (payload, context) -> {
            context.client().execute(new ClientCloudMessageReceiver(payload, context));
        });
	}
}
