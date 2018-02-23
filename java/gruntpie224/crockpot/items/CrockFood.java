package gruntpie224.crockpot.items;

import gruntpie224.crockpot.CrockPot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrockFood extends ItemFood{
	public CrockFood(String name, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		setRegistryName(name);
		setUnlocalizedName(CrockPot.MODID + "." + name);
		setCreativeTab(CreativeTabs.FOOD);
	}
	
	@Override
	public CrockFood setPotionEffect(PotionEffect effect, float probability)
    {
        super.setPotionEffect(effect, probability);
        return this;
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
