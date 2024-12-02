package main;

// Utility Tool is used for game maintenance, Correct Loading of Image and Logs
// Config is used for the Configuration of how the game should run set by user

import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JFrame;

public class Main {
	public static JFrame window;

	public static void main(final String[] args) {
		UtilityTool.log("launched");

		// MAIN CONFIGURATION
		(Main.window = new JFrame()).setDefaultCloseOperation(3);
		Main.window.setResizable(false);
		Main.window.setTitle("Astral Rift: Journeys Beyond");

		// SET ICON
		new Main().windowIcon();

		// GAMEPANEL
		final GamePanel gamePanel = new GamePanel();

		// ADD GAMEPANEL
		Main.window.add((Component) gamePanel);

		UtilityTool.log("GamePanel created");

		// LOAD GAME CONFIGURATION
		gamePanel.config.loadConfiguration();

		// FULL SCREEN UNDECORATED
		if (gamePanel.fullScreenOn) {
			Main.window.setUndecorated(true);
		}

		// OTHER MAIN CONFIGURATION
		// pack window
		Main.window.pack();
		// center to screen
		Main.window.setLocationRelativeTo(null);
		// set visible
		Main.window.setVisible(true);

		// START GAME
		gamePanel.gameStart();

		UtilityTool.log("setupGame done");

		// START GAME TIME
		gamePanel.startGameThread();
	}

	// METHOD FOR ICON
	public void windowIcon() {
		final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("others/icon.jpg"));
		Main.window.setIconImage(icon.getImage());
	}
}
