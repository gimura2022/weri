package org.fourmur.wathe_extended_rus_improved.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.AbstractClientPlayerEntity;

import org.fourmur.wathe_extended_rus_improved.client.mixininterface.CloudMessageQueueHolder;
import org.fourmur.wathe_extended_rus_improved.network.CloudMessageClientboundPayload;
import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;

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

        WatheExtendedRusImproved.LOGGER.debug(sender + " was sended " + payload.message());

        ((CloudMessageQueueHolder) sender).wathe_extended_rus_improved$getQueue().queueMessage(payload.message());
	}
}
