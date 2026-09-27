package org.fourmur.wathe_extended_rus_improved.client.render.entity;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.fourmur.wathe_extended_rus_improved.client.entity.PlayerBerserkModeEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public final class PlayerBerserkModeModel extends DefaultedEntityGeoModel<PlayerBerserkModeEntity> {
    public static final Identifier MODEL = WatheExtendedRusImproved.id("berserk");

	public PlayerBerserkModeModel() {
		super(MODEL);
	}

	@Override
	public void setCustomAnimations(PlayerBerserkModeEntity animatable, long instanceId,
			AnimationState<PlayerBerserkModeEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone body = this.getBone("head").orElse(null);

        if (body == null)
            return;

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        
        body.setRotX(entityData.headPitch() * ((float) Math.PI / 180F));
        body.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));
	}
}
