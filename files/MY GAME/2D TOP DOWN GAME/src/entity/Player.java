// Player Information

package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	// keyhandler object
	KeyHandler keyH;
	
	// displayed screen 
	public final int screenX;
	public final int screenY;
	public int hasChest = 0;
	public static BufferedImage playerimage = null;
	
	public String character;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyH = keyH;
		
		// get and initialize screen values
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight /2 - (gp.tileSize / 2);
		
		// collision box
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 30;
		solidArea.height = 32;
		
		// collision area
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	// player default information
	public void setDefaultValues() {
		if (gp.scale != 3) {
			worldX = 392;
			worldY = 403;
			speed = 1; 
		} else {
			worldX = gp.tileSize * 23;
			worldY = gp.tileSize * 21;
			/*
			 * 1104, 1008
			 */
			speed = 3; 
		}
		
		maxLife = 6;
		Life = maxLife;
		
		direction = "idle";
	}
	
	// method to initialize character animation
	public void getPlayerImage() {
		character = "cat";
		if (character == "human") {
			idle = setUp("/player/idle");
			up1 = setUp("/player/boy_up_1");
			up2 = setUp("/player/boy_up_2");
			down1 = setUp("/player/boy_down_1");
			down2 = setUp("/player/boy_down_2");
			left1 = setUp("/player/boy_left_1");
			left2 = setUp("/player/boy_left_2");
			right1 = setUp("/player/boy_right_1");
			right2 = setUp("/player/boy_right_2");
		}
		
		if (character == "cat") {
			idle = setUp("/player/catidle");
			up1 = setUp("/player/catup1");
			up2 = setUp("/player/catup2");
			down1 = setUp("/player/catdown1");
			down2 = setUp("/player/catdown2");
			left1 = setUp("/player/catleft1");
			left2 = setUp("/player/catleft2");
			right1 = setUp("/player/catright1");
			right2 = setUp("/player/catright2");
		}
	}

	
	// LOOP METHOD FOR PLAYER
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.lefPressed == true || keyH.rightPressed == true) {
		if (keyH.upPressed == true) {
			direction = "up";
		}
		else if (keyH.downPressed == true) {
			direction = "down";
		}
		else if (keyH.lefPressed == true) {
			direction = "left";
		} 
		else if (keyH.rightPressed == true) {
			direction = "right";
		}
		
		// COLLISION INFORMATION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		gp.eHandler.checkEvent();
		keyH.interaction = false;
		
		if (collisionOn == false) {
			switch (direction) {
			case "up": worldY -= speed;
				break;
			case "down": worldY += speed;
				break;
			case "left": worldX -= speed;
				break;
			case "right": worldX += speed;
				break;

			default:
				break;
			}
		}
		
		// ANIMATION INFORMATION
		spriteCounter++;
		if (spriteCounter > 15) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
			}
		} else {
			direction = "idle";
		}
	}
	
	// WHEN OBJECT IS TOUCHED
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
			switch (objectName) {
			case "chest":
				hasChest++;
				gp.obj[i] = null;
				gp.ui.showMessage("Chest Aquired");
				break;

			default:
				break;
			}
		}
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			if (keyH.interaction == true) {
				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
		}
	}
	
	// draw player
	public void draw(Graphics2D g2) {
		switch (direction) {
		case "up": 
			if (spriteNum == 1) {
				playerimage = up1;
			}
			
			if (spriteNum == 2) {
				playerimage = up2;
			}
			
			break;
			
		case "down": 
			if (spriteNum == 1) {
				playerimage = down1;
			}
			if (spriteNum == 2) {
				playerimage = down2;
			}
			
			break;
			
		case "left": 
			if (spriteNum == 1) {
				playerimage = left1;
			}
			if (spriteNum == 2) {
				playerimage = left2;
			}
			
			break;
			
		case "right": 
			if (spriteNum == 1) {
				playerimage = right1;
			}
			if (spriteNum == 2) {
				playerimage = right2;
			}
			
			break;
			
		case "idle":
			playerimage = idle;
			break;
			
		case "attack":
			// image = attack;
			break;
		}
		int x = screenX;
		int y = screenY;
		
		if (screenX > worldX) {
			x = worldX;
		}
		
		if (screenY > worldY) {
			y = worldY;
		}
		
		int rightOffset = gp.screenWidth - screenX;
		
		if (rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}
		
		int bottomOffset = gp.screenHeight - screenY;
		
		if (bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		g2.drawImage(playerimage, x, y, null);
	}
}
