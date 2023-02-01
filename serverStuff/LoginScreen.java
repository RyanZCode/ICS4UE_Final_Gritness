package gritnessApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * [LoginScreen.java]
 * The first screen the user sees, gives them the option to log
 * into their account or create a new one
 * @author Nathan Kong
 * @author Justin Zhou
 * @author Ryan Zhou
 * @version 1.0 Jan 24, 2023
 */
public class LoginScreen extends JPanel implements ActionListener{
    private JLabel title, text, username, password, account, background;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton login, register;
    Client client;

    /**
     * LoginScreen
     * Creates the login screen for 
     * the user to login or register a new account
     * @param client User client
     */
    public LoginScreen(Client client){
    	this.client = client;
    	
    	//draw labels and buttons
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

    /**
     * actionPerformed
     * Records mouse clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
        	String username = usernameField.getText();
        	@SuppressWarnings("deprecation")
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
	
	    			//successful login
	                if (serverMessage.equals("success")) {
	                	client.setUsername(username);
	                	
						ProfileTab profile;
						SocialTab social;
						NutritionTab nutrition;
						
						try {
							profile = new ProfileTab(client);
							Window.container.add(profile, "profile");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
		                HistoryTab history;
						try {
							history = new HistoryTab(client);
							Window.container.add(history, "history");
						} catch (IOException e3) {
							e3.printStackTrace();
						}
		                
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
        else if (e.getSource() == register) {
            // Change screens, load in new password and field boxes
            Window.layout.show(Window.container, "signup");
        }
    }
}