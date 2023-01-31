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
	//output.println(user.getCalories() + "$$" + user.getProtein() + "$$" + user.getCarbs() + "$$" + user.getSugar() + "$$" + user.getFiber() + "$$" + user.getFats() + "$$" + user.getSodium());

	public int getCalories() {
		return history.get(history.size() - 1).getTotalCalories();
	}
	
	public int getProtein() {
		return history.get(history.size() - 1).getTotalProtein();
	}
	
	public int getCarbs() {
		return history.get(history.size() - 1).getTotalCarbs();
	}
	
	public int getSugar() {
		return history.get(history.size() - 1).getTotalSugar();
	}
	
	public int getFiber() {
		return history.get(history.size() - 1).getTotalFiber();
	}
	
	public int getFats() {
		return history.get(history.size() - 1).getTotalFats();
	}
	
	public int getSodium() {
		return history.get(history.size() - 1).getTotalSodium();
	}
	
	public void addDay(Day day) {
		history.add(day);
	}
    
	public void addFriend(String friend) {
		friends.add(friend);
	}
	
	public void addCalories(int calories) {
		history.get(history.size() - 1).addCalories(calories);
		System.out.println("new calories is " + history.get(history.size() - 1).getTotalCalories());
	}
	
	public void addProtein(int protein) {
		history.get(history.size() - 1).addProtein(protein);
	}
	
	public void addCarbs(int carbs) {
		history.get(history.size() - 1).addCarbs(carbs);
	}
	
	public void addSugar(int sugar) {
		history.get(history.size() - 1).addSugar(sugar);
	}
	
	public void addFiber(int fiber) {
		history.get(history.size() - 1).addFiber(fiber);
	}
	
	public void addFats(int fats) {
		history.get(history.size() - 1).addFats(fats);
	}
	
	public void addSodium(int sodium) {
		history.get(history.size() - 1).addSodium(sodium);
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
	
	public Day getToday() {
		return history.get(history.size() - 1);
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
