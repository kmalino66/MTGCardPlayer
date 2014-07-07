package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class DropBox {

	private Color foreground, background;
	private Image downArrow = new ImageIcon("res/Button/downArrow.png").getImage();
	private DropBoxEntry[] optionList;
	
	
	
	private int xPos, yPos, width, height, clickedObject;
	
	private boolean opened;
	
	public DropBox(int xPos, int yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.background = Color.white;
		this.foreground = Color.gray;
		this.opened = false;
		
	}
	
	public DropBox(int xPos, int yPos, int width, int height, Color foreground, Color background)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.background = background;
		this.foreground = foreground;
		this.opened = false;
	}
	
	public void drawDropBox(Graphics g)
	{
		
		g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), xPos, yPos, width, height, null);
		g.drawImage(new ImageIcon("res/General/DownButtonOfDoom.jpg").getImage(), xPos + width - 50, yPos, 50, 50, null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, height - 4));
		
		if(clickedObject > optionList.length - 1) clickedObject = 0;
		g.drawString(optionList[clickedObject].getOption(), xPos + 10, (yPos + height - 4));
		
		if(opened)
		{
			int options = optionList.length;
			g.setColor(background);
			
			for(int i = 0; i < options; i++)
			{
				g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), xPos + 10, (yPos + (height * 1)) + (i * height) - 10, null);
			}
			
			for(int i = 0; i < options; i++)
			{
				g.setColor(foreground);
				g.drawString(optionList[i].getOption(), xPos + 10, (yPos + (height * 2)) + (i * height) - 10);
			}
		}
	}
	
	public void addOption(String optionText)
	{
		if(optionList != null){
			int options = optionList.length;
			
			DropBoxEntry[] par1EntryList = new DropBoxEntry[options + 1];
			
			for(int i = 0; i < optionList.length; i++)
			{
				par1EntryList[i] = optionList[i];
			}

			optionList = par1EntryList;
			optionList[options] = new DropBoxEntry(options, optionText);
		}else
		{
			optionList = new DropBoxEntry[1];
			optionList[0] = new DropBoxEntry(0, optionText);
		}
		
	}
	
	public void checkClicked(MouseEvent e)
	{
		if(!opened)
		{
			Rectangle rect = new Rectangle(xPos, yPos, width, height);
			if(rect.contains(e.getPoint()))
			{
				opened = true;
			}
		}else{
			for(int i = 0; i < optionList.length; i++)
			{
				Rectangle rect = new Rectangle(xPos, (yPos + (height * (i +1))), width, height);
				if(rect.contains(e.getPoint()))
				{
					clickedObject = i;
					break;
				}
				
			}
			
			opened = false;
			
		}
	}
	
	public boolean getOpened()
	{
		return opened;
	}
	
	public int getClickedId()
	{
		return clickedObject;
	}
	
	public String getSelected()
	{
		return optionList[clickedObject].getOption();
	}
	
	public String[] getOptions()
	{
		String[] par1 = null;
		
		try {
			par1 = new String[optionList.length];
			for(int i = 0; i < optionList.length; i++)
			{
				par1[i] = optionList[i].getOption();
			}
		} catch (NullPointerException e)
		{
			
		}
		
		return par1;
		
	}
	
	public void removeOptions()
	{
		optionList = null;
	}
	
}

class DropBoxEntry {
	private int optionId;
	private String option;
	
	public DropBoxEntry(int optionId, String option)
	{
		this.optionId = optionId;
		this.option = option;
		
	}
	
	public int getOptionId()
	{
		return optionId;
	}
	
	public String getOption()
	{
		return option;
	}
	
	public void ChangeEntry(int optionId, String optionString)
	{
		this.optionId = optionId;
		option = optionString;
	}
	
}