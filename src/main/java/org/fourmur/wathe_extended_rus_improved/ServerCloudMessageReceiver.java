package org.fourmur.wathe_extended_rus_improved;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ServerCloudMessageReceiver implements Runnable {
    public static final double SENDING_RADIUS = 10.0;

    private final CloudMessagePacket.ServerboundPayload payload;
    private final ServerPlayNetworking.Context context;

    public ServerCloudMessageReceiver(CloudMessagePacket.ServerboundPayload payload, ServerPlayNetworking.Context context) {
        this.payload = payload;
        this.context = context;
    }

	@Override
	public void run() {
        ServerPlayerEntity sender = context.player();

        if (sender == null)
            return;

        for (ServerPlayerEntity player : PlayerLookup.around(sender.getServerWorld(), sender.getPos(), SENDING_RADIUS)) {
            ServerPlayNetworking.send(player, new CloudMessagePacket.ClientboundPayload(payload.message(), sender.getUuid()));
        }
	}
}
