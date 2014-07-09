package net.trizmo.mtgcards.deckeditor;

public class DeckManagerCard {
	
	private int amountOfCard;
	private int id;
	private String name, setName;
	
	public DeckManagerCard(int id, String name, String setName, int amountOfCard)
	{
		this.id = id;
		this.name = name;
		this.amountOfCard = amountOfCard;
		this.setName = setName;
	}
	
	public void setAmountOfCard(int amount)
	{
		amountOfCard = amount;
	}
	
	public int getAmountOfCard()
	{
		return amountOfCard;
	}
	
	public String getCardName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getSetName()
	{
		return setName;
	}

}
