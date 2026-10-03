package org.fourmur.weri.client.cloudmessage;

import java.util.List;

import org.fourmur.weri.client.mixin.accessor.EntityRendererAccessor;
import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;

public final class CloudMessageRenderer {
    public static final double MESSAGE_IN_TIME = 2.0;
    public static final double MESSAGE_OUT_TIME = 2.0;
    public static final double Y_OFFSET = 0.2;
    public static final double Y_STEP = 0.2;
    public static final float TEXT_SCALE = 0.016f;

    public void render(
        PlayerEntityRenderer playerRenderer,
		AbstractClientPlayerEntity entity,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light
    ) {
        List<CloudMessageQueue.Message> messages = ((CloudMessageQueueHolder) entity).weri$getQueue().getMessages();

        for (CloudMessageQueue.Message message : messages) {
            renderMessage(
                playerRenderer,
                entity,
                tickDelta,
                matrices,
                vertexConsumers,
                light,
                message,
                messages.indexOf(message)
            );
        }
    }

    private void renderMessage(
        PlayerEntityRenderer playerRenderer,
		AbstractClientPlayerEntity entity,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        CloudMessageQueue.Message message,
        int messageIndex
    ) {
		Vec3d pos = entity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, entity.getYaw(tickDelta));

        if (pos == null)
            return;

        double offset = messageIndex * Y_STEP;
        float scale = TEXT_SCALE;

        if (message.age <= MESSAGE_IN_TIME)
            scale *= message.age / MESSAGE_IN_TIME;

        if ((CloudMessageQueue.MAX_MESSAGE_LIFETIME - message.age) <= MESSAGE_OUT_TIME)
            scale *= (CloudMessageQueue.MAX_MESSAGE_LIFETIME - message.age) / MESSAGE_OUT_TIME;

        matrices.push();
        matrices.translate(pos.x, pos.y + 0.5 + Y_OFFSET + offset, pos.z);
        matrices.multiply(((EntityRendererAccessor) playerRenderer).weri$getDispatcher().getRotation());
		matrices.scale(scale, -scale, scale);

        TextRenderer textRenderer = playerRenderer.getTextRenderer();

        textRenderer.draw(
            message.message,
            -textRenderer.getWidth(message.message) / 2,
            0,
            Colors.WHITE,
            true,
            matrices.peek().getPositionMatrix(),
            vertexConsumers,
            TextRenderer.TextLayerType.NORMAL,
            0,
            7340256
        );

        matrices.pop();
    }
}
