package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
	public static final String objName = "Wood Shield";

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		this.type = 5;
		this.name = "Wood Shield";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/shield_wood", 48, 48);
		this.defenseValue = 1;
		this.description = "[" + this.name + "]\nHold it by pressing \nSPACE key.";
		this.price = 35;
	}
}
