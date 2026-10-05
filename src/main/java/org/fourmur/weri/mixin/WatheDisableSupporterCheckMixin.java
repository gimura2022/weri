package org.fourmur.weri.mixin;

import net.minecraft.entity.player.PlayerEntity;

import dev.doctor4t.wathe.Wathe;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Wathe.class)
public abstract class WatheDisableSupporterCheckMixin {
    @Overwrite
    public static @NotNull Boolean isSupporter(PlayerEntity player) {
        return true;
    }
}
