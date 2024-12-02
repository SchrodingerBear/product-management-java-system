package main;

import tile_interactive.IT_MetalPlate;
import tile_interactive.IT_Trunk;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.InteractiveTile;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Axe;
import object.OBJ_Shield_Blue;
import object.OBJ_Lantern;
import object.OBJ_Potion_Blue;
import object.OBJ_Pickaxe;
import object.OBJ_Door_Iron;
import object.OBJ_Coin_Gold;
import object.OBJ_Sword_Blue;
import object.OBJ_BlueHeart;
import object.OBJ_Heart;
import object.OBJ_Chest;
import object.OBJ_Boots;
import object.OBJ_Tent;
import object.OBJ_Rock;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Coin_Bronze;
import object.OBJ_Fireball;
import object.OBJ_ReturnOrb;
import object.OBJ_Sword_Normal;
import object.OBJ_Shield_Wood;
import object.OBJ_Coin_Silver;
import entity.Entity;

public class EntityGenerator {
	GamePanel gp;

	public EntityGenerator(final GamePanel gp) {
		this.gp = gp;
	}

	public Entity getObject(final String itemName) {
		Entity obj = null;
		switch (itemName) {
		case "Silver Coin": {
			obj = (Entity) new OBJ_Coin_Silver(this.gp);
			break;
		}
		case "Wood Shield": {
			obj = (Entity) new OBJ_Shield_Wood(this.gp);
			break;
		}
		case "Normal Sword": {
			obj = (Entity) new OBJ_Sword_Normal(this.gp);
			break;
		}
		case "Return Orb": {
			obj = (Entity) new OBJ_ReturnOrb(this.gp);
			break;
		}
		case "Fireball": {
			obj = (Entity) new OBJ_Fireball(this.gp);
			break;
		}
		case "Bronze Coin": {
			obj = (Entity) new OBJ_Coin_Bronze(this.gp);
			break;
		}
		case "Key": {
			obj = (Entity) new OBJ_Key(this.gp);
			break;
		}
		case "Door": {
			obj = (Entity) new OBJ_Door(this.gp);
			break;
		}
		case "Rock": {
			obj = (Entity) new OBJ_Rock(this.gp);
			break;
		}
		case "Tent": {
			obj = (Entity) new OBJ_Tent(this.gp);
			break;
		}
		case "Boots": {
			obj = (Entity) new OBJ_Boots(this.gp);
			break;
		}
		case "Chest": {
			obj = (Entity) new OBJ_Chest(this.gp);
			break;
		}
		case "Heart": {
			obj = (Entity) new OBJ_Heart(this.gp);
			break;
		}
		case "Blue Heart": {
			obj = (Entity) new OBJ_BlueHeart(this.gp);
			break;
		}
		case "Blue Sword": {
			obj = (Entity) new OBJ_Sword_Blue(this.gp);
			break;
		}
		case "Gold Coin": {
			obj = (Entity) new OBJ_Coin_Gold(this.gp);
			break;
		}
		case "Iron Door": {
			obj = (Entity) new OBJ_Door_Iron(this.gp);
			break;
		}
		case "Pickaxe": {
			obj = (Entity) new OBJ_Pickaxe(this.gp);
			break;
		}
		case "Blue Potion": {
			obj = (Entity) new OBJ_Potion_Blue(this.gp);
			break;
		}
		case "Lantern": {
			obj = (Entity) new OBJ_Lantern(this.gp);
			break;
		}
		case "Blue Shield": {
			obj = (Entity) new OBJ_Shield_Blue(this.gp);
			break;
		}
		case "Woodcutter's Axe": {
			obj = (Entity) new OBJ_Axe(this.gp);
			break;
		}
		case "Red Potion": {
			obj = (Entity) new OBJ_Potion_Red(this.gp);
			break;
		}
		case "Mana Crystal": {
			obj = (Entity) new OBJ_ManaCrystal(this.gp);
			break;
		}
		default:
			break;
		}
		return obj;
	}

	public InteractiveTile getItile(final String name, final int worldX, final int worldY) {
		InteractiveTile iTile = null;
		
		final int col = worldX / 48;
		
		final int row = worldY / 48;
		switch (name) {
		case "Destructible Wall": {
			iTile = (InteractiveTile) new IT_DestructibleWall(this.gp, col, row);
			break;
		}
		case "Dry Tree": {
			iTile = (InteractiveTile) new IT_DryTree(this.gp, col, row);
			break;
		}
		case "Trunk": {
			iTile = (InteractiveTile) new IT_Trunk(this.gp, col, row);
			break;
		}
		case "Metal Plate": {
			iTile = (InteractiveTile) new IT_MetalPlate(this.gp, col, row);
			break;
		}
		default:
			break;
		}
		return iTile;
	}
}
