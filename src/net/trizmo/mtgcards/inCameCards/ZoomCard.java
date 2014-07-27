package net.trizmo.mtgcards.inCameCards;

import java.awt.Image;

public class ZoomCard {

	private int index;
	
	private Image textureImage;
	
	private CounterAttribute counterInfo;
	
	private String place;
	
	public ZoomCard(Image textureImage1, CounterAttribute counterInfo, String place, int index)
	{
		this(textureImage1, place, index);
		this.counterInfo = counterInfo;
	}
	
	public ZoomCard(Image textureImage, String place, int index)
	{
		this.textureImage = textureImage;
		this.place = place;
		counterInfo = new CounterAttribute();
		this.index = index;
	}
	
	public int getArrayIndex()
	{
		return index;
	}
	
	public Image getImage()
	{
		return textureImage;
	}
	
	public CounterAttribute getCounterInfo()
	{
		return counterInfo;
	}
	
	public String getPlace()
	{
		return place;
	}
	
	public void setArrayIndex(int index)
	{
		this.index = index;
	}
}