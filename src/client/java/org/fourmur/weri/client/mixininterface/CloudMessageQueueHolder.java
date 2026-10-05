package org.fourmur.weri.client.mixininterface;

import org.fourmur.weri.client.cloudmessage.CloudMessageQueue;

import org.spongepowered.asm.mixin.Unique;

public interface CloudMessageQueueHolder {
    @Unique
    CloudMessageQueue weri$getQueue();
}
