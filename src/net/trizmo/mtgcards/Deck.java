package net.trizmo.mtgcards;

public class Deck extends Card{
	
	private int cardId;
	private int ammountOfCard;
	private int rarity;
	
	private String cardName;
	private String textureName;

	public Deck(int cardId, String cardName, int rarity, String setName, String textureName, int ammountOfCard) {
		super(cardId, cardName, rarity, setName, textureName);	
		this.cardId = cardId;
		this.cardName = cardName;
		this.rarity = rarity;
		this.textureName = textureName;
		this.ammountOfCard = ammountOfCard;
	}
	
	public int getCardId() {
		return cardId;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public int getAmmountOfCard() {
		return ammountOfCard;
	}
	
	public int getRarity() {
		return rarity;
	}
	
	public String getTextureName() {
		return textureName;
	}

}
