package org.fourmur.wathe_extended_rus_improved.block.entity;

import org.fourmur.wathe_extended_rus_improved.index.BlockEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public final class StatueBlockEntity extends BlockEntity {
	public StatueBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.STATUE, pos, state);
	}
}
