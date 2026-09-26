package org.fourmur.weri;

import org.fourmur.weri.network.CloudMessageClientboundPayload;
import org.fourmur.weri.network.CloudMessageServerReceiver;
import org.fourmur.weri.network.CloudMessageServerboundPayload;
import org.fourmur.weri.index.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public final class WatheExtendedRusImproved implements ModInitializer {
    public static final String MOD_ID = "weri";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	@Override
	public void onInitialize() {
        Items.initialize();

        registerNetwork();
	}

    private void registerNetwork() {
        CloudMessageClientboundPayload.register();
        CloudMessageServerboundPayload.register();
        CloudMessageServerReceiver.register();
    }
}
