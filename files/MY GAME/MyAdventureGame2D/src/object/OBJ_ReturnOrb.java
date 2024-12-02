package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ReturnOrb extends Entity {
	GamePanel gp;

	public static final String objName = "Return Orb";

	public OBJ_ReturnOrb(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 6;
		this.name = "Return Orb";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/returnorb", 48, 48);
		this.description = "[Return Orb]\nTeleport to the\nsave point.";
		this.price = 5;
		this.stackable = true;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You use the " + this.name + "!";
	}

	@Override
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		this.gp.playSE(2);
		
		this.gp.nextArea = 50;
		this.gp.currentMap = 0;
		
		this.gp.player.worldX = 48 * 23;
		
		this.gp.player.worldY = 48 * 13;
		this.gp.changeArea();
		return true;
	}
}
