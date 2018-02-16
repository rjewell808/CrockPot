package gruntpie224.crockpot.gui;

import gruntpie224.crockpot.CrockPot;
import gruntpie224.crockpot.tileentity.CrockContainerTileEntity;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

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
	protected void actionPerformed(GuiButton b)
	{
		if(b.id == 1)
		{
			System.out.println("THIS BUTTON WORKS");
		}
	}

}
