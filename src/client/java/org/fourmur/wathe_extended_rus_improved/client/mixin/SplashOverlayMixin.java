package org.fourmur.wathe_extended_rus_improved.client.mixin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.resource.metadata.TextureResourceMetadata;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Mixin(SplashOverlay.class)
public final class SplashOverlayMixin {
    @Unique
	private static final Identifier TRAIN = WatheExtendedRusImproved.id("textures/gui/train.png");

    @Inject(method = "render", at = @At("TAIL"))
	public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo info) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(770, 1);

		context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        context.drawTexture(
            TRAIN,
            context.getScaledWindowWidth() - 100,
            context.getScaledWindowHeight() - 100,
            1.0f, 1.0f,
            100, 100,
            100, 100
        );

		context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
    }

    @Unique
	private static class SplashOverlayTextrue extends ResourceTexture {
        private final Identifier id;

		public SplashOverlayTextrue(Identifier id) {
			super(id);
            this.id = id;
		}

		@Override
		protected ResourceTexture.TextureData loadTextureData(ResourceManager resourceManager) {
			DefaultResourcePack defaultResourcePack = MinecraftClient.getInstance().getDefaultResourcePack();
			InputSupplier<InputStream> inputSupplier = defaultResourcePack.open(ResourceType.CLIENT_RESOURCES, id);
			if (inputSupplier == null) {
				return new ResourceTexture.TextureData(new FileNotFoundException(id.toString()));
			}

			try (InputStream inputStream = (InputStream)inputSupplier.get()) {
				return new ResourceTexture.TextureData(new TextureResourceMetadata(true, true), NativeImage.read(inputStream));
			} catch (IOException iOException) {
				return new ResourceTexture.TextureData(iOException);
			}
		}
	}
}
