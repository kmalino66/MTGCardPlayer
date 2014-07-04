package net.trizmo.mtgcards;

import java.util.Random;

public class SealedPlayManager {

	public static Card[][] createPacks(Card[] common, Card[] uncommon, Card[] rare, Card[] mythicRare, Card[] special)
	{
		Card[][] packArray = new Card[14][7];

		Random rand = new Random();

		for(int i = 0; i < 7; i++)
		{
			if(rand.nextInt(9) == 6) //Check if rare card is mythic rare
			{
				packArray[0][i] = mythicRare[rand.nextInt(mythicRare.length)];
			}else
			{
				packArray[0][i] = rare[rand.nextInt(rare.length)];
			}
			
			//Pack uncommon cards
			for(int j = 0; j < 3; j++)
			{
				packArray[j + 1][i] = uncommon[rand.nextInt(uncommon.length)];
			}
			
			//Pack Common Cards
			for(int j = 0; j < 10; j++)
			{
				if (j==0 && special != null)
				{
					packArray[j + 4][i] = special[rand.nextInt(special.length)];
				}else
				{
					packArray[j + 4][i] = common[rand.nextInt(common.length)];
				}
			}
		}
		return packArray;
	}
}
