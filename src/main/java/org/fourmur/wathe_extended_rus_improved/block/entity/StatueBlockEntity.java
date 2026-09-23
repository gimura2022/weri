package org.fourmur.wathe_extended_rus_improved.block.entity;

import java.util.UUID;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.fourmur.wathe_extended_rus_improved.index.BlockEntities;

import com.mojang.authlib.GameProfile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.math.BlockPos;

public final class StatueBlockEntity extends BlockEntity {
    private PlayerEntity playerEntity;

	public StatueBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.STATUE, pos, state);
	}

	@Override
	protected void writeNbt(NbtCompound nbt, WrapperLookup registryLookup) {
        nbt.putUuid("profile", playerEntity.getUuid());

		super.writeNbt(nbt, registryLookup);
	}

	@Override
	protected void readNbt(NbtCompound nbt, WrapperLookup registryLookup) {
		super.readNbt(nbt, registryLookup);

        playerEntity = getPlayerEntity(nbt.getUuid("profile"));

        WatheExtendedRusImproved.LOGGER.debug(playerEntity.toString());
	}

	public PlayerEntity getPlayerEntity() {
		return playerEntity;
	}

    private PlayerEntity getPlayerEntity(UUID profile) {
        return new PlayerEntity(getWorld(), getPos(), 0.0f, new GameProfile(profile, "<statue profile>")) {
			@Override
			public boolean isSpectator() {
                return false;
			}

			@Override
			public boolean isCreative() {
                return false;
			}
        };
    }
}
