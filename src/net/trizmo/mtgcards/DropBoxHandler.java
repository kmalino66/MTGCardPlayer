package net.trizmo.mtgcards;

import java.awt.Color;

public class DropBoxHandler {
	public static void createDropBoxes()
	{
		Screen.dropBox[0] = new DropBox(Screen.width / 20, Screen.height / 20, 600, 50, Color.WHITE, Color.BLACK); // Deck Drop box
		Screen.dropBox[1] = new DropBox((2 * (Screen.width / 20)) + 600, (Screen.height / 20), 600, 50, Color.WHITE, Color.BLACK); // Die options drop box
		Screen.dropBox[2] = new DropBox(10, 10, 600, 50, Color.white, Color.black); //For set options for sealed play
		Screen.dropBox[3] = new DropBox(10, 10, 600, 50, Color.white, Color.black);//For deck options for card Editor
		Screen.dropBox[4] = new DropBox(10, 60, 600, 50, Color.white, Color.black);//For set options for card editor

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
