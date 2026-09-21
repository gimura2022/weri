package org.fourmur.wathe_extended_rus_improved.mixin;

import java.util.List;

import org.fourmur.wathe_extended_rus_improved.CloudMessageQueue;
import org.fourmur.wathe_extended_rus_improved.mixininterface.CloudMessageQueueHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;

@Mixin(PlayerEntityRenderer.class)
public final class CloudMessageRenderMixin {
    private static final float TEXT_SCALE = 0.016f;
    private static final float MESSAGE_PADDING = 0.2f;
    private static final float Y_OFFSET = 0.2f;

    @Unique
    private final PlayerEntityRenderer renderer = (PlayerEntityRenderer) (Object) this;

    @Unique
    private final EntityRendererAccessor entityRendererAccessor = (EntityRendererAccessor) renderer;

    @Inject(method = "render", at = @At(value = "HEAD"))
    private void render(
		AbstractClientPlayerEntity entity,
        float yaw,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        CallbackInfo info
	) {
        List<CloudMessageQueue.Message> messages = ((CloudMessageQueueHolder) entity).wathe_extended_rus_improved$getQueue().getMessages();

        for (int i = 0; i < messages.size(); i++) {
            renderCloudMessage(entity, tickDelta, matrices, vertexConsumers, light, messages.get(i).message, i * MESSAGE_PADDING);
        }
    }

    private void renderCloudMessage(
		AbstractClientPlayerEntity entity,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        String text,
        float offset
    ) {
        TextRenderer textRenderer = renderer.getTextRenderer();
		Vec3d pos = entity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, entity.getYaw(tickDelta));

        if (pos == null)
            return;

        matrices.push();
        matrices.translate(pos.x, pos.y + 0.5 + Y_OFFSET + offset, pos.z);
        matrices.multiply(entityRendererAccessor.wathe_extended_rus_improved$getDispatcher().getRotation());
		matrices.scale(TEXT_SCALE, -TEXT_SCALE, TEXT_SCALE);

        textRenderer.draw(
            text,
            -textRenderer.getWidth(text) / 2,
            0,
            Colors.WHITE,
            true,
            matrices.peek().getPositionMatrix(),
            vertexConsumers,
            TextRenderer.TextLayerType.NORMAL,
            0,
            light
        );

        matrices.pop();
    }
}
