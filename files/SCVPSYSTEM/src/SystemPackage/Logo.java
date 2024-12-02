package SystemPackage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class Logo extends JFrame {
    private int zoomLevel = 100;
    private int maxWidth = 800;
    private int maxHeight = 600;
    private JLabel logo;
    private Timer fadeTimer;
    private float transparency = 0.0f;
    private boolean fadeIn = true;

    public Logo() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        getContentPane().setLayout(null);

        URL logopath = Project.class.getResource("/logo.png");
        ImageIcon originalIcon = new ImageIcon(logopath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(maxWidth * zoomLevel / 230, maxHeight * zoomLevel / 188, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        logo = new JLabel(resizedIcon);
        logo.setBounds(10, 90, 1000, 300);
        getContentPane().add(logo);
        
        Timer disposeTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
        	    try {
        		      // Set Nimbus look and feel
        		      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        		      // Set Nimbus theme to "Nimbus Light"
        		      UIManager.put("nimbusBlueGrey", new java.awt.Color(200, 200, 200));
        		      UIManager.put("control", new java.awt.Color(230, 230, 230));
        		    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
        		      UnsupportedLookAndFeelException ex) {
        		      ex.printStackTrace();
        		    }

        		    EventQueue.invokeLater(new Runnable() {
        		      public void run() {
        		        try {
        		          POS frame = new POS();
        		          frame.setUndecorated(true);
        		          frame.setVisible(true);
        		          frame.setSize(985, 510);
        		          frame.setLocationRelativeTo(null);
        		          frame.setResizable(false);
        		        } catch (Exception e) {
        		          e.printStackTrace();
        		        }
        		      }
        		    });
            }
        });
        
        disposeTimer.setRepeats(false); 
        
        disposeTimer.start();
        
        fadeTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
                if (fadeIn) {
                    transparency += 0.01f;
                    if (transparency >= 1.0f) {
                        transparency = 1.0f;
                        fadeIn = false;
                    }
                } else {
                    transparency -= 0.01f;
                    if (transparency <= 0.0f) {
                        transparency = 0.0f;
                        fadeIn = true;
                        
                    }
                }
                logo.setIcon(new ImageIcon(getTransparentImage(resizedImage, transparency)));
                
            }
        });
        fadeTimer.start();

        
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playSound("/start.wav");
    }

    private Image getTransparentImage(Image image, float transparency) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }

    private void playSound(String soundFile) {
    	URL musicpath = Project.class.getResource(soundFile);
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicpath);
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Logo frame = new Logo();
            frame.setVisible(true);
        });
    }
}
