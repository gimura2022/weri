package org.fourmur.wathe_extended_rus_improved.client.entity;

import net.minecraft.entity.EntityType;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public final class PlayerBerserkModeEntity implements GeoReplacedEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
    }

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
	}

	@Override
	public EntityType<?> getReplacingEntityType() {
        return EntityType.PLAYER;
	}
}
