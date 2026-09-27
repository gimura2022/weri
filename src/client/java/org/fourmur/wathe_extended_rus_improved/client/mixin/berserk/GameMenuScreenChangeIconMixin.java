package org.fourmur.wathe_extended_rus_improved.client.mixin.berserk;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.util.Identifier;

@Mixin(value = GameMenuScreen.class, priority = 2000)
public final class GameMenuScreenChangeIconMixin {
    @Shadow
    private static final Identifier watheIcon = WatheExtendedRusImproved.id("textures/gui/sprites/hud/wathe.png");
}
