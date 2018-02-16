package gruntpie224.crockpot.gui;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		
		if (te instanceof CrockContainerTileEntity)
		{
			return new CrockPotContainer(player.inventory, (CrockContainerTileEntity) te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		
		if (te instanceof CrockContainerTileEntity)
		{
			CrockContainerTileEntity crock_te = (CrockContainerTileEntity) te;
			return new CrockPotGUIContainer(crock_te, new CrockPotContainer(player.inventory, crock_te));
		}
		return null;
	}

}
