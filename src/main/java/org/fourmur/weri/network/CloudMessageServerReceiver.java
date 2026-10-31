package org.fourmur.weri.network;

import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import org.fourmur.weri.init.WeriNetwork;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public final record CloudMessageServerReceiver(
        CloudMessageServerboundPayload payload, ServerPlayNetworking.Context context) implements Runnable {
    public static final double SENDING_RADIUS = 10.0;

    public static final WeriNetwork.ServerReceiverInfo<CloudMessageServerboundPayload, CloudMessageServerReceiver>
            RECEIVER_INFO = new WeriNetwork.ServerReceiverInfo<>(
                    CloudMessageServerboundPayload.PAYLOAD_INFO, CloudMessageServerReceiver::new);

    @Override
    public void run() {
        ServerPlayerEntity sender = context.player();

        if (sender == null) return;

        for (ServerPlayerEntity player :
                PlayerLookup.around(sender.getServerWorld(), sender.getPos(), SENDING_RADIUS)) {
            ServerPlayNetworking.send(player, new CloudMessageClientboundPayload(payload.message(), sender.getUuid()));

            player.networkHandler.sendPacket(new PlaySoundS2CPacket(
                    Registries.SOUND_EVENT.getEntry(SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT),
                    SoundCategory.PLAYERS,
                    sender.getX(),
                    sender.getY(),
                    sender.getZ(),
                    1f,
                    1f,
                    player.getRandom().nextLong()));
        }
    }
}
