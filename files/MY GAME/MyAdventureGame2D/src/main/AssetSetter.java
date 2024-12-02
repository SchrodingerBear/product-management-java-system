package main;

import data.Progress;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import monster.MON_RedOrc;
import monster.MON_RedSlime;
import monster.MON_SkeletonLord;
import object.OBJ_Axe;
import object.OBJ_BlueHeart;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_Sword_Blue;
import object.OBJ_Tent;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
//		int mapNum = 0;
//		int i = 0;
//		this.gp.obj[mapNum][i] = new OBJ_Axe(this.gp);
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 33;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 7;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Door(this.gp);
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 12;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 12;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Key(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 30;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 29;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Tent(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 27;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 20;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Lantern(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 28;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 20;
//		i++;
//		mapNum = 2;
//		i = 0;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 40;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 41;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Tent(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 13;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 16;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 26;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 34;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Potion_Blue(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 27;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 15;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_Door_Iron(this.gp);
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 18;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 23;
//		i++;
//		mapNum = 3;
//		i = 0;
//		this.gp.obj[mapNum][i] = new OBJ_Chest(this.gp);
//		this.gp.obj[mapNum][i].setLoot(new OBJ_Sword_Blue(this.gp));
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 23;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 28;
//		i++;
//		mapNum = 4;
//		i = 0;
//		this.gp.obj[mapNum][i] = new OBJ_Door_Iron(this.gp);
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 25;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 15;
//		i++;
//		this.gp.obj[mapNum][i] = new OBJ_BlueHeart(this.gp);
//		
//		(this.gp.obj[mapNum][i]).worldX = 48 * 25;
//		
//		(this.gp.obj[mapNum][i]).worldY = 48 * 8;
//		i++;
	}

	public void setNPC() {
//		int mapNum = 0;
//		int i = 0;
//		this.gp.npc[mapNum][i] = new NPC_OldMan(this.gp);
//		
//		(this.gp.npc[mapNum][i]).worldX = 48 * 21;
//		
//		(this.gp.npc[mapNum][i]).worldY = 48 * 21;
//		i++;
//		UtilityTool.log("NPC_OldMan done");
//		mapNum = 1;
//		i = 0;
//		this.gp.npc[mapNum][i] = new NPC_Merchant(this.gp);
//		
//		(this.gp.npc[mapNum][i]).worldX = 48 * 12;
//		
//		(this.gp.npc[mapNum][i]).worldY = 48 * 7;
//		i++;
//		UtilityTool.log("NPC_Merchant done");
//		mapNum = 2;
//		i = 0;
//		this.gp.npc[mapNum][i] = new NPC_BigRock(this.gp);
//		
//		(this.gp.npc[mapNum][i]).worldX = 48 * 20;
//		
//		(this.gp.npc[mapNum][i]).worldY = 48 * 25;
//		i++;
//		UtilityTool.log("NPC_BigRock done");
//		this.gp.npc[mapNum][i] = new NPC_BigRock(this.gp);
//		
//		(this.gp.npc[mapNum][i]).worldX = 48 * 11;
//		
//		(this.gp.npc[mapNum][i]).worldY = 48 * 18;
//		i++;
//		this.gp.npc[mapNum][i] = new NPC_BigRock(this.gp);
//		
//		(this.gp.npc[mapNum][i]).worldX = 48 * 23;
//		
//		(this.gp.npc[mapNum][i]).worldY = 48 * 14;
//		i++;
	}

	public void setMonster() {
//		int mapNum = 0;
//		int i = 0;
//		this.gp.monster[mapNum][i] = new MON_GreenSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 21;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 38;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_GreenSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 23;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 42;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_GreenSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 24;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 37;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_GreenSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 34;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 42;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_GreenSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 38;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 42;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 35;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 8;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 40;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 9;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedSlime(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 35;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 10;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 12;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 33;
//		i++;
//		mapNum = 2;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 34;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 39;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 36;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 25;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 39;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 26;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 28;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 11;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 10;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 19;
//		i++;
//		mapNum = 3;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 14;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 13;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 13;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 15;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 18;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 15;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 28;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 16;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 24;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 18;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 30;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 14;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 24;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 12;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 28;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 15;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Bat(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 31;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 14;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 36;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 27;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedOrc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 25;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 34;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedOrc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 36;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 39;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 13;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 33;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_Orc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 14;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 37;
//		i++;
//		this.gp.monster[mapNum][i] = new MON_RedOrc(this.gp);
//		
//		(this.gp.monster[mapNum][i]).worldX = 48 * 11;
//		
//		(this.gp.monster[mapNum][i]).worldY = 48 * 37;
//		i++;
//		mapNum = 4;
//		i++;
//		if (!Progress.skeletonLordDefeated) {
//			this.gp.monster[mapNum][i] = new MON_SkeletonLord(this.gp);
//			
//			(this.gp.monster[mapNum][i]).worldX = 48 * 23;
//			
//			(this.gp.monster[mapNum][i]).worldY = 48 * 16;
//			i++;
//		}
	}

	public void setInteractiveTile() {
//		int mapNum = 0;
//		int i = 0;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 14, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 10, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 27, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 28, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 29, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 30, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 31, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 32, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 33, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 31, 21);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 18, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 17, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 16, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 15, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 14, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 13, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 13, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 12, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 11, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 10, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 10, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 25, 27);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 26, 27);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 27, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 27, 29);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 27, 30);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 27, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 28, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DryTree(this.gp, 29, 31);
//		i++;
//		mapNum = 2;
//		i = 0;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 30);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 17, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 17, 32);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 17, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 33);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 10, 22);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 10, 24);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 38, 18);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 38, 19);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 38, 20);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 38, 21);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 13);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 14);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 22, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 30, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 32, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_MetalPlate(this.gp, 20, 22);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_MetalPlate(this.gp, 8, 17);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_MetalPlate(this.gp, 39, 31);
//		i++;
//		mapNum = 3;
//		i = 0;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 9, 8);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 12, 8);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 14, 9);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 14, 11);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 10, 17);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 21, 12);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 21, 16);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 33, 29);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 33, 30);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 32, 30);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 32, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 31, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 31, 32);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 31, 33);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 30, 33);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 29, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 28, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 27, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 26, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 25, 33);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 24, 33);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 23, 32);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 23, 31);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 23, 30);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 23, 29);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 36, 34);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 37, 35);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 38, 35);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 15, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 15, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 16, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 17, 28);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 17, 27);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 27);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 18, 25);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 19, 25);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 20, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 21, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 22, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 23, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 24, 39);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 25, 39);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 26, 39);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 26, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 27, 39);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 28, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 29, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 29, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 30, 41);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 31, 40);
//		i++;
//		this.gp.iTile[mapNum][i] = new IT_DestructibleWall(this.gp, 32, 40);
//		i++;
	}
}
