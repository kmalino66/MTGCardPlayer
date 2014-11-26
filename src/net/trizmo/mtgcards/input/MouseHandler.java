package net.trizmo.mtgcards.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import net.trizmo.mtgcards.CardHandler;
import net.trizmo.mtgcards.LifeHandler;
import net.trizmo.mtgcards.SceneDrawer;
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
			
			if(EditorBase.setPick0 != null && EditorBase.setPick0.getOpened() && !EditorBase.editingSealedDeck)
			{
				EditorBase.setPick0.scroll(e);
			}
			
			if(EditorBase.cardPick0 != null && EditorBase.cardPick0.getOpened())
			{
				EditorBase.cardPick0.scroll(e);
			}
			
			if(EditorBase.setPick1 != null && EditorBase.setPick1.getOpened() && !EditorBase.editingSealedDeck)
			{
				EditorBase.setPick1.scroll(e);
			}
			
			if(EditorBase.cardPick1 != null && EditorBase.cardPick1.getOpened())
			{
				EditorBase.cardPick1.scroll(e);
			}
			
			if(EditorBase.cardPick2 != null && EditorBase.cardPick2.getOpened())
			{
				EditorBase.cardPick2.scroll(e);
			}
			
			if(EditorBase.deckPick != null && EditorBase.deckPick.getOpened())
			{
				EditorBase.deckPick.scroll(e);
			}
			
		}
		
		if(Screen.scene == 2)
		{
			LifeHandler.changeIndex(e);
			Screen.poisonCounter.changeIndex(e);
			
			for(int i = 0; i < SceneDrawer.cardSearch.length; i++)
			{
				SceneDrawer.cardSearch[i].scroll(e);
			}
		}
	}

}
