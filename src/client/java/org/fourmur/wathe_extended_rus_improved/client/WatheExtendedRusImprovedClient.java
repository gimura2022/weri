package org.fourmur.wathe_extended_rus_improved.client;

import org.fourmur.wathe_extended_rus_improved.client.network.CloudMessageClientReceiver;
import org.fourmur.wathe_extended_rus_improved.client.render.blockentity.StatueBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;

public final class WatheExtendedRusImprovedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        CloudMessageClientReceiver.register();
        StatueBlockEntityRenderer.register();
	}
}
