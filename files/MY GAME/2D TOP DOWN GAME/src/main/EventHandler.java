package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23; 
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void checkEvent() {
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent == true) {
			if (hit(21, 22, "any") == true) {
				damage(21, 22, gp.dialogState);
			}
			
//			if (hit(21, 22, "any") == true) {
//				heal(gp.dialogState);
//			}
//			
//			if (hit(21, 22, "any") == true) {
//				teleport(gp.dialogState);
//			}
		}
	}
	
	public void teleport(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleported";
		gp.player.worldX = gp.originalTileSize * 52;
		gp.player.worldY = gp.originalTileSize * 52;
	}
	
	public void damage(int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You are damaged";
		gp.player.Life -= 1;
		canTouchEvent = false;
		
		// eventRect[col][row].eventDone = true; // 1 time event
	}
	
	public void heal(int gameState) {
		if (gp.keyH.interaction == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You are Healed";
			gp.player.Life = gp.player.maxLife;
		}
		gp.keyH.interaction = false;
	}
	
	
	public boolean hit(int col, int row, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		
		if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

		return hit;
	}
}
