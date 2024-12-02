package SystemPackage;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Processing extends JFrame {

  private JProgressBar progressBar;
  private Timer timer;
  private int value;

  public Processing() {
    super("Installation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 100);
    setUndecorated(true); 
    
    progressBar = new JProgressBar();
    progressBar.setBackground(new Color(0, 0, 0));  
    progressBar.setForeground(new Color(255, 0, 0));
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setStringPainted(true);

    getContentPane().add(progressBar);

    setVisible(true);
    setLocationRelativeTo(null);

    int duration = 500; 
    int increment = 100 / (duration / 50); // Increment value for each update
    value = 0; // Current progress value

    timer = new Timer(50, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (value <= 100) {
          progressBar.setValue(value);
          value += increment;
        } else {
        	
          timer.stop();
          dispose();
          
        }
      }
    });
    timer.start();
    
  }

  public static void main(String[] args) {
	  
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Processing();
      }
    });
  }
}
