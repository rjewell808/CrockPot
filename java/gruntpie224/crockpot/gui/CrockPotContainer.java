package gruntpie224.crockpot.gui;

import javax.annotation.Nullable;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CrockPotContainer extends Container{

	private CrockContainerTileEntity te;
	
	public CrockPotContainer(InventoryPlayer playerInventory, CrockContainerTileEntity te)
	{
		this.te = te;
		
		addOwnSlots(te);
		addPlayerSlots(playerInventory);
	}
	
	private void addPlayerSlots(IInventory playerInventory)
	{
		for(int row = 0; row < 4; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				int x = 9 + col * 18;
				int y = row * 18 + 70;
				if (row == 0)
					y = 4 * 18 + 74;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9, x, y + 17));
			}
		}
	}
	
	private void addOwnSlots(IInventory inventory)
	{
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 9 + (4 * 18);
		int y = -5;
		
		int slotIndex = 0;
		for(int i = 0; i < itemHandler.getSlots(); i++)
		{
			addSlotToContainer(new Slot(inventory, slotIndex, x, y));
			slotIndex++;
			y += 18;
		}
	}
	
	
	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index < CrockContainerTileEntity.SIZE)
			{
				if(!this.mergeItemStack(itemstack1, CrockContainerTileEntity.SIZE, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
				else if (!this.mergeItemStack(itemstack1, 0, CrockContainerTileEntity.SIZE, false))
				{
					return ItemStack.EMPTY;
				}
				
				if(itemstack1.isEmpty())
					slot.putStack(ItemStack.EMPTY);
				else
					slot.onSlotChanged();
			}
			else if (index >= CrockContainerTileEntity.SIZE)
			{
                if (index >= 4 && index < 31)
                {
                    if (!this.mergeItemStack(itemstack1, 31, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 31 && index <= 39 && !this.mergeItemStack(itemstack1, 4, 31, false))
                {
                    return ItemStack.EMPTY;
                }
            }
			else if (!this.mergeItemStack(itemstack1, 4, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}

}
