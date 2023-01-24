package gritnessApp.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NutritionTab extends JPanel implements ActionListener{
   JLabel age;
   JButton profile, workout, food, social, history;
   JTable table;
   JScrollPane scroll;
   String[] columnNames = {"Meal","Calories", "Protein", "Carbs", "Sugar", "Fiber", "Fats", "Sodium"};
   Object[][] meals = {{"Chicken Shawarma", "500", "20", "30", "5", "5", "15", "300"}};
   
    NutritionTab(){
        age = new JLabel("Nutrition");
        age.setFont(Const.TEXT_FONT);
        age.setBounds(50, 50, 500, 500);
        this.add(age);
       
        table = new JTable(meals, columnNames);
        scroll = new JScrollPane(table);
        scroll.setBounds(100, 270, 800, 300);
        this.add(scroll);        
        
        profile = new JButton ("Profile");
        profile.setBackground(Const.NAV_BAR_COLOUR);
        profile.setForeground(Color.white);
        profile.setFocusable(false);
        profile.addActionListener(this);
        profile.setBorderPainted(false);
        profile.setIcon(Const.PROFILE_ICON);
        profile.setFont(Const.BUTTON_FONT);
        profile.setBounds(0, 570, 270, 125);
        profile.setHorizontalTextPosition(JButton.CENTER);
        profile.setVerticalTextPosition(JButton.BOTTOM);

        history = new JButton ("History");
        history.setBackground(Const.NAV_BAR_COLOUR);
        history.setForeground(Color.white);
        history.setFocusable(false);
        history.setBorderPainted(false);
        history.addActionListener(this);
        history.setIcon(Const.HISTORY_ICON);
        history.setFont(Const.BUTTON_FONT);
        history.setBounds(250, 570, 270, 125);
        history.setHorizontalTextPosition(JButton.CENTER);
        history.setVerticalTextPosition(JButton.BOTTOM);

        workout = new JButton ("Workout");
        workout.setBackground(Const.NAV_BAR_COLOUR);
        workout.setForeground(Color.white);
        workout.setFocusable(false);
        workout.setBorderPainted(false);
        workout.addActionListener(this);
        workout.setIcon(Const.WORKOUT_ICON);
        workout.setFont(Const.BUTTON_FONT);
        workout.setBounds(500, 570, 270, 125);
        workout.setHorizontalTextPosition(JButton.CENTER);
        workout.setVerticalTextPosition(JButton.BOTTOM);

        food = new JButton ("Food");
        food.setBackground(Const.NAV_BAR_COLOUR);
        food.setForeground(Color.white);
        food.setFocusable(false);
        food.setBorderPainted(false);
        food.addActionListener(this);
        food.setIcon(Const.FOOD_ICON);
        food.setFont(Const.BUTTON_FONT);
        food.setBounds(750, 570, 270, 125);
        food.setHorizontalTextPosition(JButton.CENTER);
        food.setVerticalTextPosition(JButton.BOTTOM);

        social = new JButton ("Social");
        social.setBackground(Const.NAV_BAR_COLOUR);
        social.setForeground(Color.white);
        social.setFocusable(false);
        social.setBorderPainted(false);
        social.addActionListener(this);
        social.setIcon(Const.SOCIAL_ICON);
        social.setFont(Const.BUTTON_FONT);
        social.setBounds(1000, 570, 270, 125);
        social.setHorizontalTextPosition(JButton.CENTER);
        social.setVerticalTextPosition(JButton.BOTTOM);
        
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
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
          
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
