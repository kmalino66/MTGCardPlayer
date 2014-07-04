package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.DeckNames;
import net.trizmo.mtgcards.SceneDrawer;
import net.trizmo.mtgcards.Screen;

public class EditorBase {
	
	

	public static void drawEditor(Graphics g, int scene)
	{
		if(scene == 3) {
			//We use dropbox 3 and 4
			
			if(Screen.dropBox[3].getOpened())
			{
				Screen.dropBox[3].drawDropBox(g);
				Screen.dropBox[4].drawDropBox(g);
			}else if(Screen.dropBox[4].getOpened())
			{
				Screen.dropBox[4].drawDropBox(g);
				Screen.dropBox[3].drawDropBox(g);
			}else
			{
				Screen.dropBox[3].drawDropBox(g);
				Screen.dropBox[4].drawDropBox(g);
				
			}
			
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
