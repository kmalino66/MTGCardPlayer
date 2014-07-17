package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class DeckManagerButton {
	
	private Image buttonTexture1;
	private Image buttonTexture2;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int textureNumber;
	
	public DeckManagerButton(int xPos, int yPos, int width, int height, String textureName1, String textureName2, int textureNumber)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		buttonTexture1 = new ImageIcon("res/Button/" + textureName1 + ".png").getImage();
		buttonTexture2 = new ImageIcon("res/Button/" + textureName2 + ".png").getImage();
		this.textureNumber = textureNumber;
	}
	
	public boolean getClicked(MouseEvent e)
	{
		boolean clicked;
		Rectangle buttonHitBox = new Rectangle(xPos, yPos, width, height);
		
		if(buttonHitBox.contains(e.getPoint()))
		{
			clicked = true;
		}else {
			clicked = false;
		}
		
		return clicked;
	}
	
	public void drawButton(Graphics g)
	{
		if(textureNumber == 1)
		{
			g.drawImage(buttonTexture1, xPos, yPos, width, height, null);
		}else {
			g.drawImage(buttonTexture2, xPos, yPos, width, height, null);
		}
	}
	
	public int getTextureNumber()
	{
		return textureNumber;
	}
	
	public void setTextureNumber(int textureNumber)
	{
		this.textureNumber = textureNumber;
	}
}
