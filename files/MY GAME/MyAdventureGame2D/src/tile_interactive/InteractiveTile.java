package tile_interactive;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
	GamePanel gp;

	public boolean destructible = false;

	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}

	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}

	public void playSE() {
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}

	@Override
	public void update() {
		if (this.invincible) {
			this.invincibleCounter++;
			if (this.invincibleCounter > 20) {
				this.invincible = false;
				this.invincibleCounter = 0;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		int screenX = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
		int screenY = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
		
		
		
		
		if (this.worldX + 48 > this.gp.player.worldX - this.gp.player.screenX
				&& this.worldX - 48 < this.gp.player.worldX + this.gp.player.screenX
				&& this.worldY + 48 > this.gp.player.worldY - this.gp.player.screenY
				&& this.worldY - 48 < this.gp.player.worldY + this.gp.player.screenY)
			g2.drawImage(this.down1, screenX, screenY, null);
	}
}
