package net.trizmo.mtgcards;

import javax.swing.ImageIcon;

public class Token {

	private String name;
	private int power;
	private int toughness;
	private String textureName;
	
	public Token(String name, int power, int toughness, String textureName)
	{
		this.name = name;
		this.power = power;
		this.toughness = toughness;
		this.textureName = textureName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getToughness()
	{
		return toughness;
	}
	
	public ImageIcon getTextureImage()
	{
		return new ImageIcon("res/Tokens/TokenTexture/" + textureName +".png");
	}
}
