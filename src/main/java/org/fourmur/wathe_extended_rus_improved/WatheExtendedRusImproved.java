package org.fourmur.wathe_extended_rus_improved;

import org.fourmur.wathe_extended_rus_improved.network.CloudMessageClientboundPayload;
import org.fourmur.wathe_extended_rus_improved.network.CloudMessageServerReceiver;
import org.fourmur.wathe_extended_rus_improved.network.CloudMessageServerboundPayload;
import org.fourmur.wathe_extended_rus_improved.index.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public final class WatheExtendedRusImproved implements ModInitializer {
    public static final String MOD_ID = "wathe-extended-rus-improved";
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
