package gritnessApp.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class SignUpTab extends JPanel implements ActionListener{
    private JFrame window;
    ProfileTab main;
    private JLabel title, text, username, password, account, background;
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
        canvas = new GraphicsPanel();
        background = new JLabel(Const.COVER_PHOTO);
        background.setBounds(0,0,1280,720);
        
        title = new JLabel("LETS GET STARTED", SwingConstants.CENTER);
        title.setBounds(0, 100, Const.MAIN_LENGTH, 50);
        title.setFont(Const.COVER_FONT);
        title.setForeground(Color.white);
        
        text = new JLabel("START YOUR FITNESS JOURNEY TODAY:", SwingConstants.CENTER);
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

//        showPassword = new JCheckBox("Show password");

        signup = new JButton("Create Account");
        signup.addActionListener(this);
        signup.setBounds(570, 340, 150, 30);
        signup.setBackground(Color.WHITE);
        signup.setFocusable(false);
        signup.setBorderPainted(false);
//        showPassword.addActionListener(this);
        
        //passwordField.setEchoChar('â—�');
//        showPassword.setBounds(590, 310, 300, 30);        

        this.add(title);
        this.add(text);
        this.add(username);
        this.add(password);
        this.add(account);
        this.add(usernameField);
        this.add(passwordField);
//        this.add(showPassword);
        this.add(signup);
        this.add(background);
        
        this.loggedIn = false;
        
        this.setLayout(null);
//        window.repaint();
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
