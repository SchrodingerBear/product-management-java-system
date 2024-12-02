package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		// CREATE JFRAME
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		
		// PANEL
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		// SET CUSTOMIZATION
		window.setResizable(false);
		window.setTitle("2D Adventure Game");
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// RUN THE GAME
		gamePanel.setupGame();
		
		// SET TIME
		gamePanel.startGameThread();
	}
}
