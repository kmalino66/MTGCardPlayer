package net.trizmo.mtgcards;

import net.trizmo.mtgcards.inCameCards.HandCard;

public class StackManager {
	public static void reformatStacks()
	{
		//Reformat battlefield
		for(int i = 0; i < Screen.battlefieldCards.length - 1 ; i++)
		{
			if(Screen.battlefieldCards[i] == null && Screen.battlefieldCards[i+1] != null)
			{
				
				Screen.battlefieldCards[i] = Screen.battlefieldCards[i+1];
				Screen.battlefieldCards[i+1] = null;
				if(CardHandler.interactionCard != null && i + 1 == CardHandler.interactionCard.getArrayLocation())
				{
					CardHandler.interactionCard = new CardInteract(2, i);
				}
				if(CardHandler.zoomCard != null && CardHandler.zoomCard.getPlace().equals("battlefield") && CardHandler.zoomCard.getArrayIndex() == i + 1)
				{
					CardHandler.zoomCard.setArrayIndex(i);
				}
			}
		}
		
		//Reformat Hand
		for(int i = 0; i < Screen.handCards.length - 1; i++)
		{
			if(Screen.handCards[i] == null && Screen.handCards[i + 1] != null)
			{
				Screen.handCards[i] = Screen.handCards[i + 1];
				Screen.handCards[i].setIndex(i);
				Screen.handCards[i + 1] = null;
			}
		}
		
		//Reformat Library
		for(int i = 0; i < Screen.libraryCards.length - 1; i++)
		{
			if(Screen.libraryCards[i] == null && Screen.libraryCards[i + 1] != null)
			{
				Screen.libraryCards[i] = Screen.libraryCards[i + 1];
				Screen.libraryCards[i + 1] = null;
			}
		}
		
		//Reformat Graveyard
		for(int i = 0; i < Screen.graveyardCards.length - 1; i++)
		{
			if(Screen.graveyardCards[i] == null && Screen.graveyardCards[i + 1] != null)
			{
				Screen.graveyardCards[i] = Screen.graveyardCards[i + 1];
				Screen.graveyardCards[i + 1] = null;
			}
		}
		
		//Reformat Exile
		for(int i = 0; i < Screen.exiledCards.length - 1; i++)
		{
			if(Screen.exiledCards[i] == null && Screen.exiledCards[i + 1] != null)
			{
				Screen.exiledCards[i] = Screen.exiledCards[i + 1];
				Screen.exiledCards[i + 1] = null;
			}
		}
	}

	public static void drawCard() 
	{
		for(int i = 0; i < Screen.handCards.length; i++)
		{
			if(Screen.handCards[i] == null)
			{
				for(int j = Screen.libraryCards.length - 1; j > 0; j--)
				{
					if(Screen.libraryCards[j] != null)
					{
						Screen.handCards[i] = new HandCard(Screen.libraryCards[j].getCardName(), Screen.libraryCards[j].getImage(), i, Screen.libraryCards[j].getRarity());
						Screen.libraryCards[j] = null;
						break;
					}
				}
				break;
			}
		}
	}
	
	public static void shiftTokenBattlefield()
	{
		for(int magic = 0; magic < Screen.tokenBattlefield.length; magic++)
		{
			if(Screen.tokenBattlefield[magic] == null)
			{
				for(int par1Magic = magic; par1Magic < Screen.tokenBattlefield.length; par1Magic++)
				{
					if(Screen.tokenBattlefield[par1Magic] != null && Screen.tokenBattlefield[magic] == null)
					{
						Screen.tokenBattlefield[magic] = Screen.tokenBattlefield[par1Magic];
						Screen.tokenBattlefield[par1Magic] = null;
					}
				}
			}
		}
	}
}
