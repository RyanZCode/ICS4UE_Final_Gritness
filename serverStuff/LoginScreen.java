package gritnessApp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class LoginScreen extends JPanel implements ActionListener{
    private JLabel title, text, username, password, account, background;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton login, register;
    private GraphicsPanel canvas;
    private boolean loggedIn;
    private String usernameEntered;
    private String passwordEntered;
    Client client;

    public LoginScreen(Client client){
    	this.client = client;
        title = new JLabel("WELCOME TO GRITNESS.", SwingConstants.CENTER);
        title.setForeground(Color.white);
        title.setFont(Const.COVER_FONT);
        title.setBounds(0, 100, Const.MAIN_LENGTH, 75);
        text = new JLabel("SIGN IN:", SwingConstants.CENTER);
        text.setBounds(0, 200, Const.MAIN_LENGTH, 50);
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.white);
        
        username = new JLabel("USERNAME:");
        username.setForeground(Color.white);
        username.setBounds(490, 250, 1000, 30);
        
        usernameField = new JTextField();
        usernameField.setBounds(570, 250, 150, 30);
        
        password = new JLabel("PASSWORD:");
        password.setBounds(490, 280, 1000, 30);
        password.setForeground(Color.white);
        
        account = new JLabel("Create an account");
        
        passwordField = new JPasswordField();
        passwordField.setBounds(570, 280, 150, 30);
        
//        showPassword = new JCheckBox("Show Password");
//        showPassword.setBounds(600, 310, 150, 30);
//        showPassword.setBackground(Color.BLACK);
//        showPassword.setForeground(Color.WHITE);
        
        background = new JLabel(Const.COVER_PHOTO);
        background.setBounds(0,0,1280,720);
        
        login = new JButton("LETS GET BIG.");
        login.setBounds(570, 330, 150, 30);
        login.addActionListener(this); 
        login.setBackground(Color.WHITE);
        login.setFocusable(false);
        login.setBorderPainted(false);
        
        register = new JButton("New User?");
        register.setBounds(570, 400, 150, 30);
        register.addActionListener(this);
        register.setBackground(Color.WHITE);
        register.setFocusable(false);
        register.setBorderPainted(false);

        this.add(title);
        this.add(text);
        this.add(username);
        this.add(password);
        this.add(account);
        this.add(usernameField);
        this.add(passwordField);
//        this.add(showPassword);
        this.add(login);
        this.add(register);
        this.add(background);
        
        this.loggedIn = false;
        this.setLayout(null);
        this.setVisible(true);
    }

    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            
            // Anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String pwd = new String(passwordField.getPassword());
            setEnteredUsername(usernameField.getText());
            setEnteredPassword(pwd);
            this.loggedIn = true;
            // Send login data to server, receive message back
            String serverMessage = null;
			try {
				serverMessage = client.sendLogin(usernameField.getText(), new String(passwordField.getPassword()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            if (serverMessage.equals("success")) {
            	client.setUsername(usernameField.getText());
            	Window.layout.show(Window.container, "profile");
            } else if (serverMessage.equals("wrong password")) {
            	System.out.println("wrong password");
            } else if (serverMessage.equals("username not found")) {
            	System.out.println("username not found");
            } else {
            	System.out.println(serverMessage);
            }
        }
        else if (e.getSource() == showPassword) {
            if(showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        }
        else if (e.getSource() == register) {
            // Change screens, load in new password and field boxes
            Window.layout.show(Window.container, "signup");
        }
    }
    public boolean userLoggedIn() {
        return this.loggedIn;
    }
    public String getEnteredUsername() {
        return usernameEntered;
    }
    public void setEnteredUsername(String enteredUsername) {
        this.usernameEntered = enteredUsername;
    }
    public void setEnteredPassword(String enteredPassword) {
        this.passwordEntered = enteredPassword;
    }
    public String getEnteredPassword() {
        return this.passwordEntered;
    }
}