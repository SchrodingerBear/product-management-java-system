package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity {
	GamePanel gp;

	public static final String objName = "Iron Door";

	public OBJ_Door_Iron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 8;
		this.name = "Iron Door";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/door_iron", 48, 48);
		this.collision = true;
		this.solidArea.x = 0;
		this.solidArea.y = 16;
		this.solidArea.width = 48;
		this.solidArea.height = 32;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "It won't budge.";
	}

	@Override
	public void interact() {
		startDialogue(this, 0);
	}
}
