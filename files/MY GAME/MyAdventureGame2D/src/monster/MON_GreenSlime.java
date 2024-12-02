package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_GreenSlime extends Entity {
	GamePanel gp;

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 2;
		this.name = "Green Slime";
		this.defaultSpeed = 1;
		this.speed = this.defaultSpeed;
		this.maxLife = 5;
		this.life = this.maxLife;
		this.attack = 5;
		this.defense = 2;
		this.exp = 2;
		this.solidArea.x = 3;
		this.solidArea.y = 18;
		this.solidArea.width = 42;
		this.solidArea.height = 30;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		getImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/monster/greenslime_down_1", 48, 48);
		
		
		this.up2 = setup("/monster/greenslime_down_2", 48, 48);
		
		
		this.down1 = setup("/monster/greenslime_down_1", 48, 48);
		
		
		this.down2 = setup("/monster/greenslime_down_2", 48, 48);
		
		
		this.left1 = setup("/monster/greenslime_down_1", 48, 48);
		
		
		this.left2 = setup("/monster/greenslime_down_2", 48, 48);
		
		
		this.right1 = setup("/monster/greenslime_down_1", 48, 48);
		
		
		this.right2 = setup("/monster/greenslime_down_2", 48, 48);
	}

	@Override
	public void setAction() {
		if (!this.onPath)
			getRandomDirection(100);
	}

	@Override
	public void damageReaction() {
		getRandomDirection(0);
		this.actionLockCounter = 0;
	}

	@Override
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i < 60)
			dropItem(new OBJ_Coin_Bronze(this.gp));
		if (i >= 60 && i < 70)
			dropItem(new OBJ_Coin_Silver(this.gp));
		if (i >= 70 && i < 90)
			dropItem(new OBJ_Heart(this.gp));
		if (i >= 90 && i < 100)
			dropItem(new OBJ_ManaCrystal(this.gp));
	}
}
