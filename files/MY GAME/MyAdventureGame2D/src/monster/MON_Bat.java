package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_ReturnOrb;

public class MON_Bat extends Entity {
	GamePanel gp;

	public MON_Bat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 2;
		this.name = "Bat";
		this.defaultSpeed = 4;
		this.speed = this.defaultSpeed;
		this.maxLife = 10;
		this.life = this.maxLife;
		this.attack = 9;
		this.defense = 3;
		this.exp = 7;
		this.idleTime = 60;
		this.solidArea.x = 3;
		this.solidArea.y = 15;
		this.solidArea.width = 42;
		this.solidArea.height = 21;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		getImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/monster/bat_down_1", 48, 48);
		
		
		this.up2 = setup("/monster/bat_down_2", 48, 48);
		
		
		this.down1 = setup("/monster/bat_down_1", 48, 48);
		
		
		this.down2 = setup("/monster/bat_down_2", 48, 48);
		
		
		this.left1 = setup("/monster/bat_down_1", 48, 48);
		
		
		this.left2 = setup("/monster/bat_down_2", 48, 48);
		
		
		this.right1 = setup("/monster/bat_down_1", 48, 48);
		
		
		this.right2 = setup("/monster/bat_down_2", 48, 48);
	}

	@Override
	public void setAction() {
		if (!this.onPath)
			getRandomDirection(15);
	}

	@Override
	public void damageReaction() {
		this.actionLockCounter = 0;
	}

	@Override
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i < 45)
			dropItem(new OBJ_Coin_Silver(this.gp));
		if (i >= 45 && i < 55)
			dropItem(new OBJ_Coin_Gold(this.gp));
		if (i >= 55 && i < 65)
			dropItem(new OBJ_ReturnOrb(this.gp));
		if (i >= 65 && i < 75)
			dropItem(new OBJ_Heart(this.gp));
		if (i >= 75 && i < 85)
			dropItem(new OBJ_Potion_Red(this.gp));
		if (i >= 85 && i < 85)
			dropItem(new OBJ_ManaCrystal(this.gp));
		if (i >= 85 && i < 100)
			dropItem(new OBJ_Potion_Blue(this.gp));
	}
}
