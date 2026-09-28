package org.fourmur.weri.init;

import org.fourmur.weri.Weri;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class WeriSounds {
    public static final SoundEvent WHITE_NOIZ = register("white_noiz");
    public static final SoundEvent ORDINARY_VANITY = register("ordinary_vanity");

    public static void initialize() {}

    private static SoundEvent register(String key) {
        Identifier id = Weri.id(key);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
