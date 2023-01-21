package gritnessApp;

public class Tab {
	private LoginScreen login;
	private SignUpTab signup;
    private ProfileTab profile;
    private HistoryTab history;
    private WorkoutTab workout;
    private NutritionTab nutrition;
    private SocialTab social;


    Tab(){
    	login = new LoginScreen();
        profile = new ProfileTab();
        history = new HistoryTab();
        workout = new WorkoutTab();
        nutrition = new NutritionTab();
        social = new SocialTab();
        signup = new SignUpTab();
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
        window.setVisible(true);
    }

}
