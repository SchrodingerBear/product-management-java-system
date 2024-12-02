package entity;

import java.awt.Rectangle;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_ReturnOrb;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity {
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		this.direction = "down";
		this.speed = 1;
		this.solidArea = new Rectangle();
		this.solidArea.x = 8;
		this.solidArea.y = 16;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 32;
		this.solidArea.height = 32;
		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {
		
		
		this.up1 = setup("/npc/merchant_down_1", 48, 48);
		
		
		this.up2 = setup("/npc/merchant_down_2", 48, 48);
		
		
		this.down1 = setup("/npc/merchant_down_1", 48, 48);
		
		
		this.down2 = setup("/npc/merchant_down_2", 48, 48);
		
		
		this.left1 = setup("/npc/merchant_down_1", 48, 48);
		
		
		this.left2 = setup("/npc/merchant_down_2", 48, 48);
		
		
		this.right1 = setup("/npc/merchant_down_1", 48, 48);
		
		
		this.right2 = setup("/npc/merchant_down_2", 48, 48);
		UtilityTool.log("merchant getimage done");
	}

	public void setDialogue() {
		this.dialogues[0][0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
		this.dialogues[1][0] = "Come again, hehe!";
		this.dialogues[2][0] = "You need more coin to buy that!";
		this.dialogues[3][0] = "You cannot carry any more!";
		this.dialogues[4][0] = "You cannot sell an equipped item!";
		this.dialogues[5][0] = "You cannot sell that item!";
	}

	public void setItems() {
		this.inventory.add(new OBJ_Potion_Red(this.gp));
		UtilityTool.log("OBJ_Potion_Red done");
		this.inventory.add(new OBJ_Potion_Blue(this.gp));
		UtilityTool.log("OBJ_Potion_Blue done");
		this.inventory.add(new OBJ_ReturnOrb(this.gp));
		UtilityTool.log("OBJ_ReturnOrb done");
		this.inventory.add(new OBJ_Tent(this.gp));
		UtilityTool.log("OBJ_Tent done");
		this.inventory.add(new OBJ_Lantern(this.gp));
		UtilityTool.log("OBJ_Lantern done");
		this.inventory.add(new OBJ_Key(this.gp));
		UtilityTool.log("OBJ_Key done");
		this.inventory.add(new OBJ_Sword_Normal(this.gp));
		UtilityTool.log("OBJ_Sword_Normal done");
		this.inventory.add(new OBJ_Axe(this.gp));
		UtilityTool.log("OBJ_Axe done");
		this.inventory.add(new OBJ_Pickaxe(this.gp));
		UtilityTool.log("OBJ_Pickaxe done");
		this.inventory.add(new OBJ_Shield_Wood(this.gp));
		UtilityTool.log("OBJ_Shield_Wood done");
		this.inventory.add(new OBJ_Shield_Blue(this.gp));
		UtilityTool.log("merchant setitems done");
	}

	@Override
	public void speak() {
		facePlayer();
		
		this.gp.gameState = 8;
		this.gp.ui.npc = this;
	}
}
