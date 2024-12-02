package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, lefPressed, rightPressed, interaction;
	
	public boolean debug = false;
	public boolean debugtile = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// MAINMENU 
		if (gp.gameState == gp.titleState) {
			// MAIN SCREEN
			if (gp.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
				
				if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}
				
				if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
					// NEW GAME
					if (gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = gp.ui.newWorldScreenState;
					}
					
					// LOAD GAME
					if (gp.ui.commandNum == 1) {
						gp.music.stop();
						gp.playMusic(1);
						gp.gameState = gp.playState;
					}
					
					// OPTIONS
					if (gp.ui.commandNum == 2) {
					}
					
					// QUIT
					if (gp.ui.commandNum == 3) {
						System.exit(0);
					}
				}
				
				// NEW GAME
			} else if (gp.ui.titleScreenState == gp.ui.newWorldScreenState) {
				if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				
				if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}
				
				if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
					if (gp.ui.commandNum == 0) {
						
					}
					
					if (gp.ui.commandNum == 1) {
					}
					
					if (gp.ui.commandNum == 2) {
						gp.ui.titleScreenState = 0;
						gp.ui.commandNum = 0;
					}
				} 
			}
		}
		
		// IN GAME
		if (gp.gameState == gp.playState) {
			// MOVEMENT
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				lefPressed = true;
			}
			
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			
			// PAUSE
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.pauseState;
			}
			
			// INTERACTION
			if (code == KeyEvent.VK_E) {
				interaction = true;
			}
			
			// DEBUG
			if (code == KeyEvent.VK_T) {
				if (debugtile == false && debug == false) {
					debug = true;
				} else if (debugtile == false && debug == true) {
					debugtile = true;
					debug = false;
				} else if (debugtile == true && debug == false) {
					debugtile = false;
				}
			}	
		} else if (code == KeyEvent.VK_ESCAPE && gp.gameState == gp.pauseState) {
			gp.gameState = gp.playState;
		}
		

		if (gp.gameState == gp.dialogState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
		}
		
		if (gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.pausecommandNum--;
				if (gp.ui.pausecommandNum < 0) {
					gp.ui.pausecommandNum = 2;
				}
			}

			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.pausecommandNum++;
				if (gp.ui.pausecommandNum > 2) {
					gp.ui.pausecommandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
				if (gp.ui.pausecommandNum == 0) {
					gp.gameState = gp.playState;
				}
				
				if (gp.ui.pausecommandNum == 1) {
					
				}
				
				if (gp.ui.pausecommandNum == 2) {
					gp.gameState = gp.ui.titleScreenState;
					gp.ui.commandNum = 0;
					gp.ui.pausecommandNum = 0;
				}
			} 
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			lefPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
