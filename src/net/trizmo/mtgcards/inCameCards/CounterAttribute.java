package net.trizmo.mtgcards.inCameCards;

public class CounterAttribute {
	
	private int powerMod, toughnessMod, counters;
	
	public CounterAttribute()
	{
		this(0, 0, 0);
	}
	
	public CounterAttribute(int powerMod, int toughnessMod, int counters)
	{
		this.powerMod = powerMod;
		this.toughnessMod = toughnessMod;
		this.counters = counters;
	}
	
	//Get the values of the variables
	public int getPowerModifier()
	{
		return powerMod;
	}
	
	public int getToughnessModifier()
	{
		return toughnessMod;
	}
	
	public int getCounters()
	{
		return counters;
	}
	
	//Change the values of the variables by 1.
	public void addPower()
	{
		powerMod++;
	}
	
	public void addToughness()
	{
		toughnessMod++;
	}
	
	public void addCounter()
	{
		counters++;
	}
	
	//Change the values of the variables by -1.
	public void subPower()
	{
		powerMod--;
	}
	
	public void subToughness()
	{
		toughnessMod--;
	}
	
	public void subCounter()
	{
		counters--;
	}
	
}
