package org.fourmur.weri.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.AbstractClientPlayerEntity;

import org.fourmur.weri.client.index.WeriClientNetwork;
import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;
import org.fourmur.weri.network.CloudMessageClientboundPayload;
import org.fourmur.weri.Weri;

public final record CloudMessageClientReceiver(CloudMessageClientboundPayload payload,
        ClientPlayNetworking.Context context) implements Runnable {
    public static final WeriClientNetwork.ClientReceiverInfo<CloudMessageClientboundPayload, CloudMessageClientReceiver> RECEIVER_INFO =
        new WeriClientNetwork.ClientReceiverInfo<>(CloudMessageClientboundPayload.PAYLOAD_INFO, CloudMessageClientReceiver::new);

	@Override
	public void run() {
        AbstractClientPlayerEntity sender = (AbstractClientPlayerEntity) context.client().world.getPlayerByUuid(payload.player());

        if (sender == null)
            return;

        Weri.LOGGER.debug(sender + " was sended " + payload.message());

        ((CloudMessageQueueHolder) sender).wathe_extended_rus_improved$getQueue().queueMessage(payload.message());
	}
}
