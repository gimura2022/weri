package org.fourmur.weri.client.render.entity;

import org.fourmur.weri.client.entity.PlayerBerserkModeEntity;
import org.fourmur.weri.client.mixininterface.CustomPlayerArmRenderer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public final class PlayerBerserkModeRenderer extends
        GeoReplacedEntityRenderer<PlayerEntity, PlayerBerserkModeEntity> implements CustomPlayerArmRenderer {
    private final PlayerEntityRenderer playerEntityRenderer;

	public PlayerBerserkModeRenderer(Context renderManager, PlayerEntityRenderer playerEntityrenderer) {
		super(renderManager, new PlayerBerserkModeModel(), new PlayerBerserkModeEntity());

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
