package gritnessApp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class LoginScreen extends JPanel implements ActionListener{
    private JFrame window;
    ProfileTab main;
    private JLabel title, text, username, password, account;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton login, register;
    private GraphicsPanel canvas;  
    private JTextArea textArea = new JTextArea();
    private boolean loggedIn;
    private String usernameEntered;
    private String passwordEntered;
    LoginScreen(){
    	
    	
    	
        window = new JFrame("Gritness Login");
        window.setResizable(false);
        window.setSize(Const.LOGIN_LENGTH, Const.LOGIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //figure out how to make things not squish together on minimize
        canvas = new GraphicsPanel();
        window.add(canvas);
        
        window.setVisible(true);
        window.setResizable(false);
        window.setLayout(null);
        canvas.setVisible(true);
        
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
        
        title.setBounds(100, 50, 1000, 50);
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        
        text.setBounds(150, 200, 1000, 50);
        text.setFont(new Font("Calibri", Font.BOLD, 16));
        
        username.setBounds(120, 250, 1000, 30);
        password.setBounds(120, 280, 1000, 30);
        usernameField.setBounds(200, 250, 150, 30);
        passwordField.setBounds(200, 280, 150, 30);
        
        //passwordField.setEchoChar('â—�');
        showPassword.setBounds(190, 310, 300, 30);        
        login.setBounds(175, 340, 150, 30);
        register.setBounds(175, 420, 150, 30);

        canvas.add(title);
        canvas.add(text);
        canvas.add(username);
        canvas.add(password);
        canvas.add(account);
        canvas.add(usernameField);
        canvas.add(passwordField);
        canvas.add(showPassword);
        canvas.add(login);
        canvas.add(register);
        
        this.loggedIn = false;
        
        this.setLayout(null);
        repaint();
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
    
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
        	String pwd = new String(passwordField.getPassword());
        	setEnteredUsername(usernameField.getText());
        	setEnteredPassword(pwd);
        	this.loggedIn = true;
            //search entire database
        	
            //after successful login 
            main = new ProfileTab();
            window.dispose();            
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
        }
    }
    public static void main(String[] args) {
        LoginScreen login = new LoginScreen();
        
        login.run(login);
    }
    public boolean userLoggedIn() {
    	return this.loggedIn;
    }
    public void run(LoginScreen login) {
    	login.window.repaint();
    }
    //run function if needed
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
