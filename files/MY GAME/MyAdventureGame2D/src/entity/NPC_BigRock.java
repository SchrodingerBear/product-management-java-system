package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class NPC_BigRock extends Entity {
	public static final String npcName = "Big Rock";

	public NPC_BigRock(GamePanel gp) {
		super(gp);
		this.name = "Big Rock";
		this.direction = "down";
		this.speed = 4;
		this.solidArea = new Rectangle();
		this.solidArea.x = 2;
		this.solidArea.y = 6;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 44;
		this.solidArea.height = 40;
		this.dialogueSet = -1;
		getImage();
		setDialogue();
	}

	public void getImage() {
		
		
		this.up1 = setup("/npc/bigrock", 48, 48);
		
		
		this.up2 = setup("/npc/bigrock", 48, 48);
		
		
		this.down1 = setup("/npc/bigrock", 48, 48);
		
		
		this.down2 = setup("/npc/bigrock", 48, 48);
		
		
		this.left1 = setup("/npc/bigrock", 48, 48);
		
		
		this.left2 = setup("/npc/bigrock", 48, 48);
		
		
		this.right1 = setup("/npc/bigrock", 48, 48);
		
		
		this.right2 = setup("/npc/bigrock", 48, 48);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "It's a giant rock";
	}

	@Override
	public void setAction() {
	}

	@Override
	public void update() {
	}

	@Override
	public void speak() {
		facePlayer();
		startDialogue(this, this.dialogueSet);
		this.dialogueSet++;
		if (this.dialogues[this.dialogueSet][0] == null)
			this.dialogueSet--;
	}

	@Override
	public void move(String d) {
		this.direction = d;
		checkCollision();
		if (!this.collisionOn) {
			String str;
			switch ((str = this.direction).hashCode()) {
			case 3739:
				if (!str.equals("up"))
					break;
				this.worldY -= this.speed;
				break;
			case 3089570:
				if (!str.equals("down"))
					break;
				this.worldY += this.speed;
				break;
			case 3317767:
				if (!str.equals("left"))
					break;
				this.worldX -= this.speed;
				break;
			case 108511772:
				if (!str.equals("right"))
					break;
				this.worldX += this.speed;
				break;
			}
		}
		detectPlate();
	}

	public void detectPlate() {
		ArrayList<InteractiveTile> plateList = new ArrayList<>();
		ArrayList<Entity> rockList = new ArrayList<>();
		int i;
		for (i = 0; i < (this.gp.iTile[1]).length; i++) {
			if (this.gp.iTile[this.gp.currentMap][i] != null && (this.gp.iTile[this.gp.currentMap][i]).name != null
					&& (this.gp.iTile[this.gp.currentMap][i]).name.equals("Metal Plate"))
				plateList.add(this.gp.iTile[this.gp.currentMap][i]);
		}
		for (i = 0; i < (this.gp.npc[1]).length; i++) {
			if (this.gp.npc[this.gp.currentMap][i] != null
					&& (this.gp.npc[this.gp.currentMap][i]).name.equals("Big Rock"))
				rockList.add(this.gp.npc[this.gp.currentMap][i]);
		}
		int count = 0;
		int j;
		for (j = 0; j < plateList.size(); j++) {
			int xDistance = Math.abs(this.worldX - plateList.get(j).worldX);
			int yDistance = Math.abs(this.worldY - plateList.get(j).worldY);
			int distance = Math.max(xDistance, yDistance);
			if (distance < 8) {
				if (this.linkedEntity == null) {
					this.linkedEntity = plateList.get(j);
					this.gp.playSE(3);
				}
			} else if (this.linkedEntity == plateList.get(j)) {
				this.linkedEntity = null;
			}
		}
		for (j = 0; j < rockList.size(); j++) {
			if (rockList.get(j).linkedEntity != null)
				count++;
		}
		if (count == rockList.size())
			for (j = 0; j < (this.gp.obj[1]).length; j++) {
				if (this.gp.obj[this.gp.currentMap][j] != null
						&& (this.gp.obj[this.gp.currentMap][j]).name.equals("Iron Door")) {
					this.gp.obj[this.gp.currentMap][j] = null;
					this.gp.playSE(21);
				}
			}
	}
}
