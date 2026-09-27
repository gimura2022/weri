package org.fourmur.wathe_extended_rus_improved.index;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;

public final class Attachments {
    public static final AttachmentType<Boolean> FORCE_BERSERK_MODEL = AttachmentRegistry.create(
        WatheExtendedRusImproved.id("force_berserk_model"),
        builder -> builder
            .initializer(() -> false)
            .syncWith(PacketCodecs.BOOL, AttachmentSyncPredicate.all())
    );

    public static void initialize() {}
}
