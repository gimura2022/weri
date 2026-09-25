package org.fourmur.wathe_extended_rus_improved.client.mixin.berserk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.render.entity.PlayerEntityRenderer;

@Mixin(PlayerEntityRenderer.class)
public final class PlayerEntityRendererMixin {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "<init>"))
    private static void injectCustomPlayerModel(Args args) {
        args.set(1, null);
    }
}
