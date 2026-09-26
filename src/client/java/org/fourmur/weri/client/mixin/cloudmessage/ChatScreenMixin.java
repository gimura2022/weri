package org.fourmur.weri.client.mixin.cloudmessage;

import org.fourmur.weri.client.mixin.accessor.ScreenAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Text;

@Mixin(ChatScreen.class)
public final class ChatScreenMixin {
    private static final int MAX_MESSAGE_LENGTH = 48;

    @Unique
    private final ChatScreen chatScreen = (ChatScreen) (Object) this;

    @Unique
    private final ScreenAccessor screenAccessor = (ScreenAccessor) chatScreen;

    @Overwrite
	public void sendMessage(String chatText, boolean addToHistory) {
        MinecraftClient client = screenAccessor.wathe_extended_rus_improved$getClient();
		chatText = chatScreen.normalize(chatText);

		if (!chatText.isEmpty()) {
			if (addToHistory) {
				client.inGameHud.getChatHud().addToMessageHistory(chatText);
			}

			if (chatText.startsWith("/")) {
				client.player.networkHandler.sendChatCommand(chatText.substring(1));
			} else {
                if (chatText.length() > MAX_MESSAGE_LENGTH && !client.player.isCreative()) {
                    client.player.sendMessage(Text.translatable("chat.weri.message_too_long"));
                    return;
                }

				client.player.networkHandler.sendChatMessage(chatText);
			}
		}
	}
}
