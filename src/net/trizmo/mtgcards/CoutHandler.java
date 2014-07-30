package net.trizmo.mtgcards;

public class CoutHandler {//Console Output Handler
	
	public static void success(String event) {
		
		System.out.println("[Success] " + event);
	}
	
	public static void event(String event) {
		System.out.println("[Event] " + event);
	}
	
	public static void error(String event) {
		System.out.println("-------------------------------");
		System.out.println("[Error] " + event);
		System.out.println("-------------------------------");
	}
	
	public static void criticalError(String event) 
	{
		System.out.println("-");
		System.out.println("--");
		System.out.println("---");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("!!!![CRITICAL ERROR]!!!!" + event);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("---");
		System.out.println("--");
		System.out.println("-");

	}
	
	public static void info(String info)
	{
		System.out.println("[Info] " + info);
	}
}