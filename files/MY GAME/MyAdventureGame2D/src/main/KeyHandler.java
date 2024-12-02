package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {
	GamePanel gp;

	// MOVEMENTS
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;

	// OTHER KEYS
	public boolean enterPressed;
	public boolean shotKeyPressed;
	public boolean spacePressed;

	// DEBUG
	public boolean showDebugText = false;
	public boolean godModeOn = false;

	public ArrayList<String> inputs = new ArrayList<>();

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// KEYS TO EACH SELECTION
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (this.gp.gameState) {
		case 0:
			titleState(code);
			break;
		case 1:
			playState(code);
			break;
		case 2:
			pauseState(code);
			break;
		case 3:
		case 11:
			dialogueState(code);
			break;
		case 4:
			characterState(code);
			break;
		case 5:
			optionsState(code);
			break;
		case 6:
			gameOverState(code);
			break;
		case 8:
			tradeState(code);
			break;
		case 10:
			mapState(code);
			break;
		default:
			break;
		}
	}

	// MAIN MENU
	public void titleState(int code) {
		if (code == KeyEvent.VK_W) {
			this.gp.ui.commandNum--;
			if (this.gp.ui.commandNum < 0)
				this.gp.ui.commandNum = 3;
		}

		if (code == KeyEvent.VK_S) {
			this.gp.ui.commandNum++;
			if (this.gp.ui.commandNum > 3)
				this.gp.ui.commandNum = 0;
		}

		// MAINMENU SELECTION
		if (code == KeyEvent.VK_ENTER) {
			if (this.gp.ui.commandNum == 0) {
				this.gp.gameState = 11;
				this.gp.csManager.getClass();
				this.gp.csManager.sceneNum = 1;
			}

			if (this.gp.ui.commandNum == 1) {
				this.gp.saveLoad.load();
				
				this.gp.gameState = 1;
				this.gp.playMusic(0);
			}

			if (this.gp.ui.commandNum == 2) {
				// option 2
			}

			if (this.gp.ui.commandNum == 3) {
				System.exit(0);
			}
		}
	}

	public void playState(int code) {
		// MOVEMENT
		if (code == KeyEvent.VK_W && !this.upPressed) {
			this.upPressed = true;
			this.inputs.add(0, "up");
		}
		if (code == KeyEvent.VK_S && !this.downPressed) {
			this.downPressed = true;
			this.inputs.add(0, "down");
		}
		if (code == KeyEvent.VK_A && !this.leftPressed) {
			this.leftPressed = true;
			this.inputs.add(0, "left");
		}
		if (code == KeyEvent.VK_D && !this.rightPressed) {
			this.rightPressed = true;
			this.inputs.add(0, "right");
		}

		// PAUSE
		if (code == KeyEvent.VK_P) {
			this.gp.gameState = 2;
		}
		if (code == KeyEvent.VK_SHIFT) {
			gp.player.speed = 4;
		}
		if (code == KeyEvent.VK_C) {
			this.gp.gameState = 4;
		}
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
		if (code == KeyEvent.VK_F) {
			this.shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			this.gp.gameState = 5;
		}
		if (code == KeyEvent.VK_M) {
			this.gp.gameState = 10;
		}
		if (code == KeyEvent.VK_X) {
			if (!this.gp.map.miniMapOn) {
				this.gp.map.miniMapOn = true;
			} else {
				this.gp.map.miniMapOn = false;
			}
		}
		if (code == KeyEvent.VK_SPACE) {
			this.spacePressed = true;
		}
		if (code == KeyEvent.VK_T) {
			if (!this.showDebugText) {
				this.showDebugText = true;
			} else if (this.showDebugText) {
				this.showDebugText = false;
			}
		}
	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			this.gp.gameState = 1;
		}
	}

	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
	}

	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			
			this.gp.gameState = 1;
		}
		if (code == KeyEvent.VK_ENTER) {
			this.gp.player.selectItem();
		}
		playerInventory(code);
	}

	public void optionsState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			
			this.gp.gameState = 1;
		}
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
		int maxCommandNum = 0;
		switch (this.gp.ui.subState) {
		case 0:
			maxCommandNum = 5;
			break;
		case 3:
			maxCommandNum = 1;
			break;
		}
		if (code == KeyEvent.VK_W) {
			this.gp.ui.commandNum--;
			this.gp.playSE(9);
			if (this.gp.ui.commandNum < 0)
				this.gp.ui.commandNum = maxCommandNum;
		}
		if (code == KeyEvent.VK_S) {
			this.gp.ui.commandNum++;
			this.gp.playSE(9);
			if (this.gp.ui.commandNum > maxCommandNum)
				this.gp.ui.commandNum = 0;
		}
		if (code == KeyEvent.VK_A && this.gp.ui.subState == 0) {
			if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale > 0) {
				this.gp.music.volumeScale--;
				this.gp.music.checkVolume();
				this.gp.playSE(9);
			}
			if (this.gp.ui.commandNum == 2 && this.gp.se.volumeScale > 0) {
				this.gp.se.volumeScale--;
				this.gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_D && this.gp.ui.subState == 0) {
			if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale < 5) {
				this.gp.music.volumeScale++;
				this.gp.music.checkVolume();
				this.gp.playSE(9);
			}
			if (this.gp.ui.commandNum == 2 && this.gp.se.volumeScale < 5) {
				this.gp.se.volumeScale++;
				this.gp.playSE(9);
			}
		}
	}

	public void gameOverState(int code) {
		if (code == KeyEvent.VK_W) {
			this.gp.ui.commandNum--;
			if (this.gp.ui.commandNum < 0)
				this.gp.ui.commandNum = 1;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_S) {
			this.gp.ui.commandNum++;
			if (this.gp.ui.commandNum > 1)
				this.gp.ui.commandNum = 0;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_ENTER) {
			if (this.gp.ui.commandNum == 0) {
				this.gp.gameState = 1;
				this.gp.resetGame(false);
				this.gp.playMusic(0);
			} else if (this.gp.ui.commandNum == 1) {
				
				this.gp.gameState = 0;
				this.gp.resetGame(true);
			}
		}
	}

	public void tradeState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
		if (this.gp.ui.subState == 0) {
			if (code == KeyEvent.VK_W) {
				this.gp.ui.commandNum--;
				if (this.gp.ui.commandNum < 0)
					this.gp.ui.commandNum = 2;
				this.gp.playSE(9);
			}
			if (code == KeyEvent.VK_S) {
				this.gp.ui.commandNum++;
				if (this.gp.ui.commandNum > 2)
					this.gp.ui.commandNum = 0;
				this.gp.playSE(9);
			}
		}
		if (this.gp.ui.subState == 1) {
			npcInventory(code);
			if (code == KeyEvent.VK_ESCAPE)
				this.gp.ui.subState = 0;
		}
		if (this.gp.ui.subState == 2) {
			playerInventory(code);
			if (code == KeyEvent.VK_ESCAPE)
				this.gp.ui.subState = 0;
		}
	}

	public void mapState(int code) {
		if (code == KeyEvent.VK_M) {
			
			this.gp.gameState = 1;
		}
	}

	public void playerInventory(int code) {
		if (code == KeyEvent.VK_W && this.gp.ui.playerSlotRow != 0) {
			this.gp.ui.playerSlotRow--;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_A && this.gp.ui.playerSlotCol != 0) {
			this.gp.ui.playerSlotCol--;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_S && this.gp.ui.playerSlotRow != 3) {
			this.gp.ui.playerSlotRow++;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_D && this.gp.ui.playerSlotCol != 4) {
			this.gp.ui.playerSlotCol++;
			this.gp.playSE(9);
		}
	}

	public void npcInventory(int code) {
		if (code == KeyEvent.VK_W && this.gp.ui.npcSlotRow != 0) {
			this.gp.ui.npcSlotRow--;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_A && this.gp.ui.npcSlotCol != 0) {
			this.gp.ui.npcSlotCol--;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_S && this.gp.ui.npcSlotRow != 3) {
			this.gp.ui.npcSlotRow++;
			this.gp.playSE(9);
		}
		if (code == KeyEvent.VK_D && this.gp.ui.npcSlotCol != 4) {
			this.gp.ui.npcSlotCol++;
			this.gp.playSE(9);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			this.upPressed = false;
			this.inputs.remove("up");
		}
		if (code == KeyEvent.VK_S) {
			this.downPressed = false;
			this.inputs.remove("down");
		}
		if (code == KeyEvent.VK_SHIFT) {
			gp.player.speed = 3;
		}
		if (code == KeyEvent.VK_A) {
			this.leftPressed = false;
			this.inputs.remove("left");
		}
		if (code == KeyEvent.VK_D) {
			this.rightPressed = false;
			this.inputs.remove("right");
		}
		if (code == KeyEvent.VK_F)
			this.shotKeyPressed = false;
		if (code == KeyEvent.VK_ENTER)
			this.enterPressed = false;
		if (code == KeyEvent.VK_SPACE)
			this.spacePressed = false;
	}
}