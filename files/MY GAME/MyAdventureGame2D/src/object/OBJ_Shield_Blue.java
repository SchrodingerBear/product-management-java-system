package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {
	public static final String objName = "Blue Shield";

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		this.type = 5;
		this.name = "Blue Shield";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/shield_blue", 48, 48);
		this.defenseValue = 4;
		this.description = "[" + this.name + "]\nA shiny blue shield.";
		this.price = 150;
	}
}
