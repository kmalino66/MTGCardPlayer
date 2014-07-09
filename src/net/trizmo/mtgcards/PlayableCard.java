package net.trizmo.mtgcards;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class PlayableCard {

	private int x;
	private int y;
	private int width;
	private int height;
	private int rarity;
	
	private boolean tapped;
	private boolean isInLibrary;
	private boolean isInGraveyard;
	private boolean isExiled;
	private boolean isBattlefield;
	private boolean isHand;
	
	private Image textureImage;
	
	public PlayableCard(int x, int y, int width, int height, boolean tapped, boolean isInLibrary, boolean isInGraveyard, boolean isExiled, boolean isBattlefield, boolean isHand, int rarity, Image textureImage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tapped = tapped;
		this.isInLibrary = isInLibrary;
		this.isInGraveyard = isInGraveyard;
		this.isExiled = isExiled;
		this.isBattlefield = isBattlefield;
		this.isHand = isHand;
		this.textureImage = textureImage;
		this.rarity = rarity;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public boolean getTapped()
	{
		return tapped;
	}
	
	public boolean getIsInLibrary()
	{
		return isInLibrary;
	}
	
	public boolean getIsInGraveyard()
	{
		return isInGraveyard;
	}
	
	public boolean getIsExiled()
	{
		return isExiled;
	}
	
	public boolean getIsBattlefield()
	{
		return isBattlefield;
	}
	
	public Image getTextureImage()
	{
		return textureImage;
	}
	
	public boolean contains(MouseEvent e) {
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect.contains(e.getPoint());
	}
	
	public void setPos(int xy, int yy)
	{
		x = xy;
		y = yy;
	}
	
	public boolean getIsInHand()
	{
		return isHand;
	}

	public int getRarity() {
		return rarity;
	}
}
