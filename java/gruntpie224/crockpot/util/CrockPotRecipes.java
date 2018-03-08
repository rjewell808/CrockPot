package gruntpie224.crockpot.util;

import java.util.ArrayList;

import gruntpie224.crockpot.items.ItemsInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CrockPotRecipes {
	NonNullList<ItemStack> items;
	float[] food_values = new float[8];
	
	public CrockPotRecipes(NonNullList<ItemStack> items, float[] food_values)
	{
		this.items = items;
		
		for(int i = 0; i < food_values.length; i++)
		{
			this.food_values[i] = food_values[i];
			System.out.println(i + " = " + food_values[i]);
		}
	}
	
	//[0]meat - [1]monster - [2]fish - [3]egg - [4]fruit - [5]vegetable - [6]sweet - [7]dairy
	
	public ItemStack getRecipeResult()
	{
		
		//Priority 10
		
		if(numberOf(Items.STICK) == 1 && numberOf(Items.MELON) > 0 && numberOf(Item.getItemFromBlock(Blocks.ICE)) > 0
				&& food_values[0] == 0 && food_values[3] == 0 && food_values[5] == 0)
			return new ItemStack(ItemsInit.melonsicle, 4);
		
		if(food_values[3] >= 1.0f && food_values[4] >= 1.0f && food_values[7] >= 1.0f)
			return new ItemStack(ItemsInit.waffles);
		
		if(food_values[0] >= 1.5f && food_values[3] >= 2.0f && food_values[5] == 0.0f)
			return new ItemStack(ItemsInit.bacon_eggs);
		
		if(food_values[1] >= 2.0f && numberOf(Items.STICK) == 0)
			return new ItemStack(ItemsInit.monster_lasagna);
		
		if(food_values[6] >= 2.0f && numberOf(Item.getItemFromBlock(Blocks.PUMPKIN)) > 0)
			return new ItemStack(ItemsInit.pumpkin_cookie);
		
		if(food_values[6] >= 1.0f && food_values[7] >= 1.0f && numberOf(Item.getItemFromBlock(Blocks.ICE)) > 0
				&& food_values[0] == 0 && food_values[3] == 0 && food_values[5] == 0 && numberOf(Items.STICK) == 0)
			return new ItemStack(ItemsInit.ice_cream);
		
		if(food_values[2] >= 0.5f && numberOf(Items.STICK) == 1)
			return new ItemStack(ItemsInit.fish_sticks);
		
		return new ItemStack(ItemsInit.wet_goop);
	}
	
	private int numberOf(Item item)
	{
		int count = 0;
		
		for(ItemStack itemstack : items)
			if(itemstack.isItemEqual(new ItemStack(item)))
				count++;
				
		return count;
	}
}
