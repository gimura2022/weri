package org.fourmur.weri.mixin.fix;

import java.util.Collections;

import net.minecraft.item.ItemStack;

import dev.doctor4t.wathe.entity.PlayerBodyEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerBodyEntity.class)
public abstract class PlayerBodyEntityNullInventoryFixMixin {
    @Overwrite
    public Iterable<ItemStack> getArmorItems() {
        return Collections.emptyList();
    }
}
