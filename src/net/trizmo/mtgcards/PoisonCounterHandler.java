package net.trizmo.mtgcards;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PoisonCounterHandler {

	private int counterAmount;
	private Image texture = new ImageIcon("res/Dice/Phi.png").getImage();
	
	public PoisonCounterHandler(int counterAmount)
	{
		this.counterAmount = counterAmount;
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
}
