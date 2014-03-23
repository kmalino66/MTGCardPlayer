package net.trizmo.mtgcards.input;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.Screen;
import net.trizmo.mtgcards.StackManager;

public class ButtonHandler {
	public static Rectangle playButton;
	public static Rectangle editDeckButton;
	public static Rectangle closeButton;

	public static void sceneFinder(int scene, Graphics g, int width, int height, int btnW, int btnH){
		if(scene == 0) drawScene0(g, width, height, btnW, btnH);
		
		
	}
	
	private static void drawScene0(Graphics g, int width, int height, int buttonW, int buttonH){
		int middleX = width / 2;
		int middleY = height / 2;
		int gap = buttonH / 10;
		int Xpos = (middleX - (buttonW / 2));
		Image btnPlay = new ImageIcon("res/Button/ButtonPlay.png").getImage();
		Image btnEdit = new ImageIcon("res/Button/ButtonEditDeck.png").getImage();
		Image btnClose = new ImageIcon("res/Button/ButtonClose.png").getImage();
		
		
		g.drawImage(btnPlay, Xpos, middleY - (buttonH / 2), buttonW, buttonH, null);
		g.drawImage(btnEdit, Xpos , gap + (middleY - (buttonH / 2)) + buttonH, buttonW, buttonH, null);
		g.drawImage(btnClose, Xpos , (gap * 2) + middleY - (buttonH/2) + (buttonH * 2), buttonW, buttonH, null);
		
		playButton = new Rectangle(Xpos, middleY - (buttonH / 2), buttonW, buttonH);
		editDeckButton = new Rectangle(Xpos, gap + middleY - (buttonH / 2) + buttonH, buttonW, buttonH);
		closeButton = new Rectangle(Xpos, gap + gap + middleY - (buttonH / 2) + buttonH + buttonH, buttonW, buttonH);
		
	}
	
	public static void scene0Click(MouseEvent e) {
		if(playButton.contains(e.getPoint())) {
			Screen.changeScene(1);
		}else if(editDeckButton.contains(e.getPoint())) {
			Screen.changeScene(2);
		}else if(closeButton.contains(e.getPoint())) {
			Screen.stopGame();
		}
		
	}
	
	public static void scene1Click(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width / 20, Screen.height - 100, 500, 100);
		if(rect.contains(e.getPoint())) Screen.playGame();
	}
	
	public static void scene2Click(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width - 200, 0, 100, 20);
		if(rect.contains(e.getPoint()))
		{
			StackManager.drawCard();
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] != null && Screen.battlefieldCards[i].getTapped())
				{
					Screen.battlefieldCards[i].setTapped(false);
				}
			}
		}
	}
}
