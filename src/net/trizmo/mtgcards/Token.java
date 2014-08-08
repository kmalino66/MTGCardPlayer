package net.trizmo.mtgcards;

import javax.swing.ImageIcon;

public class Token {

	private String name;
	private String setName;
	private String textureName;
	
	public Token(String name, String setName, String textureName)
	{
		this.name = name;
		this.setName = setName;
		this.textureName = textureName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSetName()
	{
		return setName;
	}
	
	public ImageIcon getTextureImage()
	{
		return new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg");
	}
}
