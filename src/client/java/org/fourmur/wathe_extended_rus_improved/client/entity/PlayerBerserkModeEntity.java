package org.fourmur.wathe_extended_rus_improved.client.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

public final class PlayerBerserkModeEntity implements GeoReplacedEntity {
    public static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("misc.idle");
    public static final RawAnimation WALK_ANIMATION = RawAnimation.begin().thenLoop("move.walk");
    public static final RawAnimation RUN_ANIMATION = RawAnimation.begin().thenLoop("move.run");
    public static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenLoop("act.attack");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Idle/Walk/Run Controller", 5, state -> {
            Entity entity = state.getData(DataTickets.ENTITY);

            if (state.isMoving()) {
                return state.setAndContinue(entity.isSprinting() ? RUN_ANIMATION : WALK_ANIMATION);
            }

            return state.setAndContinue(IDLE_ANIMATION);
        }));

        controllers.add(new AnimationController<>(this, "Attack Controller", 1, state -> {
            LivingEntity entity = (LivingEntity) state.getData(DataTickets.ENTITY);

            if (entity.handSwinging) {
                return state.setAndContinue(ATTACK_ANIMATION);
            }

            return PlayState.STOP;
        }));
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
