package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Silver extends Entity {
	GamePanel gp;

	public static final String objName = "Silver Coin";

	public OBJ_Coin_Silver(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = 7;
		this.name = "Silver Coin";
		this.value = 5;
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/objects/coin_silver", 48, 48);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.playSE(1);
		this.gp.ui.addMessage("Coin +" + this.value);
		this.gp.player.coin += this.value;
		return true;
	}
}
