package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {
	GamePanel gp;

	public static final String objName = "Blue Heart";

	public OBJ_BlueHeart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 7;
		this.name = "Blue Heart";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/blueheart", 48, 48);
		setDialogues();
	}

	public void setDialogues() {
		this.dialogues[0][0] = "You pick up a beautiful blue gem.";
		this.dialogues[0][1] = "You find the Blue Heart, the legendary treasure!";
	}

	@Override
	public boolean use(Entity entity) {
		
		this.gp.gameState = 11;
		this.gp.csManager.getClass();
		this.gp.csManager.sceneNum = 3;
		return true;
	}
}
