package net.trizmo.mtgcards.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import net.trizmo.mtgcards.CardHandler;
import net.trizmo.mtgcards.Screen;
import net.trizmo.mtgcards.deckeditor.EditorBase;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener{
	
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
			if(Screen.dropBox[i] != null && Screen.dropBox[i].getOptions() != null) Screen.dropBox[i].checkClicked(arg0);
			
		}
		
		//Sends the click event over to the EditorBase to deal with the interaction.
		if(Screen.scene == 3) EditorBase.handleClick3(arg0);
		if(Screen.scene == 6) EditorBase.handleClick(arg0);
		
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


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(int i = 0; i <Screen.dropBox.length; i++)
		{
			if(Screen.dropBox[i] != null && Screen.dropBox[i].getOpened())
			{
				Screen.dropBox[i].scroll(e);
			}
			
			if(EditorBase.setPick != null && EditorBase.setPick.getOpened())
			{
				EditorBase.setPick.scroll(e);
			}
			if(EditorBase.cardPick != null && EditorBase.cardPick.getOpened())
			{
				EditorBase.cardPick.scroll(e);
			}
			
			if(EditorBase.deckPick != null && EditorBase.deckPick.getOpened())
			{
				EditorBase.deckPick.scroll(e);
			}
			
		}
	}

}
