package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.SceneDrawer;
import net.trizmo.mtgcards.Screen;

public class EditorBase {
	
	

	public static void drawEditor(Graphics g, int scene)
	{
		if(scene == 3) {
			//We use dropbox 3
			Screen.dropBox[3].drawDropBox(g);
			
			Image btnPlay = new ImageIcon("res/Button/ButtonPlay.png").getImage();
			SceneDrawer.playButton = new Rectangle(500, 0, Screen.buttonWidth, Screen.buttonHeight);
			g.drawImage(btnPlay, 500, 0, Screen.buttonWidth, Screen.buttonHeight, null);

			
		}
	}
}
