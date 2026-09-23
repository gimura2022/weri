package org.fourmur.wathe_extended_rus_improved.client.render.blockentity;

import org.fourmur.wathe_extended_rus_improved.block.entity.StatueBlockEntity;
import org.fourmur.wathe_extended_rus_improved.index.BlockEntities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public final class StatueBlockEntityRenderer implements BlockEntityRenderer<StatueBlockEntity> {
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
        if (entity.getPlayerEntity() == null)
            return;

        MinecraftClient.getInstance().getEntityRenderDispatcher().render(
            entity.getPlayerEntity(),
            0.0, 0.0, 0.0,
            0.0f,
            tickDelta,
            matrices,
            vertexConsumers,
            light
        );
	}
}
