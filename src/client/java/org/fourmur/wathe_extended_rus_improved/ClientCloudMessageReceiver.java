package org.fourmur.wathe_extended_rus_improved;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;

import org.fourmur.wathe_extended_rus_improved.mixininterface.CloudMessageQueueHolder;

public final class ClientCloudMessageReceiver implements Runnable {
    private final CloudMessagePacket.ClientboundPayload payload;
    private final ClientPlayNetworking.Context context;

    public ClientCloudMessageReceiver(CloudMessagePacket.ClientboundPayload payload, ClientPlayNetworking.Context context) {
        this.payload = payload;
        this.context = context;
    }

	@Override
	public void run() {
        ClientPlayerEntity sender = (ClientPlayerEntity) context.client().world.getPlayerByUuid(payload.player());

        if (sender == null)
            return;

        ((CloudMessageQueueHolder) sender).wathe_extended_rus_improved$getQueue().queueMessage(payload.message());
	}
}
