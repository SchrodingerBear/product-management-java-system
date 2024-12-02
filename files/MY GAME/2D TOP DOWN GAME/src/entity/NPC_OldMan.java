package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 1;
        getImage();
        setDialogue();
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();

            int i = random.nextInt(125) + 1;

            if (gp.gameState == gp.playState) {
            	if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                
                if (i > 100 && i <= 125) {
                	direction = "idle";
    			}
			}
            actionLockCounter = 0;
        }
    }
    
    public void setDialogue() {
    	dialogues[0] = "Hello Player";
    	dialogues[1] = "How Are You?";
    }
	
	// METHOD TO INITIALIZE CHARACTER ANIMATION
	public void getImage() {
		idle = setUp("/npc/oldidle");
		up1 = setUp("/npc/oldup1");
		up2 = setUp("/npc/oldup2");
		down1 = setUp("/npc/olddown1");
		down2 = setUp("/npc/olddown2");
		left1 = setUp("/npc/oldleft1");
		left2 = setUp("/npc/oldleft2");
		right1 = setUp("/npc/oldright1");
		right2 = setUp("/npc/oldright2");
	}	
	
	public void speak() {
		super.speak();
	}
}
