package gruntpie224.crockpot.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemsInit {
	@GameRegistry.ObjectHolder("cp:bacon_eggs")
	public static CrockFood bacon_eggs;
	
	public static void initItems()
	{
		bacon_eggs = new CrockFood("bacon_eggs");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		bacon_eggs.initModel();
	}
}
