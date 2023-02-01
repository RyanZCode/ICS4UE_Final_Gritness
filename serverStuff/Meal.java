package gritnessApp;

/**
 * [Meal.java]
 * Creates a meal
 * @author Jason Wu
 * @author Nathan Kong
 * @version 1.0 Jan 24, 2023
 */
public class Meal {
	String name;
	int calories;
	int protein;
	int carbs;
	int fiber;
	int sugar;
	int fat;
	int sodium;
	
	/**
	 * Meal
	 * Constructs one meal
	 * @param name Name
	 * @param calories Calories
	 * @param protein Protein
	 * @param carbs Carbs
	 * @param fiber Fiber
	 * @param sugar Sugar
	 * @param fat Fat
	 * @param sodium Sodium
	 */
	Meal(String name, int calories, int protein, int carbs, int fiber, int sugar, int fat, int sodium) {
		this.name = name;
		this.calories = calories;
		this.protein = protein;
		this.carbs = carbs;
		this.fiber = fiber;
		this.sugar = sugar;
		this.fat = fat;
		this.sodium = sodium;
	}
}
