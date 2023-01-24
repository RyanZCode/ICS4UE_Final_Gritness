package gritnessApp;

import gritnessApp.NavigationBar;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileTab extends JPanel implements ActionListener  {
    Client Client;
    JButton profile, workout, food, social, history;
    JButton ageButton, heightButton, BMIButton, BMRButton, nameButton, weightButton;
    String age, height, name;
    int BMI, BMR;
    BarGraph barGraph;
    LineGraph lineGraph;
    ProfileTab(){   
        nameButton = newProfileButton("Name:", 80,35,165);
        ageButton = newProfileButton("Age:", 75,165,145);
        weightButton = newProfileButton("Weight:", 320,165,185);
        heightButton = newProfileButton("Height:", 75,295, 185);
        BMIButton = newProfileButton("BMI:", 75,425,145);
        BMRButton = newProfileButton("BMR: ", 355, 425, 145);
        
        profile = newNavBarButton("Profile", 0, Const.PROFILE_ICON);
        profile.setBackground(Const.NAV_BAR_COLOUR.brighter());
        history = newNavBarButton("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton("Food", 768, Const.FOOD_ICON);   
        social = newNavBarButton("Social", 1024,Const.SOCIAL_ICON);
        
        this.setVisible(true);
        this.setLayout(null);
    }	 
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int[] barGraphData = {1, 2, 3, 4, 3, 2, 1};
        barGraph = new BarGraph(barGraphData, "Day", "Calories", "Calories", 850, 275, 5);
        barGraph.draw(g);
        
        int[] lineGraphData = {450, 300, 1500, 1770, 690, 580, 280};
        lineGraph = new LineGraph(lineGraphData, "Day", "Calories", "Calories", 850, 550, 2500);
        lineGraph.draw(g);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profile) {
            Window.layout.show(Window.container, "profile");
            profile.repaint();
        }

        else if(e.getSource() == history) {
            Window.layout.show(Window.container, "history");
        }

        else if (e.getSource() == workout) {
            Window.layout.show(Window.container, "workout");
        }

        else if (e.getSource() == food) {
            Window.layout.show(Window.container, "food");
        }

        else if (e.getSource() == social) {
            Window.layout.show(Window.container, "social");
        }
        else if(e.getSource() == ageButton) {
        	
        }
    }
    public JButton newProfileButton(String name, int x, int y, int width) {
    	JButton button = new JButton();
    	button.setBackground(Const.BACKGROUND_COLOUR);
    	button.setForeground(Color.black);
    	button.setFocusable(false);
    	button.setFont(Const.PROFILE_BUTTON_FONT);
    	button.setText(name);
    	button.setBounds(x,y,width,70);
    	button.addActionListener(this);
        button.setBorderPainted(false);
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
    	button.setHorizontalAlignment(JButton.CENTER);
    	button.setVerticalAlignment(JButton.CENTER);
    	this.add(button);
    	return button;
    }
}