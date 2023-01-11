package gritnessApp;

import java.util.*;

public class User {
	String displayName;
	String username;
	double weight;
	String goal;
	ArrayList<Workout> userWorkoutTemplates;
	ArrayList<Workout> userMealTemplates;
	
	public User(String username, String displayName) {
		this.username = username;
		this.displayName = displayName;
	}
	
}
