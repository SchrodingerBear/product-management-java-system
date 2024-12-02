package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	int standCounter = 0;
	
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;

		int n = 960 / 2;
		this.screenX = n - 48 / 2;
		int n2 = 576 / 2;
		this.screenY = n2 - 48 / 2;
		
		// AREA OF PLAYER (COLLISION)
		this.solidArea = new Rectangle();
		this.solidArea.x = 8;
		this.solidArea.y = 16;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 32;
		this.solidArea.height = 32;

		this.setPosition();
		this.setDefaultValues();
	}

	public void setPosition() {
		this.worldX = 48 * 23;
		this.worldY = 48 * 21;
	}

	public void setDefaultValues() {
		// STATS
		this.speed = this.defaultSpeed = 3;
		this.direction = "idle";
		this.level = 1;
		this.life = this.maxLife = 6;
		this.mana = this.maxMana = 2;
		this.strength = 1;
		this.dexterity = 15;
		this.exp = 0;
		this.nextLevelExp = 5;
		this.coin = 99999;

		// SET ITEMS
		this.currentWeapon = new OBJ_Sword_Normal(this.gp);
		this.currentShield = new OBJ_Shield_Wood(this.gp);

		this.currentLight = null;
		this.projectile = new OBJ_Fireball(this.gp);
		this.attack = this.getAttack();
		this.defense = this.getDefense();
		this.getImage();
		this.getAttackImage();
		this.getGuardImage();
		this.setItems();
		this.setDialogue();
	}

	public void cheatStats() {
		this.level = 3;
		this.life = this.maxLife = 28;
		this.mana = this.maxMana = 8;
		this.strength = 20;
		this.dexterity = 4;
		this.coin = 500;
	}

	public void setDefaultPositions() {
		this.gp.currentMap = 0;

		this.worldX = 48 * 23;

		this.worldY = 48 * 21;
		this.direction = "down";
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You are level " + this.level + " now!\n" + "You feel stronger!";
	}

	public void restoreStatus() {
		this.life = this.maxLife;
		this.mana = this.maxMana;
		this.speed = this.defaultSpeed;
		this.invincible = false;
		this.transparent = false;
		this.attacking = false;
		this.guarding = false;
		this.knockBack = false;
		this.lightUpdated = true;
	}

	public void setItems() {
		this.inventory.clear();
		this.inventory.add(this.currentWeapon);
		this.inventory.add(this.currentShield);
	}

	public int getAttack() {
		this.attackArea = this.currentWeapon.attackArea;
		this.motion1_duration = this.currentWeapon.motion1_duration;
		this.motion2_duration = this.currentWeapon.motion2_duration;
		this.attack = this.strength + this.currentWeapon.attackValue;
		return this.attack;
	}

	public int getDefense() {
		this.defense = this.dexterity + this.currentShield.defenseValue;
		return this.defense;
	}

	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (this.inventory.get(i) != this.currentWeapon)
				continue;
			currentWeaponSlot = i;
		}
		return currentWeaponSlot;
	}

	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (this.inventory.get(i) != this.currentShield)
				continue;
			currentShieldSlot = i;
		}
		return currentShieldSlot;
	}

	public void getImage() {
		gp.character = "human";
		if (gp.character == "human") {
			this.idleimage = this.setup("/player/human_idle", 48, 48);
			this.up1 = this.setup("/player/boy_up_1", 48, 48);
			this.up2 = this.setup("/player/boy_up_2", 48, 48);
			this.down1 = this.setup("/player/boy_down_1", 48, 48);
			this.down2 = this.setup("/player/boy_down_2", 48, 48);
			this.left1 = this.setup("/player/boy_left_1", 48, 48);
			this.left2 = this.setup("/player/boy_left_2", 48, 48);
			this.right1 = this.setup("/player/boy_right_1", 48, 48);
			this.right2 = this.setup("/player/boy_right_2", 48, 48);
		}

		if (gp.character == "cat") {
			this.idleimage = this.setup("/player/catidle", 48, 48);
			this.up1 = this.setup("/player/catup1", 48, 48);
			this.up2 = this.setup("/player/catup2", 48, 48);
			this.down1 = this.setup("/player/catdown1", 48, 48);
			this.down2 = this.setup("/player/catdown2", 48, 48);
			this.left1 = this.setup("/player/catleft1", 48, 48);
			this.left2 = this.setup("/player/catleft2", 48, 48);
			this.right1 = this.setup("/player/catright1", 48, 48);
			this.right2 = this.setup("/player/catright2", 48, 48);
		}
	}

	public void getSleepingImage(BufferedImage image) {
		this.up1 = image;
		this.up2 = image;
		this.down1 = image;
		this.down2 = image;
		this.left1 = image;
		this.left2 = image;
		this.right1 = image;
		this.right2 = image;
	}

	public void getAttackImage() {
		if (this.currentWeapon.type == 3) {
			if (this.currentWeapon.name == "Normal Sword") {
				this.attackUp1 = this.setup("/player/boy_attack_up_1", 48, 48 * 2);
				this.attackUp2 = this.setup("/player/boy_attack_up_2", 48, 48 * 2);
				this.attackDown1 = this.setup("/player/boy_attack_down_1", 48, 48 * 2);
				this.attackDown2 = this.setup("/player/boy_attack_down_2", 48, 48 * 2);
				this.attackLeft1 = this.setup("/player/boy_attack_left_1", 48 * 2, 48);
				this.attackLeft2 = this.setup("/player/boy_attack_left_2", 48 * 2, 48);
				this.attackRight1 = this.setup("/player/boy_attack_right_1", 48 * 2, 48);
				this.attackRight2 = this.setup("/player/boy_attack_right_2", 48 * 2, 48);
			}

			if (this.currentWeapon.name == "Blue Sword") {
				this.attackUp1 = this.setup("/player/boy_blueattack_up_1", 48, 48 * 2);
				this.attackUp2 = this.setup("/player/boy_blueattack_up_2", 48, 48 * 2);
				this.attackDown1 = this.setup("/player/boy_blueattack_down_1", 48, 48 * 2);
				this.attackDown2 = this.setup("/player/boy_blueattack_down_2", 48, 48 * 2);
				this.attackLeft1 = this.setup("/player/boy_blueattack_left_1", 48 * 2, 48);
				this.attackLeft2 = this.setup("/player/boy_blueattack_left_2", 48 * 2, 48);
				this.attackRight1 = this.setup("/player/boy_blueattack_right_1", 48 * 2, 48);
				this.attackRight2 = this.setup("/player/boy_blueattack_right_2", 48 * 2, 48);
			}
		}
		if (this.currentWeapon.type == 4) {
			this.attackUp1 = this.setup("/player/boy_axe_up_1", 48, 48 * 2);
			this.attackUp2 = this.setup("/player/boy_axe_up_2", 48, 48 * 2);
			this.attackDown1 = this.setup("/player/boy_axe_down_1", 48, 48 * 2);
			this.attackDown2 = this.setup("/player/boy_axe_down_2", 48, 48 * 2);
			this.attackLeft1 = this.setup("/player/boy_axe_left_1", 48 * 2, 48);
			this.attackLeft2 = this.setup("/player/boy_axe_left_2", 48 * 2, 48);
			this.attackRight1 = this.setup("/player/boy_axe_right_1", 48 * 2, 48);
			this.attackRight2 = this.setup("/player/boy_axe_right_2", 48 * 2, 48);
		}
		if (this.currentWeapon.type == 10) {
			this.attackUp1 = this.setup("/player/boy_pick_up_1", 48, 48 * 2);
			this.attackUp2 = this.setup("/player/boy_pick_up_2", 48, 48 * 2);
			this.attackDown1 = this.setup("/player/boy_pick_down_1", 48, 48 * 2);
			this.attackDown2 = this.setup("/player/boy_pick_down_2", 48, 48 * 2);
			this.attackLeft1 = this.setup("/player/boy_pick_left_1", 48 * 2, 48);
			this.attackLeft2 = this.setup("/player/boy_pick_left_2", 48 * 2, 48);
			this.attackRight1 = this.setup("/player/boy_pick_right_1", 48 * 2, 48);
			this.attackRight2 = this.setup("/player/boy_pick_right_2", 48 * 2, 48);
		}
	}

	public void getGuardImage() {
		if (this.currentShield.name == "Wood Shield") {
			this.guardUp = this.setup("/player/boy_guard_up", 48, 48);
			this.guardDown = this.setup("/player/boy_guard_down", 48, 48);
			this.guardLeft = this.setup("/player/boy_guard_left", 48, 48);
			this.guardRight = this.setup("/player/boy_guard_right", 48, 48);
		}
		if (this.currentShield.name == "Blue Shield") {
			this.guardUp = this.setup("/player/boy_blueguard_up", 48, 48);
			this.guardDown = this.setup("/player/boy_blueguard_down", 48, 48);
			this.guardLeft = this.setup("/player/boy_blueguard_left", 48, 48);
			this.guardRight = this.setup("/player/boy_blueguard_right", 48, 48);
		}
	}

	public void update() {
		block34: {
			block37: {
				block38: {
					block36: {
						block35: {
							block33: {
								if (!this.knockBack)
									break block33;
								this.inKnockBack();
								break block34;
							}
							if (!this.attacking)
								break block35;
							this.attacking();
							break block34;
						}
						if (!this.keyH.spacePressed)
							break block36;
						this.guarding = true;
						++this.guardCounter;
						break block34;
					}
					if (!this.keyH.upPressed && !this.keyH.downPressed && !this.keyH.leftPressed
							&& !this.keyH.rightPressed && !this.keyH.enterPressed)
						break block37;
					if (this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed
							|| this.keyH.rightPressed) {
						this.direction = (String) this.gp.keyH.inputs.get(0);
					}
					this.collisionOn = false;
					this.gp.cChecker.checkTile((Entity) this);
					int objIndex = this.gp.cChecker.checkObject((Entity) this, true);
					this.pickUpObject(objIndex);
					int npcIndex = this.gp.cChecker.checkEntity((Entity) this, this.gp.npc);
					this.interactNPC(npcIndex);
					int monsterIndex = this.gp.cChecker.checkEntity((Entity) this, this.gp.monster);
					this.contactMonster(monsterIndex);
					this.gp.cChecker.checkEntity((Entity) this, (Entity[][]) this.gp.iTile);
					this.gp.eHandler.checkEvent();
					
					if (this.collisionOn || this.keyH.enterPressed)
						break block38;

					// SPEED ADJUSTMENT BASED ON DIRECTION
					switch (this.direction) {
					case "up": {
						this.worldY -= this.speed;
						break;
					}
					case "down": {
						this.worldY += this.speed;
						break;
					}
					case "left": {
						this.worldX -= this.speed;
						break;
					}
					case "right": {
						this.worldX += this.speed;
					}
					}
				}
				
				if (this.keyH.enterPressed && !this.attackCanceled) {
					this.gp.playSE(7);
					this.attacking = true;
					this.spriteCounter = 0;
				}
				
				this.attackCanceled = false;
				this.gp.keyH.enterPressed = false;
				this.guarding = false;
				this.guardCounter = 0;
				++this.spriteCounter;
				
				if (this.spriteCounter > 15) {
					if (this.spriteNum == 1) {
						this.spriteNum = 2;
					} else if (this.spriteNum == 2) {
						this.spriteNum = 1;
					} 
					this.spriteCounter = 0;
				}
				break block34;
			}
			
			++this.standCounter;
			if (this.standCounter == 20) {
				this.spriteNum = 1;
				this.standCounter = 0;
			}
			
			this.guarding = false;
			this.guardCounter = 0;
			
		}
		if (this.gp.keyH.shotKeyPressed && !this.projectile.alive && this.shotAvailableCounter == 30) {
			if (this.projectile.haveResource((Entity) this)) {
				this.projectile.set(this.worldX, this.worldY, this.direction, true, (Entity) this);
				this.projectile.subtractResource((Entity) this);
				for (int i = 0; i < this.gp.projectile[1].length; ++i) {
					if (this.gp.projectile[this.gp.currentMap][i] != null)
						continue;
					this.gp.projectile[this.gp.currentMap][i] = this.projectile;
					break;
				}
				this.shotAvailableCounter = 0;
				this.gp.playSE(10);
			} else {
				this.gp.ui.addMessage("No mana!");
				this.gp.keyH.shotKeyPressed = false;
			}
		}
		if (this.invincible) {
			++this.invincibleCounter;
			if (this.invincibleCounter > 60) {
				this.invincible = false;
				this.transparent = false;
				this.invincibleCounter = 0;
			}
		}
		if (this.shotAvailableCounter < 30) {
			++this.shotAvailableCounter;
		}
		if (this.life > this.maxLife) {
			this.life = this.maxLife;
		}
		if (this.mana > this.maxMana) {
			this.mana = this.maxMana;
		}
		if (!this.keyH.godModeOn && this.life <= 0) {

			this.gp.gameState = 6;
			this.gp.ui.commandNum = -1;
			this.gp.stopMusic();
			this.gp.playSE(12);
		}
	}

	public void inKnockBack() {
		block18: {
			block17: {
				this.collisionOn = false;
				this.gp.cChecker.checkTile((Entity) this);
				this.gp.cChecker.checkObject((Entity) this, true);
				this.gp.cChecker.checkEntity((Entity) this, this.gp.npc);
				this.gp.cChecker.checkEntity((Entity) this, this.gp.monster);
				this.gp.cChecker.checkEntity((Entity) this, (Entity[][]) this.gp.iTile);
				if (!this.collisionOn)
					break block17;
				this.knockBackCounter = 0;
				this.knockBack = false;
				this.speed = this.defaultSpeed;
				break block18;
			}
			if (this.collisionOn)
				break block18;
			switch (this.knockBackDirection) {
			case "up": {
				this.worldY -= this.speed;
				break;
			}
			case "down": {
				this.worldY += this.speed;
				break;
			}
			case "left": {
				this.worldX -= this.speed;
				break;
			}
			case "right": {
				this.worldX += this.speed;
			}
			}
		}
		++this.knockBackCounter;
		if (this.knockBackCounter == 10) {
			this.knockBackCounter = 0;
			this.knockBack = false;
			this.speed = this.defaultSpeed;
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			if (this.gp.obj[this.gp.currentMap][i].type == 7) {
				this.gp.obj[this.gp.currentMap][i].use((Entity) this);
				this.gp.obj[this.gp.currentMap][i] = null;
			} else if (this.gp.obj[this.gp.currentMap][i].type == 8) {
				if (this.keyH.enterPressed) {
					this.attackCanceled = true;
					this.gp.obj[this.gp.currentMap][i].interact();
				}
			} else {
				String text;
				if (this.canObtainItem(this.gp.obj[this.gp.currentMap][i])) {
					this.gp.playSE(1);
					text = "Got a " + this.gp.obj[this.gp.currentMap][i].name + "!";
				} else {
					text = "You cannot carry any more!";
				}
				this.gp.ui.addMessage(text);
				this.gp.obj[this.gp.currentMap][i] = null;
			}
		}
	}

	public void interactNPC(int i) {
		if (i != 999) {
			if (this.gp.keyH.enterPressed) {
				this.attackCanceled = true;
				this.gp.npc[this.gp.currentMap][i].speak();
			}
			this.gp.npc[this.gp.currentMap][i].move(this.direction);
		}
	}

	public void contactMonster(int i) {
		if (i != 999 && !this.invincible && !this.gp.monster[this.gp.currentMap][i].dying) {
			this.gp.playSE(6);
			int damage = this.gp.monster[this.gp.currentMap][i].attack - this.defense;
			if (damage < 1) {
				damage = 1;
			}
			this.life -= damage;
			this.invincible = true;
			this.transparent = true;
		}
	}

	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		if (i != 999 && !this.gp.monster[this.gp.currentMap][i].invincible) {
			int damage;
			this.gp.playSE(5);
			if (knockBackPower > 0) {
				this.setKnockBack(this.gp.monster[this.gp.currentMap][i], attacker, knockBackPower);
			}
			if (this.gp.monster[this.gp.currentMap][i].offBalance) {
				attack *= 5;
			}
			if ((damage = attack - this.gp.monster[this.gp.currentMap][i].defense) < 0) {
				damage = 0;
			}
			this.gp.monster[this.gp.currentMap][i].life -= damage;
			this.gp.ui.addMessage(String.valueOf(damage) + " damage!");
			this.gp.monster[this.gp.currentMap][i].invincible = true;
			this.gp.monster[this.gp.currentMap][i].damageReaction();
			if (this.gp.monster[this.gp.currentMap][i].life <= 0) {
				this.gp.monster[this.gp.currentMap][i].dying = true;
				this.gp.ui.addMessage("Killed the " + this.gp.monster[this.gp.currentMap][i].name + "!");
				this.gp.ui.addMessage("Exp + " + this.gp.monster[this.gp.currentMap][i].exp);
				this.exp += this.gp.monster[this.gp.currentMap][i].exp;
				this.checkLevelUp();
			}
		}
	}

	public void damageInteractiveTile(int i) {
		if (i != 999 && this.gp.iTile[this.gp.currentMap][i].destructible
				&& this.gp.iTile[this.gp.currentMap][i].isCorrectItem((Entity) this)
				&& !this.gp.iTile[this.gp.currentMap][i].invincible) {
			this.gp.iTile[this.gp.currentMap][i].playSE();
			--this.gp.iTile[this.gp.currentMap][i].life;
			this.gp.iTile[this.gp.currentMap][i].invincible = true;
			this.generateParticle((Entity) this.gp.iTile[this.gp.currentMap][i],
					(Entity) this.gp.iTile[this.gp.currentMap][i]);
			if (this.gp.iTile[this.gp.currentMap][i].life == 0) {
				this.gp.iTile[this.gp.currentMap][i].checkDrop();
				this.gp.iTile[this.gp.currentMap][i] = this.gp.iTile[this.gp.currentMap][i].getDestroyedForm();
			}
		}
	}

	public void damageProjectile(int i) {
		if (i != 999) {
			Entity projectile = this.gp.projectile[this.gp.currentMap][i];
			projectile.alive = false;
			this.generateParticle(projectile, projectile);
		}
	}

	public void checkLevelUp() {
		if (this.exp >= this.nextLevelExp) {
			this.gp.playSE(8);

			this.gp.gameState = 3;
			this.gp.uniTimerOn = true;
			++this.level;
			this.nextLevelExp *= 2;
			this.maxLife += 2;
			this.life += 2;
			++this.strength;
			++this.dexterity;
			this.attack = this.getAttack();
			this.defense = this.getDefense();
			if (this.level % 2 == 0) {
				++this.maxMana;
				++this.mana;
			}
			this.setDialogue();
			this.startDialogue(this, 0);
		}
	}

	public void selectItem() {
		int itemIndex = this.gp.ui.getItemIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
		if (itemIndex < this.inventory.size()) {
			Entity selectedItem = (Entity) this.inventory.get(itemIndex);
			if (selectedItem.type == 3 || selectedItem.type == 4 || selectedItem.type == 10) {
				this.currentWeapon = selectedItem;
				this.attack = this.getAttack();
				this.getAttackImage();
			}
			if (selectedItem.type == 5) {
				this.currentShield = selectedItem;
				this.defense = this.getDefense();
				this.getGuardImage();
			}
			if (selectedItem.type == 9) {
				this.currentLight = this.currentLight == selectedItem ? null : selectedItem;
				this.lightUpdated = true;
			}
			if (selectedItem.type == 6 && selectedItem.use((Entity) this)) {
				if (selectedItem.amount > 1) {
					--selectedItem.amount;
				} else {
					this.inventory.remove(itemIndex);
				}
			}
		}
	}

	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (!((Entity) this.inventory.get((int) i)).name.equals(itemName))
				continue;
			itemIndex = i;
			break;
		}
		return itemIndex;
	}

	public boolean canObtainItem(Entity item) {
		boolean canObtain = false;
		Entity newItem = this.gp.eGenerator.getObject(item.name);
		if (newItem.stackable) {
			int index = this.searchItemInInventory(newItem.name);
			if (index != 999) {
				++((Entity) this.inventory.get((int) index)).amount;
				canObtain = true;
			} else if (this.inventory.size() != 20) {
				this.inventory.add(newItem);
				canObtain = true;
			}
		} else if (this.inventory.size() != 20) {
			this.inventory.add(newItem);
			canObtain = true;
		}
		return canObtain;
	}

	// DRAW PLAYER ANIMATION
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = this.screenX;
		int tempScreenY = this.screenY;

		switch (this.direction) {
		case "idle":
			image = this.idleimage;
			break;

		case "up": 
			if (!this.attacking) {
				if (this.spriteNum == 1) {
					image = this.up1;
				}
				if (this.spriteNum == 2) {
					image = this.up2;
				}
			}
			if (this.attacking) {

				tempScreenY = this.screenY - 48;
				if (this.spriteNum == 1) {
					image = this.attackUp1;
				}
				if (this.spriteNum == 2) {
					image = this.attackUp2;
				}
			}
			if (!this.guarding)
				break;
			image = this.guardUp;
			break;
			
		case "down": 
			if (!this.attacking) {
				if (this.spriteNum == 1) {
					image = this.down1;
				}
				if (this.spriteNum == 2) {
					image = this.down2;
				}
			}
			if (this.attacking) {
				if (this.spriteNum == 1) {
					image = this.attackDown1;
				}
				if (this.spriteNum == 2) {
					image = this.attackDown2;
				}
			}
			if (!this.guarding)
				break;
			image = this.guardDown;
			break;

		case "left": 
			if (!this.attacking) {
				if (this.spriteNum == 1) {
					image = this.left1;
				}
				if (this.spriteNum == 2) {
					image = this.left2;
				}
			}
			if (this.attacking) {

				tempScreenX = this.screenX - 48;
				if (this.spriteNum == 1) {
					image = this.attackLeft1;
				}
				if (this.spriteNum == 2) {
					image = this.attackLeft2;
				}
			}
			if (!this.guarding)
				break;
			image = this.guardLeft;
			break;

		case "right": 
			if (!this.attacking) {
				if (this.spriteNum == 1) {
					image = this.right1;
				}
				if (this.spriteNum == 2) {
					image = this.right2;
				}
			}
			if (this.attacking) {
				if (this.spriteNum == 1) {
					image = this.attackRight1;
				}
				if (this.spriteNum == 2) {
					image = this.attackRight2;
				}
			}
			if (!this.guarding)
				break;
			image = this.guardRight;
		}
		
		if (this.transparent) {
			g2.setComposite(AlphaComposite.getInstance(3, 0.4f));
		}
		if (this.drawing) {
			g2.drawImage(image, tempScreenX, tempScreenY, null);
		}
		g2.setComposite(AlphaComposite.getInstance(3, 1.0f));
	}
}
