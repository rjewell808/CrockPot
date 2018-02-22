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
	
	static float[] stats = new float[8];
	
	public static void initFoodValues()
	{
		//[0]meat - [1]monster - [2]fish - [3]egg - [4]fruit - [5]vegetable - [6]sweet - [7]dairy
		
		//Meats
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
		
		//Monster Foods
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.ROTTEN_FLESH, stats_n(1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.SPIDER_EYE, 1.0f, "monster"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.FERMENTED_SPIDER_EYE, 1.0f, "monster"));
		
		//Fish
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.FISH, stats_n(0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f)));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKED_FISH, stats_n(0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f)));
		
		//Eggs
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.EGG, 1.0f, "egg"));
		
		//Fruits
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.APPLE, 0.5f, "fruit"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.GOLDEN_APPLE, 1.0f, "fruit"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.MELON, 0.5f, "fruit"));
		
		//Vegetables
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.CARROT, 0.5f, "vegetable"));
		
		//Sweeteners
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.SUGAR, 0.5f, "sweet"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKIE, 1.0f, "sweet"));
		
		//Dairy
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.MILK_BUCKET, 1.0f, "dairy"));
	}
	
	private static float[] stats_n(float...values)
	{
		for(int i = 0; i < values.length; i++)
			stats[i] = values[i];
		
		return stats;
	}
}
