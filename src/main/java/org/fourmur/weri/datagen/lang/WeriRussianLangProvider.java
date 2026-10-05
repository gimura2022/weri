package org.fourmur.weri.datagen.lang;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public final class WeriRussianLangProvider extends WeriAbstractLangProvider {
	public WeriRussianLangProvider(FabricDataOutput dataOutput,
            CompletableFuture<WrapperLookup> registryLookup) {
		super(dataOutput, "ru_ru", registryLookup);
	}

	@Override
	protected String getMusicDiscTranslation() {
		return "Пластинка";
	}
}
