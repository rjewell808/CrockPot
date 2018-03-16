package gruntpie224.crockpot.items;

import java.util.List;

import javax.annotation.Nullable;

import gruntpie224.crockpot.CrockPot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrockFood extends ItemFood{
	
	private int food_amount;
	
	public CrockFood(String name, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		this.food_amount = amount;
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
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("Fills " + this.food_amount + " Hunger");
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
