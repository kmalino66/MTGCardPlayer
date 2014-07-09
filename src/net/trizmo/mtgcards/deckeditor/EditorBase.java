package net.trizmo.mtgcards.deckeditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.Deck;
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
	public static DeckManagerCard[] deckCards;

	public static boolean addCard = false;

	public static void prepare()
	{
		addCard = false;

		String[] sets = Screen.getSets();

		for(int i = 0; i < sets.length; i++)
		{
			setPick.addOption(sets[i]);
		}

		deckCards = new DeckManagerCard[Screen.cardList.length];

		for(int i = 0; i < deckCards.length; i++)
		{
			if(Screen.cardList[i] != null) 
			{
				deckCards[i] = new DeckManagerCard(Screen.cardList[i].getId(), Screen.cardList[i].getName(), Screen.cardList[i].getSetName(), 0);
			}else {
				deckCards[i] = null;
			}
		}
		@SuppressWarnings("unused")
		Deck[] par1temp = Screen.deck;
		System.out.println(Screen.deck.length);
		for(int i = 0; i < Screen.deck.length; i++)
		{
			for(int j = 0; j < deckCards.length; j++)
			{
				if(deckCards[j] != null && Screen.deck[i].getCardId() == deckCards[j].getId())
				{
					deckCards[j].setAmountOfCard(Screen.deck[i].getAmmountOfCard());
				}
			}
		}


	}

	public static void addCardScreen()
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
			addCardsToDropbox(addCard);
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



			if(setPick.getOpened()){
				try{
					cardPick.drawDropBox(g);
				} catch (NullPointerException e){

				}

				setPick.drawDropBox(g);
			}else{
				setPick.drawDropBox(g);

				try{
					cardPick.drawDropBox(g);
				} catch (NullPointerException e){

				}
			}
		}

	}

	public static void addDeckNames(DeckNames[] deckNames)
	{
		for(int i = 0; i < deckNames.length; i++)
		{
			Screen.dropBox[3].addOption(deckNames[i].getDeckName());
		}
	}

	public static void addCardsToDropbox(boolean par1Boolean)
	{
		cardPick.removeOptions();

		if(!par1Boolean)
		{

			for(int i = 0; i < deckCards.length; i++)
			{
				String pickedSet = setPick.getSelected();

				if(deckCards[i] != null && deckCards[i].getSetName().equals(pickedSet) && deckCards[i].getAmountOfCard() > 0)
				{
					cardPick.addOption(deckCards[i].getCardName());
				}
			}
		}else {
			for(int i = 0; i < deckCards.length; i++)
			{
				String pickedSet = setPick.getSelected();

				if(deckCards[i] != null && deckCards[i].getSetName().equals(pickedSet))
				{
					cardPick.addOption(deckCards[i].getCardName());
				}
			}
		}
	}

	public static void handleClick(MouseEvent e)
	{
		setPick.checkClicked(e);
		cardPick.checkClicked(e);

		if(setPick.clickedBool(e))
		{
			addCardsToDropbox(addCard);
		}

		if(addCardButton.getClicked(e))
		{
			addCardScreen();
		}
	}

}
