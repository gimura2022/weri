package org.fourmur.weri.datagen;

import java.util.Optional;

import org.fourmur.weri.init.WeriItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

public final class WeriModelProvider extends FabricModelProvider {
    public static final Model FLAT_MODEL = parent("generated", TextureKey.LAYER0);

	public WeriModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator modelGenerator) {}

	@Override
	public void generateItemModels(ItemModelGenerator modelGenerator) {
        modelGenerator.register(WeriItems.WHITE_NOIZ_MUSIC_DISC, FLAT_MODEL);
        modelGenerator.register(WeriItems.ORDINARY_VANITY_MUSIC_DISC, FLAT_MODEL);
	}

    private static Model parent(String parent, TextureKey... textureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("item/" + parent)), Optional.empty(), textureKeys);
    }
}
