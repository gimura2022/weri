package org.fourmur.wathe_extended_rus_improved;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class Items {
    public static final Item CANDLE = register("candle", new Item(new Item.Settings()));

    public static void initialize() {}

    public static Item register(String key, Item item) {
        Identifier id = WatheExtendedRusImproved.id(key);
        return Registry.register(Registries.ITEM, id, item);
    }
}
