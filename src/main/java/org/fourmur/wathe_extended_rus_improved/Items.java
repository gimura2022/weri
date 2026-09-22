package org.fourmur.wathe_extended_rus_improved;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class Items {
    public static final Item CANDLE = register("candle", new Item(new Item.Settings()));

    public static final RegistryKey<ItemGroup> MOD_CREATIVE_TAB_KEY = RegistryKey.of(
        Registries.ITEM_GROUP.getKey(),
        WatheExtendedRusImproved.id("items")
    );
    public static final ItemGroup MOD_CREATIVE_TAB = FabricItemGroup.builder()
        .icon(() -> new ItemStack(CANDLE))
        .displayName(Text.translatable("itemGroup.wathe-extended-rus-improved.items"))
        .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, MOD_CREATIVE_TAB_KEY, MOD_CREATIVE_TAB);

        ItemGroupEvents.modifyEntriesEvent(MOD_CREATIVE_TAB_KEY).register(itemGroup -> {
            itemGroup.add(CANDLE);
        });
    }

    public static Item register(String key, Item item) {
        Identifier id = WatheExtendedRusImproved.id(key);
        return Registry.register(Registries.ITEM, id, item);
    }
}
