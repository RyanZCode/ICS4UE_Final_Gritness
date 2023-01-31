package gritnessApp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
    Client client;
    
    SignUpTab(Client client){
    	this.client = client;
        canvas = new GraphicsPanel();
        background = new JLabel(Const.COVER_PHOTO);
        background.setBounds(0,0,1280,720);
        
        title = new JLabel("LETS GET STARTED", SwingConstants.CENTER);
        title.setBounds(0, 100, Const.MAIN_LENGTH, 75);
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

        signup = new JButton("Create Account");
        signup.addActionListener(this);
        signup.setBounds(570, 340, 150, 30);
        signup.setBackground(Color.WHITE);
        signup.setFocusable(false);
        signup.setBorderPainted(false);     

        this.add(title);
        this.add(text);
        this.add(username);
        this.add(password);
        this.add(account);
        this.add(usernameField);
        this.add(passwordField);
        this.add(signup);
        this.add(background);
        
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
    
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
        	String username = usernameField.getText();
        	String password = passwordField.getText();
        	
        	if (username.contains("$") ||  password.contains("$")) {
				JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
        	} else {
	        	// Send sign up data to server, receive message back
	        	if (!username.isBlank() && !password.isBlank()) {
	        		String serverMessage = null;
	        		try {
	    				serverMessage = client.sendSignUp(username, password);
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	
	                if (serverMessage.equals("success")) {
	                	client.setUsername(username);
	                	Window.layout.show(Window.container, "information");
	                } else {
	                	JOptionPane.showMessageDialog(new JFrame(), serverMessage);
	                }
	        	} else {
	        		JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields");
	        	}
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
    }
}
