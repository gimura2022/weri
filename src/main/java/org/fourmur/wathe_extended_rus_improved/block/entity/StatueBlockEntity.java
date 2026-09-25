package org.fourmur.wathe_extended_rus_improved.block.entity;

import java.util.UUID;

import org.fourmur.wathe_extended_rus_improved.index.BlockEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.math.BlockPos;

public final class StatueBlockEntity extends BlockEntity {
    private UUID profileId = UUID.fromString("6430ff8e-977e-4137-bbb9-b2733b32c6a4");

	public StatueBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.STATUE, pos, state);
	}

	@Override
	protected void writeNbt(NbtCompound nbt, WrapperLookup registryLookup) {
        nbt.putUuid("profile", profileId);

		super.writeNbt(nbt, registryLookup);
	}

	@Override
	protected void readNbt(NbtCompound nbt, WrapperLookup registryLookup) {
		super.readNbt(nbt, registryLookup);

        profileId = nbt.getUuid("profile");
	}

    public UUID getProfileId() {
        return profileId;
    }
}
