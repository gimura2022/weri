package org.fourmur.wathe_extended_rus_improved.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;

@Mixin(EntityRenderer.class)
public interface EntityRendererAccessor {
    @Accessor("dispatcher")
    EntityRenderDispatcher wathe_extended_rus_improved$getDispatcher();
}
