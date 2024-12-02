package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Blue extends Entity {
	GamePanel gp;

	public static final String objName = "Blue Potion";

	public OBJ_Potion_Blue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 6;
		this.name = "Blue Potion";
		this.value = 5;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/potion_blue", 48, 48);
		this.description = "[Blue Potion]\nHeals your mana by " + this.value + ".";
		this.price = 20;
		this.stackable = true;
		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You drink the " + this.name + "!\n" + "Your mana has been recovered by " + this.value
				+ ".";
	}

	@Override
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.mana += this.value;
		this.gp.playSE(2);
		return true;
	}
}
