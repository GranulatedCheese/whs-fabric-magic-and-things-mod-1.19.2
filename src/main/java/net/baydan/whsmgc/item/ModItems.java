package net.baydan.whsmgc.item;

import net.baydan.whsmgc.MagicAndThings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    
    public static final Item TEST_ITEM = registerItem("test_item", 
        new Item(new FabricItemSettings().group(ItemGroup.MISC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MagicAndThings.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MagicAndThings.LOGGER.debug("Registering Mod Items for " + MagicAndThings.MOD_ID);
    }
}
