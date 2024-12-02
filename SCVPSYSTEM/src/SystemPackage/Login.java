package SystemPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Login extends JFrame {
	// declaration of variables
    private int zoomLevel = 100;  // Initial zoom level
    private int maxWidth = 800;   // Maximum width of the displayed image
    private int maxHeight = 600;  // Maximum height of the displayed image
    private JLabel gui; // the displayed timer
    private JPasswordField p;
    public static JTextField u;

    public Login() {
        // Set frame properties
        setUndecorated(true);  // Removes the window decorations (title bar, etc.)
        setBackground(new Color(0, 0, 0, 0));  // Set transparent background
        getContentPane().setLayout(null);
        
        URL imagePath = Project.class.getResource("/login.png");

        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(maxWidth * zoomLevel / 200, maxHeight * zoomLevel / 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        JButton login = new JButton("LOGIN");
        login.setBackground(new Color(27, 94, 50));
        login.setForeground(new Color(255, 255, 255));
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = u.getText();
                String password = new String(p.getPassword());

                if (username == "admin" && password == "admin" || username.equals("admin") && password.equals("admin")) {
                    try {
                        writeLoginHistory(username, password);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                	dispose();
                    SwingUtilities.invokeLater(() -> {
                        Logo frame = new Logo();
                        frame.setVisible(true);
                    });
				} else if (username.equals("....")) {
                    JOptionPane.showMessageDialog(null, "Username Field Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (password.equals("....")) {
                	JOptionPane.showMessageDialog(null, "Password Field Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				} else if (!accountExists(username, password)) {
                	JOptionPane.showMessageDialog(null, "Account doesn't exist.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                	return;
				} else if (checkCredentials(username, password)) {
                    try {
                        writeLoginHistory(username, password);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        Logo frame = new Logo();
                        frame.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        login.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	login.doClick();
                }
            }
        });
        


        login.setFont(new Font("Century", Font.PLAIN, 15));
        login.setBounds(99, 569, 302, 45);
        getContentPane().add(login);
        
        JButton close = new JButton("CLOSE");
        close.setBackground(new Color(181, 20, 27));
        close.setForeground(new Color(255, 255, 255));
        close.setFont(new Font("Century", Font.PLAIN, 15));
        close.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	dispose();
        	}
        });
        close.setBounds(99, 626, 302, 45);
        getContentPane().add(close);
        
        p = new JPasswordField();
        p.setFont(new Font("SansSerif", Font.PLAIN, 20));
        p.setText("....");
        p.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        	    String password = new String(p.getPassword());
        	    if (password.isEmpty() || password.isBlank()) {
        	        p.setText("....");
        	    }
        	}
        	@Override
        	public void focusGained(FocusEvent e) {
        	    String password = new String(p.getPassword());
        	    if (!password.isEmpty() || !password.isBlank()) {
        	        p.setText("");
        	    }
        	}
        });
        
        p.setHorizontalAlignment(SwingConstants.CENTER);
        p.setBounds(99, 493, 302, 45);
        getContentPane().add(p);
        
        u = new JTextField();
        u.setFont(new Font("SansSerif", Font.PLAIN, 20));
        u.setText("....");
        u.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (u.getText().equals("....")) {
					u.setText("");
				}
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (u.getText().isEmpty() || u.getText() == null || u.getText().isBlank()) {
					u.setText("....");
				}
        	}
        });
        
        u.setHorizontalAlignment(SwingConstants.CENTER);
        u.setColumns(10);
        u.setBounds(99, 408, 302, 45);
        getContentPane().add(u);
        
        p.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	login.doClick();
                }
            }
        });
        
        u.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	login.doClick();
                }
            }
        });
        
        JLabel logo = new JLabel("");
        
        URL logopath = Project.class.getResource("/logo.png");
        
        ImageIcon icon = new ImageIcon(logopath);

        // Resize the image to the desired width and height
        Image image = icon.getImage().getScaledInstance(220, 221, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(image));

        logo.setHorizontalAlignment(SwingConstants.RIGHT);
        logo.setBounds(132, 142, 236, 203);
        getContentPane().add(logo);
        
        JLabel ul = new JLabel("USERNAME");
        ul.setFont(new Font("Century", Font.PLAIN, 15));
        ul.setForeground(Color.WHITE);
        ul.setBackground(Color.WHITE);
        ul.setBounds(99, 380, 151, 16);
        getContentPane().add(ul);
        
        JLabel pl = new JLabel("PASSWORD");
        pl.setFont(new Font("Century", Font.PLAIN, 15));
        pl.setForeground(Color.WHITE);
        pl.setBackground(Color.WHITE);
        pl.setBounds(99, 465, 151, 16);
        getContentPane().add(pl);
        
                gui = new JLabel(resizedIcon);
                
                gui.setBounds(10, 11, 480, 778); 
                
                        getContentPane().add(gui);
        
        // Set the size of the frame
        setSize(500, 800);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private boolean checkCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("database/accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2 && values[0].equals(username) && values[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void writeLoginHistory(String username, String password) throws IOException {
        String loginHistory = "database/loginhistory.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(loginHistory, true))) {
            bw.write("Username: " + username);
            bw.newLine();
            bw.write("Password: " + password);
            bw.newLine();
            bw.write("Date and Time: " + currentDateTime);
            bw.newLine();
            bw.newLine(); // Add an empty line to separate entries
        } catch (IOException e) {
            throw e;
        }
    }
    
    private boolean accountExists(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("database/accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2 && values[0].equals(username) && values[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
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