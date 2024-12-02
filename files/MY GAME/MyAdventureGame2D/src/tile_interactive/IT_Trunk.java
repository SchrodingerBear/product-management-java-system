package tile_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile {
	GamePanel gp;

	public static final String objName = "Trunk";

	public IT_Trunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		gp.getClass();
		this.worldX = 48 * col;
		gp.getClass();
		this.worldY = 48 * row;
		this.name = "Trunk";
		gp.getClass();
		gp.getClass();
		this.down1 = setup("/tiles_interactive/trunk", 48, 48);
		this.solidArea.x = 0;
		this.solidArea.y = 0;
		this.solidArea.width = 0;
		this.solidArea.height = 0;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
}
