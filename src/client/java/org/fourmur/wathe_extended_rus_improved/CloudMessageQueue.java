package org.fourmur.wathe_extended_rus_improved;

import java.util.ArrayList;
import java.util.List;

import org.fourmur.wathe_extended_rus_improved.mixin.EntityRendererAccessor;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;

public final class CloudMessageQueue {
    public static final int MAX_ELEMENTS = 3;
    public static final double MAX_MESSAGE_LIFETIME = 80;
    public static final double RENDER_Y_OFFSET = 0.2;
    public static final double RENDER_Y_STEP = 0.2;
    public static final float RENDER_TEXT_SCALE = 0.016f;

    private final List<Message> messages = new ArrayList<>();

    public void queueMessage(String message) {
        messages.add(0, new Message(message, 0));
        
        if (messages.size() == MAX_ELEMENTS + 1)
            messages.remove(MAX_ELEMENTS);
    }

    public void render(
        PlayerEntityRenderer playerRenderer,
		AbstractClientPlayerEntity entity,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light
    ) {
        for (Message message : messages) {
            message.age += tickDelta;
        }
 
        if (messages.size() != 0 && messages.getLast().age > MAX_MESSAGE_LIFETIME)
            messages.removeLast();

        for (Message message : messages) {
            renderMessage(playerRenderer, entity, tickDelta, matrices, vertexConsumers, light, message);
        }
    }

    private void renderMessage(
        PlayerEntityRenderer playerRenderer,
		AbstractClientPlayerEntity entity,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        Message message
    ) {
		Vec3d pos = entity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, entity.getYaw(tickDelta));

        if (pos == null)
            return;

        double offset = messages.indexOf(message) * RENDER_Y_STEP;

        matrices.push();
        matrices.translate(pos.x, pos.y + 0.5 + RENDER_Y_OFFSET + offset, pos.z);
        matrices.multiply(((EntityRendererAccessor) playerRenderer).wathe_extended_rus_improved$getDispatcher().getRotation());
		matrices.scale(RENDER_TEXT_SCALE, -RENDER_TEXT_SCALE, RENDER_TEXT_SCALE);

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
            light
        );

        matrices.pop();
    }

    private final class Message {
        public final String message;
        public double age;

        public Message(String message, double age) {
            this.message = message;
            this.age = age;
        }
    }
}
