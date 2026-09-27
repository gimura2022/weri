package org.fourmur.wathe_extended_rus_improved.client.mixin.berserk;

import org.fourmur.wathe_extended_rus_improved.client.mixininterface.CustomPlayerArmRenderer;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(PlayerEntityRenderer.class)
public final class PlayerEntityArmRendererMixin implements CustomPlayerArmRenderer {
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
