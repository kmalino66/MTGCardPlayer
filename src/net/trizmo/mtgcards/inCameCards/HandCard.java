package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.trizmo.mtgcards.Screen;

public class HandCard {
	private Image textureImage;
	
	private int index;
	
	
	public HandCard(Image textureImage, int index)
	{
		this.textureImage = textureImage;
		this.index = index;
	}
	
	public Image getTextureImage()
	{
		return textureImage;
	}
	
	public void setIndex(int i)
	{
		index = i;
	}
	
	public boolean contains(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.cardWidth * index, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth, Screen.cardHeight);
		return rect.contains(e.getPoint());
	}
	
}
