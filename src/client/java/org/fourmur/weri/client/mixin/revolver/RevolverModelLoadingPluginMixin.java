package org.fourmur.weri.client.mixin.revolver;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import dev.doctor4t.wathe.client.model.item.RevolverModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.Context;

@Mixin(RevolverModelLoadingPlugin.class)
public final class RevolverModelLoadingPluginMixin {
    @Overwrite
    public void onInitializeModelLoader(Context pluginContext) {}
}
