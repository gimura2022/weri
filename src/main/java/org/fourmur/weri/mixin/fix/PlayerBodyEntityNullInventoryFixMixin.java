package org.fourmur.weri.mixin.fix;

import java.util.Collections;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import dev.doctor4t.wathe.entity.PlayerBodyEntity;
import net.minecraft.item.ItemStack;

@Mixin(PlayerBodyEntity.class)
public abstract class PlayerBodyEntityNullInventoryFixMixin {
    @Overwrite
    public Iterable<ItemStack> getArmorItems() {
        return Collections.emptyList();
    }
}
