package tile_interactive;

import java.awt.Color;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class IT_DryTree extends InteractiveTile {
	GamePanel gp;

	public static final String objName = "Dry Tree";

	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		gp.getClass();
		this.worldX = 48 * col;
		gp.getClass();
		this.worldY = 48 * row;
		this.name = "Dry Tree";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/tiles_interactive/drytree", 48, 48);
		this.destructible = true;
		this.life = 1;
	}

	@Override
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if (entity.currentWeapon.type == 4)
			isCorrectItem = true;
		return isCorrectItem;
	}

	@Override
	public void playSE() {
		this.gp.playSE(11);
	}

	@Override
	public InteractiveTile getDestroyedForm() {
		
		
		InteractiveTile tile = new IT_Trunk(this.gp, this.worldX / 48, this.worldY / 48);
		return tile;
	}

	@Override
	public Color getParticleColor() {
		Color color = new Color(65, 50, 30);
		return color;
	}

	@Override
	public int getParticleSize() {
		int size = 6;
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

	@Override
	public void checkDrop() {
		int i = (new Random()).nextInt(100) + 1;
		if (i >= 70 && i < 90)
			dropItem(new OBJ_Coin_Bronze(this.gp));
		if (i >= 90 && i < 95)
			dropItem(new OBJ_Heart(this.gp));
		if (i >= 95 && i < 100)
			dropItem(new OBJ_ManaCrystal(this.gp));
	}
}
