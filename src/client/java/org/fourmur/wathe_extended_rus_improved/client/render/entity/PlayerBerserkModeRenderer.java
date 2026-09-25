package org.fourmur.wathe_extended_rus_improved.client.render.entity;

import org.fourmur.wathe_extended_rus_improved.entity.PlayerBerserkModeEntity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public final class PlayerBerserkModeRenderer extends GeoReplacedEntityRenderer<PlayerEntity, PlayerBerserkModeEntity> {
	public PlayerBerserkModeRenderer(Context renderManager) {
		super(renderManager, new PlayerBerserkModeModel(), new PlayerBerserkModeEntity());
	}

    public static void register() {
        EntityRendererRegistry.register(EntityType.PLAYER, PlayerBerserkModeRenderer::new); 
    }
}
