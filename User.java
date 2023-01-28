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
	String goal;
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
	}
	
    public static void main(String[] args) {
    	User user = new User("dog", "dog", "dog");
    	user.getCalorieHistory();
    }
    
    public String getFriendsString() {
    	String friendsString = "";
    	for (int i = 0; i < friends.size(); i++) {
    		friendsString += friends.get(i) + "$$";
    	}
    	return friendsString;
    }
	
	public String getCalorieHistory() {
		Day day = new Day();
		day.setDate(LocalDate.now());
		history.add(day);
		String calHistory = "";
		if (history.size() < 7) {
			for (int i = history.size() + 1; i > 1; i--) {
				Day date = history.get(history.size() - i);
				calHistory += String.valueOf(date.getDate().getDayOfWeek());
				calHistory += date.getTotalCalories();
				calHistory += "$$";
			}
		} else {
			for (int i = 8; i > 1; i--) {
				Day date = history.get(history.size() - i);
				calHistory += String.valueOf(date.getDate().getDayOfWeek());
				calHistory += date.getTotalCalories();
				calHistory += "$$";
			}
		}
		return calHistory;
	}
	
	public String getWorkoutNumberHistory() {
		Day day = new Day();
		day.setDate(LocalDate.now());
		history.add(day);
		String workoutHistory = "";
		if (history.size() < 7) {
			for (int i = history.size() + 1; i > 1; i--) {
				Day date = history.get(history.size() - i);
				workoutHistory += String.valueOf(date.getDate().getDayOfWeek());
				workoutHistory += date.getNumberWorkouts();
				workoutHistory += "$$";
			}
		} else {
			for (int i = 8; i > 1; i--) {
				Day date = history.get(history.size() - i);
				workoutHistory += String.valueOf(date.getDate().getDayOfWeek());
				workoutHistory += date.getNumberWorkouts();
				workoutHistory += "$$";
			}
		}
		return workoutHistory;
	}
	
	public void updateWeight(double weight) {
		this.weight = weight;
	}
	
	public void updateGoal(String goal) {
		this.weight = weight;
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

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
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
}
