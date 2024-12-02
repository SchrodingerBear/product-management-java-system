package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Blue extends Entity {
	public static final String objName = "Blue Sword";

	public OBJ_Sword_Blue(GamePanel gp) {
		super(gp);
		this.type = 3;
		this.name = "Blue Sword";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/sword_blue", 48, 48);
		this.attackValue = 5;
		this.attackArea.width = 36;
		this.attackArea.height = 36;
		this.description = "[" + this.name + "]\nA legendary sword.";
		this.price = 200;
		this.knockBackPower = 4;
		this.motion1_duration = 3;
		this.motion2_duration = 10;
	}
}
