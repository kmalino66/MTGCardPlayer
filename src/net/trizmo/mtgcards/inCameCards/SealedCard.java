package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class SealedCard {
	
	private String textureName;
	private int quantityOfCard;
	private String cardName;
	private String setName;
	
	public SealedCard(String cardName, String setName, int quantity, Point place, String textureName)
	{
		this.cardName = cardName;
		this.setName = setName;
		this.quantityOfCard = quantity;
		this.textureName = textureName;
	}
	
	public String getCardName()
	{
		return cardName;
	}
	
	public int getAmountOfCard()
	{
		return quantityOfCard;
	}
	
	public String getTextureName()
	{
		return textureName;
	}
	
	public Image getTextureImage()
	{
		return new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage();
	}
	
	public void changeQuantity(int delta)
	{
		quantityOfCard += delta;
	}
	
	public boolean sameCard(String cardName)
	{
		if(this.cardName.equalsIgnoreCase(cardName))
		{
			return true;
		}else {
			return false;
		}
	}
}
