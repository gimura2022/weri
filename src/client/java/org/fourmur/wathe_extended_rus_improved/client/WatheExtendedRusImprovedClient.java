package org.fourmur.wathe_extended_rus_improved.client;

import org.fourmur.wathe_extended_rus_improved.client.network.CloudMessageClientReceiver;

import net.fabricmc.api.ClientModInitializer;

public final class WatheExtendedRusImprovedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        CloudMessageClientReceiver.register();
	}
}
