// Put Objects to Locations
package main;

import entity.NPC_OldMan;
// import class objects
import object.OBJ_Chest;

// Create a House

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Chest(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 22 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Chest(gp);
		gp.obj[1].worldX = 24 * gp.tileSize;
		gp.obj[1].worldY = 22 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = 21 * gp.tileSize;
		gp.npc[0].worldY = 21 * gp.tileSize;
		
	}
}
