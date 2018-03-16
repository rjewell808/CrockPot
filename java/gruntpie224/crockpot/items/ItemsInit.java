package gruntpie224.crockpot.items;

import java.util.ArrayList;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemsInit {
	private static ArrayList<CrockFood> all_items = new ArrayList<CrockFood>();
	
	@GameRegistry.ObjectHolder("cp:bacon_eggs")
	public static CrockFood bacon_eggs;
	
	@GameRegistry.ObjectHolder("cp:fish_sticks")
	public static CrockFood fish_sticks;
	
	@GameRegistry.ObjectHolder("cp:ice_cream")
	public static CrockFood ice_cream;
	
	@GameRegistry.ObjectHolder("cp:melonsicle")
	public static CrockFood melonsicle;
	
	@GameRegistry.ObjectHolder("cp:pumpkin_cookie")
	public static CrockFood pumpkin_cookie;
	
	@GameRegistry.ObjectHolder("cp:waffles")
	public static CrockFood waffles;
	
	@GameRegistry.ObjectHolder("cp:monster_lasagna")
	public static CrockFood monster_lasagna;
	
	@GameRegistry.ObjectHolder("cp:wet_goop")
	public static CrockFood wet_goop;
	
	@GameRegistry.ObjectHolder("cp:meat_balls")
	public static CrockFood meat_balls;
	
	@GameRegistry.ObjectHolder("cp:fist_of_jam")
	public static CrockFood fist_of_jam;
	
	//Saturation Reference
	//Rotten Flesh - 0.8 | Bread - 6.0 | Cooked Steak - 12.8
	
	//Potion Effect for healing
	//Starts at 2 hearts, 1 amplify gives 4 hearts
	public static void initItems()
	{
		bacon_eggs = new CrockFood("bacon_eggs", 10, 12.0f, true);
		all_items.add(bacon_eggs);
		
		fish_sticks = new CrockFood("fish_sticks", 4, 7.0f, false).setPotionEffect(new PotionEffect(Potion.getPotionById(6), 1, 1), 1.0f);
		all_items.add(fish_sticks);
		
		ice_cream = new CrockFood("ice_cream", 4, 8.0f, false);
		all_items.add(ice_cream);
		
		melonsicle = new CrockFood("melonsicle", 5, 5.0f, false);
		all_items.add(melonsicle);
		
		pumpkin_cookie = new CrockFood("pumpkin_cookie", 4, 7.0f, false);
		all_items.add(pumpkin_cookie);
		
		waffles = new CrockFood("waffles", 4, 7.5f, false).setPotionEffect(new PotionEffect(Potion.getPotionById(6), 1, 2), 1.0f);
		all_items.add(waffles);
		
		monster_lasagna = new CrockFood("monster_lasagna", 4, 5.0f, true).setPotionEffect(new PotionEffect(Potion.getPotionById(20), 20 * 5, 5), 0.8f);
		all_items.add(monster_lasagna);
		
		wet_goop = new CrockFood("wet_goop", 0, 0, false);
		all_items.add(wet_goop);
		
		meat_balls = new CrockFood("meat_balls", 9, 10f, true);
		all_items.add(meat_balls);
		
		fist_of_jam = new CrockFood("fist_of_jam", 5, 5.0f, false);
		all_items.add(fist_of_jam);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		for(CrockFood item : all_items)
			item.initModel();
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for(CrockFood item : all_items)
			event.getRegistry().register(item);
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
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Item.getItemFromBlock(Blocks.DRAGON_EGG), 4.0f, "egg"));
		
		//Fruits
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.APPLE, 0.5f, "fruit"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.GOLDEN_APPLE, 1.0f, "fruit"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.MELON, 0.5f, "fruit"));
		
		//Vegetables
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.CARROT, 0.5f, "vegetable"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.POTATO, 0.5f, "vegetable"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.BAKED_POTATO, 1.0f, "vegetable"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.BEETROOT, 0.5f, "vegetable"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Item.getItemFromBlock(Blocks.PUMPKIN), 1.0f, "vegetable"));
		
		//Sweeteners
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.SUGAR, 0.5f, "sweet"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.COOKIE, 1.0f, "sweet"));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.PUMPKIN_PIE, stats_n(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f)));
		
		//Dairy
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.MILK_BUCKET, 1.0f, "dairy"));
		
		//Misc
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Items.STICK));
		CrockContainerTileEntity.crock_foods.add(new CrockIngredient(Item.getItemFromBlock(Blocks.ICE)));
	}
	
	private static float[] stats_n(float...values)
	{
		for(int i = 0; i < values.length; i++)
			stats[i] = values[i];
		
		return stats;
	}
}
