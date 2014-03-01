package net.trizmo.mtgcards;

import java.awt.Color;

public class DropBoxHandler {
	public static void createDropBoxes()
	{
		Screen.dropBox[0] = new DropBox(Screen.width / 20, Screen.height / 20, 400, 50, Color.WHITE, Color.BLACK); // Deck Drop box
		Screen.dropBox[1] = new DropBox((2 * (Screen.width / 20)) + 400, (Screen.height / 20), 400, 50, Color.WHITE, Color.BLACK); // Die options drop box
		
		for(int i = 0; i < Screen.deckAmmount; i++)
		{
			Screen.dropBox[0].addOption(Screen.deckNames[i].getDeckName());
		}
		
		//black, blue, green, red, white
		Screen.dropBox[1].addOption("Black");
		Screen.dropBox[1].addOption("Blue");
		Screen.dropBox[1].addOption("Green");
		Screen.dropBox[1].addOption("Red");
		Screen.dropBox[1].addOption("White");
	}
}
