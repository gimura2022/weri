package org.fourmur.weri.init;

import org.fourmur.weri.Weri;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class WeriSounds {
    public static final SoundEvent WHITE_NOIZ = register("white_noiz");
    public static final RegistryKey<JukeboxSong> WHITE_NOIZ_KEY =
        RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Weri.id("white_noiz"));

    public static void initialize() {}

    private static SoundEvent register(String key) {
        Identifier id = Weri.id(key);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
