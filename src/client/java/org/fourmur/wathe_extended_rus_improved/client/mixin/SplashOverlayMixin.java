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

    @Unique
    private static final int TEXTURE_SIZE = 100;

    @Unique
    private static final int ANIMATION_SPEED = 4;

    @Unique
    private float time;

    @Unique
    private boolean animation;

    @Inject(method = "render", at = @At(value = "TAIL", target = "drawTexture"))
	public void render(
        DrawContext context,
        int mouseX, int mouseY,
        float delta,
        CallbackInfo info
    ) {
        time += delta;

        if (((int) time) % ANIMATION_SPEED == 0)
            animation = !animation;

        context.drawTexture(
            TRAIN,
            context.getScaledWindowWidth() - TEXTURE_SIZE,
            context.getScaledWindowHeight() - TEXTURE_SIZE + (animation ? 1 : 0),
            1.0f, 1.0f,
            TEXTURE_SIZE, TEXTURE_SIZE,
            TEXTURE_SIZE, TEXTURE_SIZE
        );
    }
}
