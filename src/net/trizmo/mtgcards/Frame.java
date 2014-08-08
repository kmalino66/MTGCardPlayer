package net.trizmo.mtgcards;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	static ImageIcon play = new ImageIcon("res/Button/Button.png");

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Frame();
			}
		});
	}

	public Frame() {
		Screen.printVersionInfo();

		new JFrame();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension hi = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(0,0);
		this.setSize(hi);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);

		Screen screen = new Screen(this);
		this.add(screen);
	}
}