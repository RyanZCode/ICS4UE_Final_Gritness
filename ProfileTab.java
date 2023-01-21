package gritnessApp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileTab extends JPanel implements ActionListener{
    private JFrame window;
    private GraphicsPanel canvas;
    private JButton profile, history, workout, food, social;
    private JLabel age, height, bmi, bmr, name;
    
    ProfileTab(){
    	
    	this.setBackground(Color.WHITE);
    	this.setLayout(null);
    	repaint();
    	this.setVisible(true);
//        window = new JFrame("Profile");
//        window.setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
        canvas = new GraphicsPanel();
//        window.add(canvas);
//        window.setResizable(false);
//        
//        window.setVisible(true);
        canvas.setLayout(null);
        
        age = new JLabel("Age");
        age.setFont(Const.TEXT_FONT);
        age.setBounds(50, 50, 50, 50);
        
        height = new JLabel("Height");
        bmi = new JLabel("BMI");
        bmr = new JLabel("BMR");
        
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
        social.setFont( new Font("Calibri", Font.PLAIN, 35));
        social.setBounds(1000, 570, 270, 125);
        social.setHorizontalTextPosition(JButton.CENTER);
        social.setVerticalTextPosition(JButton.BOTTOM);
        
        this.add(profile);
        this.add(history);
        this.add(workout);
        this.add(food);
        this.add(social);
        this.add(age);
    }	
    
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            
            // Anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
            
            g.setColor(Color.white);

        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
        // TODO Auto-generated method stub
        
    }

}
