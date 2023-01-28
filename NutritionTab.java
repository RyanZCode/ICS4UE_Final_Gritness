package gritnessApp.client;

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

public class NutritionTab extends JPanel implements ActionListener{
    JLabel meal, calories, protein, carbs, sugar, fiber, fats, sodium;
    JTextField mealNameField, caloriesField, proteinField, carbsField, sugarField, fiberField, fatsField, sodiumField;
    JButton profile, workout, food, social, history, addMeal;
    JTable table;
    JScrollPane scroll;
    String[] columnNames = {"Meal","Calories", "Protein", "Carbs", "Sugar", "Fiber", "Fats", "Sodium"};
    Object[] row = new Object[8];

    NutritionTab(){
        //        age = new JLabel("Nutrition");
        //        age.setFont(Const.TEXT_FONT);
        //        age.setBounds(50, 50, 500, 500);
        //        this.add(age)
        //meals[0] =  {"Chicken Shawarma", "500", "20", "30", "5", "5", "15", "300"};
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

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(columnNames);

        scroll = new JScrollPane(table);
        scroll.setBounds(100, 270, 800, 300);
        this.add(scroll);  

        addMeal = new JButton("Add meal");
        addMeal.setBounds(1010, 520, 129, 20);
        this.add(addMeal);

        profile =  newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history =  newNavBarButton ("History", 250, Const.HISTORY_ICON);
        workout =  newNavBarButton ("Workout", 500, Const.WORKOUT_ICON);
        food =  newNavBarButton ("Food", 750, Const.FOOD_ICON);
        social =  newNavBarButton ("Social", 1000, Const.SOCIAL_ICON);

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
            }
        });   
    }


    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

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
        button.setBounds(x, 570, 270, 125);
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
