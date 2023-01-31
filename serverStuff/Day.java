package gritnessApp;

import java.time.LocalDate;
import java.util.*;

// Ryan
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
	
	public void addWorkout(String name, int time) {
		workouts.add(name + "$$" + time);
	}
	
	public ArrayList<String> getWorkouts() {
		return this.workouts;
	}
	
	public void addCalories(int calories) {
		totalCalories += calories;
	}
	
	public void addProtein(int protein) {
		totalProtein += protein;
	}
	
	public void addCarbs(int carbs) {
		totalCarbs += carbs;
	}
	
	public void addSugar(int sugar) {
		totalSugar += sugar;
	}
	
	public void addFiber(int fiber) {
		totalFiber += fiber;
	}
	
	public void addFats(int fats) {
		totalFats += fats;
	}
	
	public void addSodium(int sodium) {
		totalSodium += sodium;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public int getTotalFats() {
		return totalFats;
	}

	public void setTotalFats(int totalFat) {
		this.totalFats = totalFat;
	}

	public int getTotalSodium() {
		return totalSodium;
	}

	public void setTotalSodium(int totalSodium) {
		this.totalSodium = totalSodium;
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
