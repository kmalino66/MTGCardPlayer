package net.trizmo.mtgcards.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import net.trizmo.mtgcards.CardHandler;
import net.trizmo.mtgcards.Screen;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	private Screen screen;
	//private Screen.MouseHeld mouseHeld;

	public MouseHandler(Screen screen){
		this.screen = screen;

	}
	

	public void mouseDragged(MouseEvent arg0) {
		CardHandler.mouseDragged(arg0);
	}

	public void mouseMoved(MouseEvent arg0) {

	}

	public void mouseClicked(MouseEvent arg0) {
		screen.mouseClicked(arg0);
		
		for(int i = 0; i < Screen.dropBox.length; i++)
		{
			if(Screen.dropBox[i] != null) Screen.dropBox[i].checkClicked(arg0);
			
		}
		
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		CardHandler.mousePressed(arg0);
	}

	public void mouseReleased(MouseEvent arg0) {
		CardHandler.mouseReleased(arg0);
		CardHandler.alreadyScanned = false;
	}

}
