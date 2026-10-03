package org.fourmur.weri.index;

import org.fourmur.weri.Weri;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;

public final class Attachments {
    public static final AttachmentType<Boolean> FORCE_BERSERK_MODEL = AttachmentRegistry.create(
        Weri.id("force_berserk_model"),
        builder -> builder
            .initializer(() -> false)
            .syncWith(PacketCodecs.BOOL, AttachmentSyncPredicate.all())
    );

    public static void initialize() {}
}
