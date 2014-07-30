package net.trizmo.mtgcards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;

public class LifeHandler {

	public static int index;
	
	public static boolean open = false;
	
	public static void changeLife(MouseEvent e)
	{
		Rectangle rect = new Rectangle(Screen.width - 100, 0, 100, 100);
		if(open)
		{
			open = false;
			addLife(index);
			index = 0;
		}else if(rect.contains(e.getPoint())){
			open = true;
			index = 0;
		}
	}
	
	public static void addLife(int deltaAmmount)
	{
		Screen.lifeAmmount += deltaAmmount;
	}
	
	public static void subtractLife(int deltaAmmount)
	{
		Screen.lifeAmmount -= deltaAmmount;
	}
	
	public boolean getOpened()
	{
		return open;
	}
	
	public static void drawComponent(Graphics g)
	{
		g.drawImage(Screen.dice, Screen.width - 100, 0, 100, 100, null);

		if(Screen.whiteText)
		{
			g.setColor(Color.white);
		}else
		{
			g.setColor(Color.BLACK);
		}

		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 25));
		g.drawString("" + (Screen.lifeAmmount) + "", Screen.width - 60, 50);
		
		if(open)
		{
			g.drawImage(new ImageIcon("res/General/choicebox.jpg").getImage(), Screen.width - 100, 100, 100, 50, null);
			g.setColor(Color.white);
			if(index > 0){
				g.drawString((index + Screen.lifeAmmount) + ":(+" + index + ")", Screen.width - 95, 125);
			}else{
				g.drawString((index + Screen.lifeAmmount) + ":(" + index + ")", Screen.width - 95, 125);

			}
		}
	}
	
	public static void changeIndex(MouseWheelEvent e)
	{
		if(open) index += e.getWheelRotation();
	}
	
	public void click(MouseEvent e)
	{
		changeLife(e);
	}
}
