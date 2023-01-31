package gritnessApp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

// Ryan
public class User {
	String displayName;
	String username;
	String password;
	double weight;
	int calorieGoal;
	ArrayList<Workout> userWorkoutTemplates;
	ArrayList<Meal> userMealTemplates;
	ArrayList<Day> history;
	ArrayList<String> friends;
	int age;
	int height;

	User(String username, String displayName, String password) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
		this.history = new ArrayList<Day>();
		this.friends = new ArrayList<String>();
	}
	
	public void addDay(Day day) {
		history.add(day);
	}
    
	public void addFriend(String friend) {
		friends.add(friend);
	}
	
    public String getFriendsString() {
    	String friendsString = "";
    	for (int i = 0; i < friends.size(); i++) {
    		friendsString += friends.get(i) + "$$";
    	}
    	return friendsString;
    }
	
	public String getCalorieHistory() {
		String calHistory = "";
		if (history.size() < 7) {
			for (int i = history.size() - 1; i >= 0; i--) {
				Day date = history.get(i);
				calHistory += String.valueOf(date.getDate().getDayOfWeek());
				calHistory += "$$";
				calHistory += date.getTotalCalories();
				calHistory += "$$";
			}
		} else {
			for (int i = history.size() - 1; i > history.size() - 8; i--) {
				Day date = history.get(i);
				calHistory += String.valueOf(date.getDate().getDayOfWeek());
				calHistory += "$$";
				calHistory += date.getTotalCalories();
				calHistory += "$$";
			}
		}
		return calHistory;
	}
	
	public String getWorkoutNumberHistory() {		
		String workoutHistory = "";
		if (history.size() < 7) {
			for (int i = history.size() - 1; i >= 0; i--) {
				Day date = history.get(i);
				workoutHistory += String.valueOf(date.getDate().getDayOfWeek());
				workoutHistory += "$$";
				workoutHistory += date.getNumberWorkouts();
				workoutHistory += "$$";
			}
		} else {
			for (int i = history.size() - 1; i > history.size() - 8; i--) {
				Day date = history.get(i);
				workoutHistory += String.valueOf(date.getDate().getDayOfWeek());
				workoutHistory += "$$";
				workoutHistory += date.getNumberWorkouts();
				workoutHistory += "$$";
			}
		}
		return workoutHistory;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addUserWorkoutTemplate(Workout workout) {
		this.userWorkoutTemplates.add(workout);
	}
	
	public void addUserMealTemplate(Meal meal) {
		this.userMealTemplates.add(meal);
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getCalorieGoal() {
		return calorieGoal;
	}

	public void setCalorieGoal(int calorieGoal) {
		this.calorieGoal = calorieGoal;
	}

	public ArrayList<Workout> getUserWorkoutTemplates() {
		return userWorkoutTemplates;
	}

	public void setUserWorkoutTemplates(ArrayList<Workout> userWorkoutTemplates) {
		this.userWorkoutTemplates = userWorkoutTemplates;
	}

	public ArrayList<Meal> getUserMealTemplates() {
		return userMealTemplates;
	}

	public void setUserMealTemplates(ArrayList<Meal> userMealTemplates) {
		this.userMealTemplates = userMealTemplates;
	}

	public ArrayList<Day> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Day> history) {
		this.history = history;
	}

	public ArrayList<String> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	
	public double getBMI() {
		return weight / (Math.pow(height / 100,2));
	}
	
	public double getBMR() {
		return 88.362 + (13.397 * weight) + (4.799 + height) - (5.677 * age);
	}
}
