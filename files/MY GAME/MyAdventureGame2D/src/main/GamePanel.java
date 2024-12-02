	package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {
	// TILE SETTING
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = 48;

	// TILE IN WINDOW SIZE
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;

	// WINDOW SIZE
	public final int screenWidth = 960;
	public final int screenHeight = 576;

	// WORLD SIZE
	public int maxWorldCol;
	public int maxWorldRow;

	// MAP
	public final int maxMap = 10;
	public int currentMap = 0;

	int screenWidth2 = 960;
	int screenHeight2 = 576;
	BufferedImage tempScreen;

	Graphics2D g2;

	public boolean fullScreenOn = false;
	int FPS = 60;

	public boolean uniTimerOn;
	public int uniTimer;

	// OBJECTS
	// tiles Setting
	public TileManager tileM = new TileManager(this);
	// key Setting
	public KeyHandler keyH = new KeyHandler(this);
	// collision Setting
	public CollisionChecker cChecker = new CollisionChecker(this);
	// asset Setting
	public AssetSetter aSetter = new AssetSetter(this);
	// game UI
	public UI ui = new UI(this);
	// game Events
	public EventHandler eHandler = new EventHandler(this);
	// game Configuration
	public Config config = new Config(this);
	// AI Path
	public PathFinder pFinder = new PathFinder(this);
	// game Environment
	public EnvironmentManager eManager = new EnvironmentManager(this);
	// game Map
	public Map map = new Map(this);
	// game Loading
	public SaveLoad saveLoad = new SaveLoad(this);
	// game Entities
	public EntityGenerator eGenerator = new EntityGenerator(this);
	// game Scenes
	public CutsceneManager csManager = new CutsceneManager(this);

	public Entity e = new Entity(this);

	// SOUND
	Sound music = new Sound();
	Sound se = new Sound();

	// GAME TIME
	Thread gameThread;

	// ASSETS
	public Player player = new Player(this, this.keyH);
	public Entity[][] obj = new Entity[10][20];
	public Entity[][] npc = new Entity[10][10];
	public Entity[][] monster = new Entity[10][40];
	public InteractiveTile[][] iTile = new InteractiveTile[10][100];
	public Entity[][] projectile = new Entity[10][20];
	// particle
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();

	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	public final int cutsceneState = 11;

	public boolean bossBattleOn = false;
	public int currentArea;
	public int nextArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;

	public String character;

	public GamePanel() {
		setPreferredSize(new Dimension(960, 576));
		setBackground(Color.black);
		setDoubleBuffered(true);
		addKeyListener(this.keyH);
		setFocusable(true);
	}

	public void gameStart() {
		this.aSetter.setObject();
		UtilityTool.log("setObject Loaded");

		this.aSetter.setNPC();
		UtilityTool.log("setNPC Loaded");

		this.aSetter.setMonster();
		UtilityTool.log("setMonster Loaded");

		this.aSetter.setInteractiveTile();
		UtilityTool.log("setInteractiveTile Loaded");

		this.eManager.setup();
		UtilityTool.log("eManager Loaded");

		this.gameState = titleState;
		this.currentArea = 50;
		this.tempScreen = new BufferedImage(960, 576, 2);
		this.g2 = (Graphics2D) this.tempScreen.getGraphics();
		if (this.fullScreenOn)
			setFullScreen();

		playMusic(23);
	}

	public void resetGame(boolean restart) {
		stopMusic();
		this.currentArea = 50;
		removeTempEntity();
		this.bossBattleOn = false;
		this.player.setDefaultPositions();
		this.player.restoreStatus();
		this.player.resetCounter();
		this.aSetter.setNPC();
		this.aSetter.setMonster();
		if (restart) {
			this.player.setDefaultValues();
			this.aSetter.setObject();
			this.aSetter.setInteractiveTile();
			this.eManager.lighting.resetDay();
		}
	}

	public void setFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		this.screenWidth2 = Main.window.getWidth();
		this.screenHeight2 = Main.window.getHeight();
	}

	public void startGameThread() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = (1000000000 / this.FPS);
		double delta = 0.0D;
		long lastTime = System.nanoTime();
		while (this.gameThread != null) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			while (delta >= 1.0D) {
				update();
				drawToTempScreen();
				drawToScreen();
				delta--;
			}
		}
	}

	public void update() {
		if (this.gameState == playState) {
			this.player.update();
			int i;
			for (i = 0; i < (this.npc[1]).length; i++) {
				if (this.npc[this.currentMap][i] != null)
					this.npc[this.currentMap][i].update();
			}

			for (i = 0; i < (this.monster[1]).length; i++) {
				if (this.monster[this.currentMap][i] != null) {
					if ((this.monster[this.currentMap][i]).alive && !(this.monster[this.currentMap][i]).dying)
						this.monster[this.currentMap][i].update();
					if (!(this.monster[this.currentMap][i]).alive) {
						this.monster[this.currentMap][i].checkDrop();
						this.monster[this.currentMap][i] = null;
					}
				}
			}
			for (i = 0; i < (this.projectile[1]).length; i++) {
				if (this.projectile[this.currentMap][i] != null) {
					if ((this.projectile[this.currentMap][i]).alive)
						this.projectile[this.currentMap][i].update();
					if (!(this.projectile[this.currentMap][i]).alive)
						this.projectile[this.currentMap][i] = null;
				}
			}
			for (i = 0; i < this.particleList.size(); i++) {
				if (this.particleList.get(i) != null) {
					if (this.particleList.get(i).alive)
						this.particleList.get(i).update();
					if (!this.particleList.get(i).alive)
						this.particleList.remove(i);
				}
			}
			for (i = 0; i < (this.iTile[1]).length; i++) {
				if (this.iTile[this.currentMap][i] != null)
					this.iTile[this.currentMap][i].update();
			}
			this.eManager.update();
		}
	}

	public void drawToTempScreen() {
		long drawStart = 0L;
		if (this.keyH.showDebugText)
			drawStart = System.nanoTime();
		if (this.gameState == 0) {
			this.ui.draw(this.g2);
		} else if (this.gameState == 10) {
			this.map.drawFullMapScreen(this.g2);
		} else {
			this.tileM.draw(this.g2);
			int i;
			for (i = 0; i < (this.iTile[1]).length; i++) {
				if (this.iTile[this.currentMap][i] != null)
					this.iTile[this.currentMap][i].draw(this.g2);
			}
			this.entityList.add(this.player);
			for (i = 0; i < (this.npc[1]).length; i++) {
				if (this.npc[this.currentMap][i] != null)
					this.entityList.add(this.npc[this.currentMap][i]);
			}
			for (i = 0; i < (this.obj[1]).length; i++) {
				if (this.obj[this.currentMap][i] != null)
					this.entityList.add(this.obj[this.currentMap][i]);
			}
			for (i = 0; i < (this.monster[1]).length; i++) {
				if (this.monster[this.currentMap][i] != null)
					this.entityList.add(this.monster[this.currentMap][i]);
			}
			for (i = 0; i < (this.projectile[1]).length; i++) {
				if (this.projectile[this.currentMap][i] != null)
					this.entityList.add(this.projectile[this.currentMap][i]);
			}
			for (i = 0; i < this.particleList.size(); i++) {
				if (this.particleList.get(i) != null)
					this.entityList.add(this.particleList.get(i));
			}
			Collections.sort(this.entityList, new Comparator<Entity>() {
				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			for (Entity en : this.entityList)
				en.draw(this.g2);
			this.entityList.clear();
			this.eManager.draw(this.g2);
			this.map.drawMiniMap(this.g2);
			this.csManager.draw(this.g2);
			this.ui.draw(this.g2);
		}
		if (this.keyH.showDebugText) {
			g2.setColor(Color.blue);
			g2.drawRect(screenWidth / 2 - (tileSize / 2) + player.solidArea.x, screenHeight /2 - (tileSize / 2) + player.solidArea.y, player.solidArea.width, player.solidArea.height);
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			this.g2.setFont(new Font("Arial", 0, 20));
			this.g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			this.g2.drawString("WorldX: " + this.player.worldX, x, y);
			y += lineHeight;
			this.g2.drawString("WorldY: " + this.player.worldY, x, y);
			y += lineHeight;
			this.g2.drawString("Col: " + ((this.player.worldX + this.player.solidArea.x) / 48), x, y);
			y += lineHeight;
			this.g2.drawString("Row: " + ((this.player.worldY + this.player.solidArea.y) / 48), x, y);
			y += lineHeight;
			this.g2.drawString("Draw Time: " + passed, x, y);
			y += lineHeight;
			this.g2.drawString("God Mode:" + this.keyH.godModeOn, x, y);
		}
	}
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, null);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void playMusic(int i) {
		this.music.setFile(i);
		this.music.play();
		this.music.loop();
	}

	public void stopMusic() {
		this.music.stop();
	}

	public void playSE(int i) {
		this.se.setFile(i);
		this.se.play();
	}

	public void changeArea() {
		if (this.nextArea != this.currentArea) {
			stopMusic();
			if (this.nextArea == 50)
				playMusic(0);
			if (this.nextArea == 51)
				playMusic(18);
			if (this.nextArea == 52)
				playMusic(19);
			this.aSetter.setNPC();
		}
		this.currentArea = this.nextArea;
		this.aSetter.setMonster();
	}

	public void removeTempEntity() {
		for (int mapNum = 0; mapNum < 10; mapNum++) {
			for (int i = 0; i < (this.obj[1]).length; i++) {
				if (this.obj[mapNum][i] != null && (this.obj[mapNum][i]).temp)
					this.obj[mapNum][i] = null;
			}
		}
	}
}
