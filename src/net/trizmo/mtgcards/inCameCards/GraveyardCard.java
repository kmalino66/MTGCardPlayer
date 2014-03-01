package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.trizmo.mtgcards.Screen;

public class GraveyardCard {
private Image textureImage;
	
	private int XPOSITION = Screen.width - ((Screen.deckCard[1].getWidth() + 30) * 2) + 30;
	private int YPOSITION = Screen.height - (Screen.deckCard[1].getHeight() + 15) + (int)(15 / 2);
	
	public GraveyardCard(Image textureImage)
	{
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
		Rectangle rect = new Rectangle(XPOSITION, YPOSITION, Screen.cardWidth, Screen.cardHeight);
		return rect.contains(e.getPoint());
	}
}

