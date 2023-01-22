package gritnessApp.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationBar extends JPanel implements ActionListener{

    JButton profile, history, food, workout, social;

    NavigationBar(){
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
       
        this.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profile) {
            Tab.cardLayout.show(Tab.tabs, "profile");
        }

        else if(e.getSource() == history) {
            Tab.cardLayout.show(Tab.tabs, "history");
        }

        else if (e.getSource() == workout) {
            Tab.cardLayout.show(Tab.tabs, "workout");
        }

        else if (e.getSource() == food) {
            Tab.cardLayout.show(Tab.tabs, "nutrition");
        }

        else if (e.getSource() == social) {
            Tab.cardLayout.show(Tab.tabs, "social");
        }
    }
}
