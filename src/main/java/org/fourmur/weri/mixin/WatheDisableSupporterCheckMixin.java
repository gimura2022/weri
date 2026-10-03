package org.fourmur.weri.mixin;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import dev.doctor4t.wathe.Wathe;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(Wathe.class)
public abstract class WatheDisableSupporterCheckMixin {
    @Overwrite
    public static @NotNull Boolean isSupporter(PlayerEntity player) {
        return true;
    }
}
