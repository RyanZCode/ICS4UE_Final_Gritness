// Import classes
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.BufferedReader;

/** [LogInMenu.java]
  * Desc: The page that displays the log in
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class LogInMenu extends JPanel implements ActionListener {
    // Variables
    private JLabel title, text, username, password, registerText;
    private JTextField usernameField; 
    private static String currentUser;
    private JPasswordField passwordField;
    private JButton login, register;
    private JCheckBox showPassword;
    private ImageIcon logo;
    
    /**
     * LogInMenu
     * This constructor creates a new LogInMenu object.
     */
    public LogInMenu() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        
        title = new JLabel("Welcome.");
        text = new JLabel("Sign into your cargo placement optimization account:");
        username = new JLabel("Email:");
        password = new JLabel("Password:");
        registerText = new JLabel("Dont have an account?");
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        login = new JButton("Login");
        register = new JButton("Register Here");
        
        showPassword = new JCheckBox("Show Password");
        
        logo = new ImageIcon("images/logo.png");
        
        // Set boundaries
        title.setBounds(Utility.MIDDLE_X-70, Utility.MIDDLE_Y-130, 500, 35);
        title.setFont(new Font("Assistant", Font.BOLD, 35));
        text.setBounds(Utility.MIDDLE_X-240, Utility.MIDDLE_Y-70, 500, 30);
        text.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        username.setBounds(Utility.MIDDLE_X-180, Utility.MIDDLE_Y-10, 100, 30);
        password.setBounds(Utility.MIDDLE_X-180, Utility.MIDDLE_Y+30, 100, 30);
        usernameField.setBounds(Utility.MIDDLE_X-70, Utility.MIDDLE_Y-10, 150, 30);
        passwordField.setBounds(Utility.MIDDLE_X-70, Utility.MIDDLE_Y+30, 150, 30);
        showPassword.setBounds(Utility.MIDDLE_X-70, Utility.MIDDLE_Y+70, 150, 30);
        
        login.setBounds(Utility.MIDDLE_X-70, Utility.MIDDLE_Y+115, 150, 30);
        login.setFont(new Font("Assistant", Font.BOLD, 12));
        login.setBorderPainted(false);
        login.setOpaque(true);
        login.setBackground(Utility.BACKGROUND);
        
        registerText.setBounds(Utility.MIDDLE_X-130, Utility.MIDDLE_Y+150, 250, 50);
        register.setBounds(Utility.MIDDLE_X, Utility.MIDDLE_Y+150, 150, 50);
        register.setForeground(Color.blue);
        register.setOpaque(false);
        register.setBorderPainted(false);
        
        // Add action listeners
        login.addActionListener(this);
        showPassword.addActionListener(this);
        register.addActionListener(this);
        
        // Add all of the components
        this.add(title);
        this.add(text);
        this.add(username);
        this.add(password);
        this.add(usernameField);
        this.add(passwordField);
        this.add(showPassword);
        this.add(login);
        this.add(register);
        this.add(registerText);
        
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Scan through all of the accounts and check if the username and password exists
        if (e.getSource() == login) {
            try {
                String read;
                String account = "all_accounts.txt";
                String userText = usernameField.getText();
                String passwordText = new String (passwordField.getPassword());
                
                FileReader fr = new FileReader(account);
                BufferedReader br = new BufferedReader(fr);
                String line, user, pass;
                boolean isLoginSuccess = false;
                while ((read = br.readLine()) !=null) {
                    String[] split = read.split("[,]", 0);
                    // If the account exists, allow the system to move onto the next menu
                    if (userText.equals(split[0]) && passwordText.equals(split[1])) {
                        isLoginSuccess = true;
                        // Display the option menu
                        Frame.layout.show(Frame.container, "optionMenu");
                        break;
                    } 
                }
                // Tell the user that the username or password is wrong
                if (!isLoginSuccess) {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
                fr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // If the show password button is clicked, show the password characters, otherwise mask the password
        else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        // If the register button is clicked, show the register page
        else if (e.getSource() == register) {
            Frame.layout.show(Frame.container, "registerMenu");
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the rectangle
        g.setColor(Color.white);
        g.fillRect(170, 100, 660, 550);
        // Draw the logo
        g.drawImage(logo.getImage(), Utility.MIDDLE_X-85, Utility.MIDDLE_Y-280, 180, 150, null);
    }
    
    // Getters and setters
    /**
     * getCurrentUser
     * This method gets the current user.
     * @return A string of the current user
     */
    public static String getCurrentUser() {
        return currentUser;
    }
    
    /**
     * setCurrentUser
     * This method sets the user to current user.
     * @param user A string of the user
     */
    public static void setCurrentUser(String user) {
        currentUser = user;
    }
}