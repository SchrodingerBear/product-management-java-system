package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	public long clipPosition;
	
	public Sound() {
		
		try {
            // Initialize the clip object here
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
		
		soundURL[0] = getClass().getResource("/sound/MainMenuMusic.wav");
		soundURL[1] = getClass().getResource("/sound/BackgroundMusic.wav");
		
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
		
	}
}

// TommyMutiu (BG MS)
// GuilhermeBernardes (MM MS)