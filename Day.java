package gritnessApp;

import java.time.LocalDate;
import java.util.*;

// Ryan
public class Day {
	private LocalDate date;
	ArrayList<Workout> workouts;
	ArrayList<Meal> meals;
	
	Day() {
		LocalDate date = LocalDate.now();
		workouts = new ArrayList<Workout>();
		meals = new ArrayList<Meal>();
	}
	
	public void addWorkout(String name) {
		workouts.add(new Workout(name, new ArrayList<Exercise>()));
	}	
}
