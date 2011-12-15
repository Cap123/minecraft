package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiDrag extends GuiScreen {
	
	private GuiScreen parentScreen;
	private boolean dragging = false;
	private int diffu;
	private int diffv;
	private int k;
	private int l;

	
	public GuiDrag(GuiScreen parentscreen)
	{
		this.parentScreen = parentscreen;
		Minecraft mc = powney.mc;
		
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        k = scaledresolution.getScaledWidth();
        l = scaledresolution.getScaledHeight();
	}
	
	public void updateScreen()
	{

	}
	
	public void drawScreen(int i, int j, float f)
	{
		this.drawDefaultBackground();
		
		int x = Mouse.getX() / 2;
		int y = (mc.displayHeight - Mouse.getY()) / 2;
		
		//System.out.println(dragging);
		if(Mouse.isButtonDown(0) && !dragging)
		{
			
			
			
			System.out.println(x);
			System.out.println(y);
			
			if((x >= k/2 - powney.boxu && x <= k/2 - powney.boxu + powney.boxwidth) && (y >= l - powney.boxv && y <= l - powney.boxv + powney.boxheight))
				{
					diffu = (k/2-x) - powney.boxu;
					diffv = (l-y) - powney.boxv;
					dragging = true;
				}
		}
		else if(Mouse.isButtonDown(0) && dragging)
		{
			powney.boxu =k/2 - (x + diffu);
			powney.boxv =l -  (y + diffv);
			
		}
		else
		{
			dragging = false;
		}
		
		//draw box
		GL11.glTranslatef(k/2 -powney.boxu, l -powney.boxv, 0);
    	drawRect(0, 0, powney.boxwidth, powney.boxheight, 0x50FF0000);
    	GL11.glTranslatef(-k/2 +powney.boxu, -l +powney.boxv, 0);
		//end draw box
    	

		
		super.drawScreen(i, j, f);
		
		
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(1, width/2 -100, height - 20, "Back"));
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			this.mc.displayGuiScreen(this.parentScreen);
		}
	}
	
	public void keyTyped(char c, int i)
	{

	}
	
	public void mouseCicked(int i, int j, int k)
	{

		super.mouseClicked(i, j, k);
	}

}
