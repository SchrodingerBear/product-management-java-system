package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.GamePanel;

public class SaveLoad {
	GamePanel gp;

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}

	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			DataStorage ds = new DataStorage();
			ds.level = this.gp.player.level;
			ds.maxLife = this.gp.player.maxLife;
			ds.life = this.gp.player.life;
			ds.maxMana = this.gp.player.maxMana;
			ds.mana = this.gp.player.mana;
			ds.strength = this.gp.player.strength;
			ds.dexterity = this.gp.player.dexterity;
			ds.exp = this.gp.player.exp;
			ds.nextLevelExp = this.gp.player.nextLevelExp;
			ds.coin = this.gp.player.coin;
			for (int i = 0; i < this.gp.player.inventory.size(); i++) {
				ds.itemNames.add(this.gp.player.inventory.get(i).name);
				ds.itemAmounts.add(Integer.valueOf(this.gp.player.inventory.get(i).amount));
			}
			ds.currentWeaponSlot = this.gp.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = this.gp.player.getCurrentShieldSlot();
			
			ds.mapObjectNames = new String[10][(this.gp.obj[1]).length];
			
			ds.mapObjectWorldX = new int[10][(this.gp.obj[1]).length];
			
			ds.mapObjectWorldY = new int[10][(this.gp.obj[1]).length];
			
			ds.mapObjectLootNames = new String[10][(this.gp.obj[1]).length];
			
			ds.mapObjectOpened = new boolean[10][(this.gp.obj[1]).length];
			int mapNum = 0;
			
			for (; mapNum < 10; mapNum++) {
				for (int j = 0; j < (this.gp.obj[1]).length; j++) {
					if (this.gp.obj[mapNum][j] == null) {
						ds.mapObjectNames[mapNum][j] = "NA";
					} else {
						ds.mapObjectNames[mapNum][j] = (this.gp.obj[mapNum][j]).name;
						ds.mapObjectWorldX[mapNum][j] = (this.gp.obj[mapNum][j]).worldX;
						ds.mapObjectWorldY[mapNum][j] = (this.gp.obj[mapNum][j]).worldY;
						if ((this.gp.obj[mapNum][j]).loot != null)
							ds.mapObjectLootNames[mapNum][j] = (this.gp.obj[mapNum][j]).loot.name;
						ds.mapObjectOpened[mapNum][j] = (this.gp.obj[mapNum][j]).opened;
					}
				}
			}
			
			ds.iTileNames = new String[10][(this.gp.iTile[1]).length];
			
			ds.iTileWorldX = new int[10][(this.gp.iTile[1]).length];
			
			ds.iTileWorldY = new int[10][(this.gp.iTile[1]).length];
			mapNum = 0;
			
			for (; mapNum < 10; mapNum++) {
				for (int j = 0; j < (this.gp.iTile[1]).length; j++) {
					if (this.gp.iTile[mapNum][j] == null) {
						ds.iTileNames[mapNum][j] = "NA";
					} else {
						ds.iTileNames[mapNum][j] = (this.gp.iTile[mapNum][j]).name;
						ds.iTileWorldX[mapNum][j] = (this.gp.iTile[mapNum][j]).worldX;
						ds.iTileWorldY[mapNum][j] = (this.gp.iTile[mapNum][j]).worldY;
					}
				}
			}
			oos.writeObject(ds);
		} catch (Exception e) {
			System.out.println("Save Exception!");
		}
	}

	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			DataStorage ds = (DataStorage) ois.readObject();
			this.gp.player.level = ds.level;
			this.gp.player.maxLife = ds.maxLife;
			this.gp.player.life = ds.life;
			this.gp.player.maxMana = ds.maxMana;
			this.gp.player.mana = ds.mana;
			this.gp.player.strength = ds.strength;
			this.gp.player.dexterity = ds.dexterity;
			this.gp.player.exp = ds.exp;
			this.gp.player.nextLevelExp = ds.nextLevelExp;
			this.gp.player.coin = ds.coin;
			this.gp.player.inventory.clear();
			for (int i = 0; i < ds.itemNames.size(); i++) {
				this.gp.player.inventory.add(this.gp.eGenerator.getObject(ds.itemNames.get(i)));
				this.gp.player.inventory.get(i).amount = ds.itemAmounts.get(i).intValue();
			}
			this.gp.player.currentWeapon = this.gp.player.inventory.get(ds.currentWeaponSlot);
			this.gp.player.currentShield = this.gp.player.inventory.get(ds.currentShieldSlot);
			this.gp.player.getAttack();
			this.gp.player.getDefense();
			this.gp.player.getAttackImage();
			int mapNum = 0;
			
			for (; mapNum < 10; mapNum++) {
				for (int j = 0; j < (this.gp.obj[1]).length; j++) {
					if (ds.mapObjectNames[mapNum][j].equals("NA")) {
						this.gp.obj[mapNum][j] = null;
					} else {
						this.gp.obj[mapNum][j] = this.gp.eGenerator.getObject(ds.mapObjectNames[mapNum][j]);
						(this.gp.obj[mapNum][j]).worldX = ds.mapObjectWorldX[mapNum][j];
						(this.gp.obj[mapNum][j]).worldY = ds.mapObjectWorldY[mapNum][j];
						if (ds.mapObjectLootNames[mapNum][j] != null)
							this.gp.obj[mapNum][j]
									.setLoot(this.gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][j]));
						(this.gp.obj[mapNum][j]).opened = ds.mapObjectOpened[mapNum][j];
						if ((this.gp.obj[mapNum][j]).opened)
							(this.gp.obj[mapNum][j]).down1 = (this.gp.obj[mapNum][j]).image2;
					}
				}
			}
			System.out.println("before itile");
			mapNum = 0;
			
			for (; mapNum < 10; mapNum++) {
				for (int j = 0; j < (this.gp.iTile[1]).length; j++) {
					if (ds.iTileNames[mapNum][j].equals("NA")) {
						this.gp.iTile[mapNum][j] = null;
					} else {
						String name = ds.iTileNames[mapNum][j];
						int worldX = ds.iTileWorldX[mapNum][j];
						int worldY = ds.iTileWorldY[mapNum][j];
						this.gp.iTile[mapNum][j] = this.gp.eGenerator.getItile(name, worldX, worldY);
					}
				}
			}
			System.out.println("itile loading done");
		} catch (Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
