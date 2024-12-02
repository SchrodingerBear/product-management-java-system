package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {
	GamePanel gp;

	public static final String objName = "Mana Crystal";

	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 7;
		this.name = "Mana Crystal";
		this.value = 1;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/manacrystal_full", 48, 48);
		gp.getClass();
		gp.getClass();
		this.image = setup("/objects/manacrystal_full", 48, 48);
		gp.getClass();
		gp.getClass();
		this.image2 = setup("/objects/manacrystal_blank", 48, 48);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.playSE(2);
		this.gp.ui.addMessage("Mana +" + this.value);
		entity.mana += this.value;
		return true;
	}
}
