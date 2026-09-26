package org.fourmur.wathe_extended_rus_improved.client.mixininterface;

import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

public interface CustomPlayerArmRenderer {
    @Unique
    void weri$renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player);

    @Unique
    void weri$renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player);
}
