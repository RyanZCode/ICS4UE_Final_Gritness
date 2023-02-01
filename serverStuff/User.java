package gritnessApp;

import java.util.ArrayList;

/**
 * [User.java]
 * User class 
 * @author Ryan Zhou
 * @version 1.2 Jan 24, 2023
 */
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
    
    /**
     * User
     * Create a new user class
     * @param username Account username
     * @param displayName Display name to be shown in the app
     * @param password Account password
     */
    User(String username, String displayName, String password) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.history = new ArrayList<Day>();
        this.friends = new ArrayList<String>();
    }
    public String getFriendsString() {
        String friendsString = "";
        for (int i = 0; i < friends.size(); i++) {
            friendsString += friends.get(i) + "$$";
        }
        return friendsString;
    }

    /**
     * getHistoryString
     * Retrieves each day and stores in a string 
     * @return String of all dates 
     */
    public String getHistoryString() {
        String historyString = "";
        for (int i = 0; i < history.size(); i++) {
            historyString += history.get(i).getDate();
            historyString += "$$";
        }
        return historyString;
    }
    
    /**
     * getDay
     * Finds the day in history
     * @param dayString String of the day
     * @return null
     */
    public Day getDay(String dayString) {
        for (int i = 0; i < history.size(); i++) {
            if (String.valueOf(history.get(i).getDate()).equals(dayString)) {
                return history.get(i);
            }
        }
        return null;
    }
    /**
     * getCalorieHistory
     * Returns entire calorie history of a user
     * @return String
     */
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
    /**
     * getWorkoutNumberHistory
     * Returns entire workout history of a user
     * @return String
     */
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
    
    /**
     * getCalories
     * Retrieves calories from history
     * @return calories 
     */
    public int getCalories() {
        return history.get(history.size() - 1).getTotalCalories();
    }
    /**
     * getCalories
     * Retrieves protein from history
     * @return protein 
     */
    public int getProtein() {
        return history.get(history.size() - 1).getTotalProtein();
    }
    /**
     * getCarbs
     * Retrieves carbs from history
     * @return carbs 
     */
    public int getCarbs() {
        return history.get(history.size() - 1).getTotalCarbs();
    }
    /**
     * getSugar
     * Retrieves sugar from history
     * @return sugar
     */
    public int getSugar() {
        return history.get(history.size() - 1).getTotalSugar();
    }
    /**
     * getFiber
     * Retrieves fiber from history
     * @return fiber
     */
    public int getFiber() {
        return history.get(history.size() - 1).getTotalFiber();
    }
    /**
     * getFats
     * Retrieves fats from history
     * @return fats
     */
    public int getFats() {
        return history.get(history.size() - 1).getTotalFats();
    }
    /**
     * getSodium
     * Retrieves sodium from history
     * @return sodium
     */
    public int getSodium() {
        return history.get(history.size() - 1).getTotalSodium();
    }
    /**
     * addDay
     * Adds day to history
     */
    public void addDay(Day day) {
        history.add(day);
    }
    /**
     * addFriend
     * Adds friend to history
     */ void addFriend(String friend) {
        friends.add(friend);
    }
     /**
      * addCalories
      * Adds calories to history
      */
    public void addCalories(int calories) {
        history.get(history.size() - 1).addCalories(calories);
    }
    /**
     * addProtein
     * Adds protein to history
     */
    public void addProtein(int protein) {
        history.get(history.size() - 1).addProtein(protein);
    }
    /**
     * addCarbs
     * Adds carbs to history
     */
    public void addCarbs(int carbs) {
        history.get(history.size() - 1).addCarbs(carbs);
    }
    /**
     * addSugar
     * Adds sugar to history
     */
    public void addSugar(int sugar) {
        history.get(history.size() - 1).addSugar(sugar);
    }
    /**
     * addFiber
     * Adds fiber to history
     */
    public void addFiber(int fiber) {
        history.get(history.size() - 1).addFiber(fiber);
    }
    /**
     * addFats
     * Adds fats to history
     */
    public void addFats(int fats) {
        history.get(history.size() - 1).addFats(fats);
    }
    /**
     * addSodium
     * Adds sodium to history
     */
    public void addSodium(int sodium) {
        history.get(history.size() - 1).addSodium(sodium);
    }
    /**
     * getToday
     * Retrieves today from history
     * @returns current day
     */
    public Day getToday() {
        return history.get(history.size() - 1);
    }
    /**
     * getAge
     * Returns user age
     * @return age
     */
    public int getAge() {
        return age;
    }
    /**
     * setAge
     * Changes user age
     * @return age
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * getHeight
     * Retrieves user height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * setHeight
     * Changes user height
     * @return height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * getDisplayName
     * Retrieves user display name 
     * @return display name
     */
    public String getDisplayName() {
        return displayName;
    }
    /**
     * setDisplayName
     * Changes user display name
     * @return display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /**
     * getUsername
     * Retrieves user's username
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * setUsername
     * Sets user's username
     * @return usernmae
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * getWeight
     * Retrieve user weight
     * @return weight
     */
    public double getWeight() {
        return weight;
    }
    /**
     * setWeight
     * Set user weight
     * @return weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * getCalorieGoal
     * Retrieve user calorie goal
     * @return calorieGoal
     */
    public int getCalorieGoal() {
        return calorieGoal;
    }
    /**
     * setCalorieGoal
     * Set user calorie goal
     * @return 
     */
    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }
    /**
     * getHistory
     * Retrieve user history of the day
     * @return history
     */
    public ArrayList<Day> getHistory() {
        return history;
    }
    /**
     * setHistory
     * Set user history of the day
     * @return history
     */
    public void setHistory(ArrayList<Day> history) {
        this.history = history;
    }
    /**
     * getFriends
     * Retrieves user friends
     * @return friends
     */
    public ArrayList<String> getFriends() {
        return friends;
    }
    /**
     * setFriends
     * Sets user friends
     * @return friends
     */
    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }
    /**
     * setPassword
     * Sets user password
     * @return password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * getPassword
     * Retrieves user password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * getBMI
     * Retrieves and calculates user BMI
     * @return bmi
     */
    public double getBMI() {
        return weight / (Math.pow(height / 100,2));
    }
    /**
     * getBMR
     * Retrieves and calculates user BMR
     * @return bmr
     */
    public double getBMR() {
        return 88.362 + (13.397 * weight) + (4.799 + height) - (5.677 * age);
    }
}
