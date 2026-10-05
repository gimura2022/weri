package org.fourmur.weri.datagen.lang;

import java.util.concurrent.CompletableFuture;

import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public final class WeriEnglishLangProvider extends WeriAbstractLangProvider {
    public WeriEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    protected String getMusicDiscTranslation() {
        return "Music Disc";
    }
}
