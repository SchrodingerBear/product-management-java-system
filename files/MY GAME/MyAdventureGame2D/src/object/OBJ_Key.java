package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	GamePanel gp;

	public static final String objName = "Key";

	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 6;
		this.name = "Key";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/key", 48, 48);
		this.description = "[" + this.name + "]\nIt opens a door.";
		this.price = 50;
		this.stackable = true;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You use the " + this.name + " and open the door";
		this.dialogues[1][0] = "What are you doing?";
	}

	@Override
	public boolean use(Entity entity) {
		int objIndex = getDetected(entity, this.gp.obj, "Door");
		if (objIndex != 999) {
			startDialogue(this, 0);
			this.gp.playSE(3);
			this.gp.obj[this.gp.currentMap][objIndex] = null;
			return true;
		}
		startDialogue(this, 1);
		return false;
	}
}
