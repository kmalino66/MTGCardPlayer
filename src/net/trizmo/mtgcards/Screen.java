package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

public class Screen extends JPanel implements Runnable, ActionListener {

	/**
	 * TODO Next turn button
	 * TODO Other useful buttons
	 * TODO Card Zoom?
	 * TODO Make cards smaller in hand
	 * TODO Finish dropBoxes
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
	public static Deck[] deck = new Deck[cardAmmount];
	public static DeckNames[] deckNames = new DeckNames[deckAmmount];
	public static PlayableCard[] deckCard;
	public static BattlefieldCard[] battlefieldCards;
	public static ExiledCard[] exiledCards;
	public static GraveyardCard[] graveyardCards;
	public static LibraryCard[] libraryCards;
	public static HandCard[] handCards;
	public static DropBox[] dropBox = new DropBox[10];

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

	public boolean isNewScene = false;
	public static boolean running = false;

	private static final long serialVersionUID = 1L;

	//Creates the screen.
	public Screen(Frame frame) 
	{

		this.frame = frame;

		width = this.frame.getWidth();
		height = this.frame.getHeight();

		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler(this));

		buttonWidth = this.frame.getWidth() / 3;
		buttonHeight = buttonWidth / 5;

		thread.start();
	}

	//Paints stuff on the screen.
	public void paintComponent(Graphics g)
	{


		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());


		if(scene == 0)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, 500, 500);
			g.drawImage(menu, 0, 0, this.frame.getWidth(), this.frame.getHeight(), null);
		}

		if(scene == 1)
		{
			g.setColor(Color.black);
			//g.drawImage(background, 0, 0, this.frame.getWidth(), this.frame.getHeight(), null);

			dropBox[0].drawDropBox(g);
			dropBox[1].drawDropBox(g);
			g.drawImage(new ImageIcon("res/Button/ButtonPlay.png").getImage(), Screen.width / 20, Screen.height - 100, 500, 100, null);

		}

		if(scene == 2) {

			g.drawImage(background, 0, 0, width, height, null);
			SceneDrawer.scene2(g);
			CardDrawer.drawLibrary(g);
			CardDrawer.drawBattlefield(g);
			CardDrawer.drawExiled(g);
			CardDrawer.drawGraveyard(g);
			CardDrawer.drawHand(g);
			g.drawImage(dice, Screen.width - 100, 0, 100, 100, null);

		}

		ButtonHandler.sceneFinder(scene, g, width, height, buttonWidth, buttonHeight);

	}

	//Loads all needed resources.
	public void loadResources()
	{
		FileManager.loadCards();
		FileManager.getDeckNames();
		FileManager.checkDeckFormat();
		DropBoxHandler.createDropBoxes();
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
				repaint();
			}

		}
	}

	//Works when mouse clicked, thought i wouldn't remember
	public void mouseClicked(MouseEvent e) {
		if (scene == 0) ButtonHandler.scene0Click(e);
		if (scene == 1) ButtonHandler.scene1Click(e);
		if (scene == 2) CardHandler.doDraw(e);
	}

	public static void changeScene(int newScene) {
		scene = newScene;
		System.out.println("[Event] The scene has changed to:" + scene);

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
		
		int par1 = dropBox[1].getClickedId();
		if(par1 == 0) dice = new ImageIcon("res/Dice/LifeDie.png").getImage();//blue, green red white
		if(par1 == 1) dice = new ImageIcon("res/Dice/LifeDieBlue.png").getImage();
		if(par1 == 2) dice = new ImageIcon("res/Dice/LifeDieGreen.png").getImage();
		if(par1 == 3) dice = new ImageIcon("res/Dice/LifeDieRed.png").getImage();
		if(par1 == 4) dice = new ImageIcon("res/Dice/LifeDieWhite.png").getImage();
		
		FileManager.loadDeck(dropBox[0].getClickedId());
		shuffleCards();
		changeScene(2);
	}
	
	
	public static void shuffleCards() {
		deckCard = new PlayableCard[totalCardsInDeck];
		CoutHandler.event("Initialized the deck of " + totalCardsInDeck + " cards");

		for(int i = 1; i < deck.length - 1; i++) {
			if(deck[i].getAmmountOfCard() > 0){
				for(int j = 1; j <= deck[i].getAmmountOfCard(); j++){
					Random rand = new Random();
					int numInDeck = rand.nextInt(totalCardsInDeck - 1);

					while(deckCard[numInDeck] != null) {
						numInDeck = rand.nextInt(totalCardsInDeck);
					}

					deckCard[numInDeck] = new PlayableCard(0, 0, (int)(width * (187.5 / 1920)), (int)((width * (187.5 / 1920)) / .717), false, true, false, false, false, false, new ImageIcon("res/CardsAndDecks/CardTextures/" + deck[i].getSetName() + "/" + deck[i].getTextureName() + ".jpg").getImage());
					System.out.println("res/CardsAndDecks/CardTextures/" + deck[i].getSetName() + deck[i].getTextureName() + ".jpg");
					CardHandler.splitByState();
				}
			}


		}
		cardWidth = (int)(width * (187.5 / 1920));
		cardHeight = (int)((width * (187.5 / 1920)) / .717);
		deckCard = null;

	}

}
