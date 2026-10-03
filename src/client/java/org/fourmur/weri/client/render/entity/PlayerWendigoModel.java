package org.fourmur.weri.client.render.entity;

import org.fourmur.weri.Weri;
import org.fourmur.weri.client.entity.PlayerWendigoEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public final class PlayerWendigoModel extends DefaultedEntityGeoModel<PlayerWendigoEntity> {
    public static final Identifier MODEL = Weri.id("wendigo");

	public PlayerWendigoModel() {
		super(MODEL);
	}

	@Override
	public void setCustomAnimations(PlayerWendigoEntity animatable, long instanceId,
			AnimationState<PlayerWendigoEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone body = this.getBone("head").orElse(null);

        if (body == null)
            return;

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        
        body.setRotX(entityData.headPitch() * ((float) Math.PI / 180F));
        body.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));
	}
}
