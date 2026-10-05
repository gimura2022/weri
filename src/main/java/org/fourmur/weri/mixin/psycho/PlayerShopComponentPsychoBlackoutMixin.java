package org.fourmur.weri.mixin.psycho;

import net.minecraft.entity.player.PlayerEntity;

import dev.doctor4t.wathe.cca.PlayerShopComponent;
import dev.doctor4t.wathe.cca.WorldBlackoutComponent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerShopComponent.class)
public abstract class PlayerShopComponentPsychoBlackoutMixin {
    @Inject(method = "usePsychoMode", at = @At("RETURN"))
    private static void usePsychoMode(@NotNull PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        WorldBlackoutComponent worldBlackout = WorldBlackoutComponent.KEY.get(player.getWorld());

        if (cir.getReturnValue() && !worldBlackout.isBlackoutActive()) worldBlackout.triggerBlackout();
    }
}
