package main;

import data.Progress;
import entity.Entity;

public class EventHandler {
	GamePanel gp;

	EventRect[][][] eventRect;

	Entity eventMaster;

	int previousEventX;

	int previousEventY;

	boolean canTouchEvent = true;

	int tempMap;

	int tempCol;

	int tempRow;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		this.eventMaster = new Entity(gp);
		gp.getClass();
		this.eventRect = new EventRect[10][gp.maxWorldCol][gp.maxWorldRow];
		int map = 0;
		int col = 0;
		int row = 0;
		gp.getClass();
		while (map < 10 && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			this.eventRect[map][col][row] = new EventRect();
			(this.eventRect[map][col][row]).x = 23;
			(this.eventRect[map][col][row]).y = 23;
			(this.eventRect[map][col][row]).width = 2;
			(this.eventRect[map][col][row]).height = 2;
			(this.eventRect[map][col][row]).eventRectDefaultX = (this.eventRect[map][col][row]).x;
			(this.eventRect[map][col][row]).eventRectDefaultY = (this.eventRect[map][col][row]).y;
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
				if (row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		setDialogue();
	}

	public void setDialogue() {
		this.eventMaster.dialogues[0][0] = "You fall into a pit!";
		this.eventMaster.dialogues[1][0] = "You drink the water.\nYour life and mana have been recovered.\n(The progress has been saved)";
		this.eventMaster.dialogues[1][1] = "Damn, this is good water.";
	}

	public void checkEvent() {
		int xDistance = Math.abs(this.gp.player.worldX - this.previousEventX);
		int yDistance = Math.abs(this.gp.player.worldY - this.previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if (distance > 48)
			this.canTouchEvent = true;
		if (this.canTouchEvent)
			if (hit(0, 27, 16, "right")) {
				
				damagePit(3);
			} else if (hit(0, 22, 12, "up")) {
				
				healingPool(3);
			} else if (hit(0, 23, 12, "up")) {
				
				healingPool(3);
			} else if (hit(0, 24, 12, "up")) {
				
				healingPool(3);
			} else if (hit(0, 10, 39, "any")) {
				
				teleport(1, 12, 12, 51);
			} else if (hit(1, 12, 13, "any")) {
				
				teleport(0, 10, 39, 50);
			} else if (hit(1, 12, 9, "up")) {
				speak(this.gp.npc[1][0]);
			} else if (hit(0, 12, 9, "any")) {
				
				teleport(2, 9, 41, 52);
			} else if (hit(2, 9, 41, "any")) {
				
				teleport(0, 12, 9, 50);
			} else if (hit(2, 8, 7, "any")) {
				
				teleport(3, 8, 7, 52);
			} else if (hit(3, 8, 7, "any")) {
				
				teleport(2, 8, 7, 52);
			} else if (hit(3, 26, 41, "any")) {
				
				teleport(4, 26, 41, 52);
			} else if (hit(4, 26, 41, "any")) {
				
				teleport(3, 26, 41, 52);
			} else if (hit(4, 25, 27, "any")) {
				skeletonLord();
			}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if (map == this.gp.currentMap) {
			this.gp.player.solidArea.x = this.gp.player.worldX + this.gp.player.solidArea.x;
			this.gp.player.solidArea.y = this.gp.player.worldY + this.gp.player.solidArea.y;
			
			(this.eventRect[map][col][row]).x = col * 48 + (this.eventRect[map][col][row]).x;
			
			(this.eventRect[map][col][row]).y = row * 48 + (this.eventRect[map][col][row]).y;
			if (this.gp.player.solidArea.intersects(this.eventRect[map][col][row])
					&& !(this.eventRect[map][col][row]).eventDone
					&& (this.gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))) {
				hit = true;
				this.previousEventX = this.gp.player.worldX;
				this.previousEventY = this.gp.player.worldY;
			}
			this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
			this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
			(this.eventRect[map][col][row]).x = (this.eventRect[map][col][row]).eventRectDefaultX;
			(this.eventRect[map][col][row]).y = (this.eventRect[map][col][row]).eventRectDefaultY;
		}
		return hit;
	}

	public void damagePit(int gameState) {
		this.gp.gameState = gameState;
		this.gp.playSE(6);
		this.eventMaster.startDialogue(this.eventMaster, 0);
		this.gp.player.life--;
		this.canTouchEvent = false;
	}

	public void healingPool(int gameState) {
		if (this.gp.keyH.enterPressed) {
			this.gp.gameState = gameState;
			this.gp.player.attackCanceled = true;
			this.gp.playSE(2);
			this.eventMaster.startDialogue(this.eventMaster, 1);
			this.gp.player.life = this.gp.player.maxLife;
			this.gp.player.mana = this.gp.player.maxMana;
			this.gp.aSetter.setMonster();
			this.gp.saveLoad.save();
		}
	}

	public void teleport(int map, int col, int row, int area) {
		
		this.gp.gameState = 7;
		this.gp.nextArea = area;
		this.tempMap = map;
		this.tempCol = col;
		this.tempRow = row;
		this.canTouchEvent = false;
		this.gp.playSE(13);
	}

	public void speak(Entity entity) {
		if (this.gp.keyH.enterPressed) {
			
			this.gp.gameState = 3;
			this.gp.player.attackCanceled = true;
			entity.speak();
		}
	}

	public void skeletonLord() {
		if (!this.gp.bossBattleOn && !Progress.skeletonLordDefeated) {
			
			this.gp.gameState = 11;
			this.gp.csManager.getClass();
			this.gp.csManager.sceneNum = 2;
		}
	}
}
