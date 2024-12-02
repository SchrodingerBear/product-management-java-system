package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {
	public final int screenX;
	public final int screenY;
	public String text;

	GamePanel gp;
	Graphics2D g2;

	BufferedImage bg = null, nwbg = null, hf = null, hh = null, bh = null;

	public boolean messageOn = false;

	public String currentDialogue = "";
	public String message = "";
	int messageCounter;

	public int commandNum = 0;
	public int pausecommandNum = 0;

	public int titleScreenState = 0;
	public int newWorldScreenState = 1;

	public UI(GamePanel gp) {
		this.gp = gp;

		// GET AND INITIALIZE SCREEN VALUES
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		try {
			bg = ImageIO.read(getClass().getResource("/gui/titlebackground.jpg"));
			nwbg = ImageIO.read(getClass().getResource("/gui/newgame.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		SuperObject heart = new OBJ_Heart(gp);
		bh = heart.image;
		hh = heart.image2;
		hf = heart.image3;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.drawImage(entity.Player.playerimage, screenX + screenX - 20, screenY + screenY - 20, gp.tileSize, gp.tileSize, null);

		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}

		if (gp.gameState == gp.playState) {
			drawPlayerHealth();
		}

		if (gp.gameState == gp.pauseState) {
			drawPausedMenu();
		}

		if (gp.gameState == gp.dialogState) {
			drawDialogueScreen();
		}

		// NOTIFICATION
		if (messageOn) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, 10, screenY * 2 + 20);
			messageCounter++;

			if (messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}

	public void drawPlayerHealth() {
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;

		while (i < gp.player.maxLife / 2) {
			g2.drawImage(bh, x, y, null);
			i++;
			x += gp.tileSize;
		}

		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;

		// DRAW CURRENT LIFE
		while (i < gp.player.Life) {
			g2.drawImage(hh, x, y, null);
			i++;
			if (i < gp.player.Life) {
				g2.drawImage(hf, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}

	public void drawDialogueScreen() {
		g2.setFont(gp.universalFont);

		int x = gp.tileSize * 2, y = gp.tileSize / 2, width = gp.screenWidth - (gp.tileSize * 4),
				height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color color = new Color(0, 0, 0, 100);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawTitleScreen() {
		if (titleScreenState == 0) {
			titleScreenState();

		} else if (titleScreenState == newWorldScreenState) {
			newWorldScreenState();
		}
	}

	public void titleScreenState() {
		// BACKGROUND PLAIN
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// BACKGROUND IMAGE
		g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, gp);

		// TITLE SETTING
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String title = "Whispering Meadows";
		int x = centerTextX(title), y = gp.tileSize * 3;

		// TITLE SHADOW
		g2.setColor(Color.GRAY);
		g2.drawString(title, x + 5, y + 5);

		// TITLE TEXT
		g2.setColor(Color.WHITE);
		g2.drawString(title, x, y);

		// MAINMENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 58F));

		text = "NEW GAME";
		x = centerTextX(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "LOAD GAME";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "OPTIONS";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 3) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		// OPTION
//					text = "CREDITS";
//					g2.drawString(text, (screenX + screenX) - 150, (gp.tileSize * 11) + 22);
//					if (commandNum == 4) {
//						g2.drawString(">" , (gp.tileSize * 11) - 6, (gp.tileSize * 11) + 22);
//					}
	}

	public void newWorldScreenState() {
		// BACKGROUND IMAGE
		g2.drawImage(nwbg, 0, 0, gp.screenWidth, gp.screenHeight, gp);

		// TITLE SETTING
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String title = "CREATE A NEW WORLD";
		int x = centerTextX(title), y = gp.tileSize * 3;

		// TITLE SHADOW
		g2.setColor(Color.GRAY);
		g2.drawString(title, x + 5, y + 5);

		// TITLE TEXT
		g2.setColor(Color.WHITE);
		g2.drawString(title, x, y);

		// MAINMENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 58F));

		text = "FLAT";
		x = centerTextX(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "INFINITE";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "BACK";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	
	public void drawPausedMenu() {
		// PAUSE MENU BACKGROUND
		Color fadeblack = new Color(0, 0, 0, 50);
		g2.setColor(fadeblack);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		String text = "PAUSED";

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		int x = centerTextX(text);
		int y = 150;

		g2.drawString(text, x, y);

		text = "RETURN TO GAME";
		x = centerTextX(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if (pausecommandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "OPTIONS";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (pausecommandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "MAIN MENU";
		x = centerTextX(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (pausecommandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	public int centerTextX(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
