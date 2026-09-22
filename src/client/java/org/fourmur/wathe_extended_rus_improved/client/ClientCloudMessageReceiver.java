package org.fourmur.wathe_extended_rus_improved.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;

import org.fourmur.wathe_extended_rus_improved.client.mixininterface.CloudMessageQueueHolder;
import org.fourmur.wathe_extended_rus_improved.network.CloudMessageClientboundPayload;

public final class ClientCloudMessageReceiver implements Runnable {
    private final CloudMessageClientboundPayload payload;
    private final ClientPlayNetworking.Context context;

    public ClientCloudMessageReceiver(CloudMessageClientboundPayload payload, ClientPlayNetworking.Context context) {
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
