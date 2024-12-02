package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	GamePanel gp;

	public static final String objName = "Heart";

	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 7;
		this.name = "Heart";
		this.value = 2;
		int size = 32;
		this.down1 = setup("/objects/heart_full", size, size);
		this.image = setup("/objects/heart_full", size, size);
		this.image2 = setup("/objects/heart_half", size, size);
		this.image3 = setup("/objects/heart_blank", size, size);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.playSE(2);
		this.gp.ui.addMessage("Life +" + this.value);
		entity.life += this.value;
		return true;
	}
}
