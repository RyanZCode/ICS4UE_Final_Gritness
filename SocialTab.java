package gritnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SocialTab extends JPanel implements ActionListener{
    JLabel name, calorieGoal, proteinGoal, carbGoal, calories, protein, fats, carbs, fibers;
    JButton profile, workout, food, social, history, addFriend;
    SocialTab(){
    	
    	
    	//ngl im not sure what i can design here without interfering with the actual data itself
    	
    	addFriend = new JButton ("Add New Friend");
    	addFriend.addActionListener(this);
    	addFriend.setBounds(50, 50, 350, 65);
    	addFriend.setFont(Const.BUTTON_FONT);
    	addFriend.setBackground(Const.BUTTON_COLOUR);
    	addFriend.setForeground(Color.white);
        addFriend.setFocusable(false);
        
        name = new JLabel("____'s Profile");
        name.setFont(Const.TITLE_FONT);
        name.setBounds(200,150,700,100);
        
        profile =  newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history =  newNavBarButton ("History", 250, Const.HISTORY_ICON);
        workout =  newNavBarButton ("Workout", 500, Const.WORKOUT_ICON);
        food =  newNavBarButton ("Food", 750, Const.FOOD_ICON);
        social =  newNavBarButton ("Social", 1000, Const.SOCIAL_ICON);
        
        this.add(profile);
        this.add(history);
        this.add(workout);
        this.add(food);
        this.add(social);
        this.add(addFriend);
        this.add(name);
        this.setLayout(null);
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
    @Override
    public void actionPerformed(ActionEvent e) {
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