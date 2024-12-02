package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {
	public static final String objName = "Woodcutter's Axe";

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		this.type = 4;
		this.name = "Woodcutter's Axe";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/axe", 48, 48);
		this.attackValue = 4;
		this.attackArea.width = 30;
		this.attackArea.height = 30;
		this.description = "[Woodcutter's Axe]\nCan cut trees\nbut pretty heavy.";
		this.price = 75;
		this.knockBackPower = 10;
		this.motion1_duration = 20;
		this.motion2_duration = 40;
	}
}
