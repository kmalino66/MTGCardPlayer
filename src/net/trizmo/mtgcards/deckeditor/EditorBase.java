package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.DeckNames;
import net.trizmo.mtgcards.DropBox;
import net.trizmo.mtgcards.SceneDrawer;
import net.trizmo.mtgcards.Screen;

public class EditorBase {
	
	public static DropBox deckPick = new DropBox(10, 10, 600, 50);
	public static DropBox setPick = new DropBox(10, 10, 600, 50);
	public static DropBox cardPick = new DropBox(10, 60, 600, 50);
	
	public boolean addCard = false;
	
	public EditorBase()
	{
		addCard = false;
	}
	
	public void addCardScreen()
	{
		if(addCard)
		{
			addCard = false;
		}else {
			addCard = true;
		}
	}

	public static void drawEditor(Graphics g, int scene)
	{
		if(scene == 3) {
			Screen.dropBox[3].drawDropBox(g);
						
			Image btnPlay = new ImageIcon("res/Button/ButtonPlay.png").getImage();
			SceneDrawer.playButton = new Rectangle(500, 0, Screen.buttonWidth, Screen.buttonHeight);
			g.drawImage(btnPlay, 500, 0, Screen.buttonWidth, Screen.buttonHeight, null);
		}
	
		

	
	}
	
	public static void addDeckNames(DeckNames[] deckNames)
	{
		for(int i = 0; i < deckNames.length; i++)
		{
			Screen.dropBox[3].addOption(deckNames[i].getDeckName());
		}
	}
}
