package net.trizmo.mtgcards;

public class CardInteract {
	
	private int locationIdentifier; // 0 = Library, 1 = Hand, 2 = Battlefield, 3 = Graveyard, 4 = Exile
	private int arrayIdentifier;
	
	public CardInteract(int locationIdentifier, int arrayIdentifier)
	{
		this.locationIdentifier = locationIdentifier;
		this.arrayIdentifier = arrayIdentifier;
	}
	
	public int getLocation()
	{
		return locationIdentifier;
	}
	
	public int getArrayLocation()
	{
		return arrayIdentifier;
	}
}
