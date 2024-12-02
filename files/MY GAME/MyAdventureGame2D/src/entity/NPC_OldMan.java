package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		this.direction = "down";
		this.speed = 1;
		this.solidArea = new Rectangle();
		this.solidArea.x = 8;
		this.solidArea.y = 16;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 30;
		this.solidArea.height = 30;
		this.dialogueSet = -1;
		getImage();
		setDialogue();
	}

	public void getImage() {
		
		
		this.up1 = setup("/npc/oldman_up_1", 48, 48);
		
		
		this.up2 = setup("/npc/oldman_up_2", 48, 48);
		
		
		this.down1 = setup("/npc/oldman_down_1", 48, 48);
		
		
		this.down2 = setup("/npc/oldman_down_2", 48, 48);
		
		
		this.left1 = setup("/npc/oldman_left_1", 48, 48);
		
		
		this.left2 = setup("/npc/oldman_left_2", 48, 48);
		
		
		this.right1 = setup("/npc/oldman_right_1", 48, 48);
		
		
		this.right2 = setup("/npc/oldman_right_2", 48, 48);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "Hello, lad.";
		this.dialogues[0][1] = "So you've come to this island to \nfind the treasure?";
		this.dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
		this.dialogues[0][3] = "Well, good luck on you.";
		this.dialogues[1][0] = "If you become tired, rest at the pond.\nThat's a magical water and it heals your wounds.";
		this.dialogues[1][1] = "However, the monsters that you have killed \nreappear if you use the effect.\nI don't know why but that's how it works.";
		this.dialogues[1][2] = "In any case, don't push yourself too hard.";
		this.dialogues[2][0] = "I wonder how to open that door...";
		this.dialogues[3][0] = "You can block projectiles with your shield.";
		this.dialogues[3][1] = "You can also parry an attack from monsters \nwho swing a weapon.";
		this.dialogues[3][2] = "If you miss the timing you receive damage \nso it is a bit risky action but you can give \nmassive damage if you succeed.";
	}

	@Override
	public void setAction() {
		if (this.onPath) {
			int goalCol = 12;
			int goalRow = 9;
			searchPath(goalCol, goalRow);
		} else {
			this.actionLockCounter++;
			if (this.actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1;
				if (i <= 20)
					this.direction = "up";
				if (i > 20 && i <= 40)
					this.direction = "down";
				if (i > 40 && i <= 60)
					this.direction = "left";
				if (i > 60 && i <= 80)
					this.direction = "right";
				if (i > 80 && i <= 100)
					this.idle = true;
				this.actionLockCounter = 0;
			}
		}
	}

	@Override
	public void speak() {
		facePlayer();
		startDialogue(this, this.dialogueSet);
		this.dialogueSet++;
		if (this.dialogues[this.dialogueSet][0] == null)
			this.dialogueSet--;
	}
}
