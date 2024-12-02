package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int tileNum1, tileNum2, entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / 48;
		
		int entityRightCol = entityRightWorldX / 48;
		
		int entityTopRow = entityTopWorldY / 48;
		
		int entityBottomRow = entityBottomWorldY / 48;
		String direction = entity.direction;
		if (entity.knockBack)
			direction = entity.knockBackDirection;
		String str1;
		switch ((str1 = direction).hashCode()) {
		case 3739:
			if (!str1.equals("up"))
				break;
			
			entityTopRow = (entityTopWorldY - entity.speed) / 48;
			tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityRightCol][entityTopRow];
			if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
				entity.collisionOn = true;
			break;
		case 3089570:
			if (!str1.equals("down"))
				break;
			
			entityBottomRow = (entityBottomWorldY + entity.speed) / 48;
			tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityRightCol][entityBottomRow];
			if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
				entity.collisionOn = true;
			break;
		case 3317767:
			if (!str1.equals("left"))
				break;
			
			entityLeftCol = (entityLeftWorldX - entity.speed) / 48;
			tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityLeftCol][entityBottomRow];
			if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
				entity.collisionOn = true;
			break;
		case 108511772:
			if (!str1.equals("right"))
				break;
			
			entityRightCol = (entityRightWorldX + entity.speed) / 48;
			tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][entityRightCol][entityBottomRow];
			if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
				entity.collisionOn = true;
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		String direction = entity.direction;
		if (entity.knockBack)
			direction = entity.knockBackDirection;
		for (int i = 0; i < (this.gp.obj[1]).length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				(this.gp.obj[this.gp.currentMap][i]).solidArea.x = (this.gp.obj[this.gp.currentMap][i]).worldX
						+ (this.gp.obj[this.gp.currentMap][i]).solidArea.x;
				(this.gp.obj[this.gp.currentMap][i]).solidArea.y = (this.gp.obj[this.gp.currentMap][i]).worldY
						+ (this.gp.obj[this.gp.currentMap][i]).solidArea.y;
				String str;
				switch ((str = direction).hashCode()) {
				case 3739:
					if (!str.equals("up"))
						break;
					entity.solidArea.y -= entity.speed;
					break;
				case 3089570:
					if (!str.equals("down"))
						break;
					entity.solidArea.y += entity.speed;
					break;
				case 3317767:
					if (!str.equals("left"))
						break;
					entity.solidArea.x -= entity.speed;
					break;
				case 108511772:
					if (!str.equals("right"))
						break;
					entity.solidArea.x += entity.speed;
					break;
				}
				if (entity

						.solidArea.intersects((this.gp.obj[this.gp.currentMap][i]).solidArea)) {
					if ((this.gp.obj[this.gp.currentMap][i]).collision)
						entity.collisionOn = true;
					if (player)
						index = i;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				(this.gp.obj[this.gp.currentMap][i]).solidArea.x = (this.gp.obj[this.gp.currentMap][i]).solidAreaDefaultX;
				(this.gp.obj[this.gp.currentMap][i]).solidArea.y = (this.gp.obj[this.gp.currentMap][i]).solidAreaDefaultY;
			}
		}
		return index;
	}

	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		String direction = entity.direction;
		if (entity.knockBack)
			direction = entity.knockBackDirection;
		for (int i = 0; i < (target[1]).length; i++) {
			if (target[this.gp.currentMap][i] != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				(target[this.gp.currentMap][i]).solidArea.x = (target[this.gp.currentMap][i]).worldX
						+ (target[this.gp.currentMap][i]).solidArea.x;
				(target[this.gp.currentMap][i]).solidArea.y = (target[this.gp.currentMap][i]).worldY
						+ (target[this.gp.currentMap][i]).solidArea.y;
				String str;
				switch ((str = direction).hashCode()) {
				case 3739:
					if (!str.equals("up"))
						break;
					entity.solidArea.y -= entity.speed;
					break;
				case 3089570:
					if (!str.equals("down"))
						break;
					entity.solidArea.y += entity.speed;
					break;
				case 3317767:
					if (!str.equals("left"))
						break;
					entity.solidArea.x -= entity.speed;
					break;
				case 108511772:
					if (!str.equals("right"))
						break;
					entity.solidArea.x += entity.speed;
					break;
				}
				if (entity

						.solidArea.intersects((target[this.gp.currentMap][i]).solidArea)
						&& target[this.gp.currentMap][i] != entity) {
					entity.collisionOn = true;
					index = i;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				(target[this.gp.currentMap][i]).solidArea.x = (target[this.gp.currentMap][i]).solidAreaDefaultX;
				(target[this.gp.currentMap][i]).solidArea.y = (target[this.gp.currentMap][i]).solidAreaDefaultY;
			}
		}
		return index;
	}

	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		this.gp.player.solidArea.x = this.gp.player.worldX + this.gp.player.solidArea.x;
		this.gp.player.solidArea.y = this.gp.player.worldY + this.gp.player.solidArea.y;
		String str;
		switch ((str = entity.direction).hashCode()) {
		case 3739:
			if (!str.equals("up"))
				break;
			entity.solidArea.y -= entity.speed;
			break;
		case 3089570:
			if (!str.equals("down"))
				break;
			entity.solidArea.y += entity.speed;
			break;
		case 3317767:
			if (!str.equals("left"))
				break;
			entity.solidArea.x -= entity.speed;
			break;
		case 108511772:
			if (!str.equals("right"))
				break;
			entity.solidArea.x += entity.speed;
			break;
		}
		if (entity

				.solidArea.intersects(this.gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
		this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
		return contactPlayer;
	}
}
