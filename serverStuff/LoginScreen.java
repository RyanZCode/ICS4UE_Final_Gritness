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
        this.add(login);
        this.add(register);
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
            Graphics2D g2d = (Graphics2D)g;
            
            // Anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
        	String username = usernameField.getText();
        	String password = passwordField.getText();
            
        	if (username.contains("$") ||  password.contains("$")) {
				JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
        	} else {
	            // Send login data to server, receive message back
	            if (!username.isBlank() && !password.isBlank()) {
	            	String serverMessage = null;
	    			try {
	    				serverMessage = client.sendLogin(usernameField.getText(), new String(passwordField.getPassword()));
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	
	                if (serverMessage.equals("success")) {
	                	client.setUsername(username);
	                	
						ProfileTab profile;
						SocialTab social;
						NutritionTab nutrition;
						
						try {
							client.sendDayCheck();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						try {
							profile = new ProfileTab(client);
							Window.container.add(profile, "profile");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
		                HistoryTab history = new HistoryTab();
		                Window.container.add(history, "history");
		                
						try {
							nutrition = new NutritionTab(client);
							Window.container.add(nutrition, "nutrition");
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						
						WorkoutTab workout = new WorkoutTab(client);
		                Window.container.add(workout, "workout");
		                
						try {
							social = new SocialTab(client);
							Window.container.add(social, "social");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
	                	Window.layout.show(Window.container, "profile");
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
        else if (e.getSource() == register) {
            // Change screens, load in new password and field boxes
            Window.layout.show(Window.container, "signup");
        }
    }
}