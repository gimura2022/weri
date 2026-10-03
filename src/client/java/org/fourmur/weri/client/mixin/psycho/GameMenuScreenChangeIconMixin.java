package org.fourmur.weri.client.mixin.psycho;

import org.fourmur.weri.Weri;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.util.Identifier;

@Mixin(value = GameMenuScreen.class, priority = 2000)
public abstract class GameMenuScreenChangeIconMixin {
    @Shadow
    private static final Identifier watheIcon = Weri.id("textures/gui/wendigo.png");
}
