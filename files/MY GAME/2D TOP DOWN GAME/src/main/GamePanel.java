// GAME PANEL
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// TILE SIZE
	public final int originalTileSize = 16;
	
	// ZOOM
	public final int scale = 3;
	
	// DISPLAY TILES
	public final int tileSize = originalTileSize * scale;
	
	// SIZE OF WORLD
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// SIZE OF TILES DISPLAYED IN CAMERA
	public final int maxScreenCol = 25;
	public final int maxScreenRow = 15;
	
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// SIZE OF SCREEN OF CAMERA
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// FPS
	public String PRINTFPS;
	int FPS = 60;
	
	// EXTRAS
	public boolean debug = false;
	public boolean npcHitbox = false;
	public boolean objHitbox = false;
	public Font universalFont;
	
	// MAIN OBJECTS
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound SE = new Sound();
	Sound music = new Sound();
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	// VARIABLE THREAD USED AS TIME
	Thread gameThread;
		
	// RULES
	public CollisionChecker cChecker = new CollisionChecker(this);
	public int gameState;
	public final int titleState = 0; 
	public final int playState = 1; 
	public final int pauseState = 2;
	public final int dialogState = 3;
	
	// PLAYER AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public AssetSetter aSetter = new AssetSetter(this);
	public Entity npc[] = new Entity[10];

	public GamePanel() {
		// SET SIZE OF PANEL
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		// MAKE THE RENDERING SMOOTH
		this.setDoubleBuffered(true);
		
		// IMPORT KEYLISTENER
		this.addKeyListener(keyH);
		
		// ALLOW TO RECEIVE INPUTS
		this.setFocusable(true);
	}

	// LOAD PLACEABLE OBJECTS
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		gameState = titleState;
		if (gameState == titleState) {
			playMusic(0);
		} else {
			playMusic(1);
		}
		
		loadFont();
	}

	// METHOD TO START GAME TIME
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void runTime() {
		double drawInterval = 1000000000 / FPS;
		// double nextDrawTime = System.nanoTime() + drawInterval;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while (gameThread != null) {
			/*
			 * test: System.out.println("Test if Game is Running"); long currentTime =
			 * System.nanoTime(); long currentTime2 = System.currentTimeMillis();
			 * System.out.println(currentTime); System.out.println(currentTime2);
			 * 
			 * Sleep Method try { double remainingTime = nextDrawTime - System.nanoTime();
			 * remainingTime = remainingTime / 1000000; if (remainingTime < 0) {
			 * remainingTime = 0; } Thread.sleep((long)remainingTime); nextDrawTime +=
			 * drawInterval; } catch (InterruptedException e) { e.printStackTrace(); }
			 */

			// Delta / Accumulator Method
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime); 
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();	
				repaint();
				delta--;
				drawCount++;
			}
			
			 if (timer >= 1000000000) { 
				 PRINTFPS = returnFPS(drawCount);
			 drawCount = 0; 
			 timer = 0; 
			 }
		}
	}
	
	// METHOD TO RUN THE GAME
	@Override
	public void run() {
		runTime();
	}
	
	// METHOD RETURN FPS
	public String returnFPS(int count) {
		String STRINGFPS = Integer.toString(count);
		return STRINGFPS;
	}

	public void update() {
		if (gameState == playState) {
			player.update();
			
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
		} 
		
		if (gameState == playState) {
			music.play();
		}
		
		if (gameState == pauseState) {
			music.stop();
		}
	}

	public void loadFont() {
		InputStream df = getClass().getResourceAsStream("/font/dialoguefont.ttf");
		
		try {
			universalFont = Font.createFont(Font.TRUETYPE_FONT, df);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// PAINT IN PANEL
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // DRAW
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setFont(universalFont);
	    g2.setColor(Color.WHITE);
	    
	    if (gameState == titleState) {
	    	ui.draw(g2);
		} else {
			
			// CHECK PERFORMANCE
		    long drawStart = 0;
		    if (keyH.debug == true) {
		    	drawStart = System.nanoTime();
			}
		    
		    // DRAW TILES
		    tileM.draw(g2);

		    // DRAW OBJECTS
		    for (int i = 0; i < obj.length; i++) {
		        if (obj[i] != null) {
		            obj[i].draw(g2, this);
		        }
		    }
		    
		    // DRAW NPCS
		    for (int i = 0; i < npc.length; i++) {
		    	if (npc[i] != null) {
					npc[i].draw(g2);
				}
		    }

		    // DRAW PLAYER
		    player.draw(g2);

		    // DRAW UI
		    ui.draw(g2);
		   
		    if (keyH.debug == true) {
		    	long drawEnd = System.nanoTime();
		    	long passed = drawEnd - drawStart;
		 	   	String text = "DT: " + passed;

		 	   	g2.setColor(Color.WHITE);
		 	   	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		 	   	g2.drawString(text, 10, screenHeight /2 - (tileSize / 2) + 200);
		 		    	
		 		debug(g2);
		 	} 
		    
		    if (keyH.debug == false) {
				 // ENTITY HITBOX
				 npcHitbox = false;
				 
				 // OBJECT HITBOX
				 objHitbox = false;
			}
		}
	    
	    // SMOOTHER DRAWING
	    g2.dispose();
	}
	
	public void debug(Graphics2D g2) {
		 // DEBUG INFORMATION
		 g2.setColor(Color.WHITE);
		 int x = 10; 
		 int y = screenHeight /2 - (tileSize / 2) + 220; 
		 
	     g2.drawString("FPS: " + PRINTFPS, x, y);
	     y += 20; 
	     g2.drawString("X: " + player.worldX, x, y);
	     y += 20; 
	     g2.drawString("Y: " + player.worldY, x, y);
	    
	     int TileX = (player.worldX + player.solidArea.x) / tileSize;
		 int TileY = (player.worldY + player.solidArea.y) / tileSize;
		 
		 y += 20; 
	     g2.drawString("Tile X: " + TileX, x, y);
	    
	     y += 20; 
	     g2.drawString("Tile Y: " + TileY, x, y);
		
		 // PLAYER HITBOX
		 g2.setColor(Color.blue);
		 g2.drawRect(screenWidth / 2 - (tileSize / 2) + player.solidArea.x, screenHeight /2 - (tileSize / 2) + player.solidArea.y, player.solidArea.width, player.solidArea.height);
		 		
		 // ENTITY HITBOX
		 npcHitbox = true;
		 
		 // OBJECT HITBOX
		 objHitbox = true;
	}
	
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		SE.setFile(i);
		SE.play();
	}
}
