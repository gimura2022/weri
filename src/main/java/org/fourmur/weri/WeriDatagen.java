package org.fourmur.weri;

import org.fourmur.weri.datagen.WeriJukeboxSongProvider;
import org.fourmur.weri.datagen.WeriModelProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class WeriDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();

        pack.addProvider(WeriJukeboxSongProvider::new);
        pack.addProvider(WeriModelProvider::new);
	}
}
