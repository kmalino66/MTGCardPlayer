package net.trizmo.mtgcards.deckeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import net.trizmo.mtgcards.Card;
import net.trizmo.mtgcards.CoutHandler;
import net.trizmo.mtgcards.Deck;
import net.trizmo.mtgcards.DeckNames;
import net.trizmo.mtgcards.DropBox;
import net.trizmo.mtgcards.FileManager;
import net.trizmo.mtgcards.SceneDrawer;
import net.trizmo.mtgcards.Screen;

public class EditorBase {

	public static DropBox deckPick = new DropBox(10, 10, 600, 50);
	public static DropBox setPick = new DropBox(10, 10, 600, 50);
	public static DropBox cardPick = new DropBox(10, 60, 600, 50);
	public static DeckManagerButton addCardButton = new DeckManagerButton(700, 10, Screen.cardWidth, Screen.cardWidth / 5, "ButtonAddCard", "ButtonCancel", 1);
	public static DeckManagerButton ammountAddButton = new DeckManagerButton(Screen.width - (Screen.cardWidth * 2), Screen.cardHeight * 2, Screen.cardWidth * 2, (Screen.cardWidth / 5) * 2, "ButtonChangeAmount", "ButtonAddCard", 1);
	public static DeckManagerButton closeButton	= new DeckManagerButton((Screen.width / 2) - 251, Screen.height / 2, 502, 100, "ButtonClose", "", 1);
	public static DeckManagerButton addButton = new DeckManagerButton((Screen.width / 2) - 251, (Screen.height / 2) - 100, 201, 100, "ButtonPlus", "", 1);
	public static DeckManagerButton minusButton = new DeckManagerButton((Screen.width / 2) + 50, (Screen.height / 2) - 100, 201, 100, "ButtonMinus", "", 1);
	public static DeckManagerButton saveButton = new DeckManagerButton(0, Screen.height - ((Screen.cardWidth / 5) * 2), Screen.cardWidth * 2, (Screen.cardWidth / 5) * 2, "ButtonSave", "", 1);
	public static DeckManagerCard[] deckCards;

	public static boolean addCard = false;
	public static boolean quantityChangeScreen;

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

		g.setColor(Color.white);

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


			if(quantityChangeScreen)
			{
				g.fillRect((Screen.width / 2) - 251, (Screen.height / 2) - 100, 502, 200);
				addButton.drawButton(g);
				minusButton.drawButton(g);
				closeButton.drawButton(g);

			}

			if(setPick.getSelected() != null && cardPick.getSelected() != null)
			{
				for(int i = 0; i < Screen.cardList.length; i++)
				{
					if(Screen.cardList[i] != null && Screen.cardList[i].getName().equals(cardPick.getSelected()))
					{
						String par1TextureName = Screen.cardList[i].getTextureName();
						String par1SetName = Screen.cardList[i].getSetName();

						g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + par1SetName + "/" + par1TextureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

						if(quantityChangeScreen)
						{
							g.setColor(Color.black);
							boolean par1IsCard = false;
							for(int j = 0; j < Screen.deck.length; j++)
							{
								if(Screen.deck[j].getCardId() == Screen.cardList[i].getId())
								{
									g.drawString(Screen.deck[j].getAmmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 90);
									par1IsCard = true;
								}
							}
							if(!par1IsCard)
							{
								g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 90);
							}
						}
					}
				}
			}
			
			saveButton.drawButton(g);
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

	//Called whenever there is a click on the deck editor scenes.
	public static void handleClick(MouseEvent e)
	{
		setPick.checkClicked(e);
		cardPick.checkClicked(e);

		if(addCardButton.getClicked(e))
		{
			addCardScreen();
		}

		if(ammountAddButton.getClicked(e))
		{
			quantityChangeScreen = true;
		}

		if(quantityChangeScreen)
		{
			if(closeButton.getClicked(e))
			{
				quantityChangeScreen = false;
			}

			if(addButton.getClicked(e))
			{
				int amount = searchAmountOfCardsInDeck();

				if(amount > 0)
				{
					int selectedId = searchSelectedCardId();

					for(int i = 0; i < Screen.deck.length; i++)
					{
						if(Screen.deck[i].getCardId() == selectedId)
						{
							Screen.deck[i].adjustCardAmmount(1);
						}
					}
				}else {
					int selectedId = searchSelectedCardId();
					expandDeck(selectedId);
				}
				
			}

			if(minusButton.getClicked(e))
			{
				int amount = searchAmountOfCardsInDeck();

				if(amount > 0)
				{
					int selectedId = searchSelectedCardId();

					for(int i = 0; i < Screen.deck.length; i++)
					{
						if(Screen.deck[i].getCardId() == selectedId)
						{
							Screen.deck[i].adjustCardAmmount(-1);
						}
					}
				}else
				{

				}
			}
		}
		//TODO Make a save button for the deck editor.
		if(saveButton.getClicked(e))
		{
			FileManager.saveDeck(Screen.chosenDeck);
		}
	}

	//Searches for how many of the selected card is in the deck.
	public static int searchAmountOfCardsInDeck()
	{
		Deck[] par1Deck = Screen.deck;
		if(setPick.getSelected() != null && cardPick.getSelected() != null)
		{
			for(int i = 0; i < Screen.cardList.length; i++)
			{
				if(Screen.cardList[i] != null);
				{

					for(int j = 0; j < Screen.deck.length; j++)
					{
						if(Screen.cardList[i] != null && Screen.deck[j].getCardId() == Screen.cardList[i].getId())
						{
							if( Screen.cardList[i].getName().equals(cardPick.getSelected())){
								return Screen.deck[j].getAmmountOfCard();

							}
						}
					}
				}
			}
		}
		return 0;
	}

	//Gets the id for the currently selected card
	public static int searchSelectedCardId()
	{
		if(setPick.getSelected() != null && cardPick.getSelected() != null)
		{
			for(int i = 0; i < Screen.cardList.length; i++)
			{
				if(Screen.cardList[i] != null && Screen.cardList[i].getSetName().equals(setPick.getSelected()) && Screen.cardList[i].getName().equals(cardPick.getSelected()))
				{
					return Screen.cardList[i].getId();
				}
			}
		}
		return 0;
	}

	//Expands the deck array by 1 to allow for a new card being added to the deck.
	public static void expandDeck(int cardId)
	{
		int arrayMod = -1;
		Deck[] par1Deck = new Deck[Screen.deck.length + 1];

		for(int i = 0; i < Screen.deck.length; i++)
		{
			par1Deck[i] = Screen.deck[i];
		}

		for(int i = 0; i < Screen.cardList.length; i++)
		{
			if(Screen.cardList[i] != null && Screen.cardList[i].getId() == cardId)
			{
				arrayMod = i;
			}
		}
		if(Screen.cardList[arrayMod] != null) {
			par1Deck[par1Deck.length - 1] = new Deck(cardId, Screen.cardList[arrayMod].getName(), Screen.cardList[arrayMod].getRarity(), Screen.cardList[arrayMod].getSetName(), Screen.cardList[arrayMod].getTextureName(), 1);
		}
		Screen.deck = null;

		Screen.deck = par1Deck;
		

	}

}
