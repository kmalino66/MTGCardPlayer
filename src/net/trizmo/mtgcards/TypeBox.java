package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Graphics;

public class TypeBox {
	@SuppressWarnings("unused")
	private Color foregroundColor = Color.black;
	@SuppressWarnings("unused")
	private Color backgroundColor = Color.white;
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	private String text;
	
	public TypeBox(int xPos, int yPos, int width, int height, Color foreground, Color background)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.foregroundColor = foreground;
		this.backgroundColor = background;
	}
	
	public TypeBox(int xPos, int yPos, int width, int height)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void drawTypeBox(Graphics g)
	{
		g.fillRect(xPos, yPos, width, height);
		g.drawString(text, xPos, yPos);
	}
	
}
