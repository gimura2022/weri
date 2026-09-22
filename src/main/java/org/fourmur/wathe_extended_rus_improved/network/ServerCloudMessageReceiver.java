package org.fourmur.wathe_extended_rus_improved.network;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ServerCloudMessageReceiver implements Runnable {
    public static final double SENDING_RADIUS = 10.0;

    private final CloudMessageServerboundPayload payload;
    private final ServerPlayNetworking.Context context;

    public ServerCloudMessageReceiver(CloudMessageServerboundPayload payload, ServerPlayNetworking.Context context) {
        this.payload = payload;
        this.context = context;
    }

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(CloudMessageServerboundPayload.ID, (payload, context) -> {
            context.server().execute(new ServerCloudMessageReceiver(payload, context));
        });
    }

	@Override
	public void run() {
        ServerPlayerEntity sender = context.player();

        if (sender == null)
            return;

        for (ServerPlayerEntity player : PlayerLookup.around(sender.getServerWorld(), sender.getPos(), SENDING_RADIUS)) {
            ServerPlayNetworking.send(player, new CloudMessageClientboundPayload(payload.message(), sender.getUuid()));
        }
	}
}
