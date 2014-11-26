package net.trizmo.mtgcards.deckeditor;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

	public static DropBox lands, setPick0, cardPick0, setPick1, cardPick1, cardPick2;

	public static DeckManagerButton closeButton	= new DeckManagerButton((Screen.width / 2) - 251, Screen.height / 2, 502, 100, "ButtonClose", "", 1);
	public static DeckManagerButton addButton = new DeckManagerButton((Screen.width / 2) - 251, (Screen.height / 2) - 250, 201, 100, "ButtonPlus", "", 1);
	public static DeckManagerButton minusButton = new DeckManagerButton((Screen.width / 2) + 50, (Screen.height / 2) - 250, 201, 100, "ButtonMinus", "", 1);
	public static DeckManagerButton addButtonSide = new DeckManagerButton((Screen.width / 2) - 251, (Screen.height / 2) - 100, 201, 100, "ButtonPlus", "", 1);
	public static DeckManagerButton minusButtonSide = new DeckManagerButton((Screen.width / 2) + 50, (Screen.height / 2) - 100, 201, 100, "ButtonMinus", "", 1);
	public static DeckManagerButton saveButton = new DeckManagerButton(0, Screen.height - ((Screen.cardWidth / 5) * 2), Screen.cardWidth * 2, (Screen.cardWidth / 5) * 2, "ButtonSave", "", 1);
	public static DeckManagerButton newDeckButton = new DeckManagerButton(610 + Screen.buttonWidth, 0, Screen.buttonWidth, Screen.buttonHeight, "ButtonCreateNewDeck", "", 1);
	public static TypingBox deckNameBox = new TypingBox((Screen.width /2 ) - 100, (Screen.height / 2) - 50, 200, 100);
	public static DeckManagerButton newDeckOk = new DeckManagerButton((Screen.width / 2) - 100, (Screen.height / 2) + 50, 200, 100, "ButtonOK", "", 1);
	public static DeckManagerCard[] deckCards;
	//public static TypeBox deckNameBox = new TypeBox(0, 0, 250, 100, Color.BLACK, Color.WHITE);

	/**
	 * Contains the cards that were picked from sealed
	 * if applicable
	 * 
	 * Screen.deck is actual deck --- pointless
	 * deckCards is card list ---deck
	 */
	public static Deck[] cardsFromSealed;

	public static boolean quantityChangeScreen;
	public static boolean newDeckScreen = false;
	public static boolean displaySave;
	public static boolean editingSealedDeck = false;
	public static boolean secondarySealed = false;

	public static void prepare()
	{

		deckCards = new DeckManagerCard[Screen.cardList.length];

		for(int i = 0; i < deckCards.length; i++)
		{
			if(Screen.cardList[i] != null) 
			{
				deckCards[i] = new DeckManagerCard(Screen.cardList[i].getId(), Screen.cardList[i].getName(), Screen.cardList[i].getSetName(), 0, Screen.cardList[i].getRarity());
			}else {
				deckCards[i] = null;
			}
		}

		System.out.println(Screen.deck.length);
		for(int i = 0; i < Screen.deck.length; i++)
		{
			for(int j = 0; j < deckCards.length; j++)
			{
				if(deckCards[j] != null && Screen.deck[i].getCardId() == deckCards[j].getId())
				{
					if(editingSealedDeck && !secondarySealed)
					{
						deckCards[j].setSideboardAmount(Screen.deck[i].getAmmountOfCard());
					}else{
						deckCards[j].setAmountOfCard(Screen.deck[i].getAmmountOfCard());
						deckCards[j].setSideboardAmount(Screen.deck[i].getSideboardAmount());
					}

				}
			}
		}



		String[] sets = Screen.getSets();

		setPick0 = new DropBox(10, 10, 600, 50);
		cardPick0 = new DropBox(10, 60, 600, 50);

		setPick1 = new DropBox(610, 10, 600, 50);
		cardPick1 = new DropBox(610, 60, 600, 50);

		cardPick2 = new DropBox(1210, 10, 600, 50);

		for(int i = 0; i < sets.length; i++)
		{
			setPick0.addOption(sets[i]);
			setPick1.addOption(sets[i]);
		}
	}



	public static void prepare(Deck[] sealed)
	{
		cardsFromSealed = sealed;
		editingSealedDeck = true;
		prepare();

	}

	public static void prepare(boolean secondarySealed1)
	{
		secondarySealed = secondarySealed1;
		editingSealedDeck = true;
		prepare();
		CoutHandler.event("SEaled? " + secondarySealed1);
	}

	public static void drawEditor(Graphics g, int scene)
	{


		g.setColor(Color.white);

		if(scene == 3) {
			Screen.dropBox[3].drawDropBox(g);

			Image btnPlay = new ImageIcon("res/Button/ButtonPlay.png").getImage();
			SceneDrawer.playButton = new Rectangle(610, 0, Screen.buttonWidth, Screen.buttonHeight);
			g.drawImage(btnPlay, 610, 0, Screen.buttonWidth, Screen.buttonHeight, null);
			newDeckButton.drawButton(g);

			if(newDeckScreen)
			{
				//TODO Make a textBox to enter the desired deck name and then hit enter to input the name.
				//g.drawRect(0, 0, 250, 200);
				deckNameBox.drawComponent(g);
				newDeckOk.drawButton(g);
			}
		}

		if(scene == 6) //Draw main editor
		{

			showAmountOfCardsInDeck(g);


			if(setPick1.getOpened() || cardPick1.getOpened())
			{
				setPick0.setSelectedToNull();
				cardPick0.setSelectedToNull();
				cardPick0.removeOptions();
				cardPick2.setSelectedToNull();
				cardPick2.removeOptions();
			} else if(setPick0.getOpened() || cardPick0.getOpened())
			{
				setPick1.setSelectedToNull();
				cardPick1.setSelectedToNull();
				cardPick2.setSelectedToNull();
				cardPick1.removeOptions();
				cardPick2.removeOptions();
			}else if(cardPick2.getOpened())
			{
				setPick0.setSelectedToNull();
				setPick1.setSelectedToNull();
				cardPick0.setSelectedToNull();
				cardPick0.removeOptions();
				cardPick1.setSelectedToNull();
				cardPick1.removeOptions();
			}

			cardPick0.drawDropBox(g);
			cardPick1.drawDropBox(g);
			cardPick2.drawDropBox(g);

			if(setPick0.getOpened())
			{
				setPick0.drawDropBox(g);
				setPick1.drawDropBox(g);
			}else {
				setPick1.drawDropBox(g);
				setPick0.drawDropBox(g);
			}

			if(quantityChangeScreen)
			{
				g.fillRect(0, 0, Screen.width, Screen.height);
				g.fillRect((Screen.width / 2) - 251, (Screen.height / 2) - 250, 502, 350);
				addButton.drawButton(g);
				minusButton.drawButton(g);
				addButtonSide.drawButton(g);
				minusButtonSide.drawButton(g);
				closeButton.drawButton(g);

				g.setColor(Color.black);
				FontMetrics fm = g.getFontMetrics();

				g.drawString("Main Deck", (Screen.width / 2) - (fm.stringWidth("Main Deck") / 2), (Screen.height / 2) - 250);
				g.drawString("Sideboard", (Screen.width / 2) - (fm.stringWidth("Sideboard") / 2), (Screen.height / 2) - 50 - fm.getHeight());

			}
			addCardsToDropbox();

			if(editingSealedDeck)
			{
				if(cardPick0.getSelected() != null)
				{
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick0.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}else if(cardPick1.getSelected() != null){
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick1.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}else if(cardPick2.getSelected() != null)
				{
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick2.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}
			}else{

				if(setPick0.getSelected() != null && cardPick0.getSelected() != null)
				{
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick0.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}else if(setPick1.getSelected() != null && cardPick1.getSelected() != null)
				{
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick1.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}else if(cardPick2.getSelected()!= null)
				{
					for(int i = 0; i < Screen.cardList.length; i++)
					{
						if(Screen.cardList[i] != null && Screen.cardList[i].getName().equalsIgnoreCase(cardPick2.getSelected()))
						{
							String textureName = Screen.cardList[i].getTextureName();
							String setName = Screen.cardList[i].getSetName();

							g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/" + setName + "/" + textureName + ".jpg").getImage(), Screen.width - (Screen.cardWidth * 2), 0, Screen.cardWidth * 2, Screen.cardHeight * 2, null);

							if(quantityChangeScreen)
							{
								boolean par1IsCard = false;
								for(int j = 0; j < deckCards.length; j++)
								{
									if(deckCards[j] != null && deckCards[j].getId() == Screen.cardList[i].getId())
									{
										g.drawString(deckCards[j].getAmountOfCard() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
										g.drawString(deckCards[j].getSideboardAmount() + "", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
										par1IsCard = true;
									}
								}
								if(!par1IsCard)
								{
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 50);
									g.drawString("0", (Screen.width / 2) - 10, (Screen.height / 2) - 200);
								}
							}
						}
					}
				}
			}
		}


		saveButton.drawButton(g);

		if(displaySave)
		{
			g.setColor(Color.white);
			g.fillRect(Screen.width / 2 - 125, Screen.height / 2 - 50 , 250, 100);

			g.setColor(Color.black);
			g.drawString("Deck Saved", Screen.width / 2 - 120, Screen.height / 2);
		}
	}




	public static void addDeckNames(DeckNames[] deckNames)
	{
		for(int i = 0; i < deckNames.length; i++)
		{
			Screen.dropBox[3].addOption(deckNames[i].getDeckName());
		}
	}

	public static void addCardsToDropbox()
	{
		cardPick0.removeOptions();
		cardPick1.removeOptions();
		cardPick2.removeOptions();

		if(editingSealedDeck)
		{
			//Add the cards picked from the sealed play into the first dropbox.
			for(int i = 0; i < deckCards.length; i++)
			{
				if(setPick0.getSelected() != null && deckCards[i] != null && deckCards[i].getAmountOfCard() == 0 && deckCards[i].getSetName().equals(setPick0.getSelected()) && deckCards[i].getSideboardAmount() > 0)
				{
					cardPick0.addOption(deckCards[i].getCardName());
				}else if(setPick1.getSelected() != null && deckCards[i] != null && deckCards[i].getAmountOfCard() > 0 && deckCards[i].getSetName().equals(setPick1.getSelected()))
				{
					cardPick1.addOption(deckCards[i].getCardName());
				}else if(deckCards[i] != null && deckCards[i].getRarity() == 4)
				{
					cardPick2.addOption(deckCards[i].getCardName());
				}
			}
		}else{
			for(int i = 0; i < deckCards.length; i++)
			{
				if(setPick0.getSelected() != null && deckCards[i] != null && deckCards[i].getSetName().equals(setPick0.getSelected()) && deckCards[i].getAmountOfCard() == 0 && deckCards[i].getSideboardAmount() == 0)
				{
					cardPick0.addOption(deckCards[i].getCardName());
				}else if(setPick1.getSelected() != null && deckCards[i] != null && deckCards[i].getSetName().equals(setPick1.getSelected()))
				{
					if(deckCards[i].getAmountOfCard() > 0 || deckCards[i].getSideboardAmount() > 0)
					{
						cardPick1.addOption(deckCards[i].getCardName());
					}
				}else if(deckCards[i] != null && deckCards[i].getRarity() == 4)
				{
					cardPick2.addOption(deckCards[i].getCardName());
				}
			}
		}


	}


	//Called whenever there is a click on the deck editor scenes.
	public static void handleClick(MouseEvent e)
	{

		addCardsToDropbox();


		if(displaySave)
		{
			displaySave = false;
		}else{

			//This is where it checks if the dropboxes are cleared.
			if(!setPick0.getOpened())
			{
				cardPick0.checkClicked(e);
			}
			if(!setPick1.getOpened())
			{
				cardPick1.checkClicked(e);
			}

			cardPick2.checkClicked(e);
			setPick0.checkClicked(e);
			setPick1.checkClicked(e);
			

			if(quantityChangeScreen)
			{
				if(closeButton.getClicked(e))
				{
					quantityChangeScreen = false;
				}

				if(addButton.getClicked(e))
				{

					int selectedId = searchSelectedCardId();

					for(int i = 0; i < deckCards.length; i++)
					{
						if(deckCards[i] != null && deckCards[i].getId() == selectedId)
						{
							if(editingSealedDeck && /*checkSealed(selectedId, deckCards[i].getAmountOfCard())*/deckCards[i].getSideboardAmount() > 0)
							{
								deckCards[i].adjustCardAmmount(1);
								deckCards[i].adjustSideboardAmount(-1);

							}else if(!editingSealedDeck || deckCards[i].getRarity() == 4)
							{
								deckCards[i].adjustCardAmmount(1);
							}
						}
					}

				}

				if(minusButton.getClicked(e))
				{
					int selectedId = searchSelectedCardId();
					int[] amount = searchAmountOfCardsInDeck(selectedId);

					if(amount[0] > 0)
					{


						for(int i = 0; i < deckCards.length; i++)
						{
							if(deckCards[i] != null && deckCards[i].getId() == selectedId)
							{
								deckCards[i].adjustCardAmmount(-1);
								if(editingSealedDeck) deckCards[i].adjustSideboardAmount(1);
							}
						}
					}else
					{

					}
				}

				if(addButtonSide.getClicked(e))
				{
					int selectedId = -1;
					selectedId = searchSelectedCardId();

					if(selectedId != -1 && editingSealedDeck)
					{
						for(int i = 0; i < deckCards.length; i++)
						{
							if(deckCards[i] != null && deckCards[i].getId() == selectedId)
							{
								if(deckCards[i].getAmountOfCard() > 0)
								{
									deckCards[i].adjustCardAmmount(-1);
									deckCards[i].adjustSideboardAmount(1);
								}
							}
						}
					}else if(selectedId != -1 && !editingSealedDeck)
					{
						for(int i = 0; i < deckCards.length; i++)
						{
							if(deckCards[i] != null && deckCards[i].getId() == selectedId)
							{
								deckCards[i].adjustSideboardAmount(1);
							}
						}
					}
				}

				if(minusButtonSide.getClicked(e))
				{
					int selectedId = -1;
					selectedId = searchSelectedCardId();

					if(selectedId != -1 && editingSealedDeck)
					{
						for(int i = 0; i < deckCards.length; i++)
						{
							if(deckCards[i] != null && deckCards[i].getId() == selectedId)
							{
								if(deckCards[i].getSideboardAmount() > 0)
								{
									deckCards[i].adjustCardAmmount(1);
									deckCards[i].adjustSideboardAmount(-1);
								}
							}
						}
					}else if(selectedId != -1 && !editingSealedDeck)
					{
						for(int i = 0; i < deckCards.length; i++)
						{
							if(deckCards[i] != null && deckCards[i].getId() == selectedId)
							{
								if(deckCards[i].getSideboardAmount() > 0)
								{
									deckCards[i].adjustSideboardAmount(-1);
								}
							}
						}
					}
				}
			}
			if(saveButton.getClicked(e))
			{
				if(!editingSealedDeck)
				{
					FileManager.saveDeck(Screen.chosenDeck, deckCards);
				}else if(editingSealedDeck && secondarySealed)
				{
				}else {
					FileManager.saveNewSealedDeck(deckCards, cardsFromSealed);
					secondarySealed = true;
				}
				displaySave = true;
			}
		}
	}

	//Searches for how many of the selected card is in the deck.
	public static int[] searchAmountOfCardsInDeck(int cardId)
	{
		for(int i = 0; i < deckCards.length;i++)
		{
			if(deckCards[i] != null && deckCards[i].getId() == cardId)
			{
				return new int[] {deckCards[i].getAmountOfCard(), deckCards[i].getSideboardAmount()};
			}
		}
		return null;
	}

	//Gets the id for the currently selected card
	public static int searchSelectedCardId()
	{
		int switchNumber = -1, cardId = 0;
		String selectedCardName;

		if(cardPick0.getSelected() != "")
		{
			switchNumber = 0;
		}else if(cardPick1.getSelected() != "")
		{
			switchNumber = 1;
		}else if(cardPick2.getSelected() != "")
		{
			switchNumber = 2;
		}

		switch(switchNumber)
		{
		case 0:
			selectedCardName = cardPick0.getSelected();

			for(int i = 0; i < deckCards.length; i++)
			{
				if(deckCards[i] != null && deckCards[i].getCardName().equalsIgnoreCase(selectedCardName))
				{
					cardId = deckCards[i].getId();
				}
			}
			break;
		case 1:
			selectedCardName = cardPick1.getSelected();

			for(int i = 0; i > deckCards.length; i++)
			{
				if(deckCards[i] != null && deckCards[i].getCardName().equalsIgnoreCase(selectedCardName))
				{
					cardId = deckCards[i].getId();
				}
			}
			break;
		case 2:
			selectedCardName = cardPick2.getSelected();

			for(int i = 0; i < deckCards.length; i++)
			{
				if(deckCards[i] != null && deckCards[i].getCardName().equalsIgnoreCase(selectedCardName))
				{
					cardId = deckCards[i].getId();
				}
			}
			break;
		}

		return cardId;
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

	//Handles the click event for the new deck button on scene 3
	public static void handleClick3(MouseEvent e)
	{
		if(newDeckButton.getClicked(e))
		{
			//Sets the value of newDeckScreen to true to tell the drawing function to draw the new deck "panel"

			//TODO Create the new deck screen
			newDeckScreen = true;
		}


		if(newDeckScreen) deckNameBox.checkClicked(e);
		if(newDeckScreen && newDeckOk.getClicked(e))
		{
			try {
				FileManager.createNewDeck(deckNameBox.getText());
				FileManager.getDeckNames();
				FileManager.loadDeck(Screen.deckAmmount - 1);
				Screen.chosenDeck = Screen.deckAmmount -1;
				EditorBase.prepare();
				Screen.changeScene(6);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}


	}

	public static void showAmountOfCardsInDeck(Graphics g)
	{

		int par1CardAmount = 0;
		for(int i = 0; i < deckCards.length; i++)
		{
			if(deckCards[i] != null)
			{
				par1CardAmount += deckCards[i].getAmountOfCard();
			}
		}

		g.drawString("Amount of cards in deck (sideboard not included): " + par1CardAmount, 10, Screen.height / 2);

	}

	public static boolean checkSealed(int selectedId, int amountOfCard)
	{

		for(int i = 0; i < cardsFromSealed.length; i++)
		{
			if(cardsFromSealed[i] != null && cardsFromSealed[i].getCardId() == selectedId)
			{
				if(cardsFromSealed[i].getAmmountOfCard() <= amountOfCard)
				{
					return false;
				}else{
					return true;
				}
			}
		}

		return false;

	}
}
