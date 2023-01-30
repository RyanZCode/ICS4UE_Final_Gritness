package gritnessApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

public class Tab {
    private LoginScreen login;
    private SignUpTab signup;
    private ProfileTab profile;
    private HistoryTab history;
    private WorkoutTab workout;
    private NutritionTab nutrition;
    private SocialTab social;
    private InformationTab information;
    public static JPanel tabs;
    public static CardLayout cardLayout;
    Client client;

    public Tab() throws Exception{
    	client = new Client();
    	client.start();
        login = new LoginScreen(client);
        signup = new SignUpTab(client);
        information = new InformationTab(client);
        tabs = new JPanel();
        cardLayout = new CardLayout();
    }

    public void run() {        
        Window window = new Window();
        Window.container.add(login, "login");
        Window.container.add(signup, "signup");
        Window.container.add(information, "information");
        window.setVisible(true);
    }
    public void paintProfile() {
    	profile.repaint();
    }
}
