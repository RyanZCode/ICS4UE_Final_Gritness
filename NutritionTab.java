package gritnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gritnessApp.client.Const;

public class NutritionTab extends JPanel implements ActionListener{
    JLabel meal, calories, protein, carbs, sugar, fiber, fats, sodium, nutrients, caloriesSum, proteinSum, carbsSum, fiberSum, sugarSum, fatSum, sodiumSum;
    JTextField mealNameField, caloriesField, proteinField, carbsField, sugarField, fiberField, fatsField, sodiumField;
    JButton profile, workout, food, social, history, addMeal;
    JTable table;
    JScrollPane scroll;
    String[] columnNames = {"Meal","Calories", "Protein", "Carbs", "Sugar", "Fiber", "Fats", "Sodium"};
    Object[] row = new Object[8];

    NutritionTab(){
        
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(columnNames);

        scroll = new JScrollPane(table);
        scroll.setBounds(100, 270, 800, 300);
        this.add(scroll);  

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
        
        nutrients = new JLabel("Nutrients");
        nutrients.setFont(Const.TITLE_FONT);
        nutrients.setBounds(900, 100, 200, 200);
        this.add(nutrients);
        
//        proteinSum = new JLabel("Protein: " + nutrientSum(model, 2));
//        carbsSum = new JLabel("Carbs: " + nutrientSum(model, 3));
//        sugarSum = new JLabel("Sugar: " + nutrientSum(model, 4));
//        fiberSum = new JLabel("Fiber: " + nutrientSum(model, 5));
//        fatSum = new JLabel("Fats: " + nutrientSum(model, 6));
//        sodiumSum = new JLabel("Sodium: " + nutrientSum(model, 7));
//       

       

        profile =  newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history =  newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout =  newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food =  newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social =  newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        food.setBackground(Const.BUTTON_COLOUR2.brighter());
        
        profile.addActionListener(this);
        history.addActionListener(this);
        workout.addActionListener(this);
        food.addActionListener(this);
        social.addActionListener(this);

        this.add(profile);
        this.add(history);
        this.add(workout);
        this.add(food);
        this.add(social);
        this.setLayout(null);

        addMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = mealNameField.getText();
                row[1] = caloriesField.getText();
                row[2] = proteinField.getText();
                row[3] = carbsField.getText();
                row[4] = sugarField.getText();
                row[5] = fiberField.getText();
                row[6] = fatsField.getText();
                row[7] = sodiumField.getText();
                model.addRow(row);
                proteinSum = new JLabel("Protein: " + nutrientSum(model, 2));
                carbsSum = new JLabel("Carbs: " + nutrientSum(model, 3));
                sugarSum = new JLabel("Sugar: " + nutrientSum(model, 4));
                fiberSum = new JLabel("Fiber: " + nutrientSum(model, 5));
                fatSum = new JLabel("Fats: " + nutrientSum(model, 6));
                sodiumSum = new JLabel("Sodium: " + nutrientSum(model, 7));
            }
        });   
    }
    
    private int nutrientSum(DefaultTableModel table, int row) {
        int sum = 0;
        for(int i=0; i<table.getColumnCount(); i++) {
            sum += (Integer) table.getValueAt(i, row);
        }
        return sum;
    }

    public JLabel newText(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(975, y, 120, 20);
        label.setFont(gritnessApp.Const.SMALLER_FONT);
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

}
