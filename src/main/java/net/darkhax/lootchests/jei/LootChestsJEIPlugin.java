package net.darkhax.lootchests.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ISubtypeRegistry.ISubtypeInterpreter;
import mezz.jei.api.JEIPlugin;
import net.darkhax.lootchests.handler.LootHandler;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class LootChestsJEIPlugin extends BlankModPlugin {
    
    private static final ISubtypeInterpreter SUBTYPE_CHESTS = new SubtypeInterpreterChests();
    
    @Override
    public void registerItemSubtypes (ISubtypeRegistry subtypeRegistry) {
        
        for (final ItemStack stack : LootHandler.chests)
            subtypeRegistry.registerNbtInterpreter(stack.getItem(), SUBTYPE_CHESTS);
    }
    
    public static class SubtypeInterpreterChests implements ISubtypeInterpreter {
        
        @Override
        public String getSubtypeInfo (ItemStack itemStack) {
            
            return itemStack.getDisplayName() + itemStack.getMetadata();
        }
    }
}