package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.Screen;

public class DeckManagerButton {
	
	private Image buttonTexture;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public DeckManagerButton(int xPos, int yPos, int width, int height, String textureName)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		buttonTexture = new ImageIcon("res/Button/" + textureName + ".jpg").getImage();
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
		g.drawImage(buttonTexture, xPos, yPos, Screen.buttonWidth, Screen.buttonHeight, null);
	}
}
