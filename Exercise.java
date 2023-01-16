package gritnessApp;

import java.util.*;

// Ryan
public class Exercise {
	String name;
	ArrayList<Set> sets;
	
	Exercise(String name) {
		this.name = name;
	}
	
	public void addSet(double lbs, int reps) {
		sets.add(new Set(lbs, reps));
	}
	
	public void removeSet(int num) {
		if (num < sets.size()) {
			sets.remove(num - 1);
		}
	}
}

