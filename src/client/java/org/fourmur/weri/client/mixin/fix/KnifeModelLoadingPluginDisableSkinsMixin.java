package org.fourmur.weri.client.mixin.fix;

import dev.doctor4t.wathe.client.model.item.KnifeModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.Context;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(KnifeModelLoadingPlugin.class)
public abstract class KnifeModelLoadingPluginDisableSkinsMixin {
    @Overwrite
    public void onInitializeModelLoader(Context pluginContext) {}
}
