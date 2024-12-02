package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {
	GamePanel gp;

	public static final String objName = "Fireball";

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Fireball";
		this.speed = 5;
		this.maxLife = 80;
		this.life = this.maxLife;
		this.attack = 2;
		this.knockBackPower = 5;
		this.useCost = 1;
		this.alive = false;
		getImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/projectile/fireball_up_1", 48, 48);
		
		
		this.up2 = setup("/projectile/fireball_up_2", 48, 48);
		
		
		this.down1 = setup("/projectile/fireball_down_1", 48, 48);
		
		
		this.down2 = setup("/projectile/fireball_down_2", 48, 48);
		
		
		this.left1 = setup("/projectile/fireball_left_1", 48, 48);
		
		
		this.left2 = setup("/projectile/fireball_left_2", 48, 48);
		
		
		this.right1 = setup("/projectile/fireball_right_1", 48, 48);
		
		
		this.right2 = setup("/projectile/fireball_right_2", 48, 48);
	}

	@Override
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if (user.mana >= this.useCost)
			haveResource = true;
		return haveResource;
	}

	@Override
	public void subtractResource(Entity user) {
		user.mana -= this.useCost;
	}

	@Override
	public Color getParticleColor() {
		Color color = new Color(240, 50, 0);
		return color;
	}

	@Override
	public int getParticleSize() {
		int size = 10;
		return size;
	}

	@Override
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}

	@Override
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
