package org.fourmur.weri.init;

import net.minecraft.network.codec.PacketCodecs;

import org.fourmur.weri.Weri;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public final class WeriAttachments {
    public static final AttachmentType<Boolean> FORCE_WENDIGO_MODEL =
            AttachmentRegistry.create(Weri.id("force_wendigo_model"), builder -> builder.initializer(() -> false)
                    .syncWith(PacketCodecs.BOOL, AttachmentSyncPredicate.all()));

    public static void initialize() {}
}
