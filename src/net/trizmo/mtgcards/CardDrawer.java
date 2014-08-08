package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class CardDrawer {
	public static void drawLibrary(Graphics g)
	{
		boolean card = false;

		for(int i = 0; i < Screen.libraryCards.length; i++)
		{
			if(Screen.libraryCards[i] != null)
			{
				card = true;
				break;
			}
		}

		if(card)
		{
			int x = Screen.width - ((Screen.cardWidth + 30) * 3) - 15;
			int y = Screen.height - ((Screen.cardHeight + 15) - (15 / 2));

			g.drawImage(new ImageIcon("res/CardsAndDecks/CardTextures/cardBack.jpg").getImage(), x, y, Screen.cardWidth, Screen.cardHeight, null);
		}
	}

	public static void drawBattlefield(Graphics g)
	{
		int cardWidth = Screen.cardWidth;
		int cardHeight = Screen.cardHeight;
		
		Font par1Font = new Font("TimesNewRoman", Font.PLAIN, 18);
		g.setFont(par1Font);

		for(int i = 0; i < Screen.battlefieldCards.length; i++)
		{
			if(Screen.battlefieldCards[i] != null){
				int x = Screen.battlefieldCards[i].getX();
				int y = Screen.battlefieldCards[i].getY();
				int tx = Screen.battlefieldCards[i].getTX();
				int ty = Screen.battlefieldCards[i].getTY();

				if(Screen.battlefieldCards[i].getTapped())
				{
					
					BufferedImage textureImage = new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);

					textureImage.getGraphics().drawImage(Screen.battlefieldCards[i].getImage(), 0, 0, cardWidth, cardHeight, null);

					Graphics2D g2d = (Graphics2D)g.create();

					g2d.rotate(Math.toRadians(90), cardWidth / 2, cardHeight / 2);
					g2d.drawImage(textureImage, tx, ty, cardWidth, cardHeight, null);
					
					g.setColor(Color.white);
					g.drawString(Screen.battlefieldCards[i].getCounterInfo().getPowerModifier() + "/" + Screen.battlefieldCards[i].getCounterInfo().getToughnessModifier() + "   " + Screen.battlefieldCards[i].getCounterInfo().getCounters() + "c", x, y + cardWidth + 5 + 18);

					g2d.dispose();
				
				}else{
					g.drawImage(Screen.battlefieldCards[i].getImage(), x, y, Screen.cardWidth, Screen.cardHeight, null);
					g.setColor(Color.white);
					g.drawString(Screen.battlefieldCards[i].getCounterInfo().getPowerModifier() + "/" + Screen.battlefieldCards[i].getCounterInfo().getToughnessModifier() + "   " + Screen.battlefieldCards[i].getCounterInfo().getCounters() + "c", x, y + cardHeight + 5 + 18);
				}
			}
		}
	}

	public static void drawHand(Graphics g)
	{
		int y = Screen.height - ((Screen.cardHeight + 15) - (15 / 2));

		for(int i = 0; i < Screen.handCards.length; i++)
		{
			if(Screen.handCards[i] != null)
			{
				g.drawImage(Screen.handCards[i].getTextureImage(), (int)((Screen.cardWidth * (i)) * .9), y, (int)(Screen.cardWidth * .9), (int)(Screen.cardHeight * .9), null);
			}
		}
	}

	public static void drawGraveyard(Graphics g)
	{
		int y = Screen.height - ((Screen.cardHeight + 15) - (15 / 2));
		int x = Screen.width - ((Screen.cardWidth + 30) * 2) - 15;

		for(int i = Screen.graveyardCards.length - 1; i >= 0; i--){
			if(Screen.graveyardCards[i] != null)
			{
				g.drawImage(Screen.graveyardCards[i].getImage(), x, y, Screen.cardWidth, Screen.cardHeight, null);
				break;
			}
		}
	}

	public static void drawExiled(Graphics g)
	{
		int y = Screen.height - ((Screen.cardHeight + 15) - (15 / 2));
		int x = Screen.width - ((Screen.cardWidth + 30) * 1);

		for(int i = Screen.exiledCards.length - 1; i >= 0; i--){
			if(Screen.exiledCards[i] != null)
			{
				g.drawImage(Screen.exiledCards[i].getImage(), x, y, Screen.cardWidth, Screen.cardHeight, null);
				break;
			}
		}
	}

	public static void drawTokenBattlefield(Graphics g) {
		int cardWidth = Screen.cardWidth;
		int cardHeight = Screen.cardHeight;
		
		Font par1Font = new Font("TimesNewRoman", Font.PLAIN, 18);
		g.setFont(par1Font);

		if(Screen.tokenBattlefield != null) for(int i = 0; i < Screen.tokenBattlefield.length; i++)
		{
			if(Screen.tokenBattlefield[i] != null){
				int x = Screen.tokenBattlefield[i].getX();
				int y = Screen.tokenBattlefield[i].getY();
				int tx = Screen.tokenBattlefield[i].getTX();
				int ty = Screen.tokenBattlefield[i].getTY();

				if(Screen.tokenBattlefield[i].getTapped())
				{
					
					BufferedImage textureImage = new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);

					textureImage.getGraphics().drawImage(Screen.tokenBattlefield[i].getImage(), 0, 0, cardWidth, cardHeight, null);

					Graphics2D g2d = (Graphics2D)g.create();

					g2d.rotate(Math.toRadians(90), cardWidth / 2, cardHeight / 2);
					g2d.drawImage(textureImage, tx, ty, cardWidth, cardHeight, null);
					
					g.setColor(Color.white);
					g.drawString(Screen.tokenBattlefield[i].getCounterInfo().getPowerModifier() + "/" + Screen.tokenBattlefield[i].getCounterInfo().getToughnessModifier() + "   " + Screen.tokenBattlefield[i].getCounterInfo().getCounters() + "c", x, y + cardWidth + 5 + 18);

					g2d.dispose();
				
				}else{
					g.drawImage(Screen.tokenBattlefield[i].getImage(), x, y, Screen.cardWidth, Screen.cardHeight, null);
					g.setColor(Color.white);
					g.drawString(Screen.tokenBattlefield[i].getCounterInfo().getPowerModifier() + "/" + Screen.tokenBattlefield[i].getCounterInfo().getToughnessModifier() + "   " + Screen.tokenBattlefield[i].getCounterInfo().getCounters() + "c", x, y + cardHeight + 5 + 18);
				}
			}
		}
	}
	
	
}
