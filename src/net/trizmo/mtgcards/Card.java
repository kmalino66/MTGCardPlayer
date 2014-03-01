package net.trizmo.mtgcards;

public class Card {
	
	private int cardId;
	private String cardName;
	int rarity;
	private String textureName;
	private String setName;
	
	public Card(int cardId, String cardName, int rarity, String setName, String textureName)
	{
		this.cardId = cardId;
		this.cardName = cardName;
		this.rarity = rarity;
		this.setName = setName;
		this.textureName = textureName;
	}
	
	public int getId()
	{
		return cardId;
	}
	
	public String getName()
	{
		return cardName;
	}
	
	public int getRarity()
	{
		return rarity;
	}
	
	public String getTextureName()
	{
		return textureName;
	}
	
	public String getSetName()
	{
		return setName;
	}
}
