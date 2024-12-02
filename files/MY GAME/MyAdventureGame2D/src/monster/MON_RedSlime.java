package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;

public class MON_RedSlime extends Entity {
	GamePanel gp;

	public MON_RedSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 2;
		this.name = "Red Slime";
		this.defaultSpeed = 2;
		this.speed = this.defaultSpeed;
		this.maxLife = 12;
		this.life = this.maxLife;
		this.attack = 7;
		this.defense = 4;
		this.exp = 5;
		this.projectile = new OBJ_Rock(gp);
		this.solidArea.x = 3;
		this.solidArea.y = 18;
		this.solidArea.width = 42;
		this.solidArea.height = 30;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		getImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/monster/redslime_down_1", 48, 48);
		
		
		this.up2 = setup("/monster/redslime_down_2", 48, 48);
		
		
		this.down1 = setup("/monster/redslime_down_1", 48, 48);
		
		
		this.down2 = setup("/monster/redslime_down_2", 48, 48);
		
		
		this.left1 = setup("/monster/redslime_down_1", 48, 48);
		
		
		this.left2 = setup("/monster/redslime_down_2", 48, 48);
		
		
		this.right1 = setup("/monster/redslime_down_1", 48, 48);
		
		
		this.right2 = setup("/monster/redslime_down_2", 48, 48);
	}

	@Override
	public void setAction() {
		if (this.onPath) {
			checkStopChasingOrNot(this.gp.player, 15, 100);
			searchPath(getGoalCol(this.gp.player), getGoalRow(this.gp.player));
			checkShootOrNot(120, 30);
		} else {
			checkStartChasingOrNot(this.gp.player, 5, 100);
			checkShootOrNot(120, 30);
			getRandomDirection(80);
		}
	}

	@Override
	public void damageReaction() {
		getRandomDirection(0);
		this.actionLockCounter = 0;
	}

	@Override
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i < 25)
			dropItem(new OBJ_Coin_Bronze(this.gp));
		if (i >= 25 && i < 60)
			dropItem(new OBJ_Coin_Silver(this.gp));
		if (i >= 60 && i < 80)
			dropItem(new OBJ_Heart(this.gp));
		if (i >= 80 && i < 95)
			dropItem(new OBJ_Potion_Red(this.gp));
		if (i >= 95 && i < 100)
			dropItem(new OBJ_Shield_Wood(this.gp));
	}
}
