package org.fourmur.wathe_extended_rus_improved.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;

@Mixin(ChatScreen.class)
public final class CloudMessageMaxLengthMixin {
    @Shadow
    private TextFieldWidget chatField;

    @Inject(method = "render", at = @At(value = "TAIL"))
    private void init(CallbackInfo info) {
	    this.chatField.setMaxLength(32);
    }
}
