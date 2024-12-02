package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.PlayerDummy;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

public class CutsceneManager {
	GamePanel gp;

	Graphics2D g2;

	public int sceneNum;

	public int scenePhase;

	int counter = 0;

	float alpha = 0.0F;

	int y;

	String endCredit;

	public final int NA = 0;

	public final int opening = 1;

	public final int skeletonLord = 2;

	public final int ending = 3;

	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
		setEndCredit();
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (this.sceneNum) {
		case 1:
			scene_opening();
			break;
		case 2:
			scene_skeletonLord();
			break;
		case 3:
			scene_ending();
			break;
		}
	}

	public void scene_opening() {
		int pressEnterY = 520;
		if (this.scenePhase == 0) {
			drawBlackBackground(1.0F);
			this.alpha += 0.005F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			String text = "In an Unknown island, you stand.\n\nAs Valorblade, your name etches a mark of adventure.\nA shipwreck graces the nearby, its tale untold...\nYet memories evade you, lost within the ship's hold.\n\n";

			drawString(this.alpha, 35.0F, 170, text, 40);
			drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
			if (this.gp.keyH.enterPressed) {
				this.gp.keyH.enterPressed = false;
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 1) {
			drawBlackBackground(1.0F);
			this.alpha -= 0.02F;
			if (this.alpha < 0.0F) {
				this.alpha = 0.0F;
				this.scenePhase++;
			}
			String text = "In an Unknown island, you stand.\n\nAs Valorblade, your name etches a mark of adventure.\nA shipwreck graces the nearby, its tale untold...\nYet memories evade you, lost within the ship's hold.\n\n";
			drawString(this.alpha, 35.0F, 170, text, 40);
			drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
		}

		if (this.scenePhase == 2) {
			drawBlackBackground(1.0F);
			this.alpha += 0.01F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			String text = "A question lingers: what should I do?\nHow can I go back Home?\n\n\n\n";
			drawString(this.alpha, 35.0F, 200, text, 40);
			drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
			if (this.gp.keyH.enterPressed) {
				this.gp.keyH.enterPressed = false;
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 3) {
			drawBlackBackground(1.0F);
			this.alpha -= 0.02F;
			if (this.alpha < 0.0F) {
				this.alpha = 0.0F;
				this.scenePhase++;
			}
			String text = "A question lingers: what should I do?\nHow can I go back Home?\n\n\n\n";
			drawString(this.alpha, 35.0F, 200, text, 40);
			drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
		}
		if (this.scenePhase == 4) {
			drawBlackBackground(1.0F);
			this.alpha += 0.005F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			drawString(this.alpha, 35.0F, 50, "<How to Play>", 40);
			String text = "Move: [W/A/S/D]\nAttack/Interact/Confirm: [ENTER]\nMagic: [F]\nGuard/Parry: [SPACE]\nInventory/Status: [C]\nMap: [M]   Mini Map: [X]\nPause: [P]\nOptions: [ESC]\n\n";
			drawString(this.alpha, 35.0F, 120, text, 45);
			drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to start the adventure)", 40);
			if (this.gp.keyH.enterPressed) {
				this.gp.keyH.enterPressed = false;
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 5) {
			this.gp.keyH.enterPressed = false;
			this.sceneNum = 0;
			this.scenePhase = 0;
			
			this.gp.gameState = 1;
		}
	}

	public void scene_skeletonLord() {
		if (this.scenePhase == 0) {
			this.gp.bossBattleOn = true;
			int i;
			for (i = 0; i < (this.gp.obj[1]).length; i++) {
				if (this.gp.obj[this.gp.currentMap][i] == null) {
					this.gp.obj[this.gp.currentMap][i] = new OBJ_Door_Iron(this.gp);
					
					(this.gp.obj[this.gp.currentMap][i]).worldX = 48 * 25;
					
					(this.gp.obj[this.gp.currentMap][i]).worldY = 48 * 28;
					(this.gp.obj[this.gp.currentMap][i]).temp = true;
					this.gp.playSE(21);
					break;
				}
			}
			for (i = 0; i < (this.gp.npc[1]).length; i++) {
				if (this.gp.npc[this.gp.currentMap][i] == null) {
					this.gp.npc[this.gp.currentMap][i] = new PlayerDummy(this.gp);
					(this.gp.npc[this.gp.currentMap][i]).worldX = this.gp.player.worldX;
					(this.gp.npc[this.gp.currentMap][i]).worldY = this.gp.player.worldY;
					(this.gp.npc[this.gp.currentMap][i]).direction = this.gp.player.direction;
					break;
				}
			}
			this.gp.player.drawing = false;
			this.scenePhase++;
		}
		if (this.scenePhase == 1) {
			this.gp.player.worldY -= 2;
			
			if (this.gp.player.worldY < 48 * 16)
				this.scenePhase++;
		}
		if (this.scenePhase == 2)
			for (int i = 0; i < (this.gp.monster[1]).length; i++) {
				if (this.gp.monster[this.gp.currentMap][i] != null
						&& (this.gp.monster[this.gp.currentMap][i]).name == "Skeleton Lord") {
					(this.gp.monster[this.gp.currentMap][i]).sleep = false;
					this.gp.ui.npc = this.gp.monster[this.gp.currentMap][i];
					this.scenePhase++;
					break;
				}
			}
		if (this.scenePhase == 3)
			this.gp.ui.drawDialogueScreen();
		if (this.scenePhase == 4) {
			for (int i = 0; i < (this.gp.npc[1]).length; i++) {
				if (this.gp.npc[this.gp.currentMap][i] != null
						&& (this.gp.npc[this.gp.currentMap][i]).name.equals("Dummy")) {
					this.gp.player.worldX = (this.gp.npc[this.gp.currentMap][i]).worldX;
					this.gp.player.worldY = (this.gp.npc[this.gp.currentMap][i]).worldY;
					this.gp.npc[this.gp.currentMap][i] = null;
					break;
				}
			}
			this.gp.player.drawing = true;
			this.sceneNum = 0;
			this.scenePhase = 0;
			
			this.gp.gameState = 1;
			this.gp.stopMusic();
			this.gp.playMusic(22);
		}
	}

	public void scene_ending() {
		if (this.scenePhase == 0) {
			this.gp.stopMusic();
			this.gp.ui.npc = new OBJ_BlueHeart(this.gp);
			this.scenePhase++;
		}
		if (this.scenePhase == 1)
			this.gp.ui.drawDialogueScreen();
		if (this.scenePhase == 2) {
			this.gp.playSE(4);
			this.scenePhase++;
		}
		if (this.scenePhase == 3)
			if (counterReached(300))
				this.scenePhase++;
		if (this.scenePhase == 4) {
			this.alpha += 0.005F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			drawBlackBackground(this.alpha);
			if (this.alpha == 1.0F) {
				this.alpha = 0.0F;
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 5) {
			drawBlackBackground(1.0F);
			this.alpha += 0.005F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			String text = "After the fierce battle with the Skeleton Lord,\nthe Blue Boy finally found the legendary treasure.\nBut this is not the end of his journey.\nThe Blue Boy's adventure has just begun.";
			drawString(this.alpha, 38.0F, 200, text, 70);
			if (counterReached(600)) {
				this.gp.playMusic(0);
				this.scenePhase++;
				this.alpha = 0.0F;
			}
		}
		if (this.scenePhase == 6) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 120.0F, 576 / 2 + 10, "Blue Boy Adventure", 40);
			if (counterReached(480))
				this.scenePhase++;
		}
		if (this.scenePhase == 7) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2 - 40, "Program/Music/Pixel Art\n\nRyiSnow", 40);
			if (counterReached(400))
				this.scenePhase++;
		}
		if (this.scenePhase == 8) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2 - 40, "Early Demo Tester\n\nWatabou\nHenry Wilcox", 40);
			if (counterReached(400))
				this.scenePhase++;
		}
		if (this.scenePhase == 9) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2 - 40,
					"Special Thanks\n\nEveryone who has accompanied me on this development\nthrough my YouTube channel.\nThis could have never been done without their support.",

					40);
			if (counterReached(500))
				this.scenePhase++;
		}
		if (this.scenePhase == 10) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2 - 0,
					"This started as just a sample material \nfor my Java 2D game tutorial video series.\n", 40);
			if (counterReached(360))
				this.scenePhase++;
		}
		if (this.scenePhase == 11) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2 - 0,
					"However, thanks to my viewers' suggestions and requests,\nit became a bit more like a (not fully but) full-fledged game.",
					40);
			if (counterReached(400))
				this.scenePhase++;
		}
		if (this.scenePhase == 12) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 38.0F, 576 / 2, "I hope you enjoyed this little game.\n", 40);
			if (counterReached(300))
				this.scenePhase++;
		}
		if (this.scenePhase == 13) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 60.0F, 576 / 2, "The End", 40);
			
			drawString(1.0F, 38.0F, 576 / 2 + 100, "Thank you for playing!", 40);
			if (counterReached(250))
				this.scenePhase++;
		}
		if (this.scenePhase == 14) {
			drawBlackBackground(1.0F);
			
			drawString(1.0F, 60.0F, 576 / 2, "The End", 40);
			
			drawString(1.0F, 38.0F, 576 / 2 + 100, "Thank you for playing!", 40);
			this.alpha += 0.005F;
			if (this.alpha > 1.0F)
				this.alpha = 1.0F;
			drawString(this.alpha, 38.0F, 520, "(Press Enter to return to the title screen)", 40);
			if (this.gp.keyH.enterPressed) {
				this.gp.keyH.enterPressed = false;
				this.sceneNum = 0;
				this.scenePhase = 0;
				this.alpha = 0.0F;
				this.gp.stopMusic();
				
				this.gp.gameState = 0;
				this.gp.resetGame(true);
			}
		}
	}

	public boolean counterReached(int target) {
		boolean counterReached = false;
		this.counter++;
		if (this.counter > target) {
			counterReached = true;
			this.counter = 0;
		}
		return counterReached;
	}

	public void drawBlackBackground(float alpha) {
		this.g2.setComposite(AlphaComposite.getInstance(3, alpha));
		this.g2.setColor(Color.black);
		
		
		this.g2.fillRect(0, 0, 960, 576);
		this.g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
	}

	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		this.g2.setComposite(AlphaComposite.getInstance(3, alpha));
		this.g2.setColor(Color.white);
		this.g2.setFont(this.g2.getFont().deriveFont(fontSize));
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = text.split("\n")).length, b = 0; b < i;) {
			String line = arrayOfString[b];
			int x = this.gp.ui.centerTextX(line);
			this.g2.drawString(line, x, y);
			y += lineHeight;
			b++;
		}
		this.g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
	}

	public void setEndCredit() {
		this.endCredit = "Program/Music/Art\nRyiSnow\n\n\n\n\n\n\n\n\n\n\n\n\nEarly Demo Tester\nWatabou\nHenry Wilcox\n\n\n\n\n\n\n\n\n\n\n\nRussian Subtitles for the Videos\nFilipp Vakhitov\n\nSwedish Subtitles for the Videos\nBandicam Nick\n\n\n\n\n\n\n\n\n\n\n\nThose who have accompanied me on this journey\n\n001archimedes\n108mmbb\nA J\nAarushi Giri  \nAC DC\nAdam Faciszewski\nAdders Otter\nAdil Bouzit\nadonis zgks\nAG Zainy\nAG\nAiden Walmer \nAlejandro Silva\nAlexander Perales\nalfred Foo\nAli Alden\nAlice006\nAltynay Kuvandykova\nAndreas\nAngela Zhou\nAnonymous\nAnsari Afzal\nApollo  \nAr! / Penyuin\nAratak Arkosh\nArle Nadja\nArris Longington\nArt E Botastic  \nArtin\nArturs Melnikovs\naruka Li\nAsica\nAspekt\nAstralius\natanki\nAuthor Daniel Kuhnley\nAwari Riawa\nbacem abidi  \nBarKnock\nBenjSz\nberkayw\nBimantara Tito\nbluestreak711\nBlueTheMCGUY\nBREN.N.Z. Gaming\nBrendan Hannum\nBryce Merritt\nBMinh\nCaleb Baughman\nCaleb Thoburn\ncandiwandi\nCarol Esteves\nCasanova\ncasual e\nCattix\nCAdisson\nCHuynh\nCelio Ishikawa\nChang Ge\nChrister Holm\nChristopher Paquin\nCloneY\nCODERZ\nCovalentCola\nCrackRokMcCaib\nCRIPz 1\nD1 Legends  \nDakota Marquez\nDambREW\nDaniel the Traveler\nDark Times\ndarth sidious\nDaruo Su\nDasha.edits11\nDattebasa\nDave Codes\ndavy swanson\nDawnlightDGArle\nDeep Sea Trip\nDenis Gorbunov\ndevoiddude\ndezik\nDor Yahav\nDouglas Sparks\nDr Stowaters\nDraven\nDude Very Friendly \nDum Dum\ndumdum dumdum\nDTuan\ne u g e n i a\nEdgar P\nEdward\nEL Gopniko\nElias Mansouri\nEliteirizz\nElvis Pontes\nemmanuel omach\nErroTheCube\nFadhil Plays Gamez\nFarmer\nFelipe Guimar\nFernando Rodriguez\nFeroov\nforte zeroke\nFunky Chicken\nG Y O Z A\nGabriela Petrova\nGage Olson\nGame_Gadget\nGameyTaste\nGanbarimasu!\nGeorge D\nGeorge Haddad\nGhostek\nGiancarlo Rodriguez  \nGiorgio Ripani\nGnarkson\nGreatvast\nGreennoob\nGregory Fir  \nGustav Henriksson\nHamoodiHajjiri\nhanno98564\nHarleyJoker\nHarllem Alves\nHarry Jesshop\nHatdog\nHawkAle\nHeisei\nHis Excellency\nHuu Thinh\nHuy Tran Quang\nHwhdhs Eghshd\nIl Padrino \niloveihop07 \nIpa Stor\nIsik Oezer\nIvan Rako\nJacob D\nJam Ford\nJames Conwell\nJames Schraffl\nJames\nJarrod McEvoy  \nJay codes\nJayMa Kay\nJeremy Griffin\nJoan Mosquera\nJoVitor Dutra Garcia\njohannesoehlenschlaeger\nJohn Bahraman\nJohn Boyle \nJOHN LESTER BASILAD\nJohn Winters\nJonathan Maynard\njordi  \nJoshD\nJulian Hemmer\nJustin Hart\nJustin Lee\nJyffers  \nJZSNooB\nKaarin Gaming\nKami Sama\nKamil Game\nkandiman9111\nKanhchana Ly\nKat McNeill\nKaylor\nKayVee\nkeep scrolling\nKegan F\nKejeszan Kejeszan\nkevin colgan\nkezzek\nkhai ho\nKid\nKieran928\nKit WU  \nKlanehZ\nKoga Shinto\nKostas Avramidis\nLamekh Gt \nLancai\nLaurenz Fitzner\nLeigh Hobson\nLeo Ono\nLeo\nLeon Kennedy\nLeonardo da Silva Fanck\nlichen link\nLikeThisEmptyComment\nLocras\nlofi hamster\nLoggog\nLordoftheLair\nLouShunt\nLuan Freitas\nLuca Tagliavini\nLucas Almeida\nLuigi\nLuis Solis\nManuel Lopez\nmarkus will\nMarrok Young\nMaster TheNoobKing\nMaster\nmasterkiller471\nMatei Roca\nMatt Listor\nMax Chew\nMax MacRosefield\nMaxfrischdev\nmeena chinmay\nMelted Ice \nMetinCloup\nMichaB\nMichael K\nMichael Saunders\nMichaJurkiewicz\nMidgetDaddy 24\nMidi-Piano  \nMihir Malladi \nMo Magi\nMohamed Human\nMOISES SANTOS\nMoonlight Macky\nMoonPaper T  \nMr. Loser\nMrMerkus\nmygunnerisguns\nNakano Miku\nNani\nNasser GH\nNaze\nNgoc Vinh\nNick D'Esposito\nNick\nNico Rajala\nNienawidzoncy\nNik Kovach\nNikita Bomba\nNikita Buria\nNino\nniqita\nNoe\nNoemi Chulo\nNoureddine Baalbaki\nnyx\nO M\nOcin\nOkay\nOleksandr\nFaruk Dan\nOperated Owl\nOrange Mango\noriooon\nOver Gamez\npaaulsens\nPanagiotis Kougioumtzidis\nPASINDU CHARUKA\nPaul O\nPaul\nPedro Henrique  \nPele\nPennsworth\nPeter McManus\nPhilip J. Fry\nPoe Cat\nPolina Logash  \npolyblank\npro GrAMER666\nProject game\npulkstenis1\nPurple Convict\nqpdbwvwdbqp\nQuarkzy HN\nRaghunath S\nRamej\nReborn\nReid C.\nRikari\nrobin\nRomaWar\nRonron\nRostyslav Levder\nS vs J Faceoff\nSake\nSascha Polaschek\nSavkorlev\nseck magal\nSJacquet\nShane Perry\nShresth Pratap\nSibin Shifflett\nSilviu Zlota\nsnarp king guy\nsome cuber\nSonu Pandey\nSorcerian_Bootleg\nSosuke & Yuto\nSrizan Buddathoki\nStanislav Semenets\nSteve Rogers\nStreet Fashion TV\nSu4amp\nSukkarakan Haitang\nSupergato\nSwan Brothers\nsydhkl\nT y\nTaari Barung'si\nTarek Gordon\nTarnum\nTeam60WG\nTeodora Irina\nTerence Chia\nTerminallyUnique95\nTermite173\nThang Duong\nThe Kikis\nTheCountofAstora\nTheGoldenApple\nTheSrGalletas\nTien Hung Nguyen\nTomas Marta\nTomatosoup17\nToze\nTran Minh Quang\nTrikki Nikki  \nUnderArea51\nUnified Videos\nUtm \nvalcaron\nvampsz\nVeeju\nVini Tran\nVIP Member\nViro\nVlad R.\nVRF \nWarren Baines\nwheel group user\nwhitebear\nWill C\nWinston\nwither_scout\nWonder Zhou\nWoodsGeorgeJr\nwoodswoody\nWren Wolf\nWyrm Gard\nxioned\nxthepersonx\nYaUhYeah\nYorippe & Anko\nYuvtajvir Singh\nZack Nelson\nZoggi\n\n\n\n\n\n\n\n\n\n\n... and more!\n\n\n\n\n\n\n\n\n\n\nThank you for watching\nand have fun developing!\n";
	}
}
