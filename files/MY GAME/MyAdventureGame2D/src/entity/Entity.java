package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	
	// ANIMATIONS
	public BufferedImage idleimage;
	public BufferedImage up1;
	public BufferedImage up2;
	public BufferedImage down1;
	public BufferedImage down2;
	public BufferedImage left1;
	public BufferedImage left2;
	public BufferedImage right1;
	public BufferedImage right2;
	public BufferedImage attackUp1;
	public BufferedImage attackUp2;
	public BufferedImage attackDown1;
	public BufferedImage attackDown2;
	public BufferedImage attackLeft1;
	public BufferedImage attackLeft2;
	public BufferedImage attackRight1;
	public BufferedImage attackRight2;
	public BufferedImage guardUp;
	public BufferedImage guardDown;
	public BufferedImage guardLeft;
	public BufferedImage guardRight;
	public BufferedImage image;
	public BufferedImage image2;
	public BufferedImage image3;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

	public int solidAreaDefaultX;
	public int solidAreaDefaultY;

	public boolean collision = false;

	public String[][] dialogues = new String[20][20];
	public Entity attacker;
	public Entity linkedEntity;

	public boolean temp = false;

	public int worldX;

	public int worldY;

	public String direction = "down";

	public int spriteNum = 1;

	public int dialogueSet = 0;

	public int dialogueIndex = 0;

	public boolean collisionOn = false;

	public boolean invincible = false;

	public boolean attacking = false;

	public boolean alive = true;

	public boolean dying = false;

	public boolean hpBarOn = false;

	public boolean onPath = false;

	public boolean knockBack = false;

	public String knockBackDirection;

	public boolean guarding = false;

	public boolean transparent = false;

	public boolean offBalance = false;

	public Entity loot;

	public boolean opened = false;

	public boolean inRage = false;

	public boolean sleep = false;

	public boolean drawing = true;

	public boolean idle = false;

	public int spriteCounter = 0;

	public int actionLockCounter = 0;

	public int invincibleCounter = 0;

	public int shotAvailableCounter = 0;

	int dyingCounter = 0;

	public int hpBarCounter = 0;

	int knockBackCounter = 0;

	public int guardCounter = 0;

	int offBalanceCounter = 0;

	public int idleCounter = 0;

	public String name;

	public int defaultSpeed;

	public int speed;

	public int maxLife;

	public int life;

	public int maxMana;

	public int mana;

	public int ammo;

	public int level;

	public int strength;

	public int dexterity;

	public int attack;

	public int defense;

	public int exp;

	public int nextLevelExp;

	public int coin;

	public int motion1_duration;

	public int motion2_duration;

	public Entity currentWeapon;

	public Entity currentShield;

	public Entity currentLight;

	public Projectile projectile;

	public boolean boss;

	public int idleTime = 60;

	public ArrayList<Entity> inventory = new ArrayList<>();

	public final int maxInventorySize = 20;

	public int value;

	public int attackValue;

	public int defenseValue;

	public String description = "";

	public int useCost;

	public int price;

	public int knockBackPower = 0;

	public boolean stackable = false;

	public int amount = 1;

	public int lightRadius;

	public boolean cannotSell;

	public int type;

	public final int type_player = 0;

	public final int type_npc = 1;

	public final int type_monster = 2;

	public final int type_sword = 3;

	public final int type_axe = 4;

	public final int type_shield = 5;

	public final int type_consumable = 6;

	public final int type_pickupOnly = 7;

	public final int type_obstacle = 8;

	public final int type_light = 9;

	public final int type_pickaxe = 10;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public int getScreenX() {
		int screenX = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
		return screenX;
	}

	public int getScreenY() {
		int screenY = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
		return screenY;
	}

	public int getLeftX() {
		return this.worldX + this.solidArea.x;
	}

	public int getRightX() {
		return this.worldX + this.solidArea.x + this.solidArea.width;
	}

	public int getTopY() {
		return this.worldY + this.solidArea.y;
	}

	public int getBottomY() {
		return this.worldY + this.solidArea.y + this.solidArea.height;
	}

	public int getCol() {
		
		return (this.worldX + this.solidArea.x) / 48;
	}

	public int getRow() {
		
		return (this.worldY + this.solidArea.y) / 48;
	}

	public int getCenterX() {
		int centerX = this.worldX + this.left1.getWidth() / 2;
		return centerX;
	}

	public int getCenterY() {
		int centerY = this.worldY + this.up1.getHeight() / 2;
		return centerY;
	}

	public int getXdistance(Entity target) {
		int xDistance = Math.abs(getCenterX() - target.getCenterX());
		return xDistance;
	}

	public int getYdistance(Entity target) {
		int yDistance = Math.abs(getCenterY() - target.getCenterY());
		return yDistance;
	}

	public int getTileDistance(Entity target) {
		
		int tileDistance = (getXdistance(target) + getYdistance(target)) / 48;
		return tileDistance;
	}

	public int getGoalCol(Entity target) {
		
		int goalCol = (target.worldX + target.solidArea.x) / 48;
		return goalCol;
	}

	public int getGoalRow(Entity target) {
		
		int goalRow = (target.worldY + target.solidArea.y) / 48;
		return goalRow;
	}

	public void resetCounter() {
		this.spriteCounter = 0;
		this.actionLockCounter = 0;
		this.invincibleCounter = 0;
		this.shotAvailableCounter = 0;
		this.dyingCounter = 0;
		this.hpBarCounter = 0;
		this.knockBackCounter = 0;
		this.guardCounter = 0;
		this.offBalanceCounter = 0;
	}

	public void setLoot(Entity loot) {
	}

	public void setAction() {
	}

	public void move(String direction) {
	}

	public void damageReaction() {
	}

	public void speak() {
	}

	public void facePlayer() {
		String str;
		switch ((str = this.gp.player.direction).hashCode()) {
		case 3739:
			if (!str.equals("up"))
				break;
			this.direction = "down";
			break;
		case 3089570:
			if (!str.equals("down"))
				break;
			this.direction = "up";
			break;
		case 3317767:
			if (!str.equals("left"))
				break;
			this.direction = "right";
			break;
		case 108511772:
			if (!str.equals("right"))
				break;
			this.direction = "left";
			break;
		}
	}

	public void startDialogue(Entity entity, int setNum) {
		
		this.gp.gameState = 3;
		this.gp.ui.npc = entity;
		this.dialogueSet = setNum;
	}

	public void interact() {
	}

	public boolean use(Entity entity) {
		return false;
	}

	public void checkDrop() {
	}

	public void dropItem(Entity droppedItem) {
		for (int i = 0; i < (this.gp.obj[1]).length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] == null) {
				this.gp.obj[this.gp.currentMap][i] = droppedItem;
				(this.gp.obj[this.gp.currentMap][i]).worldX = this.worldX;
				(this.gp.obj[this.gp.currentMap][i]).worldY = this.worldY;
				break;
			}
		}
	}

	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		Particle p1 = new Particle(this.gp, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(this.gp, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(this.gp, target, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(this.gp, target, color, size, speed, maxLife, 2, 1);
		this.gp.particleList.add(p1);
		this.gp.particleList.add(p2);
		this.gp.particleList.add(p3);
		this.gp.particleList.add(p4);
	}

	public Color getParticleColor() {
		Color color = null;
		return color;
	}

	public int getParticleSize() {
		int size = 0;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}

	public void checkCollision() {
		this.collisionOn = false;
		this.gp.cChecker.checkTile(this);
		this.gp.cChecker.checkObject(this, false);
		this.gp.cChecker.checkEntity(this, this.gp.npc);
		this.gp.cChecker.checkEntity(this, this.gp.monster);
		this.gp.cChecker.checkEntity(this, this.gp.iTile);
		boolean contactPlayer = this.gp.cChecker.checkPlayer(this);
		if (this.type == 2 && contactPlayer)
			damagePlayer(this.attack);
	}

	public void update() {
		if (!this.sleep) {
			if (this.knockBack) {
				checkCollision();
				if (this.collisionOn) {
					this.knockBackCounter = 0;
					this.knockBack = false;
					this.speed = this.defaultSpeed;
				} else if (!this.collisionOn) {
					String str;
					switch ((str = this.knockBackDirection).hashCode()) {
					case 3739:
						if (!str.equals("up"))
							break;
						this.worldY -= this.speed;
						break;
					case 3089570:
						if (!str.equals("down"))
							break;
						this.worldY += this.speed;
						break;
					case 3317767:
						if (!str.equals("left"))
							break;
						this.worldX -= this.speed;
						break;
					case 108511772:
						if (!str.equals("right"))
							break;
						this.worldX += this.speed;
						break;
					}
				}
				this

						.knockBackCounter = this.knockBackCounter + 1;
				if (this.knockBackCounter == 10) {
					this.knockBackCounter = 0;
					this.knockBack = false;
					this.speed = this.defaultSpeed;
				}
			} else if (this.attacking) {
				attacking();
			} else {
				setAction();
				checkCollision();
				if (!this.collisionOn && !this.idle) {
					String str;
					switch ((str = this.direction).hashCode()) {
					case 3739:
						if (!str.equals("up"))
							break;
						this.worldY -= this.speed;
						break;
					case 3089570:
						if (!str.equals("down"))
							break;
						this.worldY += this.speed;
						break;
					case 3317767:
						if (!str.equals("left"))
							break;
						this.worldX -= this.speed;
						break;
					case 108511772:
						if (!str.equals("right"))
							break;
						this.worldX += this.speed;
						break;
					}
				}
				if (this.idle) {
					this.idleCounter++;
					if (this.idleCounter == this.idleTime) {
						this.idleCounter = 0;
						this.idle = false;
					}
				}
				this.spriteCounter++;
				if (this.spriteCounter > 24) {
					if (this.spriteNum == 1) {
						this.spriteNum = 2;
					} else if (this.spriteNum == 2) {
						this.spriteNum = 1;
					}
					this.spriteCounter = 0;
				}
			}
			if (this.invincible) {
				this.invincibleCounter++;
				if (this.invincibleCounter > 20) {
					this.invincible = false;
					this.invincibleCounter = 0;
				}
			}
			if (this.shotAvailableCounter < 30)
				this.shotAvailableCounter++;
			if (this.offBalance) {
				this.offBalanceCounter++;
				if (this.offBalanceCounter > 60) {
					this.offBalance = false;
					this.offBalanceCounter = 0;
				}
			}
		}
	}

	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		boolean targetInRange = false;
		int xDis = getXdistance(this.gp.player);
		int yDis = getYdistance(this.gp.player);
		String str;
		switch ((str = this.direction).hashCode()) {
		case 3739:
			if (!str.equals("up"))
				break;
			if (this.gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal)
				targetInRange = true;
			break;
		case 3089570:
			if (!str.equals("down"))
				break;
			if (this.gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal)
				targetInRange = true;
			break;
		case 3317767:
			if (!str.equals("left"))
				break;
			if (this.gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal)
				targetInRange = true;
			break;
		case 108511772:
			if (!str.equals("right"))
				break;
			if (this.gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal)
				targetInRange = true;
			break;
		}
		if (targetInRange) {
			int i = (new Random()).nextInt(rate);
			if (i == 0) {
				this.attacking = true;
				this.spriteNum = 1;
				this.spriteCounter = 0;
				this.shotAvailableCounter = 0;
			}
		}
	}

	public void checkShootOrNot(int rate, int shotInterval) {
		int i = (new Random()).nextInt(rate);
		if (i == 0 && !this.projectile.alive && this.shotAvailableCounter == shotInterval) {
			this.projectile.set(this.worldX, this.worldY, this.direction, true, this);
			for (int ii = 0; ii < (this.gp.projectile[1]).length; ii++) {
				if (this.gp.projectile[this.gp.currentMap][ii] == null) {
					this.gp.projectile[this.gp.currentMap][ii] = this.projectile;
					break;
				}
			}
			this.shotAvailableCounter = 0;
		}
	}

	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = (new Random()).nextInt(rate);
			if (i == 0)
				this.onPath = true;
		}
	}

	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = (new Random()).nextInt(rate);
			if (i == 0)
				this.onPath = false;
		}
	}

	public void getRandomDirection(int interval) {
		this.actionLockCounter++;
		if (this.actionLockCounter > interval) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if (i <= 20)
				this.direction = "up";
			if (i > 20 && i <= 40)
				this.direction = "down";
			if (i > 40 && i <= 60)
				this.direction = "left";
			if (i > 60 && i <= 80)
				this.direction = "right";
			if (i > 80 && i <= 100)
				this.idle = true;
			this.actionLockCounter = 0;
		}
	}

	public void moveTowardPlayer(int interval) {
		this.actionLockCounter++;
		if (this.actionLockCounter > interval) {
			if (getXdistance(this.gp.player) > getYdistance(this.gp.player)) {
				if (this.gp.player.getCenterX() < getCenterX()) {
					this.direction = "left";
				} else {
					this.direction = "right";
				}
			} else if (getXdistance(this.gp.player) < getYdistance(this.gp.player)) {
				if (this.gp.player.getCenterY() < getCenterY()) {
					this.direction = "up";
				} else {
					this.direction = "down";
				}
			}
			this.actionLockCounter = 0;
		}
	}

	public String getOppositeDirection(String direction) {
		String oppositeDirection = "";
		String str1;
		switch ((str1 = direction).hashCode()) {
		case 3739:
			if (!str1.equals("up"))
				break;
			oppositeDirection = "down";
			break;
		case 3089570:
			if (!str1.equals("down"))
				break;
			oppositeDirection = "up";
			break;
		case 3317767:
			if (!str1.equals("left"))
				break;
			oppositeDirection = "right";
			break;
		case 108511772:
			if (!str1.equals("right"))
				break;
			oppositeDirection = "left";
			break;
		}
		return oppositeDirection;
	}

	public void attacking() {
		this.spriteCounter++;
		if (this.spriteCounter <= this.motion1_duration)
			this.spriteNum = 1;
		if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
			this.spriteNum = 2;
			int currentWorldX = this.worldX;
			int currentWorldY = this.worldY;
			int solidAreaWidth = this.solidArea.width;
			int solidAreaHeight = this.solidArea.height;
			String str;
			switch ((str = this.direction).hashCode()) {
			case 3739:
				if (!str.equals("up"))
					break;
				this.worldY -= this.attackArea.height;
				break;
			case 3089570:
				if (!str.equals("down"))
					break;
				this.worldY += this.attackArea.height;
				break;
			case 3317767:
				if (!str.equals("left"))
					break;
				this.worldX -= this.attackArea.width;
				break;
			case 108511772:
				if (!str.equals("right"))
					break;
				this.worldX += this.attackArea.width;
				break;
			}
			this.solidArea.width = this.attackArea.width;
			this.solidArea.height = this.attackArea.height;
			if (this.type == 2) {
				if (this.gp.cChecker.checkPlayer(this))
					damagePlayer(this.attack);
			} else {
				int monsterIndex = this.gp.cChecker.checkEntity(this, this.gp.monster);
				this.gp.player.damageMonster(monsterIndex, this, this.attack, this.currentWeapon.knockBackPower);
				int iTileIndex = this.gp.cChecker.checkEntity(this, this.gp.iTile);
				this.gp.player.damageInteractiveTile(iTileIndex);
				int projectileIndex = this.gp.cChecker.checkEntity(this, this.gp.projectile);
				this.gp.player.damageProjectile(projectileIndex);
			}
			this.worldX = currentWorldX;
			this.worldY = currentWorldY;
			this.solidArea.width = solidAreaWidth;
			this.solidArea.height = solidAreaHeight;
		}
		if (this.spriteCounter > this.motion2_duration) {
			this.spriteNum = 1;
			this.spriteCounter = 0;
			this.attacking = false;
		}
	}

	public void damagePlayer(int attack) {
		if (!this.gp.player.invincible) {
			int damage = attack - this.gp.player.defense;
			boolean parry = false;
			String canGuardDirection = getOppositeDirection(this.direction);
			if (this.gp.player.guarding && this.gp.player.direction.equals(canGuardDirection)) {
				if (this.gp.player.guardCounter < 10) {
					parry = true;
					damage = 0;
					this.gp.playSE(16);
					setKnockBack(this, this.gp.player, 1);
					this.offBalance = true;
					this.spriteCounter = -60;
				} else {
					damage /= 3;
					this.gp.playSE(15);
				}
			} else {
				this.gp.playSE(6);
				if (damage < 1)
					damage = 1;
			}
			if (damage != 0)
				this.gp.player.transparent = true;
			if (!parry)
				setKnockBack(this.gp.player, this, this.knockBackPower);
			this.gp.player.life -= damage;
			this.gp.player.invincible = true;
		}
	}

	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}

	public boolean inCamera() {
		boolean inCamera = false;
		
		
		
		
		if (this.worldX + 48 * 5 > this.gp.player.worldX - this.gp.player.screenX
				&& this.worldX - 48 < this.gp.player.worldX + this.gp.player.screenX
				&& this.worldY + 48 * 5 > this.gp.player.worldY - this.gp.player.screenY
				&& this.worldY - 48 < this.gp.player.worldY + this.gp.player.screenY)
			inCamera = true;
		return inCamera;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		if (inCamera()) {
			int tempScreenX = getScreenX();
			int tempScreenY = getScreenY();
			String str;
			switch ((str = this.direction).hashCode()) {
			case 3739:
				if (!str.equals("up"))
					break;
				if (!this.attacking) {
					if (this.spriteNum == 1)
						image = this.up1;
					if (this.spriteNum == 2)
						image = this.up2;
				}
				if (this.attacking) {
					tempScreenY = getScreenY() - this.up1.getHeight();
					if (this.spriteNum == 1)
						image = this.attackUp1;
					if (this.spriteNum == 2)
						image = this.attackUp2;
				}
				break;
			case 3089570:
				if (!str.equals("down"))
					break;
				if (!this.attacking) {
					if (this.spriteNum == 1)
						image = this.down1;
					if (this.spriteNum == 2)
						image = this.down2;
				}
				if (this.attacking) {
					if (this.spriteNum == 1)
						image = this.attackDown1;
					if (this.spriteNum == 2)
						image = this.attackDown2;
				}
				break;
			case 3317767:
				if (!str.equals("left"))
					break;
				if (!this.attacking) {
					if (this.spriteNum == 1)
						image = this.left1;
					if (this.spriteNum == 2)
						image = this.left2;
				}
				if (this.attacking) {
					tempScreenX = getScreenX() - this.left1.getWidth();
					if (this.spriteNum == 1)
						image = this.attackLeft1;
					if (this.spriteNum == 2)
						image = this.attackLeft2;
				}
				break;
			case 108511772:
				if (!str.equals("right"))
					break;
				if (!this.attacking) {
					if (this.spriteNum == 1)
						image = this.right1;
					if (this.spriteNum == 2)
						image = this.right2;
				}
				if (this.attacking) {
					if (this.spriteNum == 1)
						image = this.attackRight1;
					if (this.spriteNum == 2)
						image = this.attackRight2;
				}
				break;
			}
			if (this.invincible) {
				this.hpBarOn = true;
				this.hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			if (this.dying)
				dyingAnimation(g2);
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			changeAlpha(g2, 1.0F);
		}
	}

	public void dyingAnimation(Graphics2D g2) {
		this.dyingCounter++;
		int i = 5;
		if (this.dyingCounter <= i)
			changeAlpha(g2, 0.0F);
		if (this.dyingCounter > i && this.dyingCounter <= i * 2)
			changeAlpha(g2, 1.0F);
		if (this.dyingCounter > i * 2 && this.dyingCounter <= i * 3)
			changeAlpha(g2, 0.0F);
		if (this.dyingCounter > i * 3 && this.dyingCounter <= i * 4)
			changeAlpha(g2, 1.0F);
		if (this.dyingCounter > i * 4 && this.dyingCounter <= i * 5)
			changeAlpha(g2, 0.0F);
		if (this.dyingCounter > i * 5 && this.dyingCounter <= i * 6)
			changeAlpha(g2, 1.0F);
		if (this.dyingCounter > i * 6 && this.dyingCounter <= i * 7)
			changeAlpha(g2, 0.0F);
		if (this.dyingCounter > i * 7 && this.dyingCounter <= i * 8)
			changeAlpha(g2, 1.0F);
		if (this.dyingCounter > i * 8)
			this.alive = false;
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(3, alphaValue));
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(String.valueOf(imagePath) + ".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (this.worldX + this.solidArea.x) / 48;
		
		int startRow = (this.worldY + this.solidArea.y) / 48;
		this.gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		if (this.gp.pFinder.search()) {
			
			int nextX = this.gp.pFinder.pathList.get(0).col * 48;
			
			int nextY = this.gp.pFinder.pathList.get(0).row * 48;
			int enLeftX = this.worldX + this.solidArea.x;
			int enRightX = this.worldX + this.solidArea.x + this.solidArea.width;
			int enTopY = this.worldY + this.solidArea.y;
			int enBottomY = this.worldY + this.solidArea.y + this.solidArea.height;
			
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + 48) {
				this.direction = "up";
			} else {
				
				if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + 48) {
					this.direction = "down";
				} else {
					
					if (enTopY >= nextY && enBottomY < nextY + 48) {
						if (enLeftX > nextX)
							this.direction = "left";
						if (enLeftX < nextX)
							this.direction = "right";
					} else if (enTopY > nextY && enLeftX > nextX) {
						this.direction = "up";
						checkCollision();
						if (this.collisionOn)
							this.direction = "left";
					} else if (enTopY > nextY && enLeftX < nextX) {
						this.direction = "up";
						checkCollision();
						if (this.collisionOn)
							this.direction = "right";
					} else if (enTopY < nextY && enLeftX > nextX) {
						this.direction = "down";
						checkCollision();
						if (this.collisionOn)
							this.direction = "left";
					} else if (enTopY < nextY && enLeftX < nextX) {
						this.direction = "down";
						checkCollision();
						if (this.collisionOn)
							this.direction = "right";
					}
				}
			}
		}
	}

	public int getDetected(Entity user, Entity[][] target, String targetName) {
		int index = 999;
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		String str;
		switch ((str = user.direction).hashCode()) {
		case 3739:
			if (!str.equals("up"))
				break;
			nextWorldY = user.getTopY() - this.gp.player.speed;
			break;
		case 3089570:
			if (!str.equals("down"))
				break;
			nextWorldY = user.getBottomY() + this.gp.player.speed;
			break;
		case 3317767:
			if (!str.equals("left"))
				break;
			nextWorldX = user.getLeftX() - this.gp.player.speed;
			break;
		case 108511772:
			if (!str.equals("right"))
				break;
			nextWorldX = user.getRightX() + this.gp.player.speed;
			break;
		}
		
		int col = nextWorldX / 48;
		
		int row = nextWorldY / 48;
		for (int i = 0; i < (target[1]).length; i++) {
			if (target[this.gp.currentMap][i] != null && target[this.gp.currentMap][i].getCol() == col
					&& target[this.gp.currentMap][i].getRow() == row
					&& (target[this.gp.currentMap][i]).name.equals(targetName)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
