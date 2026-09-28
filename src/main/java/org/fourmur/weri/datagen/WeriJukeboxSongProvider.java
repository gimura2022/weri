package org.fourmur.weri.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.fourmur.weri.Weri;
import org.fourmur.weri.init.WeriSounds;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;

public final class WeriJukeboxSongProvider extends FabricDynamicRegistryProvider {
    private static final Map<RegistryKey<JukeboxSong>, JukeboxSong> SONGS = new HashMap<>();

    public static final RegistryKey<JukeboxSong> WHITE_NOIZ_KEY = register("white_noiz", WeriSounds.WHITE_NOIZ, 84.0f);
    public static final RegistryKey<JukeboxSong> ORDINARY_VANITY = register("ordinary_vanity", WeriSounds.ORDINARY_VANITY, 100.0f);
    public static final RegistryKey<JukeboxSong> MAXWELL = register("maxwell", WeriSounds.MAXWELL, 28.75f);

	public WeriJukeboxSongProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(WrapperLookup registries, Entries entries) {
        for (Map.Entry<RegistryKey<JukeboxSong>, JukeboxSong> entry : SONGS.entrySet()) {
            entries.add(entry.getKey(), entry.getValue());
        }
	}

	@Override
	public String getName() {
		return "jukebox_song";
	}

    private static RegistryKey<JukeboxSong> register(String key, SoundEvent sound, float length) {
        return register(key, sound, length, 15);
    }

    private static RegistryKey<JukeboxSong> register(String stringKey, SoundEvent sound, float length, int comparatorOutput) {
        RegistryKey<JukeboxSong> key = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Weri.id(stringKey));
        JukeboxSong song = new JukeboxSong(
            Registries.SOUND_EVENT.getEntry(sound),
            Text.translatable("item." + Weri.MOD_ID + "." + stringKey + "_music_disc.desc"),
            length,
            comparatorOutput
        );

        SONGS.put(key, song);

        return key;
    }
}
