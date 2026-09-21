package org.fourmur.wathe_extended_rus_improved.mixin;

import org.fourmur.wathe_extended_rus_improved.CloudMessageQueue;
import org.fourmur.wathe_extended_rus_improved.mixininterface.CloudMessageQueueHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.network.AbstractClientPlayerEntity;

@Mixin(AbstractClientPlayerEntity.class)
public final class AbstractClientPlayerEntityCloudMessageQueueHolder implements CloudMessageQueueHolder {
    @Unique
    private final CloudMessageQueue cloudMessageQueue = new CloudMessageQueue();

    @Unique
	@Override
	public CloudMessageQueue wathe_extended_rus_improved$getQueue() {
        return cloudMessageQueue;
	}

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo info) {
        cloudMessageQueue.tick();
    }
}
