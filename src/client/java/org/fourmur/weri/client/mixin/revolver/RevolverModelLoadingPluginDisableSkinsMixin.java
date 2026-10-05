package org.fourmur.weri.client.mixin.revolver;

import dev.doctor4t.wathe.client.model.item.RevolverModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.Context;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RevolverModelLoadingPlugin.class)
public abstract class RevolverModelLoadingPluginDisableSkinsMixin {
    @Overwrite
    public void onInitializeModelLoader(Context pluginContext) {}
}
