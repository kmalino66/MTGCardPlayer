package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;

public class PoisonCounterHandler {

	private static int counterAmount;
	private static int index = 0;
	private Image texture = new ImageIcon("res/Dice/Phi.png").getImage();
	private static boolean open;
	
	public PoisonCounterHandler(int counterAmount)
	{
		PoisonCounterHandler.counterAmount = counterAmount;
	}
	
	public void addCounter()
	{
		counterAmount++;
	}
	
	public void subCounter()
	{
		counterAmount--;
	}
	
	public int getCounterAmount()
	{
		return counterAmount;
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
	public void drawComponent(Graphics g)
	{
		g.drawImage(texture, Screen.width - 100, 100, 100, 100, null);
		g.setColor(Color.white);
		g.drawString(counterAmount + "", Screen.width - 50, 175);
		
		if(open)
		{
			g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), Screen.width - 100, 100, 100, 50, null);
			g.setColor(Color.white);
			if(index > 0){
				g.drawString((index + counterAmount) + ":(+" + index + ")", Screen.width - 95, 125);
			}else{
				g.drawString((index + counterAmount) + ":(" + index + ")", Screen.width - 95, 125);

			}
		}
	}
	
	public void changeCounter(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width - 100, 100, 100, 100);
		if(open)
		{
			open = false;
			counterAmount += index;
			index = 0;
		}else{
			if(rect.contains(e.getPoint())) open = true;
		}
	}
	
	public boolean getOpen()
	{
		return open;
	}
	
	public void changeIndex(MouseWheelEvent e)
	{
		if(open) index += e.getWheelRotation();
	}
}
