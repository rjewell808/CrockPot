package gruntpie224.crockpot.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.ForgeRegistry;

public class RegisterUtil {
	
	public static void registerAll(FMLPreInitializationEvent event)
	{
		
	}
	
	@SubscribeEvent
	private static void registerBlocks(RegistryEvent.Register<Block> event, Block...blocks)
	{
		for(Block block : blocks){
			final ItemBlock itemblock = new ItemBlock(block);
			event.getRegistry().register(block);
			
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, 
					new ModelResourceLocation(
							block.getRegistryName()
							, "inventory"));
		}
	}
	
	private static void registerItems(RegistryEvent.Register<Item> event, Item...items)
	{
		for(Item item : items){
			event.getRegistry().register(item);
			
			ModelLoader.setCustomModelResourceLocation(item, 0, 
					new ModelResourceLocation(
							item.getRegistryName()
							, "inventory"));
		}
	}
}
