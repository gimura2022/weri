package org.fourmur.weri.client;

import org.fourmur.weri.client.index.WeriClientNetwork;

import net.fabricmc.api.ClientModInitializer;

public final class WeriClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        WeriClientNetwork.initialize();
	}
}
