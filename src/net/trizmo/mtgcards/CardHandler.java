package net.trizmo.mtgcards;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Random;

import net.trizmo.mtgcards.inCameCards.BattlefieldCard;
import net.trizmo.mtgcards.inCameCards.ExiledCard;
import net.trizmo.mtgcards.inCameCards.GraveyardCard;
import net.trizmo.mtgcards.inCameCards.HandCard;
import net.trizmo.mtgcards.inCameCards.LibraryCard;
import net.trizmo.mtgcards.input.ButtonHandler;

public class CardHandler {

	public static boolean mouseDown;

	public static Rectangle[] spotLocations = new Rectangle[5];

	public static CardInteract interactionCard;

	public static int firstOpen;
	public static boolean alreadyScanned = false;
	public static Point par1Point = new Point(0,0);

	public static void mousePressed(MouseEvent e)
	{

		mouseDown = true;
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

	public static void mouseDragged(MouseEvent e)
	{
		if(interactionCard != null){
			moveCard();
		}
	}

	public static void mouseReleased(MouseEvent e)
	{
		mouseDown = false;
		if(interactionCard != null && Screen.scene == 2)
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

				Screen.libraryCards[firstOpen] = new LibraryCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage());
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

				Screen.handCards[firstOpen] = new HandCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage(), firstOpen);
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}else if(spotLocations[3].contains(e.getPoint()))
			{
				for(int i = Screen.graveyardCards.length - 1; i >= 0; i--)
				{
					if (Screen.graveyardCards[i] == null) firstOpen = i;
				}


				Screen.graveyardCards[firstOpen] = new GraveyardCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}else if(spotLocations[4].contains(e.getPoint()))
			{
				for(int i = Screen.exiledCards.length - 1; i >= 0; i--)
				{
					if (Screen.exiledCards[i] == null) firstOpen = i;
				}


				Screen.exiledCards[firstOpen] = new ExiledCard(Screen.battlefieldCards[interactionCard.getArrayLocation()].getImage());
				Screen.battlefieldCards[interactionCard.getArrayLocation()] = null;

			}
			interactionCard = null;

		}
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
				Screen.libraryCards[i] = new LibraryCard(Screen.deckCard[index].getTextureImage());
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
				Screen.graveyardCards[i] = new GraveyardCard(Screen.deckCard[index].getTextureImage());
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
				Screen.exiledCards[i] = new ExiledCard(Screen.deckCard[index].getTextureImage());
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
				Screen.battlefieldCards[i] = new BattlefieldCard(Screen.deckCard[index].getTextureImage(), Screen.deckCard[index].getX(), 
						Screen.deckCard[index].getY(), Screen.deckCard[index].getTapped());
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
				Screen.handCards[i] = new HandCard(Screen.deckCard[index].getTextureImage(), i);
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
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.libraryCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, false);
					Screen.libraryCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 1:

			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.handCards[interactionCard.getArrayLocation()].getTextureImage(), mouseX, mouseY, false);
					Screen.handCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 2:


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

			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.graveyardCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, false);
					Screen.graveyardCards[interactionCard.getArrayLocation()] = null;
					interactionCard = new CardInteract(2, i);
					break;
				}
			}

			break;
		case 4:

			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] == null)
				{
					Screen.battlefieldCards[i] = new BattlefieldCard(Screen.exiledCards[interactionCard.getArrayLocation()].getImage(), mouseX, mouseY, false);
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
						Screen.libraryCards[j] = new LibraryCard(Screen.handCards[i].getTextureImage());
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
		
		
	}
}
