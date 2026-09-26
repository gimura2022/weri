package org.fourmur.weri.client.mixin.cloudmessage;

import org.fourmur.weri.client.CloudMessageQueue;
import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.network.AbstractClientPlayerEntity;

@Mixin(AbstractClientPlayerEntity.class)
public final class AbstractClientPlayerEntityMixin implements CloudMessageQueueHolder {
    @Unique
    private final CloudMessageQueue cloudMessageQueue = new CloudMessageQueue();

    @Unique
	@Override
	public CloudMessageQueue wathe_extended_rus_improved$getQueue() {
        return cloudMessageQueue;
	}
}
