package gruntpie224.crockpot.gui;

import gruntpie224.crockpot.CrockPot;
import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import gruntpie224.crockpot.util.CPMessage;
import gruntpie224.crockpot.util.CPPacketHandler;
import gruntpie224.crockpot.util.CPSounds;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.main.Main;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.inventory.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CrockPotGUIContainer extends GuiContainer{
	public static final int WIDTH = 180;
	public static final int HEIGHT = 190;
	
	private static final ResourceLocation background = new ResourceLocation(CrockPot.MODID, "textures/gui/crockpotcontainer.png");
	private CrockContainerTileEntity crock_te;
	private GuiButton cook_btn = new GuiButton(1, 192, 90, 40, 20, "Cook");
	
	public CrockPotGUIContainer(CrockContainerTileEntity tileEntity, CrockPotContainer inventorySlotsIn) {
		super(inventorySlotsIn);
	
		xSize = WIDTH;
		ySize = HEIGHT;
		crock_te = tileEntity;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		cook_btn.enabled = false;
		
		this.buttonList.add(cook_btn);
	}
	
	@Override
	public void updateScreen()
    {
		super.updateScreen();
		if(crock_te.canSmelt())
			((GuiButton)(buttonList.get(buttonList.indexOf(cook_btn)))).enabled = true;
		else
			((GuiButton)(buttonList.get(buttonList.indexOf(cook_btn)))).enabled = false;
				
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop - 10, 0, 0, xSize, ySize);
	}
	
	@Override
	public void onGuiClosed()
	{
		mc.world.playSound(crock_te.getPos(), CPSounds.snd_pot_close, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
	}
	
	@Override
	protected void actionPerformed(GuiButton b)
	{
		if(b.id == 1)
		{
			crock_te.setCooking(true);
			CPPacketHandler.INSTANCE.sendToServer(new CPMessage(true, crock_te.getPos()));
            this.mc.displayGuiScreen((GuiScreen)null);
		}
	}

}
