package gritnessApp;

import gritnessApp.NavigationBar;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProfileTab extends JPanel implements ActionListener  {
    Client Client;
    JFrame window;
    JButton profile, workout, food, social, history;
    JButton ageButton, heightButton, BMIButton, BMRButton, nameButton, weightButton;
    String age, height, name;
    JTextField heightField, weightField, ageField;
    BarGraph barGraph;
    Object[] BMRMessage, BMIMessage;
    LineGraph lineGraph;
    
    
    ProfileTab(){   
    	window = new JFrame();
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
        
        heightField  = new JTextField();
        weightField  = new JTextField();
        ageField  = new JTextField();
        
        this.add(heightField);
        this.add(weightField);
        this.add(ageField);
        
        BMRMessage = new Object[]{
        		"Height(cm): ", heightField,
        		"Weight(kg): ", weightField,
        		"Age: ", ageField
        };
        BMIMessage = new Object[] {
        	"Weight(kg): ", weightField,
        	"Height(cm): ", heightField,
        };
        
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
        else if(e.getSource() == nameButton) {
        	String name = JOptionPane.showInputDialog(window, "Enter name: ", null);
        	if(name != null) {
        		nameButton.setText("Name: " + name);
        		nameButton.setSize(nameButton.getPreferredSize());
        	}
        }
        else if(e.getSource() == ageButton) {
        	String age = JOptionPane.showInputDialog(window,
                    "Enter age: ", null);
        	if(age != null) {
        		ageButton.setText("Age: " + age);
        		ageField.setText(age);
        		ageButton.setSize(ageButton.getPreferredSize());
        	}
        	
        	
        }
        else if(e.getSource() == heightButton) {
        	String height = JOptionPane.showInputDialog(window, "Enter height (cm): ", JOptionPane.OK_CANCEL_OPTION);
        	if(height != null) {
        		heightButton.setText("Height: " + height +  " cm");	
        		heightField.setText(height);
        		heightButton.setSize(heightButton.getPreferredSize());
        	}
        	
        	
        }
        else if(e.getSource() == weightButton) {
        	String weight = JOptionPane.showInputDialog(window, "Enter weight(kg): ", JOptionPane.OK_CANCEL_OPTION);
        	if(weight != null) {
        		weightButton.setText("Weight: " + weight + " kg");
        		weightField.setText(weight);
        		weightButton.setSize(weightButton.getPreferredSize());
        	}
        }
        else if(e.getSource() == BMRButton) {
        	JOptionPane.showConfirmDialog(window, BMRMessage, "BMR", JOptionPane.OK_CANCEL_OPTION);
        	if(!weightField.getText().equals("") && !heightField.getText().equals("") && !ageField.getText().equals("")) {
        		double weight = Double.parseDouble(weightField.getText());
            	double height = Double.parseDouble(heightField.getText());
            	double age = Double.parseDouble(ageField.getText());

            	Double BMRCalculation = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 *age);
            	BMRButton.setText("BMR: " + Math.round(BMRCalculation) + "Cal");
            	BMRButton.setSize(BMRButton.getPreferredSize());
        	}
        }else if(e.getSource() == BMIButton) {
        	JOptionPane.showConfirmDialog(window, BMIMessage, "BMI", JOptionPane.OK_CANCEL_OPTION);
        	if(!weightField.getText().equals("") && !heightField.getText().equals("")) {
        		double weight = Double.parseDouble(weightField.getText());
            	double height = Double.parseDouble(heightField.getText());
            	
            	Double BMICalculation = weight / Math.pow(height / 100, 2);
            	BMIButton.setText("BMI: " + Math.round(BMICalculation));
            	BMIButton.setSize(BMIButton.getPreferredSize());
        	}
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
        button.setContentAreaFilled(false);
        
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