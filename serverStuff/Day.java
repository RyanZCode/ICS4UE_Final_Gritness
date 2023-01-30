package gritnessApp;

import java.time.LocalDate;
import java.util.*;

// Ryan
public class Day {
	private LocalDate date;
	ArrayList<Workout> workouts;
	ArrayList<Meal> meals;
	// update these things as things are added/removed
	int totalCalories;
	int totalProtein;
	int totalCarbs;
	int totalFiber;
	int totalSugar;
	int totalFat;
	int totalSodium;
	int totalCaloriesBurned;
	int totalExerciseMinutes;
	
	Day() {
		date = LocalDate.now();
		workouts = new ArrayList<Workout>();
		meals = new ArrayList<Meal>();
	}
	
	public void addWorkout(String name) {
		workouts.add(new Workout(name));
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(ArrayList<Workout> workouts) {
		this.workouts = workouts;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	public int getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	public int getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(int totalProtein) {
		this.totalProtein = totalProtein;
	}

	public int getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(int totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public int getTotalFiber() {
		return totalFiber;
	}

	public void setTotalFiber(int totalFiber) {
		this.totalFiber = totalFiber;
	}

	public int getTotalSugar() {
		return totalSugar;
	}

	public void setTotalSugar(int totalSugar) {
		this.totalSugar = totalSugar;
	}

	public int getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(int totalFat) {
		this.totalFat = totalFat;
	}

	public int getTotalSodium() {
		return totalSodium;
	}

	public void setTotalSodium(int totalSodium) {
		this.totalSodium = totalSodium;
	}

	public int getTotalCaloriesBurned() {
		return totalCaloriesBurned;
	}

	public void setTotalCaloriesBurned(int totalCaloriesBurned) {
		this.totalCaloriesBurned = totalCaloriesBurned;
	}

	public int getTotalExerciseMinutes() {
		return totalExerciseMinutes;
	}

	public void setTotalExerciseMinutes(int totalExerciseMinutes) {
		this.totalExerciseMinutes = totalExerciseMinutes;
	}	
	
	public int getNumberWorkouts() {
		return workouts.size();
	}
	
}
