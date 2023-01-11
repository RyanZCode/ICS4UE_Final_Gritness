// Import classes
import java.io.IOException;
import java.io.FileWriter;             
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

/** [RegisterMenu.java]
  * Desc: The page that displays when registering an account
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class RegisterMenu extends JPanel implements ActionListener {
    // Variables
    private JLabel title, text, email, address,fullName, password;
    private ImageIcon logo, background;
    private JTextField emailField, addressField, fullNameField;
    private JPasswordField passwordField;
    private JButton register;
    
    /**
     * RegisterMenu
     * This constructor creates a new RegisterMenu object.
     */
    public RegisterMenu() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        
        title = new JLabel("Get started!");
        text = new JLabel("Please enter your account details.");
        email = new JLabel("Email:");
        address = new JLabel("Address:");
        fullName = new JLabel("Full Name:");
        password = new JLabel("Password:");
        
        logo = new ImageIcon("images/logo.png");
        background = new ImageIcon("images/background.png");
        
        emailField = new JTextField();
        addressField = new JTextField();
        fullNameField = new JTextField();
        
        passwordField = new JPasswordField();
        
        register = new JButton("Register");
        
        // Set boundaries
        title.setBounds(Utility.MIDDLE_X + 135, Utility.MIDDLE_Y-160, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        text.setBounds(Utility.MIDDLE_X+70, Utility.MIDDLE_Y-120, 500, 30);
        text.setFont(new Font("Assistant", Font.PLAIN, 16));
        
        email.setBounds(Utility.MIDDLE_X + 65, Utility.MIDDLE_Y-70, 200, 30);
        address.setBounds(Utility.MIDDLE_X + 65, Utility.MIDDLE_Y-20, 200, 30);
        fullName.setBounds(Utility.MIDDLE_X + 65, Utility.MIDDLE_Y+30, 200, 30);
        password.setBounds(Utility.MIDDLE_X + 65, Utility.MIDDLE_Y+80, 200, 30);
        
        emailField.setBounds(Utility.MIDDLE_X + 180, Utility.MIDDLE_Y-70, 150, 40);
        addressField.setBounds(Utility.MIDDLE_X + 180, Utility.MIDDLE_Y-20, 150, 40);
        fullNameField.setBounds(Utility.MIDDLE_X + 180, Utility.MIDDLE_Y+30, 150, 40);
        passwordField.setBounds(Utility.MIDDLE_X + 180, Utility.MIDDLE_Y+80, 150, 40);
        
        register.setBounds(Utility.MIDDLE_X+50, Utility.MIDDLE_Y+140, 300, 35);
        register.setFont(new Font("Assistant", Font.BOLD, 12));
        register.setBorderPainted(false);
        register.setOpaque(true);
        register.setBackground(Utility.BACKGROUND);
        
        // Add action listeners
        register.addActionListener(this);
        
        // Add all of the components
        this.add(title);
        this.add(text);
        this.add(email);
        this.add(address);
        this.add(fullName);
        this.add(password);
        this.add(emailField);
        this.add(addressField);
        this.add(fullNameField);
        this.add(passwordField);
        this.add(register);
        
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the register button is clicked, save the information and change to the next screen
        if (e.getSource() == register) {
            String emailText;
            String addressText;
            String fullNameText;
            String passwordText;
            
            emailText = emailField.getText();
            addressText = addressField.getText();
            fullNameText = fullNameField.getText();
            passwordText = new String (passwordField.getPassword());
            
            // Check that all of the fields have information in them
            if (!emailText.equalsIgnoreCase("") && !addressText.equalsIgnoreCase("") 
                    && !fullNameText.equalsIgnoreCase("") && !passwordText.equalsIgnoreCase("")) {
                createAccount(emailText, addressText, fullNameText, passwordText);
                Frame.layout.show(Frame.container, "optionMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all required information");
            }
        }
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        // Draw the rectangle
        g.setColor(Color.white);
        g.drawRoundRect(200, 150, 600, 500,10,10);
        g.fillRect(100,150,800,500);
        // Draw the logo
        g.drawImage(logo.getImage(), Utility.MIDDLE_X+135, Utility.MIDDLE_Y-265, 150, 135, null);
        // Draw the background
        g.drawImage(background.getImage(), 99, 149, 402, 502, null);
    }
    
    /**
     * createAccount
     * This method saves the given information about the account registration into a file. 
     * @param emailText A string of the user's email
     * @param addressText A string of the user's address
     * @param fullNameText A string of the user's full name
     * @param passwordText A string of the user's password
     * @exception IOException If stream to file cannot be written to or closed
     */
    public void createAccount(String emailText,String addressText, String fullNameText, String passwordText) {
        try {
            final boolean APPEND = true;
            FileWriter accounts = new FileWriter("all_accounts.txt", APPEND); 
            PrintWriter output = new PrintWriter(accounts);
            output.println(emailText + ","+passwordText + ","+addressText+ ","+fullNameText);
            output.println("\n");
            output.close();
        }
        catch (IOException e) {
        }
    }
}