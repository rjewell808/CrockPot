package gruntpie224.crockpot.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrockIngredient {
	
	public Item food;
	private float[] value = new float[8];
	
	public CrockIngredient(Item food, float[] new_val)
	{
		this.food = food;

		for(int i = 0; i < value.length; i++)
			if(i < new_val.length && new_val[i] > 0)
				value[i] = new_val[i];
			else
				value[i] = 0;
	}
	
	public CrockIngredient(Item food, float new_val, String cat)
	{
		this.food = food;

		value[getCategory(cat)] = new_val;
	}
	
	public boolean isEqualToItem(Item item)
	{
		return (new ItemStack(food)).isItemEqual(new ItemStack(item));
	}
	
	public float getValue(int index)
	{
		return value[index];
	}
	
	public float getValue(String cat)
	{	
		return getValue(getCategory(cat));
	}
	
	public float[] getValues()
	{
		return value;
	}
	
	private int getCategory(String cat)
	{
		switch(cat)
		{
			case "meat": return 0;
			case "monster": return 1;
			case "fish": return 2;
			case "egg": return 3;
			case "fruit": return 4;
			case "vegetable": return 5;
			case "sweet": return 6;
			case "dairy": return 7;
			default: return -1;
		}
	}
	
	@Override
	public String toString()
	{
		String str = "";
		
		str += food.getUnlocalizedName() + " : {";
		
		for(int i = 0; i < value.length; i++)
			str += value[i] + ", ";
		
		str += "}";
		
		return str;
	}
}
