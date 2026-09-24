package org.fourmur.wathe_extended_rus_improved.client.mixin;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;

@Mixin(SplashOverlay.class)
public final class SplashOverlayMixin {
    @Unique
	private static final Identifier TRAIN = WatheExtendedRusImproved.id("textures/gui/train.png");

    @Inject(method = "render", at = @At(value = "TAIL", target = "drawTexture"))
	public void render(
        DrawContext context,
        int mouseX, int mouseY,
        float delta,
        CallbackInfo info
    ) {
        context.drawTexture(
            TRAIN,
            context.getScaledWindowWidth() - 100,
            context.getScaledWindowHeight() - 100,
            1.0f, 1.0f,
            100, 100,
            100, 100
        );
    }
}
