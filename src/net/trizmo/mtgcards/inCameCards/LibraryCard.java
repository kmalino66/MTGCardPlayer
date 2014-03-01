package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.trizmo.mtgcards.Screen;

public class LibraryCard{
	private Image textureImage;
	
	private int XPOSITION = Screen.width - ((Screen.cardWidth + 30) * 3) + 30;
	private int YPOSITION = Screen.height - (Screen.cardHeight + 15) + (int)(15 / 2);

	public LibraryCard(Image textureImage) {
		
		this.textureImage = textureImage;
		
	}
	
	public Image getImage()
	{
		return textureImage;
	}
	
	public int getX()
	{
		return XPOSITION;
	}
	
	public int getY()
	{
		return YPOSITION;
	}
	
	public boolean contains(MouseEvent e)
	{
		Rectangle rect = new Rectangle(XPOSITION, YPOSITION, Screen.width, Screen.height);
		return rect.contains(e.getPoint());
	}

}
