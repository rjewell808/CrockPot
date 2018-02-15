package gruntpie224.crockpot.proxy;

import gruntpie224.crockpot.CrockPot;
import gruntpie224.crockpot.blocks.BlocksInit;
import gruntpie224.crockpot.blocks.CrockPotBlock;
import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		
	}
	public void init(FMLInitializationEvent event){}
	public void postInit(FMLPostInitializationEvent event){}
	public void serverStarting(FMLServerStartingEvent event){}
	public void serverStopping(FMLServerStoppingEvent event){}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new CrockPotBlock());
		
		GameRegistry.registerTileEntity(CrockContainerTileEntity.class, CrockPot.MODID + "_crockpotblock");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemBlock(BlocksInit.crockpotblock).setRegistryName(BlocksInit.crockpotblock.getRegistryName()));
	}
}
