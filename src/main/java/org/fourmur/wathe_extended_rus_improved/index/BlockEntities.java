package org.fourmur.wathe_extended_rus_improved.index;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.fourmur.wathe_extended_rus_improved.block.entity.StatueBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class BlockEntities {
    public static final BlockEntityType<StatueBlockEntity> STATUE =
        register("statue", StatueBlockEntity::new, Blocks.STATUE);

    public static void initialize() {}

    public static <T extends BlockEntity> BlockEntityType<T> register(
        String key,
        BlockEntityType.BlockEntityFactory<? extends T> entityFactory,
        Block... blocks
    ) {
        Identifier id = WatheExtendedRusImproved.id(key);

        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.<T>create(entityFactory, blocks).build());
    }
}
