package org.fourmur.wathe_extended_rus_improved.client.mixin.berserk;

import java.util.Map;
import java.util.stream.Collectors;

import org.fourmur.wathe_extended_rus_improved.client.render.entity.PlayerBerserkModeRenderer;
import org.fourmur.wathe_extended_rus_improved.index.Attachments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import dev.doctor4t.wathe.cca.PlayerPsychoComponent;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.ResourceManager;

@Mixin(EntityRenderDispatcher.class)
public final class EntityRenderDispatcherPlayerBerserkModeRendererMixin {
    @Shadow
	private Map<SkinTextures.Model, EntityRenderer<? extends PlayerEntity>> modelRenderers;

    @Unique
    private Map<SkinTextures.Model, PlayerBerserkModeRenderer> playerBerserkModeRenderers;

    @SuppressWarnings("unchecked")
	@Inject(method = "getRenderer", at = @At("HEAD"), cancellable = true)
    private <T extends Entity> void getRenderer(T entity, CallbackInfoReturnable<EntityRenderer<? super T>> info) {
        if (entity instanceof AbstractClientPlayerEntity player
                && (PlayerPsychoComponent.KEY.get(player).getPsychoTicks() > 0
                    || player.getAttachedOrCreate(Attachments.FORCE_BERSERK_MODEL))) {
			SkinTextures.Model model = player.getSkinTextures().model();
			EntityRenderer<? extends PlayerEntity> entityRenderer = this.playerBerserkModeRenderers.get(model);

		    info.setReturnValue((EntityRenderer<? super T>) (entityRenderer != null ?
                entityRenderer : this.playerBerserkModeRenderers.get(SkinTextures.Model.WIDE)));
        }
    }

    @Inject(method = "reload", at = @At("TAIL"))
    private void reload(
        ResourceManager manager,
        CallbackInfo info,
        @Local(ordinal = 0) EntityRendererFactory.Context context
    ) {
        this.playerBerserkModeRenderers = this.modelRenderers.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, x -> new PlayerBerserkModeRenderer(context, (PlayerEntityRenderer) x.getValue())));
    }
}
