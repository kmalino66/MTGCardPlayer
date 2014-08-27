package net.trizmo.mtgcards.deckeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class TypingBox {

	private String text, defaultText;
	private int x, y, width, height;
	private Color backActive = Color.white, backIdle = Color.gray, foreground = Color.BLACK;
	private boolean active;

	public TypingBox(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.defaultText = "+++";
		this.text = "";
	}

	public TypingBox(int x, int y, int width, int height, String defaultText)
	{
		this(x, y, width, height);
		this.defaultText = defaultText;
	}

	public void drawComponent(Graphics g)
	{
		if(active)
		{
			g.setColor(backActive);
		}else{
			g.setColor(backIdle);
		}

		g.fillRect(x, y, width, height);

		g.setColor(foreground);
		g.drawRect(x, y, width, height);

		boolean par1 = true;
		int par1EditInt = height;

		while (par1) {

			Font font = new Font("Times New Roman", Font.PLAIN, par1EditInt);
			FontMetrics fm = g.getFontMetrics(font);

			if(text == null || text.equalsIgnoreCase(""))
			{
				if(fm.stringWidth(defaultText) > width)
				{
					par1EditInt--;
				}else{
					par1 = false;
				}
			}else{
				if(fm.stringWidth(text) > width)
				{
					par1EditInt--;
				}else{
					par1 = false;
				}
			}
			
			
		}
		Font fontHold = g.getFont();
		Font font = new Font("Times New Roman", Font.PLAIN, par1EditInt);
		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics(font);
		
		if(text == null || text.equals("")){
			g.drawString(defaultText , (x + (width / 2)) - (fm.stringWidth((defaultText)) / 2), (y + (height / 2)) + (fm.getHeight() / 2));
		} else{
			g.drawString(text, (x + (width / 2) - (fm.stringWidth(text) / 2)), ((y + (height / 2)) + (fm.getHeight() / 2)));
		}

		g.setFont(fontHold);
	}

	public void checkClicked(MouseEvent e)
	{
		Rectangle rect = new Rectangle(x, y, width, height);

		if(rect.contains(e.getPoint()))
		{
			active = (active) ? false : true;
		}
	}

	public void keyPressed(char keyTyped)
	{
		if(active)
		{
			text =  text + keyTyped;
		}
	}

	public void takeChar()
	{
		if(active){
			char[] par1 = text.toCharArray(), par2 = new char[par1.length - 1];

			for(int i = 0; i < par2.length; i++)
			{
				par2[i] = par1[i];
			}

			text = "";
			
			for(int j = 0; j < par2.length; j++)
			{
				text += par2[j];
			}
		}
	}
	
	public String getText()
	{
		return text;
	}
}