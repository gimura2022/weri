package org.fourmur.weri.client.mixin.revolver;

import net.minecraft.util.math.Vec3d;

import dev.doctor4t.wathe.item.RevolverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RevolverItem.class)
public abstract class RevolverItemFlashOffsetMixin {
    @Shadow
    private static Vec3d DEFAULT_FLASH_OFFSET = new Vec3d(-0.05f, 0.24f, -0.9f);
}
