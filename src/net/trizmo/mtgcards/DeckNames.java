package net.trizmo.mtgcards;

public class DeckNames {

	private int deckId;
	private String deckName;
	private int ammountOfCards;
	
	public DeckNames(int deckId, String deckName) {
		this.deckId = deckId;
		this.deckName = deckName;
	}
	
	public int getDeckId() {
		return deckId;
	}
	
	public String getDeckName() {
		return deckName;
	}
	
	public int getAmmountOfCards() {
		return ammountOfCards;
	}
}
