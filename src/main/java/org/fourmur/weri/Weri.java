package org.fourmur.weri;

import net.minecraft.util.Identifier;

import org.fourmur.weri.init.WeriItems;
import org.fourmur.weri.init.WeriNetwork;
import org.fourmur.weri.init.WeriSounds;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Weri implements ModInitializer {
    public static final String MOD_ID = "weri";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("WERI initialized");

        WeriItems.initialize();
        WeriNetwork.initialize();
        WeriSounds.initialize();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
