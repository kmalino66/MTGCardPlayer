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
		g.setColor(background);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(foreground);
		g.drawRect(xPos, yPos, width, height);
		g.drawImage(downArrow, xPos + (width - height), yPos, height, height, null);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, height - 2));
		g.setColor(foreground);
		g.drawString(optionList[clickedObject].getOption(), xPos + 10, yPos + height - 2);
		if(opened)
		{
			int options = optionList.length;
			g.setColor(background);
			g.fillRect(xPos, yPos + height, width, height * options);
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
		boolean clickedInArray = false;
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
					clickedInArray = true;
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