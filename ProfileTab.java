package gritnessApp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Jason
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
        history = newNavBarButton("History", 250, Const.HISTORY_ICON);
        workout = newNavBarButton("Workout", 500, Const.WORKOUT_ICON);
        food = newNavBarButton("Food", 750, Const.FOOD_ICON);   
        social = newNavBarButton("Social", 1000 ,Const.SOCIAL_ICON);
        
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
        barGraph = new BarGraph(barGraphData, "Day", "Calories", "Workouts", 850, 275, 5);
        barGraph.draw(g);
        
        int[] lineGraphData = {450, 300, 1500, 1770, 690, 580, 280};
        lineGraph = new LineGraph(lineGraphData, "Day", "Calories", "Calories", 850, 550, 2500);
        lineGraph.draw(g);
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
        	addBMRAndBMICheck();
        	
        }
        else if(e.getSource() == heightButton) {
        	String height = JOptionPane.showInputDialog(window, "Enter height (cm): ", JOptionPane.OK_CANCEL_OPTION);
        	if(height != null) {
        		heightButton.setText("Height: " + height +  " cm");	
        		heightField.setText(height);
        		heightButton.setSize(heightButton.getPreferredSize());
        	}
        	addBMRAndBMICheck();
        }
        else if(e.getSource() == weightButton) {
        	String weight = JOptionPane.showInputDialog(window, "Enter weight(kg): ", JOptionPane.OK_CANCEL_OPTION);
        	if(weight != null) {
        		weightButton.setText("Weight: " + weight + " kg");
        		weightField.setText(weight);
        		weightButton.setSize(weightButton.getPreferredSize());
        	}
        	addBMRAndBMICheck();
        }
    }
    public void addBMRAndBMICheck() {
    	if(!weightField.getText().equals("") && !heightField.getText().equals("")){
         	Double weight = Double.parseDouble(weightField.getText());
         	Double height = Double.parseDouble(heightField.getText());
         	Double BMI = (weight) / (Math.pow(height / 100,2));
         	BMIButton.setText("BMI: " + Math.round(BMI));
         	BMIButton.setSize(BMIButton.getPreferredSize());
         	
         	if(!ageField.getText().equals("")) {
         		Double age = Double.parseDouble(ageField.getText());
         		Double BMR = 88.362 + (13.397 * weight) + (4.799 + height) - (5.677 * age);
         		BMRButton.setText("BMR: " + Math.round(BMR) + " cal/day");
         		BMRButton.setSize(BMRButton.getPreferredSize());
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
    	button.setBounds(x, 570, 270, 125);
    	button.setHorizontalTextPosition(JButton.CENTER);
    	button.setVerticalTextPosition(JButton.BOTTOM);
    	this.add(button);
    	return button;
    }
}