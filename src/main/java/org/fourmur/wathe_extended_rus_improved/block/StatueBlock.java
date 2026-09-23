package org.fourmur.wathe_extended_rus_improved.block;

import org.fourmur.wathe_extended_rus_improved.block.entity.StatueBlockEntity;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public final class StatueBlock extends BlockWithEntity {
    public static final MapCodec<StatueBlock> CODEC = createCodec(StatueBlock::new);

	public StatueBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StatueBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
	}
}
