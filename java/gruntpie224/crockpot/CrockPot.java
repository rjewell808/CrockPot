package gruntpie224.crockpot;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import gruntpie224.crockpot.proxy.*;

@Mod(modid = CrockPot.MODID, name = CrockPot.NAME, version = CrockPot.VERSION, dependencies="required-after:forge@[14.23.2.2655,)", useMetadata=true)

public class CrockPot
{
    public static final String MODID = "cp";
    public static final String NAME = "Crock Pot Mod";
    public static final String VERSION = "0.1.0";
    public static final String CLIENT_PROXY = "gruntpie224.crockpot.proxy.ClientProxy";
    public static final String COMMON_PROXY = "gruntpie224.crockpot.proxy.CommonProxy";
    
    @SidedProxy(clientSide = CrockPot.CLIENT_PROXY, serverSide = CrockPot.COMMON_PROXY)
    public static CommonProxy proxy;
    
    @Mod.Instance
    public static CrockPot instance;
    
    private static Logger logger = LogManager.getLogger(MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger.info("Starting Pre-Init...");
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	logger.info("Starting Init...");
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	logger.info("Starting Post-Init...");
    	proxy.postInit(event);
    }
}
