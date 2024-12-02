package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
	GamePanel gp;

	public static final String objName = "Red Potion";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 6;
		this.name = "Red Potion";
		this.value = 8;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/potion_red", 48, 48);
		this.description = "[Red Potion]\nHeals your life by " + this.value + ".";
		this.price = 10;
		this.stackable = true;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You drink the " + this.name + "!\n" + "Your life has been recovered by " + this.value
				+ ".";
	}

	@Override
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.life += this.value;
		this.gp.playSE(2);
		return true;
	}
}
