package org.fourmur.weri.client;

import org.fourmur.weri.client.network.CloudMessageClientReceiver;

import net.fabricmc.api.ClientModInitializer;

public final class WatheExtendedRusImprovedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        CloudMessageClientReceiver.register();
	}
}
