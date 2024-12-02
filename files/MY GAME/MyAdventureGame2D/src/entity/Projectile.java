package entity;

import main.GamePanel;

public class Projectile extends Entity {
	Entity user;

	public Projectile(GamePanel gp) {
		super(gp);
	}

	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	@Override
	public void update() {
		if (this.user == this.gp.player) {
			int monsterIndex = this.gp.cChecker.checkEntity(this, this.gp.monster);
			if (monsterIndex != 999) {
				this.gp.player.damageMonster(monsterIndex, this, this.attack + this.gp.player.level,
						this.knockBackPower);
				generateParticle(this.user.projectile, this.gp.monster[this.gp.currentMap][monsterIndex]);
				this.alive = false;
			}
		}
		if (this.user != this.gp.player) {
			boolean contactPlayer = this.gp.cChecker.checkPlayer(this);
			if (!this.gp.player.invincible && contactPlayer) {
				damagePlayer(this.attack);
				generateParticle(this.user.projectile, this.user.projectile);
				this.alive = false;
			}
		}
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
		this

				.life = this.life - 1;
		if (this.life <= 0)
			this.alive = false;
		this.spriteCounter++;
		if (this.spriteCounter > 12) {
			if (this.spriteNum == 1) {
				this.spriteNum = 2;
			} else if (this.spriteNum == 2) {
				this.spriteNum = 1;
			}
			this.spriteCounter = 0;
		}
	}

	public boolean haveResource(Entity user) {
		return false;
	}

	public void subtractResource(Entity user) {
	}
}
