package gritnessApp;

import java.util.*;

// Ryan
public class User {
	String displayName;
	String username;
	String password;
	double weight;
	String goal;
	ArrayList<Workout> userWorkoutTemplates;
	ArrayList<Meal> userMealTemplates;
	ArrayList<Day> history;
	ArrayList<String> friends;
	
	User(String username, String displayName, String password) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
		this.history = new ArrayList<Day>();
	}
	
	public void updateWeight(double weight) {
		this.weight = weight;
	}
	
	public void updateGoal(String goal) {
		this.weight = weight;
	}
	
	public void addUserWorkoutTemplate(Workout workout) {
		this.userWorkoutTemplates.add(workout);
	}
	
	public void addUserMealTemplate(Meal meal) {
		this.userMealTemplates.add(meal);
	}
	
	public String getPassword() {
		return this.password;
	}
}
