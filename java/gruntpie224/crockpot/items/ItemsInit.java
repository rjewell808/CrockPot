package gruntpie224.crockpot.items;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.init.Items;
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
	
	static float[] stats = new float[7];
	
	public static void initFoodValues()
	{
		//[0]Meat - [1]Monster - [2]Fish - [3]Eggs - [4]Fruit - [5]Vegetables - [6]Sweetners
		
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.BEEF, 0.5f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_BEEF, 1.0f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.PORKCHOP, 0.5f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_PORKCHOP, 1.0f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.CHICKEN, 0.5f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_CHICKEN, 1.0f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.MUTTON, 0.5f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_MUTTON, 1.0f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.RABBIT, 0.5f, "meat"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_RABBIT, 1.0f, "meat"));
		
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.ROTTEN_FLESH, stats_n(1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.SPIDER_EYE, 1.0f, "monster"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.FERMENTED_SPIDER_EYE, 1.0f, "monster"));
		
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.FISH, stats_n(0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f)));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_FISH, stats_n(0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f)));
		
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.EGG, 1.0f, "egg"));
	}
	
	private static float[] stats_n(float...values)
	{
		for(int i = 0; i < values.length; i++)
			stats[i] = values[i];
		
		return stats;
	}
}
