package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	GamePanel gp;

	public static final String objName = "Chest";

	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 8;
		this.name = "Chest";
		gp.getClass();
		gp.getClass();
		this.image = setup("/objects/chest", 48, 48);
		gp.getClass();
		gp.getClass();
		this.image2 = setup("/objects/chest_opened", 48, 48);
		this.down1 = this.image;
		this.collision = true;
		this.solidArea.x = 4;
		this.solidArea.y = 16;
		this.solidArea.width = 40;
		this.solidArea.height = 32;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	@Override
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You open the chest and find a " + this.loot.name
				+ "!\n...But you cannot carry any more!";
		this.dialogues[1][0] = "You open the chest and find a " + this.loot.name + "!\nYou obtain the " + this.loot.name
				+ "!";
		this.dialogues[2][0] = "It's empty.";
	}

	@Override
	public void interact() {
		if (!this.opened) {
			this.gp.playSE(3);
			if (!this.gp.player.canObtainItem(this.loot)) {
				startDialogue(this, 0);
			} else {
				startDialogue(this, 1);
				this.down1 = this.image2;
				this.opened = true;
			}
		} else {
			startDialogue(this, 2);
		}
	}
}
