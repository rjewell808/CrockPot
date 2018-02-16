package gruntpie224.crockpot.tileentity;

import java.io.IOException;

import javax.annotation.Nullable;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CrockContainerTileEntity extends TileEntity implements ITickable, ISidedInventory{
	public static final int SIZE = 4;
	private int cookTime;
    private int totalCookTime;
    private boolean isCooking = false;
	
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
    public void setCooking(boolean isCooking)
    {
    	System.out.println("Cooking: " + isCooking);
    	this.isCooking = isCooking;
    }
    
    public boolean isBurning()
    {
        return isCooking;
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
    	boolean flag = this.isBurning();
        boolean flag1 = false;

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.crockItemStacks.get(1);
            System.out.println("Cooking: " + this.isBurning());
            
            if (this.isBurning())
            {
                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;
                    
                    System.out.print("Cooking: " + cookTime);
                    
                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.crockItemStacks.get(0));
                        this.smeltItem();
                        this.setCooking(false);
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
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
    
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            for(int i = 0; i < SIZE; i++)
            {
            	this.crockItemStacks.get(i).shrink(1);
            }
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
