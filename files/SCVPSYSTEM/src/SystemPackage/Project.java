package SystemPackage;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.Color;

public class Project {

  public static void main(String[] args) {
    String currentDirectory = System.getProperty("user.dir");
    String databaseFolderPath = currentDirectory + "/database";

    File databaseFolder = new File(databaseFolderPath);
    if (!databaseFolder.exists()) {
      boolean folderCreated = databaseFolder.mkdirs();
      JFrame frame = new JFrame("");
      frame.setUndecorated(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 100);

      JProgressBar progressBar = new JProgressBar();
      progressBar.setBackground(new Color(255, 255, 255));
      progressBar.setForeground(new Color(0, 255, 0));
      progressBar.setMinimum(0);
      progressBar.setMaximum(100);
      progressBar.setStringPainted(true);

      frame.getContentPane().add(progressBar);

      frame.setVisible(true);
      frame.setLocationRelativeTo(null);

      int duration = 1000; 
      int increment = 100 / (duration / 50); // Increment value for each update
      int value = 0; // Current progress value

      while (value <= 100) {
        progressBar.setValue(value);
        value += increment;

        try {
          Thread.sleep(50); // Delay for visualization purposes
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
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
	    
      JOptionPane.showMessageDialog(frame, "Installation Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
      frame.dispose();
      login();
      if (folderCreated) {
    	  
      } else {
        return;
      }
    } else {}

    // Create the transaction folder
    String transactionFolderPath = databaseFolderPath + "/transaction";
    File transactionFolder = new File(transactionFolderPath);
    if (!transactionFolder.exists()) {
      boolean folderCreated = transactionFolder.mkdirs();
      if (folderCreated) {} else {
        return;
      }
    } else {}

    // Create items.txt file
    String itemsFilePath = databaseFolderPath + "/items.txt";
    File itemsFile = new File(itemsFilePath);
    try {
      boolean fileCreated = itemsFile.createNewFile();
      if (fileCreated) {} else {}
    } catch (IOException e) {
      System.out.println("An error occurred while creating items.txt file: " + e.getMessage());
    }
    
    // Create loginhistory.txt file
    String loginhistoryFilePath = databaseFolderPath + "/loginhistory.txt";
    File loginhistoryFile = new File(loginhistoryFilePath);
    try {
      boolean fileCreated = loginhistoryFile.createNewFile();
      if (fileCreated) {} else {}
    } catch (IOException e) {
      System.out.println("An error occurred while creating items.txt file: " + e.getMessage());
    }

    // Create accounts.txt file
    String accountsFilePath = databaseFolderPath + "/accounts.txt";
    File accountsFile = new File(accountsFilePath);
    try {
      boolean fileCreated = accountsFile.createNewFile();
      if (fileCreated) {} else {}
    } catch (IOException e) {
      System.out.println("An error occurred while creating accounts.txt file: " + e.getMessage());
    }
    
    // Create cart.txt file
    String cartFilePath = databaseFolderPath + "/cart.txt";
    File cartFile = new File(cartFilePath);
    try {
      boolean fileCreated = cartFile.createNewFile();
      if (fileCreated) {} else {}
    } catch (IOException e) {
      System.out.println("An error occurred while creating accounts.txt file: " + e.getMessage());
    }
    
    // Create receipt.txt file
    String receiptFilePath = databaseFolderPath + "/receipt.txt";
    File receiptFile = new File(receiptFilePath);
    try {
      boolean fileCreated = receiptFile.createNewFile();
      if (fileCreated) {
    	  
      } else {
    	  login();
      }
    } catch (IOException e) {
      System.out.println("An error occurred while creating accounts.txt file: " + e.getMessage());
    }
  }
  
  private static void login() {
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
	    
      SwingUtilities.invokeLater(() -> {
          Login frame = new Login();
          frame.setVisible(true);
      });
  }
}