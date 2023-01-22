package gritnessApp.client;

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
    public static NavigationBar nav;
    public static JPanel tabs;
    public static CardLayout cardLayout;

    Tab(){
        login = new LoginScreen();
        profile = new ProfileTab();
        history = new HistoryTab();
        workout = new WorkoutTab();
        nutrition = new NutritionTab();
        social = new SocialTab();
        signup = new SignUpTab();
        nav = new NavigationBar();
        tabs = new JPanel();
        cardLayout = new CardLayout();
    }

    public void run() {        
        Window window = new Window();
        tabs.setLayout(cardLayout);
        tabs.add(login, "login");
        tabs.add(signup, "signup");
        tabs.add(profile, "profile");
        tabs.add(history, "history");
        tabs.add(workout, "workout");
        tabs.add(nutrition, "nutrition");
        tabs.add(social, "social");
        window.add(tabs, BorderLayout.NORTH);
        window.add(nav, BorderLayout.SOUTH);
        
        window.setVisible(true);
    }
}
