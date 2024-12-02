package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;

	public Config(GamePanel gp) {
		this.gp = gp;
	}

	// method to write configuration data
	public void saveConfiguration() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			if (this.gp.fullScreenOn)
				bw.write("Full Screen: On");
			if (!this.gp.fullScreenOn)
				bw.write("Full Screen: Off");
			bw.newLine();
			bw.write(String.valueOf(this.gp.music.volumeScale));
			bw.newLine();
			bw.write(String.valueOf(this.gp.se.volumeScale));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// method to load configuration data
	public void loadConfiguration() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			String s = br.readLine();
			if (s.equals("Full Screen: On"))
				this.gp.fullScreenOn = true;
			if (s.equals("Full Screen: Off"))
				this.gp.fullScreenOn = false;
			s = br.readLine();
			this.gp.music.volumeScale = Integer.parseInt(s);
			s = br.readLine();
			this.gp.se.volumeScale = Integer.parseInt(s);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
