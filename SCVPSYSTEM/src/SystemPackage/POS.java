package SystemPackage;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.text.DecimalFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;	
import static SystemPackage.Login.u;
import java.awt.Font;

public class POS extends JFrame {

	  private JTable table;
	  private JPanel contentPane;
	  private JLabel dateTimeLabel;
	  private JTable ctable;
	  private JTable itable;
	  private JTextField search;
	  private JTextField total;
	  private JTextField payment;
	  private JTextField change;
	  private DecimalFormat decimalFormat;
	  private JTextField customername;
	  private JScrollPane item;
	  private JTextArea receipt;
	  
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
	
	    public POS() {
	    	customername = new JTextField();
	    	total = new JTextField();
	    	change = new JTextField();
	    	payment = new JTextField();
	    	decimalFormat = new DecimalFormat("0.00");
	        setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 1000, 550);
	        contentPane = new JPanel();
	        contentPane.setBackground(new Color(0, 0, 0));
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	
	        item = new JScrollPane();
	        item.setBounds(6, 46, 465, 200);
	        contentPane.add(item);
	
	        DefaultTableModel model = new DefaultTableModel() {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };

	        model.addColumn("Product");
	        model.addColumn("Price");

	        List<Map<String, Object>> results = database.select("items", "name, price", null);

	        for (Map<String, Object> row : results) {
	            String product = (String) row.get("name");
	            int price = (int) row.get("price");
	            model.addRow(new Object[]{product, price});
	        }


	        JTable itable = new JTable(model);
	        itable.setFont(new Font("Calibri Light", Font.PLAIN, 13));
	        item.setViewportView(itable);
	        
	        JScrollPane cart = new JScrollPane();
	        cart.setBounds(6, 254, 465, 215);
	        contentPane.add(cart);
	
	        ctable = new JTable();
	        ctable.setFont(new Font("Onyx", Font.PLAIN, 15));
	        cart.setViewportView(ctable);
	
	        search = new JTextField();
	        search.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String searchText = search.getText();

	                try (BufferedReader br = new BufferedReader(new FileReader("database/items.txt"))) {
	                    String line;
	                    boolean found = false;

	                    while ((line = br.readLine()) != null) {
	                        if (line.toLowerCase().contains(searchText.toLowerCase())) {
	                            found = true;
	                            break;
	                        }
	                    }

	                    if (found) {
	                        String[] itemData = line.split(",");
	                        String itemName = itemData[0].trim();
	                        String itemPrice = itemData[1].trim();

	                        // Construct the message
	                        String message = "Item: " + itemName + "\n" +
	                                         "Price: " + itemPrice;

	                        int option = JOptionPane.showOptionDialog(
	                            null,
	                            message,
	                            "Item Details",
	                            JOptionPane.DEFAULT_OPTION,
	                            JOptionPane.INFORMATION_MESSAGE,
	                            null,
	                            new Object[] {"To Cart", "Close"},
	                            null
	                        );

	                        if (option == 0) {
	                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("database/cart.txt", true))) {
	                                bw.write(itemName + "," + itemPrice);
	                                bw.newLine();
	                            } catch (IOException ex) {
	                                ex.printStackTrace();
	                            }
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No match found!", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	                cart();
	            }
	        });
	        
	        search.addFocusListener(new FocusAdapter() {
	        	@Override
	        	public void focusGained(FocusEvent e) {
	        		if (search.getText().equalsIgnoreCase("Search...")) {
						search.setText("");
					}
	        	}
	        	@Override
	        	public void focusLost(FocusEvent e) {
	        		if (search.getText().isBlank()) {
	        			search.setText("Search...");
					}
	        	}
	        });
	        
	        search.setText("Search...");
	        search.setBounds(6, 6, 318, 37);
	        contentPane.add(search);
	        search.setColumns(10);
	        
	        JScrollPane rsp = new JScrollPane();
	        rsp.setBounds(484, 39, 347, 430);
	        contentPane.add(rsp);
	
	        receipt = new JTextArea();
	        receipt.setFont(new Font("Monospaced", Font.PLAIN, 15));
	        rsp.setViewportView(receipt);
	        receipt.setEditable(false);
	        displayReceipt(receipt);
	       
	        JButton pay = new JButton("PAYMENT");
	        pay.setForeground(new Color(255, 255, 255));
	        pay.setBackground(new Color(27, 94, 50));
	        

	        
	        pay.setBounds(843, 307, 135, 46);
	        contentPane.add(pay);
	
	        JButton clear = new JButton("CLEAR");
	        
	        clear.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		payment.setText("0.00");
	        		change.setText("0.00");
	        		total.setText("0.00");
	        		
	    	        File file = new File("database/cart.txt");
	    	        
	    	        try {
	    	            if (file.exists()) {
	    	                file.delete();
	    	                file.createNewFile();
	    	            } else {
	    	            }
	    	        } catch (IOException clearerror) {
	    	            System.out.println("An error occurred: " + clearerror.getMessage());
	    	        }
	    	        
	    	        cart();
	    	        displayReceipt(receipt);
	    	        
	        	}
	        });
	        
	        clear.setBounds(336, 6, 135, 37);
	        contentPane.add(clear);
	
	        JButton btnRemove = new JButton("REMOVE");
	        btnRemove.setForeground(new Color(255, 255, 255));
	        btnRemove.setBackground(new Color(181, 20, 27));
	        btnRemove.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	    	        int selectedRow = ctable.getSelectedRow();
	        		if (selectedRow < 0) {
	        		    JOptionPane.showMessageDialog(null, "No row selected.", "Cart", JOptionPane.WARNING_MESSAGE);
	        		    return;
	        		}
	    	        if (selectedRow >= 0) {
	    	            DefaultTableModel model = (DefaultTableModel) ctable.getModel();
	    	            String productName = model.getValueAt(selectedRow, 0).toString();
	    	            String productPrice = model.getValueAt(selectedRow, 1).toString();
	    	            removeItemFromCart(productName, productPrice);
	    	            model.removeRow(selectedRow);
	    	        }
	    	        displayReceipt(receipt);
	    	        
	    	    }
	    	});
	        	   
	        
	        pay.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	    try {
	        	        double changeValue = Double.parseDouble(change.getText());
	        	        
	        	        if (changeValue < 0) {
	        	        	JOptionPane.showMessageDialog(null, "Negative change value!", "Cart", JOptionPane.WARNING_MESSAGE);
	        	        } else if (total.getText().equals("0.00")) {
	        	        	JOptionPane.showMessageDialog(null, "No items in the cart!", "Cart", JOptionPane.WARNING_MESSAGE);
	        	        } else {
	        	            SwingUtilities.invokeLater(new Runnable() {
	        	                public void run() {
	        	                  new Processing();
	        	                }
	        	              });
	        	        	try {
	        	        		try {
	        	                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        	                    SwingUtilities.updateComponentTreeUI(POS.this);
	        	                } catch (ClassNotFoundException | InstantiationException |
	        	                        IllegalAccessException | UnsupportedLookAndFeelException ex) {
	        	                    ex.printStackTrace();
	        	                }

	    	                    // Get the current date and format it
	    	                    Date currentDate = new Date();
	    	                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMdyyyy");
	    	                    String currentDateString = dateFormat.format(currentDate);
	    	                    
	    	                    // Check if the file with the current date already exists
	    	                    boolean fileExists = false;
	    	                    File currentFile = new File("database/transaction/" + currentDateString + ".txt");
	    	                    if (currentFile.exists()) {
	    	                        fileExists = true;
	    	                    }

	    	                    // Open the file in append mode or create a new file
	    	                    FileWriter writer = new FileWriter(currentFile, true);

	    	                    // Read all the values from the "database/receipt.txt" file
	    	                    BufferedReader reader = new BufferedReader(new FileReader("database/receipt.txt"));
	    	                    String line;
	    	                    StringBuilder values = new StringBuilder();
	    	                    while ((line = reader.readLine()) != null) {
	    	                        values.append(line);
	    	                        values.append(System.lineSeparator());
	    	                    }
	    	                    reader.close();

	    	                    // Write the values to the current date file
	    	                    if (fileExists) {
	    	                    	writer.write("\n");
	    	                        writer.append(values.toString());
	    	                    } else {
	    	                    	writer.write("\n");
	    	                        writer.write(values.toString());
	    	                    }

	    	                    // Close the file
	    	                    writer.close();

	    	                    Timer timer = new Timer(1000, timeerrror -> {
	    	                        try {
	    	                            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    	                            SwingUtilities.updateComponentTreeUI(POS.this);
	    	                        } catch (ClassNotFoundException | InstantiationException |
	    	                                IllegalAccessException | UnsupportedLookAndFeelException ex) {
	    	                            ex.printStackTrace();
	    	                        }
	    	                        JOptionPane.showMessageDialog(null, "Receipt saved successfully!");
	    	                        clear.doClick(); // Assuming you have a button named 'clear'
	    	                    });
	    	                    
	    	                    timer.setRepeats(false);
	    	                    timer.start();
	    	                } catch (IOException ex) {
	    	                    JOptionPane.showMessageDialog(null, "Error: Unable to write the receipt to a file!");
	    	                }
	        	        }
	        	    } catch (NumberFormatException ex) {
	        	        JOptionPane.showMessageDialog(null, "Error: Invalid change value!");
	        	    }
	        	    
	        	}
	        });
	        
	        btnRemove.setBounds(843, 423, 135, 46);
	        contentPane.add(btnRemove);
	
	        total = new JTextField();
	        total.setBackground(new Color(255, 255, 255));
	        total.setHorizontalAlignment(SwingConstants.CENTER);
	        total.setEnabled(false);
	        total.setEditable(false);
	        total.setBounds(843, 187, 135, 28);
	        contentPane.add(total);
	        total.setColumns(10);
	
	        payment = new JTextField();
	        payment.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		displayReceipt(receipt);
	        	}
	        });
	        payment.setText("0.00");
	        payment.addFocusListener(new FocusAdapter() {
	        	@Override
	        	public void focusGained(FocusEvent e) {
	        		if (payment.getText().equalsIgnoreCase("0.00")) {
	        			payment.setText("");
					}
	        	}
	        	@Override
	        	public void focusLost(FocusEvent e) {
	        		if (payment.getText().isEmpty()) {
	        			payment.setText("0.00");
					}
	        	}
	        });
	        
	        payment.setHorizontalAlignment(SwingConstants.CENTER);
	        payment.setColumns(10);
	        payment.setBounds(843, 227, 135, 28);
	        contentPane.add(payment);
	
	        change = new JTextField();
	        change.setText("0.00");
	        change.setBackground(new Color(255, 255, 255));
	        change.setHorizontalAlignment(SwingConstants.CENTER);
	        change.setEnabled(false);
	        change.setEditable(false);
	        change.setColumns(10);
	        change.setBounds(843, 267, 135, 28);
	        contentPane.add(change);
	
	        JLabel status = new JLabel("");
	        status.setFont(new Font("Baskerville Old Face", Font.ITALIC, 15));
	        status.setForeground(new Color(255, 255, 255));
	        status.setHorizontalAlignment(SwingConstants.CENTER);
	        status.setBounds(843, 12, 135, 16);
	        contentPane.add(status);
	        

	        
	        
	        JLabel logo = new JLabel("");
	        
	        URL logopath = Project.class.getResource("/logo.png");
	        
	        ImageIcon icon = new ImageIcon(logopath);
	
	        // Resize the image to the desired width and height
	        Image image = icon.getImage().getScaledInstance(135, 136, Image.SCALE_SMOOTH);
	        logo.setIcon(new ImageIcon(image));
	
	        logo.setHorizontalAlignment(SwingConstants.RIGHT);
	        logo.setBounds(843, 39, 135, 136);
	
	        contentPane.add(logo);
	        
	        JButton quantity = new JButton("QUANTITY");
	        quantity.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		int selectedRow = ctable.getSelectedRow();
	        		if (selectedRow < 0) {
	        		    JOptionPane.showMessageDialog(null, "No row selected.", "Cart", JOptionPane.WARNING_MESSAGE);
	        		    return;
	        		}
	        		
	                if (selectedRow >= 0) {
	                    DefaultTableModel model = (DefaultTableModel) ctable.getModel();
	                    String productName = model.getValueAt(selectedRow, 0).toString();
	                    String productPrice = model.getValueAt(selectedRow, 1).toString();
	                    
	                    // Ask for input titled "Enter Quantity"
	                    String input = JOptionPane.showInputDialog(null, "Enter Quantity", "Quantity", JOptionPane.PLAIN_MESSAGE);
	                    if (input == null || input.trim().isEmpty()) {
	                        return; 
	                    } else {
	                        int quantity = Integer.parseInt(input);
	                        if (quantity == 0 || quantity < 0) {
	                        	JOptionPane.showMessageDialog(null, "Invalid quantity. Cannot be less than or 0", "Error", JOptionPane.ERROR_MESSAGE);
	                            return;
	                        }
	                    }
	                    
	                    int quantity = Integer.parseInt(input);
	                    if (quantity < 0) {
	                        return; // Return if the input is negative
	                    }
	                    updateQuantityAndAmount(selectedRow, quantity);
	                    displayReceipt(receipt);
	                    
	                }
	            }
	        });
	        
	        quantity.setBounds(843, 365, 135, 46);
	        contentPane.add(quantity);
	
	
	        // Create a JMenuBar
	        JMenuBar menu = new JMenuBar();
	        menu.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
	        menu.setBackground(new Color(192, 192, 192));
	        setJMenuBar(menu);
	
	        // Create a JPanel to hold the time label
	        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        timePanel.setForeground(new Color(64, 0, 128));
	        timePanel.setBackground(new Color(255, 255, 255));
	        menu.add(timePanel);
	
	        dateTimeLabel = new JLabel();
	        dateTimeLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
	        timePanel.add(dateTimeLabel);
	        
	        JLabel titlelabel = new JLabel("POS PANEL V1.2");
	        titlelabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
	        timePanel.add(titlelabel);
	        titlelabel.setHorizontalAlignment(SwingConstants.LEFT);
	
	        JMenu manageAccount = new JMenu("Account |");
	        manageAccount.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
	        JMenu transactions = new JMenu("Transactions |");
	        transactions.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
	        JMenu inventory = new JMenu("Inventory |");
	        inventory.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
	        menu.add(transactions);
	        menu.add(inventory);
	        menu.add(manageAccount);
	        
	        JMenuItem password = new JMenuItem("Change Password");
	        manageAccount.add(password);

	        JMenuItem register = new JMenuItem("Register User");
	        manageAccount.add(register);

	        password.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
			        String username = JOptionPane.showInputDialog(null, "Enter username:");
			        
			        if (username == null) {
			            return;
			        } else if (username.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
					}
			        
			        String password = JOptionPane.showInputDialog(null, "Enter username:");
			        if (password == null) {
			            return;
			        } else if (password.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Password field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
					}

			        List<String> accounts = new ArrayList<>();
			        try (BufferedReader br = new BufferedReader(new FileReader("database/accounts.txt"))) {
			            String line;
			            while ((line = br.readLine()) != null) {
			                accounts.add(line);
			            }
			        } catch (IOException cerror) {
			        	cerror.printStackTrace();
			            return;
			        }

			        boolean usernameFound = false;
			        for (int i = 0; i < accounts.size(); i++) {
			            String[] values = accounts.get(i).split(",");
			            if (values.length >= 2 && values[0].equals(username)) {
			                accounts.set(i, username + "," + password);
			                usernameFound = true;
			                break;
			            }
			        }

			        if (usernameFound) {
			            try (BufferedWriter bw = new BufferedWriter(new FileWriter("database/accounts.txt"))) {
			                for (String account : accounts) {
			                    bw.write(account);
			                    bw.newLine();
			                }
			                JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			            } catch (IOException changeerror) {
			            	changeerror.printStackTrace();
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Username not found.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
	        
	        register.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
			        String username = JOptionPane.showInputDialog(null, "Enter username:");
			        

			        // Check if username is empty or null
			        if (username == null) {
			            return;
			        } else if (username.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
					}
			        
			        String password = JOptionPane.showInputDialog(null, "Enter password:");
			        if (password == null) {
			            return;
			        } else if (password.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Password field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
					}


			        // Write username and password to accounts.txt
			        try (BufferedWriter bw = new BufferedWriter(new FileWriter("database/accounts.txt", true))) {
			            bw.write("\n"+ username + "," + password);
			            bw.newLine();
			            JOptionPane.showMessageDialog(null, "Account registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			        } catch (IOException rgerror) {
			        	rgerror.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Failed to register account.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
	        
	        JMenuItem logs = new JMenuItem("Login History");
	        manageAccount.add(logs);
	

	        logs.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
			        String filePath = "database/loginhistory.txt";

			        File file = new File(filePath);

			        // Check if the file exists and is a file (not a directory)
			        if (file.exists() && file.isFile()) {
			            try {
			                Desktop desktop = Desktop.getDesktop();
			                desktop.edit(file); // Open the file in the default text editor
			            } catch (IOException therror) {
			            	therror.printStackTrace();
			            }
			        } else {
			            System.out.println("File not found: " + filePath);
			        }
				}
			});
	        
	        JMenuItem fulltransaction = new JMenuItem("Show Transactions");
	        transactions.add(fulltransaction);
	
	        JMenuItem transaction = new JMenuItem("Today's Transactions");
	        transactions.add(transaction);
	
	        JMenuItem remove = new JMenuItem("Remove Items");
	        inventory.add(remove);
	        
	        transaction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 String fileName = new SimpleDateFormat("MMMMddyyyy").format(new Date()) + ".txt";
				        File file = new File("database/transaction/" + fileName);
				        if (file.exists()) {
				            try {
				                Desktop.getDesktop().open(file);
				            } catch (IOException ex) {
				                ex.printStackTrace();
				            }
				        } else {
				        }
				}
			});
	        
	        fulltransaction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String folderPath = "database/transaction";
			        File folder = new File(folderPath);
			        
			        if (Desktop.isDesktopSupported()) {
			            Desktop desktop = Desktop.getDesktop();
			            
			            try {
			                desktop.open(folder);
			            } catch (IOException ex) {
			                ex.printStackTrace();
			            }
			        } else {
			        }
				}
			});

	        remove.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    List<Map<String, Object>> result = database.select("items", "name", null);
	                    List<String> productList = new ArrayList<>();

	                    for (Map<String, Object> row : result) {
	                        productList.add((String) row.get("name"));
	                    }

	                    if (productList.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "No items available to remove.", "Product Editor", JOptionPane.INFORMATION_MESSAGE);
	                        return;
	                    }

	                    String[] productsArray = productList.toArray(new String[0]);
	                    JComboBox<String> productComboBox = new JComboBox<>(productsArray);

	                    int resultDialog = JOptionPane.showConfirmDialog(null, productComboBox, "Select Product to Remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	                    if (resultDialog == JOptionPane.OK_OPTION) {
	                        String selectedProduct = (String) productComboBox.getSelectedItem();
	                        if (selectedProduct != null && !selectedProduct.isEmpty()) {
	                        	database.delete("items", "name = '" + selectedProduct + "'");
	                            JOptionPane.showMessageDialog(null, "Item removed successfully!", "Product Editor", JOptionPane.INFORMATION_MESSAGE);
	                        }
	                    }
	                } catch (Exception removerror) {
	                    JOptionPane.showMessageDialog(null, "An error occurred while removing the item", "Product Editor", JOptionPane.ERROR_MESSAGE);
	                    removerror.printStackTrace();
	                }
	            }
	        });

	        JMenuItem add = new JMenuItem("Add Items");
	        inventory.add(add);
	
	        add.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String productName = JOptionPane.showInputDialog(null, "Enter product name:", "Product Editor",
	                        JOptionPane.PLAIN_MESSAGE);
	                boolean isItemExists = false;

	                try {
	                    BufferedReader reader = new BufferedReader(new FileReader("database/items.txt"));
	                    String line;
	                    while ((line = reader.readLine()) != null) {
	                        String[] parts = line.split(", ");
	                        if (parts.length >= 1 && parts[0].equals(productName)) {
	                            reader.close();
	                            isItemExists = true;
	                            break;  // Exit the loop since item is found
	                        }
	                    }
	                    reader.close();
	                } catch (IOException existerror) {
	                    existerror.printStackTrace();
	                }

	                if (isItemExists) {
	                    JOptionPane.showMessageDialog(null, "Item already exists.", "Product Editor", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }
	                
	                if (productName == null) {
	                    return;
	                } else if (productName.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Field Empty", "Remove Item", JOptionPane.WARNING_MESSAGE);
	                    return;
	                }


	                if (productName != null && !productName.isEmpty()) {
	                    // Display a JOptionPane asking for the price
	                    String price = JOptionPane.showInputDialog(null, "Enter price:", "Product Editor",
	                            JOptionPane.PLAIN_MESSAGE);

	                    if (price == null) {
	                        return;
	                    } else if (price.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "Field Empty", "Remove Item", JOptionPane.WARNING_MESSAGE);
	                        return;
	                    }

	                    try {
	                        int parsedPrice = Integer.parseInt(price);
	                    } catch (NumberFormatException parserrror) {
	                        JOptionPane.showMessageDialog(null, "Cannot be parsed", "Invalid Price", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
		                
	                    if (price != null && !price.isEmpty()) {
	                        try {
	                            // Write the new item and price to the items.txt file
	                            BufferedWriter writer = new BufferedWriter(new FileWriter("database/items.txt", true));
	                            writer.write(productName + ", " + price + "\n");
	                            writer.close();

	                            JOptionPane.showMessageDialog(null, "Item added successfully!", "Product Editor",
	                                    JOptionPane.INFORMATION_MESSAGE);
	                        } catch (IOException adderror) {
	                            JOptionPane.showMessageDialog(null, "An error occurred while adding the item.", "Product Editor",
	                                    JOptionPane.ERROR_MESSAGE);
	                            adderror.printStackTrace();
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Invalid price.", "Product Editor", JOptionPane.WARNING_MESSAGE);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Invalid product name.", "Product Editor", JOptionPane.WARNING_MESSAGE);
	                }
	                readItems();
	            }
	        });
	        
	        JMenuItem Edit = new JMenuItem("Edit Items");
	        inventory.add(Edit);
	
	        JMenuItem logout = new JMenuItem("Logout");
	        manageAccount.add(logout);
	        
	        Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String productName = JOptionPane.showInputDialog(null, "Enter product name:", "Product Editor",
			                JOptionPane.PLAIN_MESSAGE);

			        if (productName == null ) {
			        	return;
					}
			        else if (productName.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Field Empty", "Remove Item", JOptionPane.WARNING_MESSAGE);
			        	return;
					}
			        
			        if (productName != null && !productName.isEmpty()) {
			            try {
			                BufferedReader reader = new BufferedReader(new FileReader("database/items.txt"));
			                StringBuilder itemsData = new StringBuilder();
			                String line;

			                while ((line = reader.readLine()) != null) {
			                    String[] item = line.split(", ");
			                    String value1 = item[0];
			                    String value2 = item[1];

			                    if (value1.equalsIgnoreCase(productName)) {
			                        String newPrice = JOptionPane.showInputDialog(null, "Enter new price:", "Product Editor",
			                                JOptionPane.PLAIN_MESSAGE);

					                if (newPrice == null) {
					                    return;
					                } else if (newPrice.isEmpty()) {
					                    JOptionPane.showMessageDialog(null, "Field Empty", "Remove Item", JOptionPane.WARNING_MESSAGE);
					                    return;
					                }
					                
			                        value2 = newPrice;
			                        line = value1 + ", " + value2;
			                    }

			                    itemsData.append(line).append("\n");
			                }

			                reader.close();

			                BufferedWriter writer = new BufferedWriter(new FileWriter("database/items.txt"));
			                writer.write(itemsData.toString());
			                writer.close();

			                JOptionPane.showMessageDialog(null, "Price updated successfully!", "Product Editor",
			                        JOptionPane.INFORMATION_MESSAGE);
			            } catch (IOException editerror) {
			                JOptionPane.showMessageDialog(null, "An error occurred while updating the price.", "Product Editor",
			                        JOptionPane.ERROR_MESSAGE);
			                editerror.printStackTrace();
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Invalid product name.", "Product Editor", JOptionPane.WARNING_MESSAGE);
			        }
			        readItems();
				}
			});
	
	        logout.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(() -> {
	                    Login frame = new Login();
	                    frame.setVisible(true);
	                });
	            	dispose();
	            }
	        });
	
	        javax.swing.Timer timer = new javax.swing.Timer(500, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                updateDateTime();
	                updatevalues();
	            }
	        });
	        timer.start();

	        
	        itable.addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                int row = itable.rowAtPoint(evt.getPoint());
	                int col = itable.columnAtPoint(evt.getPoint());
	                if (row >= 0 && col >= 0) {
	                    String productName = itable.getValueAt(row, 0).toString();
	                    String productPrice = itable.getValueAt(row, 1).toString();
	                    
	                    String cartEntry = productName + "," + productPrice + "\n";
	
	                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("database/cart.txt", true))) {
	                        writer.write(cartEntry);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                    cart();
	                    
	                    Timer timer = new Timer(500, timeerrror -> {
	                    	displayReceipt(receipt);
	                    });
	                    
	                    timer.setRepeats(false);
	                    timer.start();
	                }
	            }
	        });
	        
	        cart();
	        displayReceipt(receipt);
	        
	        customername = new JTextField();
	        customername.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	displayReceipt(receipt);
	        	}
	        });
	        customername.addFocusListener(new FocusAdapter() {
	        	@Override
	        	public void focusGained(FocusEvent e) {
	        		if (customername.getText().equalsIgnoreCase("CUSTOMER NAME") || customername.getText() == "CUSTOMER NAME") {
	        			customername.setText("");
					}
	        	}
	        	@Override
	        	public void focusLost(FocusEvent e) {
	        		if (customername.getText().isEmpty()) {
	        			customername.setText("CUSTOMER NAME");
					}
	        	}
	        });
	        
	        customername.setText("CUSTOMER NAME");
	        customername.setHorizontalAlignment(SwingConstants.CENTER);
	        customername.setBounds(484, 6, 347, 32);
	        contentPane.add(customername);
	        customername.setColumns(10);
	        clear.doClick();
	        
	        if (Login.u != null && Login.u.getText() != null && !Login.u.getText().isEmpty()) {
	            status.setText("Welcome " + Login.u.getText() + "!");
	            if(!Login.u.getText().equals("admin")) {
	    	        manageAccount.remove(register);
	    	        manageAccount.remove(password);
	    	        manageAccount.remove(logs);
	    	        menu.remove(transactions);
	    	        menu.remove(inventory);
	            }
	        } else {
	            status.setText("Welcome <user> !");
	        }
	    }
	    
	    public void displayReceipt(JTextArea receipt) {

            
	    	String customerName = customername.getText();
            String totalValue = total.getText();
            String paymentValue = payment.getText();
            String changeValue = change.getText();
            
	    	String[] title = {"CART:", "", "", ""};
	        DefaultTableModel model = (DefaultTableModel) ctable.getModel();
	        int rowCount = model.getRowCount();
	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database/receipt.txt"))) {
	            
	        	// Write company details
	            writer.write("BUSINESS INFORMATION:\n");
	            writer.write("Mighty Tea FoodHauz\n");
	            writer.write("Baguio City, PHISCI\n");
	            writer.write("0948 - 315 - 6716\n");
	            
	            // Write current date
	            LocalDate currentDate = LocalDate.now();
	            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
	            writer.write(formattedDate + "\n");
	            
	            // Write current time
	            LocalTime currentTime = LocalTime.now();
	            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
	            writer.write(formattedTime + "\n");
	            writer.write("\n");

	            // Write column names
	            for (String columnName : title) {
	                writer.write(columnName + "\t");
	            }
	            writer.newLine();
	            
	         // Write table data
	            for (int row = 0; row < rowCount; row++) {
	                writer.write("Itemname: " + model.getValueAt(row, 0) + "\t" + "\n");
	                writer.write("Price: " + model.getValueAt(row, 1) + "\t");
	                writer.write("Quantity: " + model.getValueAt(row, 2) + "\t");
	                writer.write("Amount: " + model.getValueAt(row, 3) + "\n");
	                writer.newLine();
	            }


	            writer.write("\n");
	            writer.write("TRANSACTION INFORMATION: \n");
	            writer.write("Customer Name: " + customerName + "\n");
	            writer.write("Total: " + totalValue + "\n");
	            writer.write("Payment: " + paymentValue + "\n");
	            writer.write("Change: " + changeValue + "\n");

	        } catch (IOException e) {
	            // Handle exception
	        }
	        
	        try (BufferedReader br = new BufferedReader(new FileReader("database/receipt.txt"))) {
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	                sb.append(System.lineSeparator());
	            }
	            receipt.setText(sb.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private void updatevalues() {
	    	double sum = 0.0;
	        for (int i = 0; i < ctable.getRowCount(); i++) {
	            double amount = Double.parseDouble(ctable.getValueAt(i, 3).toString());
	            sum += amount;
	        }

	        total.setText(decimalFormat.format(sum));

	        String paymentText = payment.getText();
	        if (!paymentText.isEmpty()) {
	            try {
	                double paymentAmount = Double.parseDouble(paymentText);
	                double changeAmount = paymentAmount - sum;

	                change.setText(decimalFormat.format(changeAmount));
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "Error: Value not parsable to double.", "Error", JOptionPane.ERROR_MESSAGE);
	                payment.setText("0.00");
	                return;
	            }
	        } 
	    }

	    
	    private void removeItemFromCart(String productName, String productPrice) {
	        String cartEntry = productName + "," + productPrice;
	        String tempFile = "database/cart_temp.txt";
	        File inputFile = new File("database/cart.txt");
	        File tempFileObj = new File(tempFile);
	
	        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
	             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFileObj))) {
	
	            String line;
	            while ((line = br.readLine()) != null) {
	                if (!line.trim().startsWith(cartEntry)) {
	                    bw.write(line);
	                    bw.newLine();
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
	        // Replace the original cart file with the modified temporary file
	        inputFile.delete();
	        tempFileObj.renameTo(inputFile);
	    }
	    
	    private void readItems() {
	        try (BufferedReader br = new BufferedReader(new FileReader("database/items.txt"))) {
	            DefaultTableModel model = new DefaultTableModel();
	            model.addColumn("Product");
	            model.addColumn("Price");

	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String product = parts[0].trim();
	                    String price = parts[1].trim();
	                    model.addRow(new Object[]{product, price});
	                }
	            }

	            itable = new JTable(model);

	            // Reattach the mouse click listener
	            itable.addMouseListener(new java.awt.event.MouseAdapter() {
	                @Override
	                public void mouseClicked(java.awt.event.MouseEvent evt) {
	                    int row = itable.rowAtPoint(evt.getPoint());
	                    int col = itable.columnAtPoint(evt.getPoint());
	                    if (row >= 0 && col >= 0) {
	                        String productName = itable.getValueAt(row, 0).toString();
	                        String productPrice = itable.getValueAt(row, 1).toString();
	                        
	                        String cartEntry = productName + "," + productPrice + "\n";

	                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database/cart.txt", true))) {
	                            writer.write(cartEntry);
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                        cart();
	                        
	                        Timer timer = new Timer(500, timeerrror -> {
	                            displayReceipt(receipt);
	                        });
	                        
	                        timer.setRepeats(false);
	                        timer.start();
	                    }
	                }
	            });

	            item.setViewportView(itable);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    private void cart() {
	        List<String[]> data = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader("database/cart.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] rowData = line.split(",");
	                String productName = rowData[0];
	                String productPrice = rowData[1];
	
	                // Check if the item already exists in the data list
	                boolean itemExists = false;
	                DecimalFormat decimalFormat = new DecimalFormat("#0.00"); // Create decimal format with 2 decimal places
	
	                for (String[] existingRow : data) {
	                    if (existingRow[0].equals(productName)) {
	                        int quantity = Integer.parseInt(existingRow[2]) + 1;
	                        double amount = Double.parseDouble(existingRow[1]) * quantity;
	                        existingRow[2] = String.valueOf(quantity);
	                        existingRow[3] = decimalFormat.format(amount);
	                        itemExists = true;
	                        break;
	                    }
	                }
	
	                if (!itemExists) {
	                    // Item doesn't exist in the data list, add it as a new row
	                    double price = Double.parseDouble(productPrice);
	                    String formattedProductPrice = decimalFormat.format(price);
	                    String formattedAmount = decimalFormat.format(price);
	                    String[] newRow = { productName, formattedProductPrice, "1", formattedAmount };
	                    data.add(newRow);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
	        String[] columnNames = {"Product Name", "Product Price", "Quantity", "Amount"};
	        DefaultTableModel model = new DefaultTableModel(data.toArray(new String[0][]), columnNames);
	        ctable.setModel(model);
	    }
	    
	    private int getQuantity(List<String[]> data, String productName) {
	        int count = 0;
	        for (String[] rowData : data) {
	            if (rowData[0].equals(productName)) {
	                count++;
	            }
	        }
	        return count;
	    }
	
	    private void updateDateTime() {
	        Date now = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy : HH:mm:ss:aa");
	        String formattedDateTime = format.format(now);
	        dateTimeLabel.setText(formattedDateTime);
	    }
	    
	    private void updateQuantityAndAmount(int row, int quantity) {
	        DefaultTableModel model = (DefaultTableModel) ctable.getModel();
	        String productName = model.getValueAt(row, 0).toString();
	        String productPrice = model.getValueAt(row, 1).toString();
	        double price = Double.parseDouble(productPrice);
	        double amount = quantity * price;
	
	        // Format the amount
	        String formattedAmount = decimalFormat.format(amount);
	
	        model.setValueAt(quantity, row, 2);
	        model.setValueAt(formattedAmount, row, 3);
	
	        // Remove matching lines from the file
	        removeMatchingLines(productName);
	
	        // Write to the file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database/cart.txt", true))) {
	            for (int i = 0; i < quantity; i++) {
	                writer.write(productName + "," + productPrice);
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }
	
	    private void removeMatchingLines(String productName) {
	        try {
	            File inputFile = new File("database/cart.txt");
	            File tempFile = new File("database/cart_temp.txt");
	
	            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (!line.startsWith(productName)) {
	                    writer.write(line);
	                    writer.newLine();
	                }
	            }
	
	            reader.close();
	            writer.close();
	
	            // Replace the original file with the modified temp file
	            if (inputFile.delete()) {
	                if (!tempFile.renameTo(inputFile)) {
	                    System.out.println("Error renaming file.");
	                }
	            } else {
	                System.out.println("Error deleting file.");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }
	}
