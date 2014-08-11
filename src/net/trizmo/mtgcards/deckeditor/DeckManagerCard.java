package net.trizmo.mtgcards.deckeditor;

public class DeckManagerCard {
	
	private int amountOfCard, sideboardAmount;
	private int id, rarity;
	private String name, setName;
	
	public DeckManagerCard(int id, String name, String setName, int amountOfCard, int rarity)
	{
		this.id = id;
		this.name = name;
		this.amountOfCard = amountOfCard;
		this.setName = setName;
		this.rarity = rarity;
		this.sideboardAmount = 0;
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

	public int getRarity() {
		return rarity;
	}

	public void adjustCardAmmount(int delta) {
		amountOfCard += delta;
	}
	
	public void adjustSideboardAmount(int delta) {
		sideboardAmount += delta;
	}
	
	public int getSideboardAmount()
	{
		return sideboardAmount;
	}

}
