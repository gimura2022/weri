package org.fourmur.wathe_extended_rus_improved.client.render.entity;

import org.fourmur.wathe_extended_rus_improved.client.entity.PlayerBerserkModeEntity;
import org.fourmur.wathe_extended_rus_improved.client.mixininterface.CustomPlayerArmRenderer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public final class PlayerBerserkModeRenderer extends
        GeoReplacedEntityRenderer<PlayerEntity, PlayerBerserkModeEntity> implements CustomPlayerArmRenderer {
	public PlayerBerserkModeRenderer(Context renderManager) {
		super(renderManager, new PlayerBerserkModeModel(), new PlayerBerserkModeEntity());
	}

    public static void register() {
        EntityRendererRegistry.register(EntityType.PLAYER, PlayerBerserkModeRenderer::new);
    }

	@Override
	public void weri$renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
			AbstractClientPlayerEntity player) {}

	@Override
	public void weri$renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
			AbstractClientPlayerEntity player) {}
}
