package org.fourmur.weri;

import org.fourmur.weri.init.WeriAttachments;
import org.fourmur.weri.init.WeriCommands;
import org.fourmur.weri.init.WeriItems;
import org.fourmur.weri.init.WeriNetwork;
import org.fourmur.weri.init.WeriSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public final class Weri implements ModInitializer {
    public static final String MOD_ID = "weri";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	@Override
	public void onInitialize() {
        LOGGER.info("WERI initialized");

        WeriItems.initialize();
        WeriNetwork.initialize();
        WeriSounds.initialize();
        WeriAttachments.initialize();
        WeriCommands.initialize();
	}
}
