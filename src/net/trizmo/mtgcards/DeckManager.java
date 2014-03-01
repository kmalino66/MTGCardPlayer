package net.trizmo.mtgcards;

public class DeckManager {

	public static void getTotalCardsInDeck(int deckId) {

		for(int i = 0; i < Screen.deck.length; i++) {
			if(Screen.deck[i] != null) {
				Screen.totalCardsInDeck += Screen.deck[i].getAmmountOfCard();
			}
		}

		CoutHandler.success(Screen.totalCardsInDeck + " cards in the deck");
	}

}
