package gritnessApp;

import java.util.*;

// Ryan
public class Workout {
	String templateName;
	ArrayList<Exercise> exercises;
	int secondsTime;
	
	Workout(String templateName, ArrayList<Exercise> exercises) {
		this.templateName = templateName;
		this.exercises = exercises;
	}
	
	public void startWorkout() {
		secondsTime = 0;
	}
	
	public void addExercise(Exercise exercise) {
		this.exercises.add(exercise);
	}
}
