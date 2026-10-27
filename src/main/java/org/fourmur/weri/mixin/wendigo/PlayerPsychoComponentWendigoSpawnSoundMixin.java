package org.fourmur.weri.mixin.wendigo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

import org.fourmur.weri.init.WeriSounds;

import dev.doctor4t.wathe.cca.PlayerPsychoComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerPsychoComponent.class)
public abstract class PlayerPsychoComponentWendigoSpawnSoundMixin {
    @Shadow
    @Final
    private PlayerEntity player;

    @Inject(method = "startPsycho", at = @At(value = "HEAD", target = "setPsychoTicks"))
    private void playSpawnSound(CallbackInfoReturnable<Boolean> cir) {
        if (this.player.getWorld() instanceof ServerWorld serverWorld)
            for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                player.networkHandler.sendPacket(new PlaySoundS2CPacket(
                        Registries.SOUND_EVENT.getEntry(WeriSounds.WENDIGO_SPAWN),
                        SoundCategory.PLAYERS,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        1f,
                        1f,
                        player.getRandom().nextLong()));
            }
    }
}
