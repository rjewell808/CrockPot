package gruntpie224.crockpot.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
	}
	
	@Override
	public void serverStarting(FMLServerStartingEvent event){
		super.serverStarting(event);
	}
	
	@Override
	public void serverStopping(FMLServerStoppingEvent event){
		super.serverStopping(event);
	}
}
