package gritnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


//Jason
public class HistoryTab extends JPanel implements ActionListener{
    JLabel age;
    JButton profile, workout, food, social, history;
    HistoryTab(){
        age = new JLabel("History");
        age.setFont(Const.TEXT_FONT);
        age.setBounds(50, 50, 500, 500);
        this.add(age);
        
        profile = newNavBarButton("Profile", 0, Const.PROFILE_ICON);
        profile.setBackground(Const.NAV_BAR_COLOUR.brighter());
        history = newNavBarButton("History", 250, Const.HISTORY_ICON);
        workout = newNavBarButton("Workout", 500, Const.WORKOUT_ICON);
        food = newNavBarButton("Food", 750, Const.FOOD_ICON);   
        social = newNavBarButton("Social", 1000 ,Const.SOCIAL_ICON);
        
        this.setLayout(null);
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
}
