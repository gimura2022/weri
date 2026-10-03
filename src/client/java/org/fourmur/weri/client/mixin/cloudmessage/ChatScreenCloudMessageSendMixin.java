package org.fourmur.weri.client.mixin.cloudmessage;

import org.fourmur.weri.client.mixin.accessor.ScreenAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;

@Mixin(ChatScreen.class)
public final class ChatScreenCloudMessageSendMixin {
    private static final int MAX_MESSAGE_LENGTH = 48;

    @Unique
    private final ScreenAccessor screenAccessor = (ScreenAccessor) (Object) this;

    @WrapOperation(method = "sendMessage", at = @At(value = "INVOKE", target = "sendChatMessage"))
    private void sendCloudMessage(ClientPlayNetworkHandler instance, String chatText, Operation<Void> original) {
        MinecraftClient client = screenAccessor.weri$getClient();

        if (chatText.length() > MAX_MESSAGE_LENGTH
                && !client.player.isCreative())
            client.player.sendMessage(Text.translatable("chat.weri.message_too_long"));
        else
            original.call(client.player.networkHandler, chatText);
    }
}
