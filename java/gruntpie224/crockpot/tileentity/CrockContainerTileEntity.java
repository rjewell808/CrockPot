package gruntpie224.crockpot.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CrockContainerTileEntity extends TileEntity implements ITickable, ISidedInventory{
	public static final int SIZE = 4;
	private int cookTime;
    private int totalCookTime;
	
	/** The ItemStacks that hold the items currently being used in the CrockPot */
    private NonNullList<ItemStack> crockItemStacks = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);
    
	
	private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE){
		@Override
		protected void onContentsChanged(int slot)
		{
			CrockContainerTileEntity.this.markDirty();
		}
	};
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.crockItemStacks = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.crockItemStacks);
		if(compound.hasKey("items"))
			itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("items", itemStackHandler.serializeNBT());
		ItemStackHelper.saveAllItems(compound, this.crockItemStacks);
		return compound;
	}
	
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
		
		return super.getCapability(capability, facing);
	}
	
	public boolean isEmpty()
    {
        for (ItemStack itemstack : this.crockItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.crockItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.crockItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.crockItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    
    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.crockItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.crockItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }
    
    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 1;
    }
    
    public int getCookTime(ItemStack stack)
    {
        return 200;
    }
    
    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }
	
    int count = 0;
    
    @Override
    public void update()
    {
    	if(count == 100)
    	{
    		System.out.println("DEBUG CROCKPOT:");
    		System.out.println("IsEmpty: " + this.isEmpty());
    		System.out.println("Slot 0: " + this.getStackInSlot(0));
    		System.out.println("Slot 1: " + this.getStackInSlot(1));
    		System.out.println("Slot 2: " + this.getStackInSlot(2));
    		System.out.println("Slot 3: " + this.getStackInSlot(3));
    		
    		count = 0;
    	}
    	count++;
    }
    
	/**
     * Returns true if the CrockPot can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    public boolean canSmelt()
    {
        if (((ItemStack)this.crockItemStacks.get(0)).isEmpty() || ((ItemStack)this.crockItemStacks.get(1)).isEmpty() || ((ItemStack)this.crockItemStacks.get(2)).isEmpty() || ((ItemStack)this.crockItemStacks.get(3)).isEmpty())
        {
            return false;
        }
        else
        {
            /*
        	ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.crockItemStacks.get(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.crockItemStacks.get(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
            */
        	return true;
        }
    }

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}
}
