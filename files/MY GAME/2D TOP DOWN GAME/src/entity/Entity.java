package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicMenuUI.ChangeHandler;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	// entity location
	public int worldX;
	public int worldY;
	
	GamePanel gp;
	
	public int x, y, speed;
	
	// ANIMATIONS
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int actionLockCounter = 0;
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public boolean collisionOn = false;
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	// CHARACTER STATUS
	public int maxLife;
	public int Life;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		if (gp.gameState == gp.dialogState) {
			switch (gp.player.direction) {
			case "up": 
				direction = "down";
				gp.player.direction = "up";
				break;
			case "down": 
				direction = "up";
				gp.player.direction = "idle";
				break;
			case "left": 
				direction = "right";
				gp.player.direction = "left";
				break;
			case "right": 
				direction = "left";
				gp.player.direction = "right";
				break;
			case "idle":
				direction = "idle";
				gp.player.direction = "idle";
			}
		}
	}
	
	public void setAction() {
		
    }

	public void update() {
		setAction();
		
		// COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
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
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage entityimage = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > worldX - gp.player.screenX && 
				worldX - gp.tileSize < worldX + gp.player.screenX &&
				worldY + gp.tileSize > worldY - gp.player.screenX && 
				worldY - gp.tileSize < worldY + gp.player.screenY
				) {
			
			switch (direction) {
			case "up": 
				if (spriteNum == 1) {
					entityimage = up1;
				}
				
				if (spriteNum == 2) {
					entityimage = up2;
				}
				
				break;
				
			case "down": 
				if (spriteNum == 1) {
					entityimage = down1;
				}
				if (spriteNum == 2) {
					entityimage = down2;
				}
				
				break;
				
			case "left": 
				if (spriteNum == 1) {
					entityimage = left1;
				}
				if (spriteNum == 2) {
					entityimage = left2;
				}
				
				break;
				
			case "right": 
				if (spriteNum == 1) {
					entityimage = right1;
				}
				if (spriteNum == 2) {
					entityimage = right2;
				}
				
				break;
				
			case "idle":
				entityimage = idle;
				break;
				
			case "attack":
				// image = attack;
				break;
			}
			
			if (gp.npcHitbox == true) {
				g2.setColor(Color.yellow);
				g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
			} 
			
			if (gp.gameState != gp.dialogState) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString("E", screenX + 15, screenY - 20);
			}
			
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			
			Rectangle eventRect = new Rectangle();
			eventRect.x = 23; 
			eventRect.y = 23;
			eventRect.width = 2;
			eventRect.height = 2;
			int eventRectDefaultX = eventRect.x;
			int eventRectDefaultY = eventRect.y;
			
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect.x = 21 * gp.tileSize + eventRect.x;
			eventRect.y = 22 * gp.tileSize + eventRect.y;
			
			g2.drawRect(eventRect.x, eventRect.y, gp.tileSize, gp.tileSize);
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect.x = eventRectDefaultX;
			eventRect.y = eventRectDefaultY;
			
			g2.drawImage(entityimage, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	// ENTITY SCALING RENDERING
	public BufferedImage setUp(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			
		}
		return image;
	}
}
