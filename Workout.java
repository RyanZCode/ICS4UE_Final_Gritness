package gritnessApp;

import java.util.*;

// Ryan
public class Workout {
	String templateName;
	ArrayList<Exercise> exercises;
	int secondsTime;
	
	Workout(String templateName) {
		this.templateName = templateName;
		this.exercises = new ArrayList<Exercise>();
	}
	
	Workout(String templateName, String[] exerciseNames) {
		this.templateName = templateName;
		this.exercises = new ArrayList<Exercise>();
		for (int i = 0; i < exerciseNames.length; i++) {
			exercises.add(new Exercise(exerciseNames[i]));
		}
	}
	
	public void startWorkout() {
		secondsTime = 0;
	}
	
	public void addExercise(Exercise exercise) {
		this.exercises.add(exercise);
	}
	
	public void removeExercise(Exercise exercise) {
		this.exercises.remove(exercise);
	}
}
