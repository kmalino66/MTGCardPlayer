package net.trizmo.mtgcards;

import java.awt.event.MouseEvent;

public class LifeHandler {

	boolean firstTime = true;
	
	public static void changeLife(MouseEvent e)
	{
		if(Screen.lifeBoxes[0].contains(e.getPoint()))
		{
			Screen.lifeAmmount += 10;
		}
		
		if(Screen.lifeBoxes[1].contains(e.getPoint()))
		{
			Screen.lifeAmmount += 5;
		}
		
		if(Screen.lifeBoxes[2].contains(e.getPoint()))
		{
			Screen.lifeAmmount += 1;
		}
		
		if(Screen.lifeBoxes[3].contains(e.getPoint()))
		{
			Screen.lifeAmmount -= 1;
		}
		
		if(Screen.lifeBoxes[4].contains(e.getPoint()))
		{
			Screen.lifeAmmount -= 5;
		}
		
		if(Screen.lifeBoxes[5].contains(e.getPoint()))
		{
			Screen.lifeAmmount -= 10;
		}
		
		CoutHandler.event("Life is now:" + Screen.lifeAmmount);
	}
	
	public static void addLife(int deltaAmmount)
	{
		Screen.lifeAmmount += deltaAmmount;
	}
	
	public static void subtractLife(int deltaAmmount)
	{
		Screen.lifeAmmount -= deltaAmmount;
	}
}
