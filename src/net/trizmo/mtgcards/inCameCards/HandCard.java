package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.trizmo.mtgcards.Screen;

public class HandCard {
	private Image textureImage;
	
	private int index;
	private int rarity;
	
	
	public HandCard(Image textureImage, int index, int rarity)
	{
		this.textureImage = textureImage;
		this.index = index;
		this.rarity = rarity;
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
		Rectangle rect = new Rectangle((int)((Screen.cardWidth * index) * .9), Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), (int)(Screen.cardWidth * .9) , (int)(Screen.cardHeight * .9));
		return rect.contains(e.getPoint());
	}
	
	public boolean contains(Point e)
	{
		Rectangle rect = new Rectangle((int)((Screen.cardWidth * index) * .9), Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), (int)(Screen.cardWidth * .9) , (int)(Screen.cardHeight * .9));
		return rect.contains(e);
	}

	public int getRarity() {
		return rarity;
	}
}
