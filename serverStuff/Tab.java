package gritnessApp;


import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;

/**
 * [Tab.java]
 * Instantiate new client and all tabs when application is run
 * @author Nathan Kong
 * @author Ryan Zhou
 * @author Justin Zhou
 * @version 1.0 Jan 24, 2023
 */
public class Tab {
    private LoginScreen login;
    private SignUpTab signup;
    private ProfileTab profile;
    private InformationTab information;
    public static JPanel tabs;
    public static CardLayout cardLayout;
    Client client;
    
    /**
     * Tab
     * Constructs all tabs and a new client 
     * @throws Exception
     */
    public Tab() throws Exception{
    	client = new Client();
    	client.start();
        login = new LoginScreen(client);
        signup = new SignUpTab(client);
        information = new InformationTab(client);
        tabs = new JPanel();
        cardLayout = new CardLayout();
    }

    /*
     * run
     * Creates new window, tabs, client
     */
    public void run() {        
        Window window = new Window();
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
               try {
            	   client.sendStop();
            	   client.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        };
        
        //Add tabs, listener
        window.addWindowListener(listener);
        Window.container.add(login, "login");
        Window.container.add(signup, "signup");
        Window.container.add(information, "information");
        window.setVisible(true);
    }
    
    /*
     * paintProfile
     * Repaints profile tab 
     */
    public void paintProfile() {
    	profile.repaint();
    }
}
