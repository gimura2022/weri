package org.fourmur.wathe_extended_rus_improved.client.mixininterface;

import org.fourmur.wathe_extended_rus_improved.client.CloudMessageQueue;
import org.spongepowered.asm.mixin.Unique;

public interface CloudMessageQueueHolder {
    @Unique
    CloudMessageQueue wathe_extended_rus_improved$getQueue();
}
