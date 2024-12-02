package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
	GamePanel gp;

	public static final String objName = "Door";

	public OBJ_Door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 8;
		this.name = "Door";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/door", 48, 48);
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
		this.dialogues[0][0] = "You need a key to open this.";
	}

	@Override
	public void interact() {
		startDialogue(this, 0);
	}
}
