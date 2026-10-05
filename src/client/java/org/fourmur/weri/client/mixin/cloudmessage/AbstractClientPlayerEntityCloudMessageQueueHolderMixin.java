package org.fourmur.weri.client.mixin.cloudmessage;

import net.minecraft.client.network.AbstractClientPlayerEntity;

import org.fourmur.weri.client.cloudmessage.CloudMessageQueue;
import org.fourmur.weri.client.mixininterface.CloudMessageQueueHolder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityCloudMessageQueueHolderMixin implements CloudMessageQueueHolder {
    @Unique
    private final CloudMessageQueue cloudMessageQueue = new CloudMessageQueue();

    @Unique
    @Override
    public CloudMessageQueue weri$getQueue() {
        return cloudMessageQueue;
    }
}
