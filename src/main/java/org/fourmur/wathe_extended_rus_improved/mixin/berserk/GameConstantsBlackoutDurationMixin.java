package org.fourmur.wathe_extended_rus_improved.mixin.berserk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import dev.doctor4t.wathe.game.GameConstants;

@Mixin(GameConstants.class)
public interface GameConstantsBlackoutDurationMixin {
    @Shadow
    int BLACKOUT_MIN_DURATION = GameConstants.PSYCHO_TIMER + GameConstants.getInTicks(0, 3);

    @Shadow
    int BLACKOUT_MAX_DURATION = GameConstants.PSYCHO_TIMER + GameConstants.getInTicks(0, 4);
}
