package net.darkhax.lootchests;

import net.darkhax.lootchests.handler.LootHandler;
import net.darkhax.lootchests.libs.Constants;
import net.darkhax.lootchests.tabs.CreativeTabLoot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER)
public class LootChests {
    
    @Instance(Constants.MOD_ID)
    public static LootChests instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        new CreativeTabLoot();
        MinecraftForge.EVENT_BUS.register(new LootHandler());
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
        
        LootHandler.registerChest("minecraft:chest", 0);
        LootHandler.registerChest("minecraft:trapped_chest", 0);
        
        if (Loader.isModLoaded("Quark")) {
            
            LootHandler.registerChest("quark:custom_chest", 0);
            LootHandler.registerChest("quark:custom_chest", 1);
            LootHandler.registerChest("quark:custom_chest", 2);
            LootHandler.registerChest("quark:custom_chest", 3);
            LootHandler.registerChest("quark:custom_chest", 4);
        }
        
        if (Loader.isModLoaded("darkutils")) {
            
            LootHandler.registerChest("darkutils:chest_glacial", 0);
            LootHandler.registerChest("darkutils:chest_glass", 0);
            LootHandler.registerChest("darkutils:chest_jungle", 0);
            LootHandler.registerChest("darkutils:chest_magic", 0);
            LootHandler.registerChest("darkutils:chest_nether", 0);
            LootHandler.registerChest("darkutils:chest_royal", 0);
            LootHandler.registerChest("darkutils:chest_sandstone", 0);
            LootHandler.registerChest("darkutils:chest_prismarine", 0);
        }
    }
}