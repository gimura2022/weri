package org.fourmur.weri.client.mixin.cloudmessage;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;

import org.fourmur.weri.client.mixin.accessor.ScreenAccessor;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.doctor4t.wathe.cca.PlayerPsychoComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChatScreen.class)
public abstract class ChatScreenCloudMessageSendMixin {
    private static final int MAX_MESSAGE_LENGTH = 48;

    @Unique
    private final ScreenAccessor screenAccessor = (ScreenAccessor) (Object) this;

    // spotless:off
    @WrapOperation(
        method = "sendMessage",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendChatMessage(Ljava/lang/String;)V"
        )
    )
    // spotless:on
    private void sendCloudMessage(ClientPlayNetworkHandler instance, String chatText, Operation<Void> original) {
        MinecraftClient client = screenAccessor.weri$getClient();

        if (chatText.length() > MAX_MESSAGE_LENGTH && !client.player.isCreative())
            client.player.sendMessage(Text.translatable("chat.weri.message_too_long"));
        else if (PlayerPsychoComponent.KEY.get(client.player).getPsychoTicks() > 0 && !client.player.isCreative())
            client.player.sendMessage(Text.translatable("chat.wathe-extended-rus-improved.psycho_mode_active"));
        else original.call(client.player.networkHandler, chatText);
    }
}
