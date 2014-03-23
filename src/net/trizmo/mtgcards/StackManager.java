package net.trizmo.mtgcards;

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
}
