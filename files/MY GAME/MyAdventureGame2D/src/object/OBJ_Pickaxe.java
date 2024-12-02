package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {
	public static final String objName = "Pickaxe";

	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		this.type = 10;
		this.name = "Pickaxe";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/pickaxe", 48, 48);
		this.attackValue = 4;
		this.attackArea.width = 30;
		this.attackArea.height = 30;
		this.description = "[Pickaxe]\nYou will dig it!";
		this.price = 75;
		this.knockBackPower = 10;
		this.motion1_duration = 15;
		this.motion2_duration = 25;
	}
}
