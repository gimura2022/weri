package org.fourmur.wathe_extended_rus_improved.mixininterface;

import org.fourmur.wathe_extended_rus_improved.CloudMessageQueue;
import org.spongepowered.asm.mixin.Unique;

public interface CloudMessageQueueHolder {
    @Unique
    CloudMessageQueue wathe_extended_rus_improved$getQueue();
}
