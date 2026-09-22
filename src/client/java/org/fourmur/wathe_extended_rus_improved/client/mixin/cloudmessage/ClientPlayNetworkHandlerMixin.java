package org.fourmur.wathe_extended_rus_improved.client.mixin.cloudmessage;

import org.fourmur.wathe_extended_rus_improved.network.CloudMessageServerboundPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(ClientPlayNetworkHandler.class)
public final class ClientPlayNetworkHandlerMixin {
    @Overwrite
	public void sendChatMessage(String content) {
	    ClientPlayNetworking.send(new CloudMessageServerboundPayload(content));
        MinecraftClient.getInstance().player.sendMessage(Text.literal(content).formatted(Formatting.ITALIC));
    }
}
