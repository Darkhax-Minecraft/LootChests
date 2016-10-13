package net.darkhax.lootchests.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LootHandler {
    
    public static List<String> tables = new ArrayList<>();
    public static List<ItemStack> itemList = new ArrayList<>();
    public static List<ItemStack> chests = new ArrayList<>();
    public static boolean isDirty;
    
    @SubscribeEvent
    public void onLootTableLoaded (LootTableLoadEvent event) {
        
        final String name = event.getName().toString();
        if (!tables.contains(name)) {
            
            tables.add(name);
            isDirty = true;
        }
    }
    
    public static void buildItemList () {
        
        itemList.clear();
        
        for (final String table : tables)
            for (final ItemStack chestStack : chests) {
                
                final ItemStack stack = chestStack.copy();
                final NBTTagCompound tag = new NBTTagCompound();
                final NBTTagCompound tileTag = new NBTTagCompound();
                tileTag.setString("LootTable", table);
                tag.setTag("BlockEntityTag", tileTag);
                stack.setTagCompound(tag);
                setLore(stack, new String[] { table });
                itemList.add(stack);
            }
        
        isDirty = false;
    }
    
    public static ItemStack setLore (ItemStack stack, String[] lore) {
        
        final NBTTagCompound tag = stack.getTagCompound();
        final NBTTagList loreList = new NBTTagList();
        
        if (!tag.hasKey("display", 10))
            tag.setTag("display", new NBTTagCompound());
        
        for (final String line : lore)
            loreList.appendTag(new NBTTagString(line));
        
        tag.getCompoundTag("display").setTag("Lore", loreList);
        stack.setTagCompound(tag);
        
        return stack;
    }
    
    public static void registerChest (String id, int meta) {
        
        final Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        
        if (block != null)
            chests.add(new ItemStack(block, 1, meta));
    }
}