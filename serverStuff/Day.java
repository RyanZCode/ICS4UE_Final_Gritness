package gritnessApp;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * [Day.Java]
 * Helper class to match the date  
 * with food intake and workouts
 * @author Ryan Zhou
 * @version 1.0 Jan 24, 2023
 */
public class Day {
	private LocalDate date;
	int totalCalories;
	int totalProtein;
	int totalCarbs;
	int totalFiber;
	int totalSugar;
	int totalFats;
	int totalSodium;
	ArrayList<String> workouts;
	int totalExerciseMinutes;
	
	/**
	 * Day
	 * Constructor for a single day
	 */
	Day() {
		date = LocalDate.now();
		totalCalories = 0;
		totalProtein = 0;
		totalCarbs = 0;
		totalFiber = 0;
		totalSugar = 0;
		totalFats = 0;
		totalSodium = 0;
		workouts = new ArrayList<String>();
	}

	
	/**
	 * getWorkouts
	 * Receives daily workouts 
	 * @return workouts
	 */
	public ArrayList<String> getWorkouts() {
		return this.workouts;
	}
	
	/**
	 * addCalories
	 * Adds consumed calories to 
	 * total daily intake of calories
	 * @param calories Consumed calories
	 */
	public void addCalories(int calories) {
		totalCalories += calories;
	}
	
	/**
	 * addProtein
	 * Adds consumed protein to
	 * total daily intake of protein
	 * @param protein Consumed protein
	 */
	public void addProtein(int protein) {
		totalProtein += protein;
	}
	
	/**
	 * addCarbs
	 * Adds consumed carbs to
	 * total daily intake of carbs 
	 * @param carbs Consumed carbs
	 */
	public void addCarbs(int carbs) {
		totalCarbs += carbs;
	}
	
	/**
	 * addSugar
	 * Adds consumed sugar to
	 * total daily intake of sugar
	 * @param sugar Consumed sugar
	 */
	public void addSugar(int sugar) {
		totalSugar += sugar;
	}
	
	/**
	 * addFiber
	 * Adds consumed fiber to
	 * total daily intake of fats
	 * @param fiber Consumed fiber
	 */
	public void addFiber(int fiber) {
		totalFiber += fiber;
	}
	
	/**
	 * addFats
	 * Adds consumed fats to
	 * total daily intake of fats
	 * @param fats Consumed fats
	 */
	public void addFats(int fats) {
		totalFats += fats;
	}
	
	/**
	 * addSodium
	 * Adds consumed sugar to
	 * total daily intake of sugar
	 * @param sodium
	 */
	public void addSodium(int sodium) {
		totalSodium += sodium;
	}

	/**
	 * getDate
	 * Gets the computer's date
	 * @return computer date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * getTotalCalories
	 * Gets daily total calories
	 * @return daily calorie intake
	 */
	public int getTotalCalories() {
		return totalCalories;
	}

	/**
	 * getTotalProtein
	 * Gets the total daily protein intake
	 * @return Daily total protein
	 */
	public int getTotalProtein() {
		return totalProtein;
	}

	/**
	 * getTotalCarbs
	 * Ggets the total carb intake for the day
	 * @return Daily total carb intake
	 */
	public int getTotalCarbs() {
		return totalCarbs;
	}
	
	/**
	 * getTotalFiber
	 * Gets daily fiber intake
	 * @return Total daily fiber intake
	 */
	public int getTotalFiber() {
		return totalFiber;
	}


	/**
	 * getTotalSugar
	 * Gets daily amount of sugar intake
	 * @return total sugar intake for the day
	 */
	public int getTotalSugar() {
		return totalSugar;
	}

	/**
	 * getTotalFats
	 * Gets total fat intake for day
	 * @return Daily fat intake
	 */
	public int getTotalFats() {
		return totalFats;
	}
	/**
	 * getTotalSodium
	 * Gets the total daily sodium intake
	 * @return Total sodium intake
	 */
	public int getTotalSodium() {
		return totalSodium;
	}
	
	/**
	 * getNumberWorkouts
	 * Gets amount of workouts done in a day
	 * @return number of workouts
	 */
	public int getNumberWorkouts() {
		return workouts.size();
	}
	
}
