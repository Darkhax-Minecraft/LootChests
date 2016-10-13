package net.darkhax.lootchests.tabs;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.lootchests.handler.LootHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabLoot extends CreativeTabs {
    
    private static List<ItemStack> cache = new ArrayList<>();
    
    public CreativeTabLoot() {
        
        super("lootchests");
        
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    public Item getTabIconItem () {
        
        return Items.SKULL;
    }
    
    @Override
    public ItemStack getIconItemStack () {
        
        return new ItemStack(Blocks.CHEST, 1, 0);
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
    
    @Override
    public void displayAllRelevantItems (List<ItemStack> itemList) {
        
        if (LootHandler.isDirty)
            LootHandler.buildItemList();
        
        itemList.addAll(LootHandler.itemList);
    }
}