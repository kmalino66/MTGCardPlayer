package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class SceneDrawer {

	public static int barHeight;
	public static int boxWidth;
	public static int pictureHeight;
	
	public static void scene2(Graphics g)
	{
		barHeight = Screen.cardHeight + 15;
		boxWidth = Screen.cardWidth + 30;
		pictureHeight = boxWidth / (275 / 50);
		
		g.setColor(new Color(100, 100, 100, 90));
		g.fillRect(0, Screen.height- (15 + Screen.cardHeight), Screen.width, barHeight);
		
		g.setColor(Color.black);
		
		//Exiled
		g.drawImage(new ImageIcon("res/Background/exiled.png").getImage(), Screen.width - boxWidth, Screen.height - barHeight - pictureHeight, 
				boxWidth, pictureHeight, null);
		g.fillRect(Screen.width - 15 - boxWidth, Screen.height - barHeight, 15, barHeight);
		
		//Graveyard
		g.drawImage(new ImageIcon("res/Background/graveyard.png").getImage(), Screen.width - (boxWidth * 2) - 15, Screen.height - barHeight - pictureHeight, 
				boxWidth, pictureHeight, null);
		g.fillRect(Screen.width - (15 * 2) - (boxWidth * 2), Screen.height - barHeight, 15, barHeight);

		//Library
		g.drawImage(new ImageIcon("res/Background/Library.png").getImage(), Screen.width - (boxWidth * 3) - 30, Screen.height - barHeight - pictureHeight,
				boxWidth, pictureHeight, null);
		g.fillRect(Screen.width - (15 * 3) - (boxWidth * 3), Screen.height - barHeight, 15, barHeight);
		
		//Life box
		g.fillRect(Screen.width - 100, 0, 100, 125);
		
		//Next turn button
		g.drawImage(new ImageIcon("res/Button/ButtonNextTurn.png").getImage(), Screen.width - 200, 0, null);
		
		//Life Buttons
		int par1Height = 50;
		int par2Width = 50 / 5;
		
		
		
	}
	
}
