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
	public static DeckManagerButton addCardButton = new DeckManagerButton(700, 10, Screen.cardWidth, Screen.cardWidth / 5, "ButtonAddCard", "ButtonCancel", 1);
	public static DeckManagerButton ammountAddButton = new DeckManagerButton(Screen.width - Screen.cardWidth, Screen.cardHeight, Screen.cardWidth, Screen.cardWidth / 5, "ButtonChangeAmount", "ButtonAddCard", 1);
	
	public static boolean addCard = false;
	
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
			SceneDrawer.playButton = new Rectangle(610, 0, Screen.buttonWidth, Screen.buttonHeight);
			g.drawImage(btnPlay, 610, 0, Screen.buttonWidth, Screen.buttonHeight, null);
		}
		
		if(scene == 6) //Draw main editor
		{
			if(addCard)
			{
				addCardButton.setTextureNumber(2);
				ammountAddButton.setTextureNumber(2);
			}else
			{
				addCardButton.setTextureNumber(1);
				ammountAddButton.setTextureNumber(1);
			}
			
			addCardButton.drawButton(g);
			ammountAddButton.drawButton(g);
			setPick.drawDropBox(g);
			cardPick.drawDropBox(g);
			
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
