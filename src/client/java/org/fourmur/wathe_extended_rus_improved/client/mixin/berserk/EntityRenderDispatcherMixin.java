package org.fourmur.wathe_extended_rus_improved.client.mixin.berserk;

import org.fourmur.wathe_extended_rus_improved.client.render.entity.PlayerBerserkModeRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.resource.ResourceManager;

@Mixin(EntityRenderDispatcher.class)
public final class EntityRenderDispatcherMixin {
    @Unique
    private PlayerBerserkModeRenderer playerBerserkModeRenderer;

    @SuppressWarnings("unchecked")
	@Inject(method = "getRenderer", at = @At("HEAD"), cancellable = true)
    private <T extends Entity> void getRenderer(T entity, CallbackInfoReturnable<EntityRenderer<? super T>> info) {
        if (entity instanceof AbstractClientPlayerEntity) {
            info.setReturnValue((EntityRenderer<? super T>) (Object) playerBerserkModeRenderer);
        }
    }

    @Inject(method = "reload", at = @At("TAIL"))
    private void reload(
        ResourceManager manager,
        CallbackInfo info,
        @Local(ordinal = 0) EntityRendererFactory.Context context
    ) {
        playerBerserkModeRenderer = new PlayerBerserkModeRenderer(context);
    }
}
