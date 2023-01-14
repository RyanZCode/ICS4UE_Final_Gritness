package gritnessApp;

import java.util.*;

public class User {
	String displayName;
	String username;
	String password;
	double weight;
	String goal;
	ArrayList<Workout> userWorkoutTemplates;
	ArrayList<Workout> userMealTemplates;
	
	public User(String username, String displayName, String password) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	
}
