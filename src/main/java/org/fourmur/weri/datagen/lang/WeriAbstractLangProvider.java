package org.fourmur.weri.datagen.lang;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import org.fourmur.weri.Weri;
import org.fourmur.weri.datagen.WeriJukeboxSongProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public abstract class WeriAbstractLangProvider extends FabricLanguageProvider {
    private final String languageCode;

	protected WeriAbstractLangProvider(FabricDataOutput dataOutput, String languageCode,
			CompletableFuture<WrapperLookup> registryLookup) {
		super(dataOutput, languageCode, registryLookup);

        this.languageCode = languageCode;
	}

    protected abstract String getMusicDiscTranslation();

	@Override
	public void generateTranslations(WrapperLookup registryLookup, TranslationBuilder builder) {
        addMusicDiscTranslation(builder, WeriJukeboxSongProvider.WHITE_NOIZ_KEY,
                "Silent Hill 2 - White Noiz");
        addMusicDiscTranslation(builder, WeriJukeboxSongProvider.ORDINARY_VANITY,
                "Silent Hill 2 - Ordinary Vanity");
        addMusicDiscTranslation(builder, WeriJukeboxSongProvider.MAXWELL,
                "Maxwell");

        try {
            Path exsistingTranslationPath = this.dataOutput.getModContainer()
                .findPath("assets/weri/lang/" + this.languageCode + ".exsisting.json")
                .get();

            builder.add(exsistingTranslationPath);
        } catch (Exception e) {
            throw new RuntimeException("Can not add exsisting translation file!");
        }
	}

    private void addMusicDiscTranslation(TranslationBuilder builder,
            RegistryKey<JukeboxSong> key, String desc) {
        String itemTranslationKey = "item." + Weri.MOD_ID
            + "." + key.getValue().getPath() + "_music_disc";

        builder.add(itemTranslationKey, getMusicDiscTranslation());
        builder.add(itemTranslationKey + ".desc", desc);
    }
}
