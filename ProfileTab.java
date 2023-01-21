package gritnessApp;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProfileTab extends JPanel implements ActionListener{
    private JFrame window;
    private GraphicsPanel canvas;
    private JButton profile, history, workout, food, social;
    HistoryTab historyTab;
    WorkoutTab workoutTab;
    NutritionTab nutritionTab;
    SocialTab socialTab;
    
    ProfileTab(){
        window = new JFrame("Profile");
        window.setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        window.add(canvas);
        window.setResizable(false);
        
        window.setVisible(true);
        canvas.setLayout(null);
        profile = new JButton ("PROFILE");
        history = new JButton ("HISTORY");
        workout = new JButton ("WORKOUT");
        food = new JButton ("FOOD");
        social = new JButton ("SOCIAL");
        
        profile.addActionListener(this);
        history.addActionListener(this);
        workout.addActionListener(this);
        food.addActionListener(this);
        social.addActionListener(this);
        
        profile.setFont( new Font("Calibri", Font.BOLD, 40));
        history.setFont( new Font("Calibri", Font.BOLD, 40));
        workout.setFont( new Font("Calibri", Font.BOLD, 40));
        food.setFont( new Font("Calibri", Font.BOLD, 40));
        social.setFont( new Font("Calibri", Font.BOLD, 40));
        
        profile.setBounds(0, 600, 270, 100);
        history.setBounds(250, 600, 270, 100);
        workout.setBounds(500, 600, 270, 100);
        food.setBounds(750, 600, 270, 100);
        social.setBounds(1000, 600, 270, 100);
        
        canvas.add(profile);
        canvas.add(history);
        canvas.add(workout);
        canvas.add(food);
        canvas.add(social);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == history) {
    		historyTab = new HistoryTab();
    		window.dispose();
    	}
    	
    	else if (e.getSource() == workout) {
    		workoutTab = new WorkoutTab();
    		window.dispose();    		
    	}
    	
    	else if (e.getSource() == food) {
    		nutritionTab = new NutritionTab();
    		window.dispose();    		
    	}
    	
    	else if (e.getSource() == social) {
    		socialTab = new SocialTab();
    		window.dispose(); 
    	}
    	
        // TODO Auto-generated method stub
        
    }

}
