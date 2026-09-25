package org.fourmur.wathe_extended_rus_improved.client.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Screen.class)
public interface ScreenAccessor {
    @Accessor("client")
    MinecraftClient wathe_extended_rus_improved$getClient();
}
