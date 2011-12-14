package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiProxy extends GuiScreen {
	
	private GuiScreen parentScreen;
	private GuiTextField serverTextField;
	
	public GuiProxy(GuiScreen parentscreen)
	{
		this.parentScreen = parentscreen;
	}
	
	public void updateScreen()
	{
		this.serverTextField.updateCursorCounter();
	}
	
	public void drawScreen(int i, int j, float f)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Proxy", this.width/2, this.height/4-60+20, 16777215);
		this.drawString(this.fontRenderer, "Hostname and Port (Leave blank to not use one)", width/2 - 125, height / 4 +60 - 60 + 15, 10526880);
		this.serverTextField.drawTextBox();
		super.drawScreen(i, j, f);
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, width/2 -100, height / 4 + 92 + 12, "Set"));
		this.controlList.add(new GuiButton(0, width/2 -100, height / 4 + 116 + 12, "Back"));
		this.serverTextField = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 26 + 15, 200, 20, "");
		this.serverTextField.isFocused = true;
		this.serverTextField.setMaxStringLength(128);
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 0)
		{
			this.mc.displayGuiScreen(this.parentScreen);
			if(this.serverTextField.getText().trim().length() == 0)
			{
				System.setProperty("socksProxyHost", "");
				System.setProperty("socksProxyPort", "");
				return;
			}
			
			String[] aParts = this.serverTextField.getText().trim().split(":");
			if(aParts.length < 2) return;
			System.setProperty("socksProxyHost", aParts[0]);
			System.setProperty("socksProxyHost", aParts[1]);
		}
		if(guibutton.id == 1)
		{
			this.mc.displayGuiScreen(this.parentScreen);
		}
	}
	
	public void keyTyped(char c, int i)
	{
		this.serverTextField.textboxKeyTyped(c, i);
		if(c == '\r') this.actionPerformed((GuiButton)this.controlList.get(0));
	}
	
	public void mouseCicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		this.serverTextField.mouseClicked(i, j, k);
	}

}
