package org.fourmur.weri.client.mixin.customarmrenderer;

import org.fourmur.weri.client.mixininterface.CustomPlayerArmRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(PlayerEntityRenderer.class)
public final class PlayerEntityArmRendererMixin implements CustomPlayerArmRenderer {
    @Unique
    private final PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer) (Object) this;

	@Override
	public void weri$renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
			AbstractClientPlayerEntity player) {
        playerEntityRenderer.renderRightArm(matrices, vertexConsumers, light, player);
	}

	@Override
	public void weri$renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
			AbstractClientPlayerEntity player) {
        playerEntityRenderer.renderLeftArm(matrices, vertexConsumers, light, player);
	}
}
