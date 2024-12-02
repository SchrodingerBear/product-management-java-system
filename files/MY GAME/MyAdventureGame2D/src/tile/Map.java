package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import main.GamePanel;

public class Map extends TileManager {
	GamePanel gp;

	BufferedImage[] worldMap;

	public boolean miniMapOn = false;

	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		createWorldMap();
	}

	public void createWorldMap() {
		
		this.worldMap = new BufferedImage[10];
		
		int worldMapWidth = 48 * this.gp.maxWorldCol;
		
		int worldMapHeight = 48 * this.gp.maxWorldRow;
		int i = 0;
		
		for (; i < 10; i++) {
			this.worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, 2);
			Graphics2D g2 = this.worldMap[i].createGraphics();
			int col = 0;
			int row = 0;
			while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
				int tileNum = this.mapTileNum[i][col][row];
				
				int x = 48 * col;
				
				int y = 48 * row;
				g2.drawImage((this.tile[tileNum]).image, x, y, (ImageObserver) null);
				col++;
				if (col == this.gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			g2.dispose();
		}
	}

	public void drawFullMapScreen(Graphics2D g2) {
		g2.setColor(Color.black);
		
		
		g2.fillRect(0, 0, 960, 576);
		int width = 500;
		int height = 500;
		
		int x = 960 / 2 - width / 2;
		
		int y = 576 / 2 - height / 2;
		g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);
		
		double scale = (48 * this.gp.maxWorldCol) / width;
		int playerX = (int) (x + this.gp.player.worldX / scale);
		int playerY = (int) (y + this.gp.player.worldY / scale);
		
		int playerSize = (int) (48.0D / scale);
		g2.drawImage(this.gp.player.down1, playerX, playerY, playerSize, playerSize, null);
		g2.setFont(this.gp.ui.maruMonica.deriveFont(32.0F));
		g2.setColor(Color.white);
		g2.drawString("Press M to close", 750, 550);
	}

	public void drawMiniMap(Graphics2D g2) {
		if (this.miniMapOn) {
			int width = 200;
			int height = 200;
			
			int x = 960 - width - 20;
			int y = 20;
			g2.setComposite(AlphaComposite.getInstance(3, 0.8F));
			g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);
			
			double scale = (48 * this.gp.maxWorldCol) / width;
			int playerX = (int) (x + this.gp.player.worldX / scale);
			int playerY = (int) (y + this.gp.player.worldY / scale);
			
			int playerSize = 48 / 3;
			g2.drawImage(this.gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
			g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
		}
	}
}
