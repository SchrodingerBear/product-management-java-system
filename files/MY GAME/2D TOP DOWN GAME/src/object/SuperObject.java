// Object Information

package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.text.Utilities;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	// image of the object
	public BufferedImage  image, image2, image3;
	
	// name of the object
	public String name;
	
	// collision of object
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0 , 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > worldX - gp.player.screenX && 
				worldX - gp.tileSize < worldX + gp.player.screenX &&
				worldY + gp.tileSize > worldY - gp.player.screenX && 
				worldY - gp.tileSize < worldY + gp.player.screenY
				) {
			
			if (gp.objHitbox == true) {
				g2.setColor(Color.GREEN);
			    g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}
			
		    
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
