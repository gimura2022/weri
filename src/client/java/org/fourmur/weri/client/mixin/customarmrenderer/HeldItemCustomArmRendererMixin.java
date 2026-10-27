package org.fourmur.weri.client.mixin.customarmrenderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import org.fourmur.weri.client.mixininterface.CustomPlayerArmRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemCustomArmRendererMixin {
    @Shadow
    private EntityRenderDispatcher entityRenderDispatcher;

    @Shadow
    private MinecraftClient client;

    @Overwrite
    private void renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm) {
        CustomPlayerArmRenderer playerEntityRenderer =
                (CustomPlayerArmRenderer) this.entityRenderDispatcher.getRenderer(this.client.player);

        matrices.push();

        float f = arm == Arm.RIGHT ? 1.0F : -1.0F;

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92.0F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * -41.0F));
        matrices.translate(f * 0.3F, -1.1F, 0.45F);

        if (arm == Arm.RIGHT) {
            playerEntityRenderer.weri$renderRightArm(matrices, vertexConsumers, light, this.client.player);
        } else {
            playerEntityRenderer.weri$renderLeftArm(matrices, vertexConsumers, light, this.client.player);
        }

        matrices.pop();
    }

    @Overwrite
    private void renderArmHoldingItem(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            float equipProgress,
            float swingProgress,
            Arm arm) {
        boolean bl = arm != Arm.LEFT;
        float f = bl ? 1.0F : -1.0F;
        float g = MathHelper.sqrt(swingProgress);
        float h = -0.3F * MathHelper.sin(g * (float) Math.PI);
        float i = 0.4F * MathHelper.sin(g * (float) (Math.PI * 2));
        float j = -0.4F * MathHelper.sin(swingProgress * (float) Math.PI);

        matrices.translate(f * (h + 0.64000005F), i + -0.6F + equipProgress * -0.6F, j + -0.71999997F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * 45.0F));

        float k = MathHelper.sin(swingProgress * swingProgress * (float) Math.PI);
        float l = MathHelper.sin(g * (float) Math.PI);

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * l * 70.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * k * -20.0F));

        AbstractClientPlayerEntity abstractClientPlayerEntity = this.client.player;

        matrices.translate(f * -1.0F, 3.6F, 3.5F);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * 120.0F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(200.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * -135.0F));
        matrices.translate(f * 5.6F, 0.0F, 0.0F);

        CustomPlayerArmRenderer playerEntityRenderer =
                (CustomPlayerArmRenderer) this.entityRenderDispatcher.getRenderer(abstractClientPlayerEntity);

        if (bl) {
            playerEntityRenderer.weri$renderRightArm(matrices, vertexConsumers, light, abstractClientPlayerEntity);
        } else {
            playerEntityRenderer.weri$renderLeftArm(matrices, vertexConsumers, light, abstractClientPlayerEntity);
        }
    }
}
