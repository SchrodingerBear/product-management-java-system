package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
	public static final String objName = "Lantern";

	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		this.type = 9;
		this.name = "Lantern";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/lantern", 48, 48);
		this.description = "[Lantern]\nIlluminates your \nsurroundings.";
		this.price = 45;
		this.lightRadius = 350;
	}
}
