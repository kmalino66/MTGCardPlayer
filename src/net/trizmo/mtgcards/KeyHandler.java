package net.trizmo.mtgcards;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.trizmo.mtgcards.Screen.KeyTyped;
import net.trizmo.keyCodes.*;

public class KeyHandler implements KeyListener{
	
	private Screen screen;
	private KeyTyped keyTyped;
	
	public KeyHandler(Screen screen)
	{
		this.screen = screen;
		this.keyTyped = this.screen.new KeyTyped();
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		int keyCode = arg0.getKeyCode();
		
		System.out.println(keyCode);
		
		//Escape key pressed
		if(keyCode == KeyCodes.KEY_ESC) keyTyped.keyESC();
		if(keyCode == 107 || keyCode == KeyCodes.KEY_PLUS) keyTyped.keyPLUS();
		if(keyCode == 109 || keyCode == KeyCodes.KEY_MINUS) keyTyped.keyMINUS();
		if(keyCode == KeyCodes.KEY_ENTER) keyTyped.keyENTER();
		if(keyCode == KeyCodes.KEY_SPACE) keyTyped.keySPACE();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
