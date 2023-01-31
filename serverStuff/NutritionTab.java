package gritnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NutritionTab extends JPanel implements ActionListener{
    JLabel meal, calories, protein, carbs, sugar, fiber, fats, sodium, nutrients, caloriesTitle, caloriesText, caloriesNumbers, caloriesSum, proteinSum, carbsSum, fiberSum, sugarSum, fatsSum, sodiumSum, operators, calorieGoalText, caloriesEaten, caloriesLost;
    JTextField mealNameField, caloriesField, proteinField, carbsField, sugarField, fiberField, fatsField, sodiumField;
    JButton profile, workout, food, social, history, addMeal;
    JTable table;
    JScrollPane scroll;
    String[] columnNames = {"Meal","Calories", "Protein", "Carbs", "Sugar", "Fiber", "Fats", "Sodium"};
    Object[] row = new Object[8];
    int numCalories, numProtein, numCarbs, numSugar, numFiber, numFats, numSodium, exerciseCalories, caloriesRemaining;
    Client client;
    int calorieGoal;
    
    NutritionTab(Client client) throws IOException {
    	this.client = client;
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        calorieGoal = Integer.parseInt(client.getCalorieGoal());
        scroll = new JScrollPane(table);
        scroll.setBounds(100, 270, 800, 300);
        this.add(scroll);  
        
        String[] split = client.getNutritionTab().split("\\$+");
        numCalories = Integer.parseInt(split[0]);
        numProtein = Integer.parseInt(split[1]);
        numCarbs = Integer.parseInt(split[2]);
        numSugar = Integer.parseInt(split[3]);
        numFiber = Integer.parseInt(split[4]);
        numFats = Integer.parseInt(split[5]);
        numSodium = Integer.parseInt(split[6]);
        
        System.out.println("Nut tab " +Arrays.toString(split));

        addMeal = new JButton("Add meal");
        addMeal.setBounds(1010, 520, 129, 20);
        this.add(addMeal);

        meal = newText("Meal:", 280);
        calories = newText("Calories:", 310);
        protein = newText("Protein:", 340);
        carbs = newText("Carbs:", 370);
        sugar = newText("Sugar:", 400);
        fiber = newText("Fiber:", 430);
        fats = newText("Fats:", 460);
        sodium = newText("Sodium:", 490);

        mealNameField = newField(280);
        caloriesField = newField(310);
        proteinField = newField(340);
        carbsField = newField(370);
        sugarField = newField(400);
        fiberField = newField(430);
        fatsField = newField(460);
        sodiumField = newField(490);
        
        caloriesTitle = new JLabel("Calories");
        caloriesTitle.setFont(Const.TITLE_FONT);
        caloriesTitle.setBounds(420, 30, 200, 50);
        this.add(caloriesTitle);
        
        operators = new JLabel("-                            +                         =");
        operators.setFont(Const.TEXT_FONT);
        operators.setBounds(280, 120, 800, 50);
        this.add(operators);

        caloriesText = new JLabel("Goal                       Food                  Exercise            Remaining");
        caloriesText.setFont(Const.TEXT_FONT);
        caloriesText.setBounds(160, 170, 800, 50);
        this.add(caloriesText);
        
        calorieGoalText = new JLabel(calorieGoal + "");
        calorieGoalText.setFont(Const.TEXT_FONT);
        calorieGoalText.setBounds(135, 120, 100, 50);
        calorieGoalText.setHorizontalAlignment(JLabel.CENTER);
        this.add(calorieGoalText);
        
        caloriesEaten = new JLabel(numCalories + "");
        caloriesEaten.setFont(Const.TEXT_FONT);
        caloriesEaten.setBounds(330, 120, 100, 50);
        caloriesEaten.setHorizontalAlignment(JLabel.CENTER);
        this.add(caloriesEaten);
        
        caloriesLost = new JLabel(exerciseCalories + "");
        caloriesLost.setFont(Const.TEXT_FONT);
        caloriesLost.setBounds(515, 120, 100, 50);
        caloriesLost.setHorizontalAlignment(JLabel.CENTER);
        this.add(caloriesLost);
        
        caloriesRemaining = calorieGoal - numCalories + exerciseCalories;
        caloriesNumbers = new JLabel(caloriesRemaining + "");
        caloriesNumbers.setFont(Const.TEXT_FONT);
        caloriesNumbers.setBounds(735, 120, 800, 50);
        this.add(caloriesNumbers);

        nutrients = new JLabel("Nutrients");
        nutrients.setFont(Const.TITLE_FONT);
        nutrients.setBounds(950, 30, 200, 50);
        this.add(nutrients);

        proteinSum = new JLabel("Protein: " + numProtein + "g");
        proteinSum.setFont(Const.SMALLER_FONT);
        proteinSum.setBounds(930, 100, 120, 20);
        this.add(proteinSum);

        carbsSum = new JLabel("Carbs: " + numCarbs + "g");
        carbsSum.setFont(Const.SMALLER_FONT);
        carbsSum.setBounds(930, 150, 120, 20);
        this.add(carbsSum);

        sugarSum = new JLabel("Sugar: " + numSugar + "g");
        sugarSum.setFont(Const.SMALLER_FONT);
        sugarSum.setBounds(930, 200, 120, 20);
        this.add(sugarSum);

        fiberSum = new JLabel("Fiber: " + numFiber + "g");
        fiberSum.setFont(Const.SMALLER_FONT);
        fiberSum.setBounds(1080, 100, 120, 20);
        this.add(fiberSum);

        fatsSum = new JLabel("Fats: " + numFats + "g"); 
        fatsSum.setFont(Const.SMALLER_FONT);
        fatsSum.setBounds(1080, 150, 120, 20);
        this.add(fatsSum);

        sodiumSum = new JLabel("Sodium: " + numSodium + "mg");
        sodiumSum.setFont(Const.SMALLER_FONT);
        sodiumSum.setBounds(1080, 200, 120, 20);
        this.add(sodiumSum);

        profile = newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social = newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        food.setBackground(Const.BUTTON_COLOUR2.brighter());

        profile.addActionListener(this);
        history.addActionListener(this);
        workout.addActionListener(this);
        food.addActionListener(this);
        social.addActionListener(this);

        this.setLayout(null);

        addMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String mealName = mealNameField.getText();
            	String calories = caloriesField.getText();
            	String protein = proteinField.getText();
            	String carbs = carbsField.getText();
            	String sugar = sugarField.getText();
            	String fiber = fiberField.getText();
            	String fats = fatsField.getText();
            	String sodium = sodiumField.getText();
                if(mealName.isBlank() || calories.isBlank() || protein.isBlank() || carbs.isBlank() || sugar.isBlank() || fiber.isBlank() || fats.isBlank() || sodium.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                } else if (mealName.contains("$")) {
                    JOptionPane.showMessageDialog(null, "The use of the $ character is not permitted");
                } else if (!isInt(calories) || !isInt(protein) || !isInt(carbs) || !isInt(sugar) || !isInt(fiber) || !isInt(fats) || !isInt(sodium)) {
                    JOptionPane.showMessageDialog(null, "Only integer values are allowed for macro fields");
                } else {
                    row[0] = mealName;
                    row[1] = calories;
                    row[2] = protein;
                    row[3] = carbs;
                    row[4] = sugar;
                    row[5] = fiber;
                    row[6] = fats;
                    row[7] = sodium;
                    model.addRow(row);
                    numCalories += Integer.parseInt(calories);
                    numProtein += Integer.parseInt(protein);
                    numCarbs += Integer.parseInt(carbs);
                    numSugar += Integer.parseInt(sugar);
                    numFiber += Integer.parseInt(fiber);
                    numFats += Integer.parseInt(fats);
                    numSodium += Integer.parseInt(sodium);
                    caloriesEaten.setText(numCalories + "");
                    caloriesRemaining = calorieGoal - numCalories + exerciseCalories;
                    caloriesNumbers.setText(caloriesRemaining + "");
                    proteinSum.setText("Protein: " + numProtein + "g");
                    carbsSum.setText("Carbs: " + numCarbs + "g");
                    sugarSum.setText("Sugar: " + numSugar + "g");
                    fiberSum.setText("Fiber: " + numFiber + "g");
                    fatsSum.setText("Fats: " + numFats + "g");
                    sodiumSum.setText("Sodium: " + numSodium + "mg");
                    mealNameField.setText("");
                    caloriesField.setText("");
                    proteinField.setText("");
                    carbsField.setText("");
                    sugarField.setText("");
                    fiberField.setText("");
                    fatsField.setText("");
                    sodiumField.setText("");
                    
                    try {
						JOptionPane.showMessageDialog(null, client.sendMealInfo(calories, protein, carbs, sugar, fiber, fats, sodium));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
            }
        });   
    }

    public JLabel newText(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(975, y, 120, 20);
        label.setFont(Const.SMALLER_FONT);
        this.add(label);
        return label;
    }

    public JTextField newField(int y){
        JTextField button = new JTextField();
        button.setBounds(1075, y, 120, 20);
        this.add(button);
        return button;
    }

    public JButton newNavBarButton(String name, int x, ImageIcon icon) {
        JButton button = new JButton(name);
        button.setBackground(Const.NAV_BAR_COLOUR);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        button.setIcon(icon);
        button.setFont(Const.BUTTON_FONT);
        button.setBounds(x, 570, 256, 125);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        this.add(button);
        return button;
    }
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == profile) {
            Window.layout.show(Window.container, "profile");
        }

        else if(e.getSource() == history) {
            Window.layout.show(Window.container, "history");
        }

        else if (e.getSource() == workout) {
            Window.layout.show(Window.container, "workout");
        }

        else if (e.getSource() == food) {
            Window.layout.show(Window.container, "nutrition");
        }

        else if (e.getSource() == social) {
            Window.layout.show(Window.container, "social");
        }

    }
    
    public static boolean isInt(String str) { 
		try {  
			Integer.parseInt(str);  
			return true;
		} catch (NumberFormatException e) {  
			return false;  
		}  
	}

}