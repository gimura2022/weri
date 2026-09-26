package org.fourmur.weri.client.mixin.cloudmessage;

import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(PlayerEntityRenderer.class)
public final class PlayerEntityRendererCloudMessageMixin {
    @Unique
    private final PlayerEntityRenderer renderer = (PlayerEntityRenderer) (Object) this;

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
        ((CloudMessageQueueHolder) entity).weri$getQueue().render(
            renderer,
            entity,
            tickDelta,
            matrices,
            vertexConsumers,
            light
        );
    }
}
