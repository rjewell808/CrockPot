package gruntpie224.crockpot.util;

import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPMessage implements IMessage{
	public CPMessage(){}
	
	private boolean toSend;
	private int pos_x;
	private int pos_y;
	private int pos_z;
	public CPMessage(boolean toSend, BlockPos pos)
	{
		this.toSend = toSend;
		this.pos_x = pos.getX();
		this.pos_y = pos.getY();
		this.pos_z = pos.getZ();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(toSend);
		buf.writeInt(pos_x);
		buf.writeInt(pos_y);
		buf.writeInt(pos_z);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		toSend = buf.readBoolean();
		pos_x = buf.readInt();
		pos_y = buf.readInt();
		pos_z = buf.readInt();
	}
	
	public static class MyMessageHandler implements IMessageHandler<CPMessage, IMessage> {
		  // Do note that the default constructor is required, but implicitly defined in this case

		  @Override public IMessage onMessage(CPMessage message, MessageContext ctx) {
		    // This is the player the packet was sent to the server from
		    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
		    // The value that was sent
		    boolean amount = message.toSend;
		    BlockPos pos = new BlockPos(message.pos_x, message.pos_y, message.pos_z);
		    // Execute the action on the main server thread by adding it as a scheduled task
		    serverPlayer.getServerWorld().addScheduledTask(() -> {
		      ((CrockContainerTileEntity)serverPlayer.getServerWorld().getTileEntity(pos)).setCooking(amount);		    
		    });
		    // No response packet
		    return null;
		  }
		}
}
