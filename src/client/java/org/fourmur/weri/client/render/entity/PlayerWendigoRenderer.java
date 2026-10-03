package org.fourmur.weri.client.render.entity;

import org.fourmur.weri.client.entity.PlayerWendigoEntity;
import org.fourmur.weri.client.mixininterface.CustomPlayerArmRenderer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public final class PlayerWendigoRenderer extends
        GeoReplacedEntityRenderer<PlayerEntity, PlayerWendigoEntity> implements CustomPlayerArmRenderer {
    private final PlayerEntityRenderer playerEntityRenderer;

	public PlayerWendigoRenderer(Context renderManager, PlayerEntityRenderer playerEntityrenderer) {
		super(renderManager, new PlayerWendigoModel(), new PlayerWendigoEntity());

        this.playerEntityRenderer = playerEntityrenderer;
	}

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
