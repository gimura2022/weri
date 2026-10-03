package org.fourmur.weri.client.mixin.cloudmessage;

import org.fourmur.weri.client.mixin.accessor.ClientCommonNetworkHandlerAccessor;
import org.fourmur.weri.network.CloudMessageServerboundPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(ClientPlayNetworkHandler.class)
public final class ClientPlayNetworkHandlerCloudMessageSendMixin {
    @Unique
    private final ClientCommonNetworkHandlerAccessor networkHandlerAccessor =
        (ClientCommonNetworkHandlerAccessor) (Object) this;

    @WrapMethod(method = "sendChatMessage")
    private void sendCloudMessage(String content, Operation<Void> original) {
        MinecraftClient client = networkHandlerAccessor.weri$getClient();

        if (client.interactionManager.getCurrentGameMode().isSurvivalLike()) {
            ClientPlayNetworking.send(new CloudMessageServerboundPayload(content));
            client.player.sendMessage(Text.literal(content).formatted(Formatting.ITALIC));
        } else
            original.call(content);
    }
}
