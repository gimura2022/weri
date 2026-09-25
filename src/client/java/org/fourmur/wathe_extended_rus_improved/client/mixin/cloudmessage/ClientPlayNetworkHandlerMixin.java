package org.fourmur.wathe_extended_rus_improved.client.mixin.cloudmessage;

import java.time.Instant;

import org.fourmur.wathe_extended_rus_improved.client.mixin.ClientCommonNetworkHandlerAccessor;
import org.fourmur.wathe_extended_rus_improved.network.CloudMessageServerboundPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.encryption.NetworkEncryptionUtils.SecureRandomUtil;
import net.minecraft.network.message.LastSeenMessagesCollector;
import net.minecraft.network.message.LastSeenMessagesCollector.LastSeenMessages;
import net.minecraft.network.message.MessageBody;
import net.minecraft.network.message.MessageChain.Packer;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(ClientPlayNetworkHandler.class)
public final class ClientPlayNetworkHandlerMixin {
    @Shadow
	private LastSeenMessagesCollector lastSeenMessagesCollector;

    @Shadow
	private Packer messagePacker;

    @Unique
    private final ClientPlayNetworkHandler networkHandler = (ClientPlayNetworkHandler) (Object) this;

    @Unique
    private final ClientCommonNetworkHandlerAccessor networkHandlerAccessor =
        (ClientCommonNetworkHandlerAccessor) networkHandler;

    @Overwrite
	public void sendChatMessage(String content) {
        MinecraftClient client = networkHandlerAccessor.wathe_extended_rus_improved$getClient();

        if (!client.interactionManager.getCurrentGameMode().isSurvivalLike()) {
            Instant instant = Instant.now();
            long l = SecureRandomUtil.nextLong();
            LastSeenMessages lastSeenMessages = this.lastSeenMessagesCollector.collect();
            MessageSignatureData messageSignatureData = this.messagePacker.pack(new MessageBody(content, instant, l, lastSeenMessages.lastSeen()));
            networkHandler.sendPacket(new ChatMessageC2SPacket(content, instant, l, messageSignatureData, lastSeenMessages.update()));

            return;
        }

	    ClientPlayNetworking.send(new CloudMessageServerboundPayload(content));
        client.player.sendMessage(Text.literal(content).formatted(Formatting.ITALIC));
    }
}
