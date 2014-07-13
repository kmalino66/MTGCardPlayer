package net.trizmo.mtgcards;

import java.util.Random;

public class SealedPlayManager {

	public static Pack[] createPacks(Card[] common, Card[] uncommon, Card[] rare, Card[] mythicRare, Card[] special)
	{

		Pack[] packArray = new Pack[7];
		
		Random rand = new Random();

		for(int i = 0; i < 7; i++)
		{
			Card[] cardInPack = new Card[14];
			
			if(rand.nextInt(9) == 6) //Check if rare card is mythic rare
			{
				cardInPack[0] = mythicRare[rand.nextInt(mythicRare.length)];
			}else
			{
				cardInPack[0] = rare[rand.nextInt(rare.length)];
			}
			
			//Pack uncommon cards
			for(int j = 0; j < 3; j++)
			{
				cardInPack[j+1] = uncommon[rand.nextInt(uncommon.length)];
			}
			
			//Pack Common Cards
			for(int j = 0; j < 10; j++)
			{
				if (j==0 && special != null)
				{
					cardInPack[j+ 4] = special[rand.nextInt(special.length)];
				}else
				{
					cardInPack[j + 4] = common[rand.nextInt(common.length)];
				}
			}
			
			packArray[i] = new Pack(cardInPack);
			cardInPack = null;
			
		}
		return packArray;
	}
	
	public static Deck[] formatForDeck(Pack[] packArray)
	{
		Deck[] totalSealedCards = new Deck[0];
		Card[] par1CardsInPack;
		
		boolean found = false;
		
		for(int i = 0; i < 7; i++) // Packs
		{
			par1CardsInPack = packArray[i].getCards();
			
			for(int j = 0; j < par1CardsInPack.length; j++)//Cards in one pack
			{
				for(int k = 0; k < totalSealedCards.length; k++)//Check previous cards
				{
					if(totalSealedCards[k] != null && par1CardsInPack[j].getId() == totalSealedCards[k].getCardId())
					{
						totalSealedCards[k].adjustCardAmmount(1);
						found = true;
					}
				}
				
				if(found = false)
				{
					totalSealedCards = expandDeckArray(totalSealedCards);
					totalSealedCards[totalSealedCards.length - 1] = new Deck(par1CardsInPack[j].getId(), par1CardsInPack[j].getName(), par1CardsInPack[j].getRarity(), par1CardsInPack[j].getSetName(), par1CardsInPack[j].getTextureName(), 1);
				}
				
				found = false;
			}
		}
		
		return totalSealedCards;
	}
	
	public static Deck[] expandDeckArray(Deck[] deckArray)
	{
		Deck[] par1DeckArray = new Deck[deckArray.length + 1];
		
		for(int i = 0; i < deckArray.length; i++)
		{
			par1DeckArray[i] = deckArray[i];
		}
		
		return par1DeckArray;
	}
}