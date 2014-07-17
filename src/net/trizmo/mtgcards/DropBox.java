package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;

public class DropBox {

	private Color foreground, background;
	private DropBoxEntry[] optionList;



	private int xPos, yPos, width, height, clickedObject;
	private int scrollAmount;

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
		this.scrollAmount = 0;
	}

	public void drawDropBox(Graphics g)
	{

		g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), xPos, yPos, width, height, null);
		g.drawImage(new ImageIcon("res/General/DownButtonOfDoom.jpg").getImage(), xPos + width - 50, yPos, 50, 50, null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, height - 4));

		if(optionList == null || clickedObject > optionList.length - 1) clickedObject = 0;
		if(optionList != null) g.drawString(optionList[clickedObject].getOption(), xPos + 10, (yPos + height - 4));

		if(opened)
		{
			if(scrollAmount < 0) scrollAmount = 0;
			int options = optionList.length;
			g.setColor(background);

			for(int i = scrollAmount; i < options; i++)
			{
				g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), xPos + 10, (yPos + (height * 1)) + ((i - scrollAmount) * height) - 10, null);
			}

			for(int i = scrollAmount; i < options; i++)
			{
				g.setColor(foreground);
				g.drawString(optionList[i].getOption(), xPos, (yPos + (height * 2)) + ((i - scrollAmount) * height) - 10);
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

	public int getAmountOfOptions()
	{
		if(optionList != null)
		{
			return optionList.length;
		} else {
			return 0;
		}
	}

	public void checkClicked(MouseEvent e)
	{
		if(optionList != null) {
			if(!opened)
			{
				Rectangle rect = new Rectangle(xPos, yPos, width, height);
				if(rect.contains(e.getPoint()))
				{
					opened = true;
				}
			}else{
				for(int i = 0; i < optionList.length - scrollAmount; i++)
				{
					Rectangle rect = new Rectangle(xPos, (yPos + (height * ((i) +1))), width, height);
					if(rect.contains(e.getPoint()))
					{
						clickedObject = i + scrollAmount;
						break;
					}

				}

				opened = false;
				scrollAmount = 0;

			}
		}
	}

	public boolean clickedBool(MouseEvent e)
	{

		boolean par1 = false;

		if(!opened)
		{
			Rectangle rect = new Rectangle(xPos, yPos, width, height);
			if(rect.contains(e.getPoint()))
			{
				par1 = true;
			}
		}else{
			for(int i = 0; i < optionList.length; i++)
			{
				Rectangle rect = new Rectangle(xPos, (yPos + (height * (i +1))), width, height);
				if(rect.contains(e.getPoint()))
				{
					par1 = true;
					break;
				}

			}


		}

		return par1;
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
		if(optionList != null)
		{
			return optionList[clickedObject].getOption();
		}else {
			return null;
		}
	}

	public String[] getOptions()
	{
		String[] par1 = null;

		if(optionList != null) 
		{
			par1 = new String[optionList.length];
			for(int i = 0; i < optionList.length; i++)
			{
				par1[i] = optionList[i].getOption();
			}

		}

		return par1;

	}

	public void removeOptions()
	{
		optionList = null;
	}

	public void scroll(MouseWheelEvent e)
	{
		int par1Int = e.getWheelRotation();
		scrollAmount += par1Int;
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