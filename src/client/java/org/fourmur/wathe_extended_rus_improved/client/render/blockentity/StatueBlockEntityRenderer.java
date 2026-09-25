package org.fourmur.wathe_extended_rus_improved.client.render.blockentity;

import org.fourmur.wathe_extended_rus_improved.block.entity.StatueBlockEntity;
import org.fourmur.wathe_extended_rus_improved.index.BlockEntities;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;

public final class StatueBlockEntityRenderer implements BlockEntityRenderer<StatueBlockEntity> {
    private AbstractClientPlayerEntity cachedPlayer;

    public StatueBlockEntityRenderer(BlockEntityRendererFactory.Context context) {}

    public static void register() {
        BlockEntityRendererFactories.register(BlockEntities.STATUE, StatueBlockEntityRenderer::new);
    }

	@Override
	public void render(
        StatueBlockEntity entity,
        float tickDelta,
        MatrixStack matrices,
		VertexConsumerProvider vertexConsumers,
        int light,
        int overlay
    ) {
        if (cachedPlayer == null)
            cachedPlayer = new AbstractClientPlayerEntity((ClientWorld) entity.getWorld(), new GameProfile(entity.getProfileId(), "_g1mura_")) {};

        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f);

        MinecraftClient.getInstance().getEntityRenderDispatcher().render(
            cachedPlayer,
            0.0, 0.0, 0.0,
            0.0f,
            tickDelta,
            matrices,
            vertexConsumers,
            light
        );

        matrices.pop();
	}
}
