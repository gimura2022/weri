package org.fourmur.wathe_extended_rus_improved.mixin;

import org.fourmur.wathe_extended_rus_improved.CloudMessagePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;

@Mixin(ClientPlayNetworkHandler.class)
public final class CloudMessageSendingMixin {
    @Overwrite
	public void sendChatMessage(String content) {
	    ClientPlayNetworking.send(new CloudMessagePacket.ServerboundPayload(content));
    }
}
