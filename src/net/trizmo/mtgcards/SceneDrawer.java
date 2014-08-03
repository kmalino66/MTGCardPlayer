package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.inCameCards.BattlefieldCard;
import net.trizmo.mtgcards.inCameCards.LibraryCard;

public class SceneDrawer {

	public static int barHeight;
	public static int boxWidth;
	public static int pictureHeight;
	public static Rectangle playButton;

	public static DropBox[] cardSearch = new DropBox[3];

	public static void scene2(Graphics g)
	{
		//Removes all options from the dropboxes and adds the ones that are still in the array back in.
		addDropBoxOptions();
		barHeight = Screen.cardHeight + 15;
		boxWidth = Screen.cardWidth + 30;
		pictureHeight = boxWidth / (275 / 50);

		g.setColor(new Color(100, 100, 100, 90));
		g.fillRect(0, Screen.height- (15 + Screen.cardHeight), Screen.width, barHeight);

		g.setColor(Color.black);

		g.setColor(Color.black);
		//Exiled
		g.fillRect(Screen.width - 15 - boxWidth, Screen.height - barHeight, 15, barHeight);

		//Graveyard
		g.fillRect(Screen.width - (15 * 2) - (boxWidth * 2), Screen.height - barHeight, 15, barHeight);

		//Library
		g.fillRect(Screen.width - (15 * 3) - (boxWidth * 3), Screen.height - barHeight, 15, barHeight);

		//Life box
		g.fillRect(Screen.width - 100, 0, 100, 125);

		//Buttons for next turn and life
		g.drawImage(new ImageIcon("res/Button/ButtonNextTurn.png").getImage(), Screen.width - 200, 0, null);

		//Restart and Mullagain buttons
		g.drawImage(new ImageIcon("res/Button/ButtonReshuffle.png").getImage(), Screen.width - 200, 20, 100, 20, null);
		g.drawImage(new ImageIcon("res/Button/ButtonMullagain.png").getImage(), Screen.width - 200, 40, 100, 20, null);
		g.drawImage(new ImageIcon("res/Button/ButtonTapAllLands.png").getImage(), Screen.width - 200, 60, 100, 20, null);

	}

	public static void scene4(Graphics g)
	{
		Screen.dropBox[2].drawDropBox(g);

		Image btnPlay = new ImageIcon("res/Button/ButtonPlay.png").getImage();
		playButton = new Rectangle(10, 500, Screen.buttonWidth, Screen.buttonHeight);
		g.drawImage(btnPlay, 10, 500, Screen.buttonWidth, Screen.buttonHeight, null);
	}

	//Initializes the dropboxes for use.
	public static void preapareDropBoxesFor2()
	{
		//This block sets some important values used in the creation of the dropboxes.
		barHeight = Screen.cardHeight + 15;
		boxWidth = Screen.cardWidth + 30;
		pictureHeight = boxWidth / (275 / 50);
		
		
		//The idea is that when a card is selected, that card will leave the pile it was in and go to the battlefield.
		//cardSearch[0] is the dropbox to search though the exiled cards.
		cardSearch[0] = new DropBox(Screen.width - boxWidth, Screen.height - barHeight - pictureHeight, boxWidth, pictureHeight);

		//cardSearch[1] is the dropbox to search through the graveyard of cards.
		cardSearch[1] = new DropBox(Screen.width - (boxWidth * 2) - 15, Screen.height - barHeight - pictureHeight, boxWidth, pictureHeight);

		//cardSearch[2] is the dropbox to search through the library for a card.
		cardSearch[2] = new DropBox(Screen.width - (boxWidth * 3) - 30, Screen.height - barHeight - pictureHeight, boxWidth, pictureHeight);
		
		for(int i = 0; i < cardSearch.length; i++)
		{
			cardSearch[i].setHiddenDefault(true);
			cardSearch[i].setSelectedToNull();
		}
	}

	//Removes the options from the dropboxes and adds the cards in the piles back.
	public static void addDropBoxOptions()
	{
		//Remove the options from the dropboxes
		cardSearch[0].removeOptions();
		cardSearch[1].removeOptions();
		cardSearch[2].removeOptions();

		for(int i = 0; i < Screen.exiledCards.length; i++)
		{
			if(Screen.exiledCards[i] != null)
			{
				cardSearch[0].addOption(Screen.exiledCards[i].getCardName());
			}
		}

		for(int i = 0; i < Screen.graveyardCards.length; i++)
		{
			if(Screen.graveyardCards[i] != null)
			{
				cardSearch[1].addOption(Screen.graveyardCards[i].getCardName());
			}
		}

		for(int i = 0; i < Screen.libraryCards.length; i++)
		{
			if(Screen.libraryCards[i] != null)
			{
				cardSearch[2].addOption(Screen.libraryCards[i].getCardName());
			}
		}
	}
	
	public static void drawDropBoxesFor2(Graphics g)
	{
		cardSearch[0].drawDropBox(g);
		cardSearch[1].drawDropBox(g);
		cardSearch[2].drawDropBox(g);
	}
	
	public static void checkCardSelected()
	{
		String par1String = null;
		int dropBoxNumber = 4;
		for(int i = 0; i < cardSearch.length; i++)
		{
			if(cardSearch[i].getSelected() != null)
			{
				par1String = cardSearch[i].getSelected();
				cardSearch[i].setSelectedToNull();
				dropBoxNumber = i;
			}
		}
		
		switch(dropBoxNumber)
		{
		case 0:
			//Card picked from exiled cards.
			for(int i = 0; i < Screen.exiledCards.length; i++)
			{
				if(Screen.exiledCards[i] != null && par1String.equals(Screen.exiledCards[i].getCardName()))
				{
					for(int j = 0; j < Screen.battlefieldCards.length; j++)
					{
						if(Screen.battlefieldCards[j] == null)
						{
							Screen.battlefieldCards[j] = new BattlefieldCard(Screen.exiledCards[i].getCardName(), Screen.exiledCards[i].getImage(), 10, 10, Screen.exiledCards[i].getRarity(), false);
							Screen.exiledCards[i] = null;
							break;
						}
					}
				}
			}
			
			CoutHandler.error("Card " + par1String + " not found in the exiled cards pile");
			break;
			
		case 1:
			//Card picked from the graveyard cards.
			for(int i = 0; i < Screen.graveyardCards.length; i++)
			{
				if(Screen.graveyardCards[i] != null && Screen.graveyardCards[i].getCardName().equals(par1String))
				{
					for(int j = 0; j < Screen.battlefieldCards.length; j++)
					{
						if(Screen.battlefieldCards[j] == null)
						{
							Screen.battlefieldCards[j] = new BattlefieldCard(Screen.graveyardCards[i].getCardName(), Screen.graveyardCards[i].getImage(), 10, 10, Screen.graveyardCards[i].getRarity(), false);
							Screen.graveyardCards[i] = null;
							break;
						}
					}
				}
			}
			
			CoutHandler.error("Card " + par1String + " not found in the graveyard cards pile");
			break;
			
		case 2:
			//Card picked from the library.
			for(int i = 0; i < Screen.libraryCards.length; i++)
			{
				if(Screen.libraryCards[i] != null && Screen.libraryCards[i].getCardName().equals(par1String))
				{
					for(int j = 0; j < Screen.battlefieldCards.length; j++)
					{
						if(Screen.battlefieldCards[j] == null && Screen.libraryCards[i] != null)
						{
							LibraryCard[] par1Temp = Screen.libraryCards;
							Screen.battlefieldCards[j] = new BattlefieldCard(Screen.libraryCards[i].getCardName(), Screen.libraryCards[i].getImage(), 10, 10, Screen.libraryCards[i].getRarity(), false);
							Screen.libraryCards[i] = null;
						}
					}
				}
			}
			
			CoutHandler.error("Card " + par1String + " not fount in the library.");
			break;
		}
		
	}
}
