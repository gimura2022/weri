package org.fourmur.wathe_extended_rus_improved.index;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.fourmur.wathe_extended_rus_improved.block.StatueBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class Blocks {
    public static final Block STATUE = register("statue", new StatueBlock(AbstractBlock.Settings.create()), true);

    public static void initialize() {}

    public static Block register(String key, Block block, boolean registerItem) {
        Identifier id = WatheExtendedRusImproved.id(key);

        if (registerItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}
