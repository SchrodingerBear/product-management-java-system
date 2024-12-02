package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Sword_Blue;

public class MON_SkeletonLord extends Entity {
	GamePanel gp;

	public static final String monName = "Skeleton Lord";

	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 2;
		this.boss = true;
		this.name = "Skeleton Lord";
		this.defaultSpeed = 1;
		this.speed = this.defaultSpeed;
		this.maxLife = 75;
		this.life = this.maxLife;
		this.attack = 15;
		this.defense = 7;
		this.exp = 50;
		this.knockBackPower = 7;
		this.sleep = true;
		gp.getClass();
		int size = 48 * 5;
		this.solidArea.x = 48;
		this.solidArea.y = 48;
		this.solidArea.width = size - 96;
		this.solidArea.height = size - 48;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.attackArea.width = 170;
		this.attackArea.height = 170;
		this.motion1_duration = 25;
		this.motion2_duration = 40;
		getImage();
		getAttackImage();
		setDialogue();
	}

	public void getImage() {
		int i = 5;
		if (!this.inRage) {
			
			
			this.up1 = setup("/monster/skeletonlord_up_1", 48 * i, 48 * i);
			
			
			this.up2 = setup("/monster/skeletonlord_up_2", 48 * i, 48 * i);
			
			
			this.down1 = setup("/monster/skeletonlord_down_1", 48 * i, 48 * i);
			
			
			this.down2 = setup("/monster/skeletonlord_down_2", 48 * i, 48 * i);
			
			
			this.left1 = setup("/monster/skeletonlord_left_1", 48 * i, 48 * i);
			
			
			this.left2 = setup("/monster/skeletonlord_left_2", 48 * i, 48 * i);
			
			
			this.right1 = setup("/monster/skeletonlord_right_1", 48 * i, 48 * i);
			
			
			this.right2 = setup("/monster/skeletonlord_right_2", 48 * i, 48 * i);
		}
		if (this.inRage) {
			
			
			this.up1 = setup("/monster/skeletonlord_phase2_up_1", 48 * i, 48 * i);
			
			
			this.up2 = setup("/monster/skeletonlord_phase2_up_2", 48 * i, 48 * i);
			
			
			this.down1 = setup("/monster/skeletonlord_phase2_down_1", 48 * i, 48 * i);
			
			
			this.down2 = setup("/monster/skeletonlord_phase2_down_2", 48 * i, 48 * i);
			
			
			this.left1 = setup("/monster/skeletonlord_phase2_left_1", 48 * i, 48 * i);
			
			
			this.left2 = setup("/monster/skeletonlord_phase2_left_2", 48 * i, 48 * i);
			
			
			this.right1 = setup("/monster/skeletonlord_phase2_right_1", 48 * i, 48 * i);
			
			
			this.right2 = setup("/monster/skeletonlord_phase2_right_2", 48 * i, 48 * i);
		}
	}

	public void getAttackImage() {
		int i = 5;
		if (!this.inRage) {
			
			
			this.attackUp1 = setup("/monster/skeletonlord_attack_up_1", 48 * i, 48 * i * 2);
			
			
			this.attackUp2 = setup("/monster/skeletonlord_attack_up_2", 48 * i, 48 * i * 2);
			
			
			this.attackDown1 = setup("/monster/skeletonlord_attack_down_1", 48 * i, 48 * i * 2);
			
			
			this.attackDown2 = setup("/monster/skeletonlord_attack_down_2", 48 * i, 48 * i * 2);
			
			
			this.attackLeft1 = setup("/monster/skeletonlord_attack_left_1", 48 * i * 2, 48 * i);
			
			
			this.attackLeft2 = setup("/monster/skeletonlord_attack_left_2", 48 * i * 2, 48 * i);
			
			
			this.attackRight1 = setup("/monster/skeletonlord_attack_right_1", 48 * i * 2, 48 * i);
			
			
			this.attackRight2 = setup("/monster/skeletonlord_attack_right_2", 48 * i * 2, 48 * i);
		}
		if (this.inRage) {
			
			
			this.attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", 48 * i, 48 * i * 2);
			
			
			this.attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", 48 * i, 48 * i * 2);
			
			
			this.attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", 48 * i, 48 * i * 2);
			
			
			this.attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", 48 * i, 48 * i * 2);
			
			
			this.attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", 48 * i * 2, 48 * i);
			
			
			this.attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", 48 * i * 2, 48 * i);
			
			
			this.attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", 48 * i * 2, 48 * i);
			
			
			this.attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", 48 * i * 2, 48 * i);
		}
	}

	public void setDialogue() {
		this.dialogues[0][0] = "No one can steal my treasure!";
		this.dialogues[0][1] = "You will die here!";
		this.dialogues[0][2] = "WELCOME TO YOUR DOOM!";
	}

	@Override
	public void setAction() {
		if (!this.inRage && this.life < this.maxLife / 2) {
			this.inRage = true;
			getImage();
			getAttackImage();
			this.defaultSpeed += 2;
			this.motion1_duration = 20;
			this.motion2_duration = 25;
			this.speed = this.defaultSpeed;
		}
		if (getTileDistance(this.gp.player) < 10) {
			moveTowardPlayer(60);
		} else {
			getRandomDirection(120);
		}
		if (!this.attacking) {
			
			
			checkAttackOrNot(60, 48 * 7, 48 * 5);
		}
	}

	@Override
	public void damageReaction() {
		this.actionLockCounter = 0;
	}

	@Override
	public void checkDrop() {
		this.gp.bossBattleOn = false;
		Progress.skeletonLordDefeated = true;
		this.gp.stopMusic();
		this.gp.playMusic(19);
		int i;
		for (i = 0; i < (this.gp.obj[1]).length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] != null
					&& (this.gp.obj[this.gp.currentMap][i]).name.equals("Iron Door")) {
				this.gp.playSE(21);
				this.gp.obj[this.gp.currentMap][i] = null;
			}
		}
		i = (new Random()).nextInt(100) + 1;
		if (i < 50)
			dropItem(new OBJ_Coin_Gold(this.gp));
		if (i >= 50 && i < 75)
			dropItem(new OBJ_Heart(this.gp));
		if (i >= 75 && i < 90)
			dropItem(new OBJ_ManaCrystal(this.gp));
		if (i >= 90)
			dropItem(new OBJ_Sword_Blue(this.gp));
	}
}
