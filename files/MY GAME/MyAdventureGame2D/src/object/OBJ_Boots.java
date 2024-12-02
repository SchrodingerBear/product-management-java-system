package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {
	public static final String objName = "Boots";

	public OBJ_Boots(GamePanel gp) {
		super(gp);
		this.name = "Boots";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/boots", 48, 48);
	}
}
