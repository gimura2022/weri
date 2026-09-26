package org.fourmur.weri.client.mixin.revolver;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import dev.doctor4t.wathe.item.RevolverItem;
import net.minecraft.util.math.Vec3d;

@Mixin(RevolverItem.class)
public final class RevolverItemMixin {
    @Shadow
    private static Vec3d DEFAULT_FLASH_OFFSET = new Vec3d(-0.05f, 0.24f, -0.9f);
}
