package gritnessApp;

import java.awt.Frame;

public class Main {
    private static LoginScreen login;
    private ProfileTab profile;
    private HistoryTab history;
    private WorkoutTab workout;
    private NutritionTab nutrition;
    private SocialTab social;


    Main(){
        profile = new ProfileTab();
        history = new HistoryTab();
        workout = new WorkoutTab();
        nutrition = new NutritionTab();
        social = new SocialTab();
    }
    
    public void run(LoginScreen login) {
        
        Window window = new Window();
        Window.container.add(profile, "profile");
        Window.container.add(history, "history");
        Window.container.add(workout, "workout");
        Window.container.add(nutrition, "nutrition");
        Window.container.add(social, "social");
        window.setVisible(true);
    }

    public static void main(String[] args) {
        login = new LoginScreen();
        login.run(login);
    }

}
