package org.fourmur.weri.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.AbstractClientPlayerEntity;

import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;
import org.fourmur.weri.network.CloudMessageClientboundPayload;
import org.fourmur.weri.Weri;

public final class CloudMessageClientReceiver implements Runnable {
    private final CloudMessageClientboundPayload payload;
    private final ClientPlayNetworking.Context context;

    public CloudMessageClientReceiver(CloudMessageClientboundPayload payload, ClientPlayNetworking.Context context) {
        this.payload = payload;
        this.context = context;
    }

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(CloudMessageClientboundPayload.ID, (payload, context) -> {
            context.client().execute(new CloudMessageClientReceiver(payload, context));
        });
    }

	@Override
	public void run() {
        AbstractClientPlayerEntity sender = (AbstractClientPlayerEntity) context.client().world.getPlayerByUuid(payload.player());

        if (sender == null)
            return;

        Weri.LOGGER.debug(sender + " was sended " + payload.message());

        ((CloudMessageQueueHolder) sender).wathe_extended_rus_improved$getQueue().queueMessage(payload.message());
	}
}
