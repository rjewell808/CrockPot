package gruntpie224.crockpot.items;

import gruntpie224.crockpot.CrockPot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrockFood extends Item{
	public CrockFood(String name)
	{
		setRegistryName(name);
		setUnlocalizedName(CrockPot.MODID + "." + name);
		setCreativeTab(CreativeTabs.FOOD);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
