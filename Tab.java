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

    public Tab(){
        login = new LoginScreen();
        profile = new ProfileTab();
        history = new HistoryTab();
        workout = new WorkoutTab();
        nutrition = new NutritionTab();
        social = new SocialTab();
        signup = new SignUpTab();
        information = new InformationTab();
        tabs = new JPanel();
        cardLayout = new CardLayout();
    }

    public void run() {        
        Window window = new Window();
        Window.container.add(login, "login");
        Window.container.add(signup, "signup");
        Window.container.add(profile, "profile");
        Window.container.add(history, "history");
        Window.container.add(workout, "workout");
        Window.container.add(nutrition, "nutrition");
        Window.container.add(social, "social");
        Window.container.add(information, "information");
        window.setVisible(true);
    }
    public void paintProfile() {
    	profile.repaint();
    }
}
