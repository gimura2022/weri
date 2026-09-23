package org.fourmur.wathe_extended_rus_improved.client.mixin.revolver;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.doctor4t.wathe.item.RevolverItem;
import net.minecraft.util.math.Vec3d;

@Mixin(RevolverItem.class)
public final class RevolverItemMixin {
    @Shadow
    @Final
    @Mutable
    private static Vec3d DEFAULT_FLASH_OFFSET;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo info) {
        DEFAULT_FLASH_OFFSET = new Vec3d(-0.15f, 0.275f, -0.77f);
    }
}
