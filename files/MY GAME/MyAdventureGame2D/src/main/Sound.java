package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;

	URL[] soundURL = new URL[30];

	FloatControl fc;

	int volumeScale = 3;

	float volume;

	public Sound() {
		this.soundURL[0] = getClass().getResource("/sound/BackgroundMusic.wav");
		this.soundURL[1] = getClass().getResource("/sound/coin.wav");
		this.soundURL[2] = getClass().getResource("/sound/powerup.wav");
		this.soundURL[3] = getClass().getResource("/sound/unlock.wav");
		this.soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		this.soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		this.soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		this.soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
		this.soundURL[8] = getClass().getResource("/sound/levelup.wav");
		this.soundURL[9] = getClass().getResource("/sound/cursor.wav");
		this.soundURL[10] = getClass().getResource("/sound/burning.wav");
		this.soundURL[11] = getClass().getResource("/sound/cuttree.wav");
		this.soundURL[12] = getClass().getResource("/sound/gameover.wav");
		this.soundURL[13] = getClass().getResource("/sound/stairs.wav");
		this.soundURL[14] = getClass().getResource("/sound/sleep.wav");
		this.soundURL[15] = getClass().getResource("/sound/blocked.wav");
		this.soundURL[16] = getClass().getResource("/sound/parry.wav");
		this.soundURL[17] = getClass().getResource("/sound/speak.wav");
		this.soundURL[18] = getClass().getResource("/sound/Merchant.wav");
		this.soundURL[19] = getClass().getResource("/sound/Dungeon.wav");
		this.soundURL[20] = getClass().getResource("/sound/chipwall.wav");
		this.soundURL[21] = getClass().getResource("/sound/dooropen.wav");
		this.soundURL[22] = getClass().getResource("/sound/FinalBattle8bit.wav");
		this.soundURL[23] = getClass().getResource("/sound/MainMenuMusic.wav");

	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundURL[i]);
			this.clip = AudioSystem.getClip();
			this.clip.open(ais);
			this.fc = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		} catch (Exception exception) {
		}
	}

	public void play() {
		this.clip.start();
	}

	public void loop() {
		this.clip.loop(-1);
	}

	public void stop() {
		this.clip.stop();
	}

	public void checkVolume() {
		switch (this.volumeScale) {
		case 0:
			this.volume = -80.0F;
			break;
		case 1:
			this.volume = -20.0F;
			break;
		case 2:
			this.volume = -12.0F;
			break;
		case 3:
			this.volume = -5.0F;
			break;
		case 4:
			this.volume = 1.0F;
			break;
		case 5:
			this.volume = 6.0F;
			break;
		}
		this.fc.setValue(this.volume);
	}
}
