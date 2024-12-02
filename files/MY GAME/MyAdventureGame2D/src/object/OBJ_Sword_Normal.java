package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
	public static final String objName = "Normal Sword";

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		this.type = 3;
		this.name = "Normal Sword";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/sword_normal", 48, 48);
		this.attackValue = 2;
		this.attackArea.width = 36;
		this.attackArea.height = 36;
		this.description = "[" + this.name + "]\nAn old sword.";
		this.price = 20;
		this.knockBackPower = 2;
		this.motion1_duration = 5;
		this.motion2_duration = 20;
	}
}
