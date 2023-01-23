package gritnessApp.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkoutTab extends JPanel implements ActionListener{
    JLabel myWorkout, sampleWorkouts;
    JButton profile, workout, createWorkout, beginWorkout, food, social, history;
    WorkoutTab(){
        
    	myWorkout = new JLabel("My Workouts");
        myWorkout.setFont(Const.TITLE_FONT);
        myWorkout.setBounds(250,20,700,100);
        
        sampleWorkouts = new JLabel("Sample Workouts");
        sampleWorkouts.setFont(Const.TITLE_FONT);
        sampleWorkouts.setBounds(800,20,350,100);
        
        beginWorkout = new JButton ("Begin Workout");
        beginWorkout.addActionListener(this);
        beginWorkout.setBounds(50, 125, 300, 85);
        beginWorkout.setFont(Const.BUTTON_FONT);
        beginWorkout.setBackground(Const.BUTTON_COLOUR);
        beginWorkout.setForeground(Color.white);
        beginWorkout.setFocusable(false);
        
        createWorkout = new JButton ("Create Workout Plan");
        createWorkout.addActionListener(this);
        createWorkout.setBounds(350, 125, 330, 85);
        createWorkout.setFont(Const.BUTTON_FONT);
        createWorkout.setBackground(Const.BUTTON_COLOUR2);
        createWorkout.setForeground(Color.white);
        createWorkout.setFocusable(false);

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
        this.add(beginWorkout);
        this.add(myWorkout);
        this.add(createWorkout);
        this.add(sampleWorkouts);
        this.setLayout(null);
          
        Scrollbar scroll = new Scrollbar();    
        scroll.setBounds (650, 220, 20, 300);  
        this.add(scroll);    
        
        Scrollbar scroll2 = new Scrollbar();    
        scroll2.setBounds (1245, 0, 20, 550);  
        this.add(scroll2);  

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
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
        	
        	super.paintComponent(g);
          
        }
    }

}
