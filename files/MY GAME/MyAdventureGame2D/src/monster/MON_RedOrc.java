package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Coin_Gold;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;

public class MON_RedOrc extends Entity {
	GamePanel gp;

	public MON_RedOrc(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 2;
		this.name = "Red Orc";
		this.defaultSpeed = 1;
		this.speed = this.defaultSpeed;
		this.maxLife = 35;
		this.life = this.maxLife;
		this.attack = 15;
		this.defense = 6;
		this.exp = 25;
		this.knockBackPower = 8;
		this.solidArea.x = 4;
		this.solidArea.y = 4;
		this.solidArea.width = 40;
		this.solidArea.height = 44;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.attackArea.width = 48;
		this.attackArea.height = 48;
		this.motion1_duration = 20;
		this.motion2_duration = 50;
		getImage();
		getAttackImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/monster/redorc_up_1", 48, 48);
		
		
		this.up2 = setup("/monster/redorc_up_2", 48, 48);
		
		
		this.down1 = setup("/monster/redorc_down_1", 48, 48);
		
		
		this.down2 = setup("/monster/redorc_down_2", 48, 48);
		
		
		this.left1 = setup("/monster/redorc_left_1", 48, 48);
		
		
		this.left2 = setup("/monster/redorc_left_2", 48, 48);
		
		
		this.right1 = setup("/monster/redorc_right_1", 48, 48);
		
		
		this.right2 = setup("/monster/redorc_right_2", 48, 48);
	}

	public void getAttackImage() {
		
		
		this.attackUp1 = setup("/monster/redorc_attack_up_1", 48, 48 * 2);
		
		
		this.attackUp2 = setup("/monster/redorc_attack_up_2", 48, 48 * 2);
		
		
		this.attackDown1 = setup("/monster/redorc_attack_down_1", 48, 48 * 2);
		
		
		this.attackDown2 = setup("/monster/redorc_attack_down_2", 48, 48 * 2);
		
		
		this.attackLeft1 = setup("/monster/redorc_attack_left_1", 48 * 2, 48);
		
		
		this.attackLeft2 = setup("/monster/redorc_attack_left_2", 48 * 2, 48);
		
		
		this.attackRight1 = setup("/monster/redorc_attack_right_1", 48 * 2, 48);
		
		
		this.attackRight2 = setup("/monster/redorc_attack_right_2", 48 * 2, 48);
	}

	@Override
	public void setAction() {
		if (this.onPath) {
			checkStopChasingOrNot(this.gp.player, 15, 100);
			searchPath(getGoalCol(this.gp.player), getGoalRow(this.gp.player));
		} else {
			checkStartChasingOrNot(this.gp.player, 5, 100);
			getRandomDirection(100);
		}
		if (!this.attacking) {
			
			
			checkAttackOrNot(30, 48 * 4, 48);
		}
	}

	@Override
	public void damageReaction() {
		this.actionLockCounter = 0;
		this.onPath = true;
	}

	@Override
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i < 50)
			dropItem(new OBJ_Coin_Gold(this.gp));
		if (i >= 50 && i < 75)
			dropItem(new OBJ_Potion_Red(this.gp));
		if (i >= 75 && i < 80)
			dropItem(new OBJ_Potion_Blue(this.gp));
		if (i >= 80 && i < 90)
			dropItem(new OBJ_Axe(this.gp));
		if (i >= 90)
			dropItem(new OBJ_Shield_Blue(this.gp));
	}
}
