package net.trizmo.mtgcards;

import java.io.*;
import java.util.Scanner;

public class FileManager {

	public static int id = 0;

	static FileInputStream fileInput;
	static InputStreamReader readerInput;
	static Scanner inputScanner;
	static int cardAmm;

	public static void loadCards()
	{

		try
		{
			fileInput = new FileInputStream("res/CardsAndDecks/Cards.txt");
			readerInput = new InputStreamReader(fileInput);

			inputScanner = new Scanner(readerInput);

			while(inputScanner.hasNext()) {

				String id1;
				String name;
				String textureName;
				String setName;

				int rarity;

				String[] full;
				full = inputScanner.nextLine().split(":");

				id1 = full[0];
				id = Integer.parseInt(id1);

				name = full[1];

				rarity = Integer.parseInt(full[2]);

				setName = full[3];

				try{
				textureName = full[4];

				}catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					System.out.println(id);
					textureName = "R";
				}
				
				if(Screen.cardList[id] == null)
				{

					Screen.cardList[id] = new Card(id, name, rarity, setName, textureName);
					
				}else
				{
					System.out.println("Error: Two cards have the same id.");
					System.out.println(id);
				}

			}
			try {
				fileInput.close();
				readerInput.close();
				inputScanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		System.out.println("[Success] " + id + " cards have been initialized");

	}

	public static int getCardAmmount()
	{
		int largeID = 0;

		try {
			fileInput = new FileInputStream("res/CardsAndDecks/Cards.txt");
			readerInput = new InputStreamReader(fileInput);

			inputScanner = new Scanner(readerInput);

			while(inputScanner.hasNextLine())
			{
				String[] par1 = inputScanner.nextLine().split(":");
				if(Integer.parseInt(par1[0]) > largeID)
				{
					largeID = Integer.parseInt(par1[0]);
				}
			}

			try {
				fileInput.close();
				readerInput.close();
				inputScanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return largeID + 1;

		}catch(FileNotFoundException e){
			e.printStackTrace();
			return 1;
		}


	}

	public static int getDeckAmmount() {

		int deckAmmount = 0;

		try {
			fileInput = new FileInputStream("res/CardsAndDecks/Decks.txt");
			readerInput = new InputStreamReader(fileInput);

			inputScanner = new Scanner(readerInput);

			while(inputScanner.hasNext())
			{
				@SuppressWarnings("unused")
				String par1Temp = inputScanner.nextLine();
				deckAmmount++;
			}


			try {
				fileInput.close();
				readerInput.close();
				inputScanner.close();
			}catch (IOException e) {
				e.printStackTrace();
			}

			return deckAmmount;

		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public static void getDeckNames() {
		String deckName;
		int deckId = 0;
		int deckAmt = Screen.deckAmmount;

		try {
			fileInput = new FileInputStream("res/CardsAndDecks/Decks.txt");
			readerInput = new InputStreamReader(fileInput);

			inputScanner = new Scanner(readerInput);
			
			
			for(int i = 0; i < deckAmt; i++){
				String rawLine = inputScanner.nextLine();

				String[] split = rawLine.split(":");
				deckId = Integer.parseInt(split[0]);
				deckName = split[1];
				CoutHandler.event("Deck: " + deckId + " with the name of " + deckName + " has been found!");

				if(Screen.deckNames[deckId] == null){
					Screen.deckNames[deckId] = new DeckNames(deckId, deckName);
				}else{
					CoutHandler.error("Two decks have the same id!!");
				}
			}

			try {
				fileInput.close();
				readerInput.close();
				inputScanner.close();
			}catch(IOException e) {
				e.printStackTrace();
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public static void loadDeck(int deckId){
		CoutHandler.event("Initializing deck " + Screen.deckNames[deckId].getDeckName());
		int cardId, ammountOfCard;
		int currentCardNumber = 0;
		int cardNameTypes = 0;
		
		try {
			fileInput = new FileInputStream("res/CardsAndDecks/" + deckId + ".txt");
			readerInput = new InputStreamReader(fileInput);
			inputScanner = new Scanner(readerInput);
			
			while(inputScanner.hasNextLine()) 
			{
				
				@SuppressWarnings("unused")
				String par1TempString = inputScanner.nextLine();
				cardNameTypes++;
			}
			
			fileInput.close();
			readerInput.close();
			inputScanner.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Screen.openDeckArray(cardNameTypes);
		CoutHandler.event("Opened array");
		try {
			fileInput = new FileInputStream("res/CardsAndDecks/" + deckId + ".txt");
			readerInput = new InputStreamReader(fileInput);
			inputScanner = new Scanner(readerInput);

			//@SuppressWarnings("unused")
			//String temp = inputScanner.nextLine();
			while(inputScanner.hasNextLine()) {
				String rawLine = inputScanner.nextLine();

				String[] splitLine = rawLine.split(":");

				cardId = Integer.parseInt(splitLine[0]);
				ammountOfCard = Integer.parseInt(splitLine[1]);

				Screen.deck[currentCardNumber] = new Deck(cardId, Screen.cardList[cardId].getName(), Screen.cardList[cardId].getRarity(), 
						Screen.cardList[cardId].getSetName(), Screen.cardList[cardId].getTextureName(), ammountOfCard);
				
				currentCardNumber++;
			}

			try {
				fileInput.close();
				readerInput.close();
				inputScanner.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		CoutHandler.success("Deck " + Screen.deckNames[deckId].getDeckName() + " has been initialized.");
		DeckManager.getTotalCardsInDeck(deckId);

	}

	public static void checkDeckFormat()
	{
		int cardAmmount = Screen.cardAmmount;
		int cardId = 0;

		for(int i = 0; i < Screen.deckNames.length; i++)
		{
			try {
				fileInput = new FileInputStream("res/CardsAndDecks/" + i + ".txt");
				readerInput = new InputStreamReader(fileInput);
				inputScanner = new Scanner(readerInput);

				@SuppressWarnings("unused")
				String temp = inputScanner.nextLine();
				while(inputScanner.hasNextLine()) {
					String rawLine = inputScanner.nextLine();

					String[] splitLine = rawLine.split(":");

					cardId = Integer.parseInt(splitLine[0]);

				}

				if(cardId < cardAmmount)
				{
					FileWriter file;
					PrintWriter printer;

					try {
						file = new FileWriter("res/CardsAndDecks/" + i + ".txt", true);
						printer = new PrintWriter(file);

						for(int j = cardId + 1; j < cardAmmount; j++)
						{
							printer.println(j + ":0");
						}

						file.close();
						printer.close();

					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				try {
					fileInput.close();
					readerInput.close();
					inputScanner.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}


		}

	}
	
	public static void saveDeck(int deckId)
	{
		try {
			File file = new File("res/CardsAndDecks/" + deckId + ".txt");
			file.delete();
			file.createNewFile();
			
			FileWriter par1File = new FileWriter("res/CardsAndDecks/" + deckId + ".txt");
			PrintWriter printer = new PrintWriter(par1File, false);
			
			for(int i = 0; i < Screen.deck.length; i++)
			{
				if(Screen.deck[i].getAmmountOfCard() > 0)
				{
					printer.println(Screen.deck[i].getCardId() + ":" + Screen.deck[i].getAmmountOfCard());
				}
			}
			
			par1File.close();
			printer.close();
		
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static void createNewDeck(String deckName) throws IOException
	{
		FileWriter par1File = new FileWriter("res/CardsAndDecks/Decks.txt");
		PrintWriter printer = new PrintWriter(par1File, true);
		
		printer.println("");
		printer.print(Screen.deckAmmount + ":" + deckName);
		
		par1File.close();
		printer.close();
		
		File newDeckFile = new File("res/CardsAndDecks/" + Screen.deckAmmount + ".txt");
		newDeckFile.createNewFile();
		
		Screen.deckAmmount++;
	}
	
}