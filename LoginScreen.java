package gritnessApp;

import java.awt.*;
import java.awt.event.*;
import gritnessApp.CentralDatabase;

import javax.swing.*;

public class LoginScreen extends JPanel implements ActionListener{
    private JFrame window;
    private JLabel title, text, username, password, account;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton login, register;
    private GraphicsPanel canvas;
    private boolean loggedIn;
    private String usernameEntered;
    private String passwordEntered;

    public LoginScreen(){
        title = new JLabel("Welcome to Gritness!");
        text = new JLabel("Sign into your account below:");
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        account = new JLabel("Create an account");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        showPassword = new JCheckBox("Show password");

        login = new JButton("Login");
        register = new JButton("New user?");

        login.addActionListener(this); 
        showPassword.addActionListener(this);
        register.addActionListener(this);

        title.setBounds(450, 100, 1000, 50);
        title.setFont(new Font("Calibri", Font.PLAIN, 48));

        text.setBounds(500, 200, 1000, 50);
        text.setFont(new Font("Calibri", Font.PLAIN, 24));

        username.setBounds(520, 250, 1000, 30);
        password.setBounds(520, 280, 1000, 30);
        usernameField.setBounds(600, 250, 150, 30);
        passwordField.setBounds(600, 280, 150, 30);

        showPassword.setBounds(590, 310, 300, 30);        
        login.setBounds(575, 340, 150, 30);
        register.setBounds(575, 420, 150, 30);

        this.add(title);
        this.add(text);
        this.add(username);
        this.add(password);
        this.add(account);
        this.add(usernameField);
        this.add(passwordField);
        this.add(showPassword);
        this.add(login);
        this.add(register);

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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String pwd = new String(passwordField.getPassword());
            setEnteredUsername(usernameField.getText());
            setEnteredPassword(pwd);
            this.loggedIn = true;
            //search entire database

            //after successful login 
            Window.layout.show(Window.container, "profile");
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
            //change screens, load in new password and field boxes
            Window.layout.show(Window.container, "register");
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