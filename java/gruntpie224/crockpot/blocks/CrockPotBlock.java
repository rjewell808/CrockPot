package gruntpie224.crockpot.blocks;

import gruntpie224.crockpot.CrockPot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class CrockPotBlock extends Block{
	public CrockPotBlock()
	{
		super(Material.ANVIL);
		setUnlocalizedName(CrockPot.MODID + ".crockpotblock");
		setRegistryName("crockpotblock");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
}
