package org.fourmur.weri.client.mixin.cloudmessage;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.doctor4t.wathe.client.WatheClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = KeyBinding.class, priority = 2000)
public abstract class KeyBindingAllowChatOpeningMixin {
    @Shadow
    public abstract boolean equals(KeyBinding other);

    @Unique
    private boolean shouldSuppressKey() {
        if (WatheClient.isPlayerAliveAndInSurvival()) {
            return this.equals(MinecraftClient.getInstance().options.swapHandsKey)
                    || this.equals(MinecraftClient.getInstance().options.jumpKey)
                    || this.equals(MinecraftClient.getInstance().options.togglePerspectiveKey)
                    || this.equals(MinecraftClient.getInstance().options.dropKey)
                    || this.equals(MinecraftClient.getInstance().options.advancementsKey);
        }

        return false;
    }

    private boolean shouldHandleKey() {
        return this.equals(MinecraftClient.getInstance().options.chatKey)
                || this.equals(MinecraftClient.getInstance().options.commandKey);
    }

    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
    private boolean weri$restrainWasPressedKeys(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else if (this.shouldHandleKey()) return true;
        else return original;
    }

    @ModifyReturnValue(method = "isPressed", at = @At("RETURN"))
    private boolean weri$restrainIsPressedKeys(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else if (this.shouldHandleKey()) return true;
        else return original;
    }

    @ModifyReturnValue(method = "matchesKey", at = @At("RETURN"))
    private boolean weri$restrainMatchesKey(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else if (this.shouldHandleKey()) return true;
        else return original;
    }
}
