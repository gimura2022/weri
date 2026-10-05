package org.fourmur.weri.datagen.lang;

import java.util.concurrent.CompletableFuture;

import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public final class WeriRussianLangProvider extends WeriAbstractLangProvider {
    public WeriRussianLangProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, "ru_ru", registryLookup);
    }

    @Override
    protected String getMusicDiscTranslation() {
        return "Пластинка";
    }
}
