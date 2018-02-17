package gruntpie224.crockpot.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gruntpie224.crockpot.util.RegisterUtil;

public class BlocksInit {
	
	@GameRegistry.ObjectHolder("cp:crockpotblock")
	public static CrockPotBlock crockpot;
	
	@GameRegistry.ObjectHolder("cp:crockpot_cooking")
	public static CrockPotBlock crockpot_cooking;
	
	public static void initBlocks(){
		crockpot = new CrockPotBlock(false);
		crockpot_cooking = new CrockPotBlock(true);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		crockpot.initModel();
		crockpot_cooking.initModel();
	}
}
