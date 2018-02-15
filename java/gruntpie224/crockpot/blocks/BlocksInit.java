package gruntpie224.crockpot.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gruntpie224.crockpot.util.RegisterUtil;

public class BlocksInit {
	
	@GameRegistry.ObjectHolder("cp:crockpotblock")
	public static CrockPotBlock crockpotblock;
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		crockpotblock.initModel();
	}
}
