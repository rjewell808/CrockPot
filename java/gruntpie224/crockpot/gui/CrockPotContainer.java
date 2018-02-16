package gruntpie224.crockpot.gui;

import javax.annotation.Nullable;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
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
					y = 4 * 18 + 70;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9, x, y));
			}
		}
	}
	
	private void addOwnSlots(IInventory inventory)
	{
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 9 + (4 * 18);
		int y = -6;
		
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
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index < CrockContainerTileEntity.SIZE)
			{
				if(!this.mergeItemStack(itemstack1, CrockContainerTileEntity.SIZE, this.inventorySlots.size(), true))
				{
					return null;
				}
				else if (!this.mergeItemStack(itemstack1, 0, CrockContainerTileEntity.SIZE, false))
				{
					return null;
				}
				
				if(itemstack1.isEmpty())
					slot.putStack(ItemStack.EMPTY);
				else
					slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}

}
