package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.trizmo.mtgcards.inCameCards.BattlefieldCard;
import net.trizmo.mtgcards.inCameCards.ExiledCard;
import net.trizmo.mtgcards.inCameCards.GraveyardCard;
import net.trizmo.mtgcards.inCameCards.HandCard;
import net.trizmo.mtgcards.inCameCards.LibraryCard;
import net.trizmo.mtgcards.input.*;
import net.trizmo.mtgcards.deckeditor.*;

public class Screen extends JPanel implements Runnable, ActionListener {

	public static final double versionNumber = 1.1;
	public static final int versionID = 2;
	public static final String versionName = "Player Beta v1.1"; 
	/**
	 * TODO Other useful buttons
	 * TODO Card Zoom
	 * TODO Draw life text
	 */

	Thread thread = new Thread(this);
	Frame frame;
	Image background = new ImageIcon("res/Background/back1.jpg").getImage();
	Image menu = new ImageIcon("res/Background/Planswalkers.jpg").getImage();

	public JButton deckButton = new JButton("Ok");
	public JComboBox<String> deckPick = new JComboBox<String>();
	public JPanel pnl = new JPanel();
	public JPanel pnl1 = new JPanel();

	public int notOne = 0;
	public int handXPos;
	public int handYPos;

	public static int cardAmmount = FileManager.getCardAmmount();
	public static int deckAmmount = FileManager.getDeckAmmount();

	public static Card[] cardList = new Card[cardAmmount];
	public static Deck[] deck;
	public static DeckNames[] deckNames = new DeckNames[deckAmmount];
	public static PlayableCard[] deckCard;
	public static BattlefieldCard[] battlefieldCards;
	public static ExiledCard[] exiledCards;
	public static GraveyardCard[] graveyardCards;
	public static LibraryCard[] libraryCards;
	public static HandCard[] handCards;
	public static DropBox[] dropBox = new DropBox[10];
	public static Rectangle[] lifeBoxes = new Rectangle[6];
	public static Token[] tokens;
	public static Card[] commonCards, uncommonCards, rareCards, mythicRareCards, specialCards;
	public static Card[][] sealedPacks = null;
	public static String[] sets = null;

	public static MouseEvent mEvent;
	public static Font customFont;
	public static Image dice;

	public static int width;
	public static int height;
	public static int buttonWidth;
	public static int buttonHeight;
	public static int totalCardsInDeck = 0;
	public static int cardInteraction = -1;
	public static int cardWidth;
	public static int cardHeight;
	public static int scene = 0;
	public static int mullagainNumber = 0;
	public static int frameX;
	public static int frameY;

	public static int lifeAmmount;

	public boolean isNewScene = false;
	public static boolean running = false;
	public static boolean whiteText = false;

	private static final long serialVersionUID = 1L;

	//Creates the screen.
	public Screen(Frame frame) 
	{

		this.frame = frame;

		width = this.frame.getWidth();
		height = this.frame.getHeight();

		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler(this));
		this.frame.addKeyListener(new KeyHandler(this));

		buttonWidth = this.frame.getWidth() / 3;
		buttonHeight = buttonWidth / 5;

		cardWidth = (int)(width * (187.5 / width));
		cardHeight = (int)((width * (187.5 / width)) / .717);

		CardHandler.spotLocations[0] = new Rectangle(Screen.width - ((Screen.cardWidth + 30) * 3) - 30, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);
		CardHandler.spotLocations[1] = new Rectangle(0, Screen.height - ((Screen.cardHeight + 15) - (15/2)), width - (15 * 3) - ((cardWidth + 30) * 3), cardHeight + 15);
		CardHandler.spotLocations[3] = new Rectangle(Screen.width - ((Screen.cardWidth + 30) * 2) - 15, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);
		CardHandler.spotLocations[4] = new Rectangle(Screen.width - (Screen.cardWidth + 30), Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);

		lifeBoxes[0] = new Rectangle(width - 100, 100, 100, 20);
		lifeBoxes[1] = new Rectangle(width - 100, 120, 100, 20);
		lifeBoxes[2] = new Rectangle(width - 100, 140, 100, 20);
		lifeBoxes[3] = new Rectangle(width - 100, 160, 100, 20);
		lifeBoxes[4] = new Rectangle(width - 100, 180, 100, 20);
		lifeBoxes[5] = new Rectangle(width - 100, 200, 100, 20);
		
		thread.start();
	}

	//Paints stuff on the screen.
	public void paintComponent(Graphics g)
	{


		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());



		if(scene == 0) //Main menu
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, 500, 500);
			g.drawImage(menu, 0, 0, this.frame.getWidth(), this.frame.getHeight(), null);
		}

		if(scene == 1) //Pick deck for playing
		{
			g.setColor(Color.black);
			//g.drawImage(background, 0, 0, this.frame.getWidth(), this.frame.getHeight(), null);

			dropBox[0].drawDropBox(g);
			dropBox[1].drawDropBox(g);
			g.drawImage(new ImageIcon("res/Button/ButtonPlay.png").getImage(), Screen.width / 20, Screen.height - 100, 500, 100, null);

		}

		if(scene == 2) { //Game Player

			g.drawImage(background, 0, 0, width, height, null);
			SceneDrawer.scene2(g);
			CardDrawer.drawLibrary(g);
			CardDrawer.drawExiled(g);
			CardDrawer.drawGraveyard(g);
			CardDrawer.drawHand(g);
			CardDrawer.drawBattlefield(g);

			g.drawImage(dice, Screen.width - 100, 0, 100, 100, null);

			if(whiteText)
			{
				g.setColor(Color.white);
			}else
			{
				g.setColor(Color.BLACK);
			}
			g.drawString("" + (lifeAmmount) + "", Screen.width - 60, 50);

			//dropBox[2].drawDropBox(g);

			StackManager.reformatStacks();
		}

		if(scene == 3) //Deck Manager Main
		{
			EditorBase.drawEditor(g, scene);
		}

		if(scene == 4)
		{
			SceneDrawer.scene4(g);
		}
		
		if(scene == 6)//Deck Manager edit
		{
			EditorBase.drawEditor(g, scene);
			
		}



		ButtonHandler.sceneFinder(scene, g, width, height, buttonWidth, buttonHeight);

	}

	//Loads all needed resources.
	public void loadResources()
	{
		FileManager.loadCards();
		FileManager.getDeckNames();
		//FileManager.checkDeckFormat();
		DropBoxHandler.createDropBoxes();
		lifeAmmount = 20;
		running = true;

	}

	//The main running method, start to everything.	
	public void run() 
	{
		System.out.println("[Success] Frame Created");
		loadResources();

		long lastFrame = System.currentTimeMillis();

		while(running) {
			long currentFrame = System.currentTimeMillis();

			long difference = (currentFrame - lastFrame) / 900;

			if(difference >= 2){

				frameX = (int)(frame.getLocation().getX());
				frameY = (int)(frame.getLocation().getY());

				width = this.frame.getWidth();
				height = this.frame.getHeight();

				buttonWidth = this.frame.getWidth() / 3;
				buttonHeight = buttonWidth / 5;

				cardWidth = (int)(width * (187.5 / 1920));
				cardHeight = (int)((width * (187.5 / 1920)) / .717);

				CardHandler.spotLocations[0] = new Rectangle(Screen.width - ((Screen.cardWidth + 30) * 3) - 30, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);
				CardHandler.spotLocations[1] = new Rectangle(0, Screen.height - ((Screen.cardHeight + 15) - (15/2)), width - (15 * 3) - ((cardWidth + 30) * 3), cardHeight + 15);
				CardHandler.spotLocations[3] = new Rectangle(Screen.width - ((Screen.cardWidth + 30) * 2) - 15, Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);
				CardHandler.spotLocations[4] = new Rectangle(Screen.width - (Screen.cardWidth + 30), Screen.height - ((Screen.cardHeight + 15) - (15 / 2)), Screen.cardWidth + 30, Screen.cardHeight + 15);


				repaint();
			}

		}

		stopGame();

	}

	//Works when mouse clicked, thought i wouldn't remember
	public void mouseClicked(MouseEvent e) {
		if (scene == 0) ButtonHandler.scene0Click(e);
		if (scene == 1) ButtonHandler.scene1Click(e);
		if (scene == 2){
			if(e.getButton() == 2)//TODO card zoom
			{
				CardHandler.zoom(); 
			}else
			{
				CardHandler.doDraw(e);
			}
			
		}
		
		
		
		Rectangle playButton = new Rectangle(500, 0, Screen.buttonWidth, Screen.buttonHeight);
		Rectangle playButton2 = new Rectangle(10, 500, Screen.buttonWidth, Screen.buttonHeight);
		if (scene == 3) {
			//dropBox[3].checkClicked(e);
			
			if(playButton.contains(e.getPoint()))
			{
				FileManager.loadDeck(dropBox[3].getClickedId());
				EditorBase.prepare();
				changeScene(6);
			}
		}
		if (scene == 4 && playButton2.contains(e.getPoint()))
		{
			changeScene(5);
			sealedPlay(dropBox[2].getSelected());
			//SealedPlayManager.formatForDeck();
		}
	}

	public static void changeScene(int newScene) {
		scene = newScene;
		System.out.println("[Event] The scene has changed to:" + scene);

		if(newScene == 4) {
			sets = getSets();

			for(int i = 0; i < sets.length; i++)
			{
				dropBox[2].addOption(sets[i]);
			}
		}

		if(newScene == 3)
		{
			String[] sets = Screen.getSets();

			for(int i = 0; i < sets.length; i++)
			{
				Screen.dropBox[4].addOption(sets[i]);
			}
			
			EditorBase.addDeckNames(deckNames);
		}
	}

	public static void stopGame() {
		running = false;
		CoutHandler.error("HELLLLPPPP SOMEONE IS CLOSING ME!!!!");
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == deckButton){
			isNewScene = false;
			frame.remove(pnl);
			CoutHandler.event(deckPick.getSelectedItem() + " is the deck that was picked!");
			FileManager.loadDeck(deckPick.getSelectedIndex());
			shuffleCards();
			changeScene(2);
		}
	}

	public static void playGame()
	{
		CoutHandler.event(dropBox[0].getSelected() + " was the deck that was picked");

		int par1 = dropBox[1].getClickedId();//blue, green red white
		if(par1 == 0) {
			dice = new ImageIcon("res/Dice/LifeDie.png").getImage();
			whiteText = true;
		}
		if(par1 == 1) dice = new ImageIcon("res/Dice/LifeDieBlue.png").getImage();
		if(par1 == 2) dice = new ImageIcon("res/Dice/LifeDieGreen.png").getImage();
		if(par1 == 3) dice = new ImageIcon("res/Dice/LifeDieRed.png").getImage();
		if(par1 == 4) {
			dice = new ImageIcon("res/Dice/LifeDieWhite.png").getImage();
			whiteText = false;
		}


		FileManager.loadDeck(dropBox[0].getClickedId());
		shuffleCards();
		changeScene(2);
	}


	public static void shuffleCards() {
		deckCard = new PlayableCard[totalCardsInDeck];
		CoutHandler.event("Initialized the deck of " + totalCardsInDeck + " cards");

		for(int i = 0; i < deck.length; i++) {
			if(deck[i].getAmmountOfCard() > 0){
				for(int j = 1; j <= deck[i].getAmmountOfCard(); j++){
					Random rand = new Random();
					int numInDeck = rand.nextInt(totalCardsInDeck - 1);

					while(deckCard[numInDeck] != null) {
						numInDeck = rand.nextInt(totalCardsInDeck);
					}

					deckCard[numInDeck] = new PlayableCard(0, 0, (int)(width * (187.5 / 1920)), (int)((width * (187.5 / 1920)) / .717), false, true, false, false, false, false, new ImageIcon("res/CardsAndDecks/CardTextures/" + deck[i].getSetName() + "/" + deck[i].getTextureName() + ".jpg").getImage());
					CardHandler.splitByState();
				}
			}


		}

		for(int j = 0; j < 7; j++)
		{
			StackManager.drawCard();
		}

		deckCard = null;

	}

	public static void openDeckArray(int length)
	{
		deck = new Deck[length];
	}

	public static void printVersionInfo()
	{
		CoutHandler.info("Version: " + versionNumber);
		CoutHandler.info("Version ID: " + versionID);
		CoutHandler.info("Version Name: " + versionName);
		System.out.println("-----------------------------");
	}

	public class KeyTyped
	{
		public void keyESC() {
			Screen.running = false;
		}

		public void keyPLUS()
		{
			LifeHandler.addLife(1);
		}

		public void keyMINUS()
		{
			LifeHandler.subtractLife(1);
		}

		public void keyENTER()
		{
			for(int i = 0; i < Screen.battlefieldCards.length; i++)
			{
				if(Screen.battlefieldCards[i] != null && Screen.battlefieldCards[i].getTapped())
				{
					Screen.battlefieldCards[i].setTapped(false);
				}
			}
		}

		public void keySPACE()
		{


		}
	}

	public static void sealedPlay(String selectedSet)
	{

		int common = 0, uncommon = 0, rare = 0, mythicRare = 0, special = 0;

		for(int i = 0; i < cardList.length; i ++){
			if(cardList[i] != null && cardList[i].getSetName().equals(selectedSet)) {
				int par1 = cardList[i].getRarity();
				switch(par1) {
				case 0:
					common++;
					break;
				case 1:
					uncommon++;
					break;
				case 2:
					rare++;
					break;
				case 3:
					mythicRare++;
					break;
				case 6:
					special++;
					break;

				}
			}

		}
		commonCards = new Card[common];
		uncommonCards = new Card[uncommon];
		rareCards = new Card[rare];
		mythicRareCards = new Card[mythicRare];
		specialCards = new Card[special];

		for(int i = 1; i < cardList.length; i++)
		{

			if(cardList[i] != null && cardList[i].getSetName().equals(selectedSet)) {
				int par2 = cardList[i].getRarity();

				switch(par2) {
				case 0:
					for(int j = 0; j< commonCards.length; j++)
					{
						if(commonCards[j] == null)
						{
							commonCards[j] = cardList[i];
							break;
						}
					}
				case 1:
					for(int j = 0; j < uncommonCards.length; j++)
					{
						if(uncommonCards[j] == null)
						{
							uncommonCards[j] = cardList[i];
							break;
						}
					}
				case 2:
					for(int j = 0; j < rareCards.length; j++)
					{
						if(rareCards[j] == null)
						{
							rareCards[j] = cardList[i];
							break;
						}
					}
				case 3:
					for( int j = 0; j < mythicRareCards.length; j++)
					{
						if(mythicRareCards[j] == null)
						{
							mythicRareCards[j] = cardList[i];
							break;
						}
					}
				case 6:
					for( int j = 0; j < specialCards.length; j++)
					{
						if(specialCards[j] == null)
						{
							specialCards[j] = cardList[i];
							break;
						}
					}
				}
			}

			if(special == 0)
			{
				specialCards = null;
			}

			sealedPacks = SealedPlayManager.createPacks(commonCards, uncommonCards, rareCards, mythicRareCards, specialCards);
		}
	}

	public static String[] getSets()
	{
		String[] par1String = null;

		for(int i = 1; i < cardList.length; i++)
		{
			String setName = null;

			if(cardList[i] != null) setName = cardList[i].getSetName();

			if(par1String != null && setName != null)
			{
				for(int j = 0; j < par1String.length; j++)
				{

					if(par1String[j].equals(setName))
					{
						break;
					}else if(j == par1String.length - 1)
					{
						int length = par1String.length + 1;
						String[] par2String = par1String;
						par1String = new String[length];

						for(int k = 0; k < par2String.length; k++)
						{
							par1String[k] = par2String[k];
						}

						par1String[length - 1] = setName;
						break;
					}


				}
			}else if(setName != null){
				par1String = new String[1];
				par1String[0] = setName;
			}

		}

		return par1String;

	}
}
