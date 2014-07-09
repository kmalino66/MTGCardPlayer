package net.trizmo.mtgcards.input;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.CardHandler;
import net.trizmo.mtgcards.Screen;
import net.trizmo.mtgcards.StackManager;

public class ButtonHandler {
	public static Rectangle playButton;
	public static Rectangle editDeckButton;
	public static Rectangle closeButton;
	public static Rectangle sealedButton;

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
		Image btnSealed = new ImageIcon("res/Button/ButtonSealedPlay.png").getImage();
		
		g.drawImage(btnPlay, Xpos, middleY - (buttonH / 2), buttonW, buttonH, null);
		g.drawImage(btnEdit, Xpos , gap + (middleY - (buttonH / 2)) + buttonH, buttonW, buttonH, null);
		g.drawImage(btnSealed, Xpos, (gap * 2) + middleY - (buttonH/2) + (buttonH * 2), buttonW, buttonH, null);
		g.drawImage(btnClose, Xpos , (gap * 3) + middleY - (buttonH/2) + (buttonH * 3), buttonW, buttonH, null);
		
		playButton = new Rectangle(Xpos, middleY - (buttonH / 2), buttonW, buttonH);
		editDeckButton = new Rectangle(Xpos, gap + middleY - (buttonH / 2) + buttonH, buttonW, buttonH);
		closeButton = new Rectangle(Xpos, (gap * 3) + middleY - (buttonH / 2) + (buttonH * 3), buttonW, buttonH);
		sealedButton = new Rectangle(Xpos, (gap * 2) + middleY - (buttonH / 2) + (buttonH * 2), buttonW, buttonH);
		
		//int par1Height = (int)(buttonW * 2) * (500/1024);
		
		g.drawImage(new ImageIcon("res/Background/CardPlayerLogo.png").getImage(), buttonW / 2, -gap * 5, (int)(buttonW * 2), (int)((buttonW * 2) * .48), null);
		
	}
	
	public static void scene0Click(MouseEvent e) {
		if(playButton.contains(e.getPoint())) {
			Screen.changeScene(1);
		}else if(editDeckButton.contains(e.getPoint())) {
			Screen.changeScene(3);
		}else if(closeButton.contains(e.getPoint())) {
			Screen.stopGame();
		}else if(sealedButton.contains(e.getPoint())) {
			Screen.changeScene(4);
		}
		
	}
	
	public static void scene1Click(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width / 20, Screen.height - 100, 500, 100);
		if(rect.contains(e.getPoint())) Screen.playGame();
	}
	
	public static void scene2Click(MouseEvent e)
	{
		
		//Check next turn button clicked
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
		
		//Check reshuffle button clicked
		Rectangle rect1 = new Rectangle(Screen.width - 200, 20, 100, 20);
		if(rect1.contains(e.getPoint()))
		{
			CardHandler.reshuffle(); //Reshuffles cards
		}
		
		//Check mullagain button clicked
		Rectangle rect2 = new Rectangle(Screen.width - 200, 40, 100, 20);
		if(rect2.contains(e.getPoint()))
		{
			CardHandler.mullagain(Screen.mullagainNumber);
			Screen.mullagainNumber++;
		}
		
		//Check tap all lands button clicked
		Rectangle rect3 = new Rectangle(Screen.width - 200, 60, 100, 20);
		if(rect3.contains(e.getPoint()))
		{
			CardHandler.tapAllLands();
		}
	}
	
	public static void scene3Click(MouseEvent e)
	{
		
	}
}
