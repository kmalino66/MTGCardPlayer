package net.trizmo.mtgcards;

public class Pack {

	private Card[] cardsInPack = new Card[14];
	
	public Pack(Card[] cardsInPack)
	{
		this.cardsInPack = cardsInPack;
	}
	
	public Card getACard(int card)
	{
		return cardsInPack[card];
	}
	
	public void setACard(int cardNumber, Card card)
	{
		cardsInPack[cardNumber] = card;
	}
	
	public Card[] getCards()
	{
		return cardsInPack;
	}
}
