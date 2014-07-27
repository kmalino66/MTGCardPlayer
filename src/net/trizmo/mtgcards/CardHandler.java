package net.trizmo.mtgcards;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Random;

import net.trizmo.mtgcards.deckeditor.DeckManagerButton;
import net.trizmo.mtgcards.inCameCards.BattlefieldCard;
import net.trizmo.mtgcards.inCameCards.ExiledCard;
import net.trizmo.mtgcards.inCameCards.GraveyardCard;
import net.trizmo.mtgcards.inCameCards.HandCard;
import net.trizmo.mtgcards.inCameCards.LibraryCard;
import net.trizmo.mtgcards.inCameCards.ZoomCard;
import net.trizmo.mtgcards.input.ButtonHandler;

public class CardHandler {

	public static boolean mouseDown;

	public static Rectangle[] spotLocations = new Rectangle[5];

	public static CardInteract interactionCard;

	public static int firstOpen;
	public static boolean alreadyScanned = false;
	public static boolean moved;
	public static Point par1Point = new Point(0,0);

	public static ZoomCard zoomCard;

	public static DeckManagerButton[] counterButtons;

	public static void mousePressed(MouseEvent e)
	{

		mouseDown = true;
		if(!Screen.zoom ){
			if(Screen.scene == 2) interactionCard = getInteractCard(e);
			Rectangle rect = new Rectangle(Screen.width - 100, 100, 100, 120);

			if(Screen.scene == 2)
			{
				if(rect.contains(e.getPoint()))
				{
					LifeHandler.changeLife(e);
				}
				rect = null;
			}
		}
	}
	public static void mouseDragged(MouseEvent e)
	{
		if(interactionCard != null){
			moved = true;
			moveCard();
		}
	}

	public static void mouseReleased(MouseEvent e)
	{
		mouseDown = false;
		if(interactionCard != null && Screen.scene == 2 && e.getButton() != 2 && moved)
		{

			if(spotLocations[0].contains(e.getPoint()))
			{
				for(int i = Screen.libraryCards.length - 1; i >= 0; i--)
				{
					if (Screen.libraryCards[i] == null)
					{
						firstOpen = i;
					}
				}

				Screen.libraryCards[firstOpen] = new LibraryCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getCardName(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getRarity());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}else if(spotLocations[1].contains(e.getPoint()))
			{
				for(int i = Screen.handCards.length - 1; i >= 0; i--)
				{
					if(Screen.handCards[i] == null)
					{
						firstOpen = i;
					}
				}

				Screen.handCards[firstOpen] = new HandCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getCardName(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage(), firstOpen, Screen.battlefieldCards[interactionCard.getArrayLocation()].getRarity());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}else if(spotLocations[3].contains(e.getPoint()))
			{
				for(int i = Screen.graveyardCards.length - 1; i >= 0; i--)
				{
					if (Screen.graveyardCards[i] == null) firstOpen = i;
				}


				Screen.graveyardCards[firstOpen] = new GraveyardCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getCardName(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getRarity());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}else if(spotLocations[4].contains(e.getPoint()))
			{
				for(int i = Screen.exiledCards.length - 1; i >= 0; i--)
				{
					if (Screen.exiledCards[i] == null) firstOpen = i;
				}


				Screen.exiledCards[firstOpen] = new ExiledCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getCardName(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage(), Screen.battlefieldCards[interactionCard.getArrayLocation()].getRarity());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}
			interactionCard = null;

		}
		moved = false;
	}
	public static void splitByState()
	{

		Screen.battlefieldCards = new BattlefieldCard[Screen.totalCardsInDeck];
		Screen.exiledCards = new ExiledCard[Screen.totalCardsInDeck];
		Screen.graveyardCards = new GraveyardCard[Screen.totalCardsInDeck];
		Screen.libraryCards = new LibraryCard[Screen.totalCardsInDeck];
		Screen.handCards = new HandCard[Screen.totalCardsInDeck];

		for(int i = 0; i < Screen.deckCard.length; i++)
		{
			int state = 0;
			if(Screen.deckCard[i] != null){
				if(Screen.deckCard[i].getIsInLibrary())
				{
					state = 1;
				}else if(Screen.deckCard[i].getIsInGraveyard())
				{
					state = 2;
				}else if(Screen.deckCard[i].getIsExiled())
				{
					state = 3;
				}else if(Screen.deckCard[i].getIsBattlefield())
				{
					state = 4;
				}else if(Screen.deckCard[i].getIsInHand())
				{
					state = 5;
				}
			}

			switch (state)
			{
			case 1:
				putInLibrary(i);
				break;

			case 2:
				putInGraveyard(i);
				break;

			case 3:
				putInExile(i);
				break;

			case 4:
				putInBattlefield(i);
				break;

			case 5:
				putInHand(i);
				break;
			}
		}
	}

	public static void putInLibrary(int index)
	{
		for(int i = 0; i < Screen.libraryCards.length; i++)
		{
			if(Screen.libraryCards[i] == null)
			{
				Screen.libraryCards[i] = new LibraryCard(Screen.deckCard[index].getCardName(), Screen.deckCard[index].getTextureImage(), Screen.deckCard[index].getRarity());
				break;
			}
		}
	}

	public static void putInGraveyard(int index)
	{
		for(int i = 0; i < Screen.graveyardCards.length; i++)
		{
			if(Screen.graveyardCards[i] == null)
			{
				Screen.graveyardCards[i] = new GraveyardCard(Screen.deckCard[index].getCardName(), Screen.deckCard[index].getTextureImage(), Screen.deckCard[index].getRarity());
				break;
			}
		}
	}

	public static void putInExile(int index)
	{
		for(int i = 0; i < Screen.exiledCards.length; i++)
		{
			if(Screen.graveyardCards[i] == null)
			{
				Screen.exiledCards[i] = new ExiledCard(Screen.deckCard[index].getCardName(), Screen.deckCard[index].getTextureImage(), Screen.deckCard[index].getRarity());
				break;
			}
		}
	}

	public static void putInBattlefield(int index)
	{
		for(int i = 0; i < Screen.battlefieldCards.length; i++)
		{
			if(Screen.battlefieldCards[i] == null)
			{
				Screen.battlefieldCards[i] = new BattlefieldCard(Screen.deckCard[index].getCardName(), Screen.deckCard[index].getTextureImage(), Screen.deckCard[index].getX(), 
						Screen.deckCard[index].getY(),Screen.deckCard[index].getRarity(), Screen.deckCard[index].getTapped());
				break;
			}
		}
	}

	public static void putInHand(int index)
	{
		for(int i = 0; i < Screen.handCards.length; i++)
		{
			if(Screen.handCards[i] == null)
			{
				Screen.handCards[i] = new HandCard(Screen.deckCard[index].getCardName(), Screen.deckCard[index].getTextureImage(), i, Screen.deckCard[index].getRarity());
				break;
			}
		}
	}

	public static CardInteract getInteractCard(MouseEvent e)
	{

		CardInteract par1, par2, par3, par4, par5;

		par1 = checkLibraryClicked(e);
		par2 = checkHandClicked(e);
		par3 = checkBattlefieldClicked(e);
		par4 = checkGraveyardClicked(e);
		par5 = checkExileClicked(e);

		if(par3 != null){
			return par3;
		}else if (par2 != null){
			return par2;
		}else if (par1 != null){
			return par1;
		}else if (par4 != null){
			return par4;
		}else if (par5 != null){
			return par5;
		}else{
			return null;
		}


	}

	public static CardInteract checkLibraryClicked(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width - ((Screen.cardWidth + 30) * 3) - 15, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth, Screen.cardHeight);
		if(rect.contains(e.getPoint())){
			int index = -1;//, par1Index = (Integer) null;

			for(int i = Screen.libraryCards.length - 1; i >= 0; i--)
			{
				if(Screen.libraryCards[i] != null)
				{
					index = i;
					break;
				}
			}

			return new CardInteract(0, index);
		} else {
			return null;
		}
	}

	public static CardInteract checkHandClicked(MouseEvent e)
	{
		int index = -1;
		for(int i = 0; i < Screen.handCards.length; i++)
		{
			if(Screen.handCards[i] != null)
			{
				if(Screen.handCards[i].contains(e))
				{
					index = i;
				}
			}
		}

		if(index != -1)
		{
			return new CardInteract(1, index);
		}else 
		{
			return null;
		}

	}

	public static CardInteract checkBattlefieldClicked(MouseEvent e)
	{
		int index = -1;
		for(int i = Screen.battlefieldCards.length - 1; i >= 0; i--)
		{

			if(Screen.battlefieldCards[i] != null)
			{
				if(Screen.battlefieldCards[i].contains(e))
				{
					index = i;
					break;
				}
			}
		}

		if(index != -1)
		{
			return new CardInteract(2, index);
		}else
		{
			return null;
		}
	}

	public static CardInteract checkGraveyardClicked(MouseEvent e)
	{
		int index = -1;
		for(int i = Screen.graveyardCards.length - 1; i >= 0; i--)
		{
			if(Screen.graveyardCards[i] != null ) {
				if( CardHandler.spotLocations[3].contains(e.getPoint()))
				{
					index = i;
					break;
				}
			}
		}

		if(index != -1)
		{
			return new CardInteract(3, index);
		}else
		{
			return null;
		}
	}

	public static CardInteract checkExileClicked(MouseEvent e)
	{
		int index = -1;
		for(int i = Screen.exiledCards.length - 1; i >= 0; i--)
		{
			if(Screen.exiledCards[i] != null)
			{
				if(CardHandler.spotLocations[4].contains(e.getPoint()))
				{
					index = i;
					break;
				}
			}
		}

		if(index != -1)
		{
			return new CardInteract(4, index);
		}else
		{
			return null;
		}
	}

	public static void moveCard()
	{

		int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX() - Screen.frameX;
		int mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY() - Screen.frameY;

		switch(interactionCard.getLocation())
		{
		case 0:
			//Transfer a library card to the battlefield for movement.
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.libraryCards[interactionCard.getArrayLocation()].getCardName(), Screen.libraryCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, Screen.libraryCards[interactionCard.getArrayLocation()].getRarity(), false);
					Screen.libraryCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 1:
			//Transfer a hand card to the battlefield for movement
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{

					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.handCards[interactionCard.getArrayLocation()].getCardName(), Screen.handCards[interactionCard.getArrayLocation()].getTextureImage(), mouseX, mouseY, Screen.handCards[interactionCard.getArrayLocation()].getRarity(), false);
					Screen.handCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 2:
			//Transfer a battlefield card to a battlefield card to bring it to the front and move it
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null && i != interactionCard.getArrayLocation() - 1)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.battlefieldCards[interactionCard.getArrayLocation()]);
					Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			if(!alreadyScanned){

				par1Point = new Point(Screen.battlefieldCards[interactionCard.getArrayLocation()].getPosOnCard(mouseX, mouseY));
				alreadyScanned = true;
			}

			Screen.battlefieldCards[interactionCard.getArrayLocation()].setPos((int)(mouseX - par1Point.getX()), (int) (mouseY - par1Point.getY()));

			break;
		case 3:
			//Transfer a graveyard card to the battlefield for movement
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.graveyardCards[interactionCard.getArrayLocation()].getCardName(), Screen.graveyardCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, Screen.graveyardCards[interactionCard.getArrayLocation()].getRarity(), false);
					Screen.graveyardCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 4:
			//Transfer an exiled card to the battlefield for movement.
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.exiledCards[interactionCard.getArrayLocation()].getCardName(), Screen.exiledCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, Screen.exiledCards[interactionCard.getArrayLocation()].getRarity(),  false);
					Screen.exiledCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);

					break;
				}
			}
			break;
		}
	}

	public static void doDraw(MouseEvent e)
	{
		CardInteract par1 = checkBattlefieldClicked(e);
		if(par1 != null){
			if(par1.getLocation() == 2)
			{

				if(Screen.battlefieldCards[par1.getArrayLocation()].getTapped())
				{
					Screen.battlefieldCards[par1.getArrayLocation()].setTapped(false);
				}else
				{
					Screen.battlefieldCards[par1.getArrayLocation()].setTapped(true);

				}
			}
		}else{
			ButtonHandler.scene2Click(e);

		}
	}

	public static void reshuffle()
	{
		Random rand = new Random();

		LibraryCard[] par1temp = new LibraryCard[Screen.libraryCards.length];

		for(int k = 0; k < par1temp.length; k++)
		{
			par1temp[k] = Screen.libraryCards[k];
		}

		for(int j = 0; j < Screen.libraryCards.length; j++)
		{
			Screen.libraryCards[j] = null;
		}

		for(int i = 0; i < par1temp.length; i++)
		{
			int par2 = rand.nextInt(Screen.libraryCards.length);

			if(par1temp[i] != null)
			{
				while(Screen.libraryCards[par2] != null)
				{
					par2 = rand.nextInt(Screen.libraryCards.length);
				}

				Screen.libraryCards[par2] = par1temp[i];
			}
		}
	}

	public static void mullagain(int mNum)
	{
		for(int i = 0; i < Screen.handCards.length; i++)
		{
			if(Screen.handCards[i] != null)
			{
				for(int j = Screen.libraryCards.length - 1; j > 0; j--)
				{
					if(Screen.libraryCards[j] == null)
					{
						Screen.libraryCards[j] = new LibraryCard(Screen.handCards[i].getCardName(), Screen.handCards[i].getTextureImage(), Screen.handCards[i].getRarity());
						Screen.handCards[i] = null;
						break;
					}
				}
			}
		}

		reshuffle();

		for(int i = 1; i < 8 - mNum; i++) StackManager.drawCard();
	}

	public static void zoom()
	{
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();

		if(zoomCard != null)
		{
			
			if(zoomCard.getPlace().equals("battlefield"))
			{
				Screen.battlefieldCards[zoomCard.getArrayIndex()].setCounterInfo(zoomCard.getCounterInfo());
			}
			zoomCard = null;
			
		}else{
			for(int i = Screen.exiledCards.length - 1; i >= 0; i--)
			{
				if(Screen.exiledCards[i] != null)
				{
					if(Screen.exiledCards[i].contains(mousePoint))
					{
						zoomCard = new ZoomCard(Screen.exiledCards[i].getImage(), "exile", i);
						break;
					}else {
						break;
					}
				}
			}

			for(int i = Screen.graveyardCards.length - 1; i >= 0; i--)
			{
				if(Screen.graveyardCards[i] != null)
				{
					if(Screen.graveyardCards[i].contains(mousePoint))
					{
						zoomCard = new ZoomCard(Screen.graveyardCards[i].getImage(), "graveyard", i);
						break;
					}else {
						break;
					}
				}
			}

			for(int i = Screen.handCards.length - 1; i >= 0; i--)
			{
				if(Screen.handCards[i] != null)
				{
					if(Screen.handCards[i].contains(mousePoint))
					{
						zoomCard = new ZoomCard(Screen.handCards[i].getTextureImage(), "hand", i);
						break;
					}
				}
			}

			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] != null && Screen.battlefieldCards[i].contains(mousePoint)) zoomCard = new ZoomCard(Screen.battlefieldCards[i].getImage(), Screen.battlefieldCards[i].counterInfo, "battlefield", i);
			}
		}

	}

	public static void drawZoomCard(Graphics g)
	{
		if(zoomCard != null)
		{
			Screen.zoom = true;
			g.drawImage(zoomCard.getImage(), (Screen.width / 2) - Screen.cardWidth, (Screen.height / 2) - Screen.cardHeight * 2, Screen.cardWidth * 2, Screen.cardHeight * 2, null);


			if(zoomCard.getPlace().equals("battlefield")) for(int i = 0; i < counterButtons.length; i++) counterButtons[i].drawButton(g);

		}else
		{
			Screen.zoom = false;
		}
	}

	public static void tapAllLands()
	{
		for(int i = 0; i < Screen.battlefieldCards.length; i++)
		{
			if(Screen.battlefieldCards[i] != null && Screen.battlefieldCards[i].getRarity() == 4)
			{
				Screen.battlefieldCards[i].setTapped(true);
			}
		}
	}

	public static void checkCounterButtons(MouseEvent e)
	{
		int par1 = 9;
		for(int i = 0; i < counterButtons.length; i++)
		{
			if(counterButtons[i].getClicked(e))
			{
				par1 = i;
			}
		}

		//power/toughness counter
		switch(par1)
		{
		case 0: 
			zoomCard.getCounterInfo().addPower();
			break;
		case 1:
			zoomCard.getCounterInfo().subPower();
			break;
		case 2:
			zoomCard.getCounterInfo().addToughness();
			break;
		case 3:
			zoomCard.getCounterInfo().subToughness();
			break;
		case 4:
			zoomCard.getCounterInfo().addCounter();
			break;
		case 5:
			zoomCard.getCounterInfo().subCounter();
		}

	}
}
