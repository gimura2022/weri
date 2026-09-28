package org.fourmur.weri.init;

import org.fourmur.weri.Weri;
import org.fourmur.weri.datagen.WeriJukeboxSongProvider;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class WeriItems {
    public static final Item CANDLE = register("candle", new Item(new Item.Settings().maxCount(1)));

    public static final Item WHITE_NOIZ_MUSIC_DISC = registerMusicDisc(WeriJukeboxSongProvider.WHITE_NOIZ_KEY);
    public static final Item ORDINARY_VANITY_MUSIC_DISC = registerMusicDisc(WeriJukeboxSongProvider.ORDINARY_VANITY);

    public static final RegistryKey<ItemGroup> MOD_CREATIVE_TAB_KEY = RegistryKey.of(
        Registries.ITEM_GROUP.getKey(),
        Weri.id("items")
    );
    public static final ItemGroup MOD_CREATIVE_TAB = FabricItemGroup.builder()
        .icon(() -> new ItemStack(CANDLE))
        .displayName(Text.translatable("itemGroup.weri.items"))
        .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, MOD_CREATIVE_TAB_KEY, MOD_CREATIVE_TAB);

        ItemGroupEvents.modifyEntriesEvent(MOD_CREATIVE_TAB_KEY).register(itemGroup -> {
            itemGroup.add(CANDLE);

            itemGroup.add(WHITE_NOIZ_MUSIC_DISC);
            itemGroup.add(ORDINARY_VANITY_MUSIC_DISC);
        });
    }

    private static Item registerMusicDisc(RegistryKey<JukeboxSong> key) {
        String songName = key.getValue().getPath();

        return register(songName + "_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(key).maxCount(1)));
    }

    private static Item register(String key, Item item) {
        Identifier id = Weri.id(key);
        return Registry.register(Registries.ITEM, id, item);
    }
}
