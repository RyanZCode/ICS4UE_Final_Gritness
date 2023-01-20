package gritnessApp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class SignUpTab extends JPanel implements ActionListener{
    private JFrame window;
    ProfileTab main;
    private JLabel title, text, username, password, account;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton signup;
    private GraphicsPanel canvas;  
    private JTextArea textArea = new JTextArea();
    private boolean loggedIn;
    private String usernameEntered;
    private String passwordEntered;
    SignUpTab(){
        window = new JFrame("Sign Up");
        window.setResizable(false);
        window.setSize(Const.LOGIN_LENGTH, Const.LOGIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new GraphicsPanel();
        window.add(canvas);
        
        window.setVisible(true);
        window.setResizable(false);
        canvas.setLayout(null);
        
        title = new JLabel("Create Account", SwingConstants.CENTER);
        text = new JLabel("Create your account:", SwingConstants.CENTER);
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        account = new JLabel("Create an account");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        showPassword = new JCheckBox("Show password");

        signup = new JButton("Create Account");

        signup.addActionListener(this);
        showPassword.addActionListener(this);
        
        title.setBounds(0, 50, Const.LOGIN_WIDTH, 50);
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        
        text.setBounds(0, 200, Const.LOGIN_WIDTH, 50);
        text.setFont(new Font("Calibri", Font.BOLD, 16));
        
        username.setBounds(120, 250, 1000, 30);
        password.setBounds(120, 280, 1000, 30);
        usernameField.setBounds(200, 250, 150, 30);
        passwordField.setBounds(200, 280, 150, 30);
        
        //passwordField.setEchoChar('â—�');
        showPassword.setBounds(190, 310, 300, 30);        
        signup.setBounds(175, 340, 150, 30);

        canvas.add(title);
        canvas.add(text);
        canvas.add(username);
        canvas.add(password);
        canvas.add(account);
        canvas.add(usernameField);
        canvas.add(passwordField);
        canvas.add(showPassword);
        canvas.add(signup);
        
        this.loggedIn = false;
        
        this.setLayout(null);
        window.repaint();
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
        if (e.getSource() == signup) {
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
    }
//    public static void main(String[] args) {
////        new SignUpTab();
//        //change this, no need for main
//        //login.run(login);
//    }
    public boolean userLoggedIn() {
    	return this.loggedIn;
    }
//    public void run(SignUpTab login) {
//    	//login.window.repaint();
//
//    }
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
