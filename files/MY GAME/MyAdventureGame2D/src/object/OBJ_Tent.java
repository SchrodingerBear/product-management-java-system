package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {
	GamePanel gp;

	public static final String objName = "Tent";

	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 6;
		this.name = "Tent";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/tent", 48, 48);
		this.description = "[Tent]\nYou can sleep until\nnext morning.";
		this.price = 30;
		this.stackable = true;
	}

	@Override
	public boolean use(Entity entity) {
		
		this.gp.gameState = 9;
		this.gp.playSE(14);
		this.gp.player.life = this.gp.player.maxLife;
		this.gp.player.mana = this.gp.player.maxMana;
		this.gp.player.getSleepingImage(this.down1);
		return true;
	}
}
