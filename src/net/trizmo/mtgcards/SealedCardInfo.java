package net.trizmo.mtgcards;

public class SealedCardInfo {

	private int cardId, amountInDeck, amountAvail;
	
	/**
	 * Used to store basic information for a sealed deck.
	 * @param cardId - The id of the card for this information.
	 * @param amountInDeck - The number of how many of this card is inside the deck.
	 * @param amountAvail - The total amount of how many of this card can be in the deck.
	 */
	public SealedCardInfo(int cardId, int amountInDeck, int amountAvail)
	{
		this.cardId = cardId;
		this.amountInDeck = amountInDeck;
		this.amountAvail = amountAvail;
	}
	
	public int[] getInfo()
	{
		int[] par1 = new int[3];
		par1[0] = cardId;
		par1[1] = amountInDeck;
		par1[2] = amountAvail;
		
		return par1;
	}
	
	/**
	 * Moves a given amount of the card over to the amount actually in the deck versus the 'sideboard'
	 * 
	 * @param moveAmount - Determines how many of the cards are moved.
	 */
	public void moveIntoDeck(int moveAmount)
	{
		if(moveAmount > amountAvail)
		{
			moveAmount = amountAvail;
		}
		
		amountInDeck += moveAmount;
		amountAvail -= moveAmount;
	}

	public int getId() {
		return cardId;
	}
}
