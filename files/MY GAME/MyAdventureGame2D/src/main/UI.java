package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class UI {
	GamePanel gp;
	Graphics2D g2;

	// UI ASSETS
	// font
	public Font maruMonica;
	// heart image
	BufferedImage heart_full;
	BufferedImage heart_half;
	BufferedImage heart_blank;
	// mana image
	BufferedImage crystal_full;
	BufferedImage crystal_blank;
	// coin image
	BufferedImage coin;

	public boolean messageOn = false;

	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();

	public boolean gameFinished = false;
	public String currentDialogue = "";

	public int commandNum = 0;
	public int titleScreenState = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;

	int subState = 0;
	int counter = 0;

	public Entity npc;

	int charIndex = 0;

	String combinedText = "";

	public UI(GamePanel gp) {
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			this.maruMonica = Font.createFont(0, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		OBJ_Heart oBJ_Heart = new OBJ_Heart(gp);
		this.heart_full = ((Entity) oBJ_Heart).image;
		this.heart_half = ((Entity) oBJ_Heart).image2;
		this.heart_blank = ((Entity) oBJ_Heart).image3;
		OBJ_ManaCrystal oBJ_ManaCrystal = new OBJ_ManaCrystal(gp);
		this.crystal_full = ((Entity) oBJ_ManaCrystal).image;
		this.crystal_blank = ((Entity) oBJ_ManaCrystal).image2;
		OBJ_Coin_Bronze oBJ_Coin_Bronze = new OBJ_Coin_Bronze(gp);
		this.coin = ((Entity) oBJ_Coin_Bronze).down1;
	}

	public void addMessage(String text) {
		this.message.add(text);
		this.messageCounter.add(Integer.valueOf(0));
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(this.maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (this.gp.gameState == this.gp.titleState)
			drawTitleScreen();

		if (this.gp.gameState == this.gp.playState) {
			drawPlayerLife();
			drawMonsterLife();
			drawMessage();
		}

		if (this.gp.gameState == this.gp.pauseState) {
			drawPlayerLife();
			drawMonsterLife();
			drawPauseScreen();
		}

		if (this.gp.gameState == this.gp.dialogueState)
			drawDialogueScreen();

		if (this.gp.gameState == this.gp.characterState) {
			drawCharacterScreen();
			drawInventory(this.gp.player, true);
		}

		if (this.gp.gameState == 5)
			drawOptionsScreen();

		if (this.gp.gameState == 6)
			drawGameOverScreen();

		if (this.gp.gameState == 7)
			drawTransition();

		if (this.gp.gameState == 8)
			drawTradeScreen();

		if (this.gp.gameState == 9)
			drawSleepScreen();
	}

	public void drawPlayerLife() {

		int x = 48 / 2;

		int y = 48 / 2;
		int i = 0;
		int iconSize = 32;

		int manaStartX = 48 / 2 - 5;
		int manaStartY = 0;
		while (i < this.gp.player.maxLife / 2) {
			this.g2.drawImage(this.heart_blank, x, y, iconSize, iconSize, null);
			i++;
			x += iconSize;
			manaStartY = y + 32;
			if (i % 8 == 0) {

				x = 48 / 2;
				y += iconSize;
			}
		}

		x = 48 / 2;

		y = 48 / 2;
		i = 0;
		while (i < this.gp.player.life) {
			this.g2.drawImage(this.heart_half, x, y, iconSize, iconSize, null);
			i++;
			if (i < this.gp.player.life)
				this.g2.drawImage(this.heart_full, x, y, iconSize, iconSize, null);
			i++;
			x += iconSize;
			if (i % 16 == 0) {

				x = 48 / 2;
				y += iconSize;
			}
		}
		x = manaStartX;
		y = manaStartY;
		i = 0;
		while (i < this.gp.player.maxMana) {
			this.g2.drawImage(this.crystal_blank, x, y, iconSize, iconSize, null);
			i++;
			x += 20;
			if (i % 10 == 0) {
				x = manaStartX;
				y += iconSize;
			}
		}
		x = manaStartX;
		y = manaStartY;
		i = 0;
		while (i < this.gp.player.mana) {
			this.g2.drawImage(this.crystal_full, x, y, iconSize, iconSize, null);
			i++;
			x += 20;
			if (i % 10 == 0) {
				x = manaStartX;
				y += iconSize;
			}
		}
	}

	public void drawMonsterLife() {

		for (int i = 0; i < (this.gp.monster[1]).length; i++) {
			Entity monster = this.gp.monster[this.gp.currentMap][i];
			if (monster != null && monster.inCamera())
				if (monster.hpBarOn && !monster.boss) {

					double oneScale = 48.0D / monster.maxLife;
					double hpBarValue = oneScale * monster.life;
					this.g2.setColor(new Color(35, 35, 35));

					this.g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() - 16, 48 + 2, 12);
					this.g2.setColor(new Color(255, 0, 30));
					this.g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int) hpBarValue, 10);
					monster.hpBarCounter++;
					if (monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				} else if (monster.boss) {

					double oneScale = 48.0D * 8.0D / monster.maxLife;
					double hpBarValue = oneScale * monster.life;

					int x = 960 / 2 - 48 * 4;

					int y = 48 * 10;
					this.g2.setColor(new Color(35, 35, 35));

					this.g2.fillRect(x - 1, y - 1, 48 * 8 + 2, 22);
					this.g2.setColor(new Color(255, 0, 30));
					this.g2.fillRect(x, y, (int) hpBarValue, 20);
					this.g2.setFont(this.g2.getFont().deriveFont(1, 24.0F));
					this.g2.setColor(Color.white);
					this.g2.drawString(monster.name, x + 4, y - 10);
				}
		}
	}

	public void drawMessage() {

		int messageX = 48 / 2;

		int messageY = 48 * 5;
		this.g2.setFont(this.g2.getFont().deriveFont(1, 26.0F));
		for (int i = 0; i < this.message.size(); i++) {
			if (this.message.get(i) != null) {
				this.g2.setColor(Color.black);
				this.g2.drawString(this.message.get(i), messageX + 2, messageY + 2);
				this.g2.setColor(Color.white);
				this.g2.drawString(this.message.get(i), messageX, messageY);
				int counter = this.messageCounter.get(i).intValue() + 1;
				this.messageCounter.set(i, Integer.valueOf(counter));
				messageY += 40;
				if (this.messageCounter.get(i).intValue() > 180) {
					this.message.remove(i);
					this.messageCounter.remove(i);
				}
			}
		}
	}

	public void drawTitleScreen() {
		BufferedImage bg = null;

		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/gui/titlebackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, gp);

		this.g2.setFont(this.g2.getFont().deriveFont(1, 96.0F));
		String text = "Astral Rift";
		int x = centerTextX(text);

		int y = 48 * 3;
		this.g2.setColor(Color.gray);
		this.g2.drawString(text, x + 5, y + 5);
		this.g2.setColor(Color.white);
		this.g2.drawString(text, x, y);

		this.g2.setFont(this.g2.getFont().deriveFont(1, 48.0F));
		text = "NEW GAME";
		x = centerTextX(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "LOAD GAME";
		x = centerTextX(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "OPTIONS";
		x = centerTextX(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = centerTextX(text);
		y += gp.tileSize * 1;
		g2.drawString(text, x, y);
		if (commandNum == 3) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	public void drawPauseScreen() {
		this.g2.setFont(this.g2.getFont().deriveFont(0, 80.0F));
		String text = "PAUSED";
		int x = centerTextX(text);

		int y = 576 / 2;
	}

	public void drawDialogueScreen() {
		if (this.gp.uniTimerOn) {
			this.gp.uniTimer++;
			if (this.gp.uniTimer > 100) {
				this.gp.uniTimerOn = false;
				this.gp.uniTimer = 0;
			}
		}

		int x = 48 * 3;

		int y = 48 / 2;

		int width = 960 - 48 * 6;

		int height = 48 * 4;
		drawSubWindow(x, y, width, height);
		this.g2.setFont(this.maruMonica);
		this.g2.setFont(this.g2.getFont().deriveFont(0, 32.0F));

		x += 48;

		y += 48;
		if (this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex] != null) {
			char[] characters = this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex].toCharArray();
			if (this.charIndex < characters.length) {
				this.gp.playSE(17);
				String s = String.valueOf(characters[this.charIndex]);
				this.combinedText = String.valueOf(this.combinedText) + s;
				this.currentDialogue = this.combinedText;
				this.charIndex++;
			}
			if (this.gp.keyH.enterPressed && !this.gp.uniTimerOn) {
				this.charIndex = 0;
				this.combinedText = "";

				if (this.gp.gameState == 3 || this.gp.gameState == 11) {
					this.npc.dialogueIndex++;
					this.gp.keyH.enterPressed = false;
				}
			}
		} else {
			this.npc.dialogueIndex = 0;

			if (this.gp.gameState == 3) {

				this.gp.gameState = 1;
			}

			if (this.gp.gameState == 11)
				this.gp.csManager.scenePhase++;
		}
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = this.currentDialogue.split("\n")).length, b = 0; b < i;) {
			String line = arrayOfString[b];
			this.g2.drawString(line, x, y);
			y += 40;
			b++;
		}
	}

	public void drawCharacterScreen() {

		int frameX = 48 * 2;

		int frameY = 48;

		int frameWidth = 48 * 5;

		int frameHeight = 48 * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		this.g2.setColor(Color.white);
		this.g2.setFont(this.g2.getFont().deriveFont(32.0F));
		int textX = frameX + 20;

		int textY = frameY + 48;
		int lineHeight = 35;
		this.g2.drawString("Level", textX, textY);
		textY += 35;
		this.g2.drawString("Life", textX, textY);
		textY += 35;
		this.g2.drawString("Mana", textX, textY);
		textY += 35;
		this.g2.drawString("Strength", textX, textY);
		textY += 35;
		this.g2.drawString("Dexterity", textX, textY);
		textY += 35;
		this.g2.drawString("Attack", textX, textY);
		textY += 35;
		this.g2.drawString("Defense", textX, textY);
		textY += 35;
		this.g2.drawString("Exp", textX, textY);
		textY += 35;
		this.g2.drawString("Next Level", textX, textY);
		textY += 35;
		this.g2.drawString("Coin", textX, textY);
		textY += 45;
		this.g2.drawString("Weapon", textX, textY);
		textY += 50;
		this.g2.drawString("Shield", textX, textY);
		textY += 35;
		int tailX = frameX + frameWidth - 30;

		textY = frameY + 48;
		String value = String.valueOf(this.gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(String.valueOf(this.gp.player.life) + "/" + this.gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(String.valueOf(this.gp.player.mana) + "/" + this.gp.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;
		value = String.valueOf(this.gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		this.g2.drawString(value, textX, textY);
		textY += 35;

		this.g2.drawImage(this.gp.player.currentWeapon.down1, tailX - 48, textY - 24, (ImageObserver) null);

		textY += 48;

		this.g2.drawImage(this.gp.player.currentShield.down1, tailX - 48, textY - 24, (ImageObserver) null);
	}

	public void drawInventory(Entity entity, boolean cursor) {
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		if (entity == this.gp.player) {

			frameX = 48 * 12;

			frameY = 48;

			frameWidth = 48 * 6;

			frameHeight = 48 * 5;
			slotCol = this.playerSlotCol;
			slotRow = this.playerSlotRow;
		} else {

			frameX = 48 * 2;

			frameY = 48;

			frameWidth = 48 * 6;

			frameHeight = 48 * 5;
			slotCol = this.npcSlotCol;
			slotRow = this.npcSlotRow;
		}
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		int slotXstart = frameX + 20;
		int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;

		int slotSize = 48 + 3;
		for (int i = 0; i < entity.inventory.size(); i++) {
			if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield
					|| entity.inventory.get(i) == entity.currentLight) {
				this.g2.setColor(new Color(240, 190, 90));

				this.g2.fillRoundRect(slotX, slotY, 48, 48, 10, 10);
			}
			this.g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, (ImageObserver) null);
			if (entity == this.gp.player && entity.inventory.get(i).amount > 1) {
				this.g2.setFont(this.g2.getFont().deriveFont(32.0F));
				int j = entity.inventory.get(i).amount;
				String jAsString = Integer.toString(j);
				int amountX = getXforAlignToRightText(jAsString, slotX + 44);

				int amountY = slotY + 48;
				this.g2.setColor(new Color(60, 60, 60));
				this.g2.drawString(jAsString, amountX, amountY);
				this.g2.setColor(Color.white);
				this.g2.drawString(jAsString, amountX - 3, amountY - 3);
			}
			slotX += slotSize;
			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		if (cursor) {
			int cursorX = slotXstart + slotSize * slotCol;
			int cursorY = slotYstart + slotSize * slotRow;

			int cursorWidth = 48;

			int cursorHeight = 48;
			this.g2.setColor(Color.white);
			this.g2.setStroke(new BasicStroke(3.0F));
			this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;

			int dFrameHeight = 48 * 3;
			int textX = dFrameX + 20;

			int textY = dFrameY + 48;
			this.g2.setFont(this.g2.getFont().deriveFont(28.0F));
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if (itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				byte b;
				int j;
				String[] arrayOfString;
				for (j = (arrayOfString = entity.inventory.get(itemIndex).description
						.split("\n")).length, b = 0; b < j;) {
					String line = arrayOfString[b];
					this.g2.drawString(line, textX, textY);
					textY += 32;
					b++;
				}
			}
		}
	}

	public void drawGameOverScreen() {
		this.g2.setColor(new Color(0, 0, 0, 150));

		this.g2.fillRect(0, 0, 960, 576);
		this.g2.setFont(this.g2.getFont().deriveFont(1, 110.0F));
		String text = "Game Over";
		this.g2.setColor(Color.black);
		int x = centerTextX(text);

		int y = 48 * 4;
		this.g2.drawString(text, x, y);
		this.g2.setColor(Color.white);
		this.g2.drawString(text, x - 4, y - 4);
		this.g2.setFont(this.g2.getFont().deriveFont(50.0F));
		text = "Retry";
		x = centerTextX(text);

		y += 48 * 4;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 0)
			this.g2.drawString(">", x - 40, y);
		text = "Quit";
		x = centerTextX(text);
		y += 55;
		this.g2.drawString(text, x, y);
		if (this.commandNum == 1)
			this.g2.drawString(">", x - 40, y);
	}

	public void drawOptionsScreen() {
		this.g2.setColor(Color.white);
		this.g2.setFont(this.g2.getFont().deriveFont(32.0F));

		int frameX = 48 * 6;

		int frameY = 48;

		int frameWidth = 48 * 8;

		int frameHeight = 48 * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		switch (this.subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotification(frameX, frameY);
			break;
		case 2:
			options_control(frameX, frameY);
			break;
		case 3:
			options_endGameConfirmation(frameX, frameY);
			break;
		}
		this.gp.keyH.enterPressed = false;
	}

	public void options_top(int frameX, int frameY) {
		String text = "Options";
		int textX = centerTextX(text);

		int textY = frameY + 48;
		this.g2.drawString(text, textX, textY);

		textX = frameX + 48;

		textY += 48 * 2;
		this.g2.drawString("Full Screen", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				if (!this.gp.fullScreenOn) {
					this.gp.fullScreenOn = true;
				} else if (this.gp.fullScreenOn) {
					this.gp.fullScreenOn = false;
				}
				this.subState = 1;
			}
		}

		textY += 48;
		this.g2.drawString("Music", textX, textY);
		if (this.commandNum == 1)
			this.g2.drawString(">", textX - 25, textY);

		textY += 48;
		this.g2.drawString("SE", textX, textY);
		if (this.commandNum == 2)
			this.g2.drawString(">", textX - 25, textY);

		textY += 48;
		this.g2.drawString("Control", textX, textY);
		if (this.commandNum == 3) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				this.subState = 2;
				this.commandNum = 0;
			}
		}

		textY += 48;
		this.g2.drawString("End Game", textX, textY);
		if (this.commandNum == 4) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				this.subState = 3;
				this.commandNum = 0;
			}
		}

		textY += 48 * 2;
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 5) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {

				this.gp.gameState = 1;
				this.commandNum = 0;
			}
		}

		textX = frameX + (int) (48.0D * 4.5D);

		textY = frameY + 48 * 2 + 24;
		this.g2.setStroke(new BasicStroke(3.0F));
		this.g2.drawRect(textX, textY, 24, 24);
		if (this.gp.fullScreenOn)
			this.g2.fillRect(textX, textY, 24, 24);

		textY += 48;
		this.g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * this.gp.music.volumeScale;
		this.g2.fillRect(textX, textY, volumeWidth, 24);

		textY += 48;
		this.g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * this.gp.se.volumeScale;
		this.g2.fillRect(textX, textY, volumeWidth, 24);
		this.gp.config.saveConfiguration();
	}

	public void options_fullScreenNotification(int frameX, int frameY) {

		int textX = frameX + 48;

		int textY = frameY + 48 * 3;
		this.currentDialogue = "The change will take \neffect after restarting \nthe game.";
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = this.currentDialogue.split("\n")).length, b = 0; b < i;) {
			String line = arrayOfString[b];
			this.g2.drawString(line, textX, textY);
			textY += 40;
			b++;
		}

		textY = frameY + 48 * 9;
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed)
				this.subState = 0;
		}
	}

	public void options_control(int frameX, int frameY) {
		String text = "Control";
		int textX = centerTextX(text);

		int textY = frameY + 48;
		this.g2.drawString(text, textX, textY);

		textX = frameX + 48;

		textY += 48;
		int lineHeight = 36;
		this.g2.setFont(this.g2.getFont().deriveFont(24.0F));
		this.g2.drawString("Move", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Attack/Interact", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Magic", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Guard/Parry", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Inventory/Status", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Map", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Mini Map", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Pause", textX, textY);
		textY += lineHeight;
		this.g2.drawString("Options", textX, textY);
		textY += lineHeight;

		textX = frameX + 48 * 5;

		textY = frameY + 48 * 2;
		this.g2.drawString("W/A/S/D", textX, textY);
		textY += lineHeight;
		this.g2.drawString("ENTER", textX, textY);
		textY += lineHeight;
		this.g2.drawString("F", textX, textY);
		textY += lineHeight;
		this.g2.drawString("SPACE", textX, textY);
		textY += lineHeight;
		this.g2.drawString("C", textX, textY);
		textY += lineHeight;
		this.g2.drawString("M", textX, textY);
		textY += lineHeight;
		this.g2.drawString("X", textX, textY);
		textY += lineHeight;
		this.g2.drawString("P", textX, textY);
		textY += lineHeight;
		this.g2.drawString("ESC", textX, textY);
		textY += lineHeight;

		textX = frameX + 48;

		textY = frameY + 48 * 9;
		this.g2.setFont(this.g2.getFont().deriveFont(32.0F));
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				this.subState = 0;
				this.commandNum = 3;
			}
		}
	}

	public void options_endGameConfirmation(int frameX, int frameY) {

		int textX = frameX + 48;

		int textY = frameY + 48 * 3;
		this.currentDialogue = "Quit the game and \nreturn to the title screen?";
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = this.currentDialogue.split("\n")).length, b = 0; b < i;) {
			String line = arrayOfString[b];
			this.g2.drawString(line, textX, textY);
			textY += 40;
			b++;
		}
		String text = "Yes";
		textX = centerTextX(text);

		textY += 48 * 3;
		this.g2.drawString(text, textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				this.subState = 0;

				this.gp.gameState = 0;
				this.gp.resetGame(true);
			}
		}
		text = "No";
		textX = centerTextX(text);

		textY += 48;
		this.g2.drawString(text, textX, textY);
		if (this.commandNum == 1) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.keyH.enterPressed) {
				this.subState = 0;
				this.commandNum = 4;
			}
		}
	}

	public void drawTransition() {
		this.counter++;
		this.g2.setColor(new Color(0, 0, 0, this.counter * 5));

		this.g2.fillRect(0, 0, 960, 576);
		if (this.counter == 50) {
			this.counter = 0;

			this.gp.gameState = 1;
			this.gp.currentMap = this.gp.eHandler.tempMap;

			this.gp.player.worldX = 48 * this.gp.eHandler.tempCol;

			this.gp.player.worldY = 48 * this.gp.eHandler.tempRow;
			this.gp.eHandler.previousEventX = this.gp.player.worldX;
			this.gp.eHandler.previousEventY = this.gp.player.worldY;
			this.gp.changeArea();
		}
	}

	public void drawTradeScreen() {
		switch (this.subState) {
		case 0:
			trade_select();
			break;
		case 1:
			trade_buy();
			break;
		case 2:
			trade_sell();
			break;
		}
		this.gp.keyH.enterPressed = false;
	}

	public void trade_select() {
		this.npc.dialogueSet = 0;
		drawDialogueScreen();

		int x = 48 * 15;

		int y = 48 * 4;

		int width = 48 * 3;

		int height = (int) (48.0D * 3.5D);
		drawSubWindow(x, y, width, height);

		x += 48;

		y += 48;
		this.g2.drawString("Buy", x, y);
		if (this.commandNum == 0) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.keyH.enterPressed)
				this.subState = 1;
		}

		y += 48;
		this.g2.drawString("Sell", x, y);
		if (this.commandNum == 1) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.keyH.enterPressed)
				this.subState = 2;
		}

		y += 48;
		this.g2.drawString("Leave", x, y);
		if (this.commandNum == 2) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.keyH.enterPressed) {
				this.commandNum = 0;
				this.npc.startDialogue(this.npc, 1);
			}
		}
	}

	public void trade_buy() {
		drawInventory(this.gp.player, false);
		drawInventory(this.npc, true);

		int x = 48 * 2;

		int y = 48 * 9;

		int width = 48 * 6;

		int height = 48 * 2;
		drawSubWindow(x, y, width, height);
		this.g2.drawString("[ESC] Back", x + 24, y + 60);

		x = 48 * 12;

		y = 48 * 9;

		width = 48 * 6;

		height = 48 * 2;
		drawSubWindow(x, y, width, height);
		this.g2.drawString("Your Coin: " + this.gp.player.coin, x + 24, y + 60);
		int itemIndex = getItemIndexOnSlot(this.npcSlotCol, this.npcSlotRow);
		if (itemIndex < this.npc.inventory.size()) {

			x = (int) (48.0D * 5.5D);

			y = (int) (48.0D * 5.5D);

			width = (int) (48.0D * 2.5D);

			height = 48;
			drawSubWindow(x, y, width, height);
			this.g2.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
			int price = this.npc.inventory.get(itemIndex).price;
			String i = Integer.toString(price);

			x = getXforAlignToRightText(i, 48 * 8 - 20);
			this.g2.drawString(i, x, y + 34);
			if (this.gp.keyH.enterPressed)
				if (this.npc.inventory.get(itemIndex).price > this.gp.player.coin) {
					this.subState = 0;
					this.npc.startDialogue(this.npc, 2);
				} else if (this.gp.player.canObtainItem(this.npc.inventory.get(itemIndex))) {
					this.gp.player.coin -= this.npc.inventory.get(itemIndex).price;
				} else {
					this.subState = 0;
					this.npc.startDialogue(this.npc, 3);
				}
		}
	}

	public void trade_sell() {
		drawInventory(this.gp.player, true);

		int x = 48 * 2;

		int y = 48 * 9;

		int width = 48 * 6;

		int height = 48 * 2;
		drawSubWindow(x, y, width, height);
		this.g2.drawString("[ESC] Back", x + 24, y + 60);

		x = 48 * 12;

		y = 48 * 9;

		width = 48 * 6;

		height = 48 * 2;
		drawSubWindow(x, y, width, height);
		this.g2.drawString("Your Coin: " + this.gp.player.coin, x + 24, y + 60);
		int itemIndex = getItemIndexOnSlot(this.playerSlotCol, this.playerSlotRow);
		if (itemIndex < this.gp.player.inventory.size()) {

			x = (int) (48.0D * 15.5D);

			y = (int) (48.0D * 5.5D);

			width = (int) (48.0D * 2.5D);

			height = 48;
			drawSubWindow(x, y, width, height);
			this.g2.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
			int price = this.gp.player.inventory.get(itemIndex).price / 2;
			String i = Integer.toString(price); // Using Integer.toString() method

			x = getXforAlignToRightText(i, 48 * 18 - 20);
			this.g2.drawString(i, x, y + 34);
			if (this.gp.keyH.enterPressed)
				if (this.gp.player.inventory.get(itemIndex) == this.gp.player.currentWeapon
						|| this.gp.player.inventory.get(itemIndex) == this.gp.player.currentShield) {
					this.commandNum = 0;
					this.subState = 0;
					this.npc.startDialogue(this.npc, 4);
				} else {
					if (this.gp.player.inventory.get(itemIndex).amount > 1) {
						this.gp.player.inventory.get(itemIndex).amount--;
					} else {
						this.gp.player.inventory.remove(itemIndex);
					}
					this.gp.player.coin += price;
				}
		}
	}

	public void drawSleepScreen() {
		this.counter++;
		if (this.counter < 120) {
			this.gp.eManager.lighting.filterAlpha += 0.01F;
			if (this.gp.eManager.lighting.filterAlpha > 1.0F)
				this.gp.eManager.lighting.filterAlpha = 1.0F;
		}
		if (this.counter >= 120) {
			this.gp.eManager.lighting.filterAlpha -= 0.01F;
			if (this.gp.eManager.lighting.filterAlpha <= 0.0F) {
				this.gp.eManager.lighting.filterAlpha = 0.0F;
				this.counter = 0;
				this.gp.eManager.lighting.getClass();
				this.gp.eManager.lighting.dayState = 0;
				this.gp.eManager.lighting.dayCounter = 0;
				this.gp.gameState = 1;
				this.gp.player.getImage();
			}
		}
	}

	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + slotRow * 5;
		return itemIndex;
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);
		this.g2.setColor(c);
		this.g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255, 255, 255);
		this.g2.setColor(c);
		this.g2.setStroke(new BasicStroke(5.0F));
		this.g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public int centerTextX(String text) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = 960 / 2 - length / 2;
		return x;
	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
