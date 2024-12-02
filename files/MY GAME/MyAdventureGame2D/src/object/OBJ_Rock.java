package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
	GamePanel gp;

	public static final String objName = "Rock";

	public OBJ_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Rock";
		this.speed = 3;
		this.maxLife = 130;
		this.life = this.maxLife;
		this.attack = 2;
		this.useCost = 1;
		this.alive = false;
		getImage();
	}

	public void getImage() {
		
		
		this.up1 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.up2 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.down1 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.down2 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.left1 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.left2 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.right1 = setup("/projectile/rock_down_1", 48, 48);
		
		
		this.right2 = setup("/projectile/rock_down_1", 48, 48);
	}

	@Override
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if (user.ammo >= this.useCost)
			haveResource = true;
		return haveResource;
	}

	@Override
	public void subtractResource(Entity user) {
		user.ammo -= this.useCost;
	}

	@Override
	public Color getParticleColor() {
		Color color = new Color(40, 50, 0);
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
