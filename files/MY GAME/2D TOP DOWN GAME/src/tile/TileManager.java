// Tile Generation 

package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;
import javax.swing.text.Utilities;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		// INITIALIZE 10 TILES
		tile = new Tile[50];
		
		// COUNTS OF TILES 
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		// LOAD TILES
		getTileImage();
		
		// LOAD MAP
		loadMap("/maps/worldmap.txt");
	}
	
	// METHOD TO LOAD MAP
	public void loadMap(String filepath) {
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {

		}
	}
	
	// METHOD TO SET TIME IMAGE
	public void getTileImage() {
		// NOT TO USE SINGLE DIGITS (INITIALIZE)
		setUp(1, "grass", false);
		setUp(2, "grass", false);
		setUp(3, "grass", false);
		setUp(4, "grass", false);
		setUp(5, "grass", false);
		setUp(6, "grass", false);
		setUp(7, "grass", false);
		setUp(8, "grass", false);
		setUp(9, "grass", false);
		setUp(10, "grass", false);
		
		
		setUp(11, "soil", false);
		setUp(12, "grass", false);
		setUp(13, "grass", false);
		
		setUp(14, "sand", false);
		setUp(15, "grass", true);
		
		// water
		setUp(16, "water00", true);
		setUp(17, "water01", true);
		setUp(18, "water02", true);
		setUp(19, "water03", true);
		setUp(20, "water04", true);
		setUp(21, "water05", true);
		setUp(22, "water06", true);
		setUp(23, "water07", true);
		setUp(24, "water08", true);
		setUp(25, "water09", true);
		setUp(26, "water10", true);
		setUp(27, "water11", true);
		setUp(28, "water12", true);
		setUp(29, "water13", true);
	}
	

	public void setUp(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName+ ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	// METHOD TO DRAW TILE IMAGE
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			// STOP CAMERA ON BOUNDERY LOGIC
			if (gp.player.screenX > gp.player.worldX) {
				screenX = worldX;
			}
			
			if (gp.player.screenY > gp.player.worldY) {
				screenY = worldY;
			}
			
			int rightOffset = gp.screenWidth - gp.player.screenX;
			
			if (rightOffset > gp.worldWidth - gp.player.worldX) {
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			}
			
			int bottomOffset = gp.screenHeight - gp.player.screenY;
			
			if (bottomOffset > gp.worldHeight - gp.player.worldY) {
				screenY = gp.screenHeight - (gp.worldHeight - worldY);
			}
			
			if (worldX + gp.tileSize > worldX - gp.player.screenX && 
					worldX - gp.tileSize < worldX + gp.player.screenX &&
					worldY + gp.tileSize > worldY - gp.player.screenX && 
					worldY - gp.tileSize < worldY + gp.player.screenY
					) {
				
				// TILE HITBOX
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				
				if (gp.keyH.debugtile == true) {
					g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
					g2.setFont(g2.getFont().deriveFont(32F));
					g2.drawString(Integer.toString(tileNum), screenX, screenY);
				}
				
			}
			
			else if (gp.player.screenX > gp.player.worldX ||
					gp.player.screenY > gp.player.worldY ||
					rightOffset > gp.worldWidth - gp.player.worldX ||
					bottomOffset > gp.worldHeight - gp.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;
			
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
