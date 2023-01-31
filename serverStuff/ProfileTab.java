package gritnessApp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * [ProfileTab.java]
 * The profile tab of the application
 * @author Jason Wu
 * @author Ryan Zhou
 * @author Justin Zhou
 * @version 21, January 2023
 */
public class ProfileTab extends JPanel implements ActionListener  {
    Client client;
    JFrame window;
    User user;
    JButton profile, workout, food, social, history;
    JButton ageButton, heightButton, BMIButton, BMRButton, nameButton, weightButton;
    String age, height, name;
    JTextField heightField, weightField, ageField;
    BarGraph barGraph;
    Object[] BMRMessage, BMIMessage;
    int[] lineGraphData, barGraphData;
    LineGraph lineGraph;

    /**
     * ProfileTab
     * Constructs the profile tab of the application
     * @param client User client
     * @throws IOException
     */
    ProfileTab(Client client) throws IOException { 
        this.client = client;
        
        window = new JFrame();
        
        //adding buttons
        ageButton = newProfileButton("Age:", 50, 110 ,145);
        weightButton = newProfileButton("Weight (KG):", 50, 195 ,185);
        heightButton = newProfileButton("Height (CM):", 50, 280, 185);
        BMIButton = newProfileButton("BMI:", 50, 365 ,145);
        BMRButton = newProfileButton("BMR: ", 50, 450 , 145);
        
        profile = newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social = newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        profile.setBackground(Const.BUTTON_COLOUR2.brighter());
        
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
        importProfileData();
        
    }  
    
    /**
     * importProfileData
     * Integrates profile data from information tab
     * @throws IOException
     */
    public void importProfileData() throws IOException {
        barGraphData = getGraphData(client.getProfileWorkoutNumHistory(client.getUsername()));
        lineGraphData = getGraphData(client.getProfileCalHistory(client.getUsername()));
        String info = client.getProfileInfo();
        
        String[] split = info.split("\\$+");
        
        nameButton = newProfileButton("" + split[0], 50 , 25, 250);
        nameButton.setIcon(Const.PROFILE_PIC);
        nameButton.setSize(nameButton.getPreferredSize());
        
        ageButton.setText("Age: " + Integer.parseInt(split[1]));
        ageField.setText(split[1]);
        ageButton.setSize(ageButton.getPreferredSize());
        
        weightButton.setText("Weight: " + Double.parseDouble(split[2]));
        weightField.setText(split[2]);
        weightButton.setSize(weightButton.getPreferredSize());
        
        heightButton.setText("Height: " + Integer.parseInt(split[3]));
        heightField.setText(split[3]);
        heightButton.setSize(heightButton.getPreferredSize());
        
        addBMRAndBMICheck();
    }
    
    /**
     * getGraphData
     * Converts data into a graph
     * @param data Graph data
     * @return Graphed data
     */
    public int[] getGraphData(String data) {
        if (data.isBlank()) {
            int[] dataArr = {0, 0, 0, 0, 0, 0, 0};
            return dataArr;
        }
        String[] dataArr = data.split("\\$+");
        int[] graphData = new int[7];
        for(int i = 0; i < dataArr.length; i += 2) {
            String dayOfWeek = dataArr[i];
            int calories = Integer.parseInt(dataArr[i + 1]);
            switch(dayOfWeek) {
                case "SUNDAY":{
                    graphData[0] = calories;
                    break;
                } case "MONDAY":{
                    graphData[1] = calories;
                    break;
                } case "TUESDAY":{
                    graphData[2] = calories;
                    break;
                } case "WEDNESDAY":{
                    graphData[3] = calories;
                    break;
                } case "THURSDAY":{
                    graphData[4] = calories;
                    break;
                } case "FRIDAY":{
                    graphData[5] = calories;
                    break;
                } case "SATURDAY":{
                    graphData[6] = calories;
                    break;
                }
            }
        }
        return graphData;
    }
    
    /**
     * paintComponent
     * Draws the graphs and data
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        barGraph = new BarGraph(barGraphData, "Day", "# Workouts", "WORKOUTS", 600, 400, 5);
        barGraph.draw(g);
        
        lineGraph = new LineGraph(lineGraphData, "Day", "Calories", "CALORIES", 950, 400, 2500);
        lineGraph.draw(g);
    }
    
    /**
     * actionPerformed
     * Records all button clicks
     */
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
        else if(e.getSource() == nameButton) {
            String name = JOptionPane.showInputDialog(window, "Enter name: ", null);
            if (name != null) {
                if (name.contains("$")) {
                    JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
                } else if (!name.isBlank()) {
                    nameButton.setText("Name: " + name);
                    nameButton.setSize(nameButton.getPreferredSize());
                    client.sendName(name);
                } else {
                    JOptionPane.showMessageDialog(this, "Name must contain characters");
                }
            }
        }
        else if(e.getSource() == ageButton) {
            String age = JOptionPane.showInputDialog(window,
                                                     "Enter age: ", null);
            if (age != null) {
                if (!isInt(age)) {
                    JOptionPane.showMessageDialog(this, "Age must be an integer");
                } else {
                    ageButton.setText("Age: " + age);
                    ageField.setText(age);
                    ageButton.setSize(ageButton.getPreferredSize());
                    client.sendAge(Integer.parseInt(age));
                }
                addBMRAndBMICheck();
            }
        }
        else if(e.getSource() == heightButton) {
            String height = JOptionPane.showInputDialog(window, "Enter height (cm): ", JOptionPane.OK_CANCEL_OPTION);
            if (height != null) {
                if (!isInt(height)) {
                    JOptionPane.showMessageDialog(this, "Height must be an integer");
                } else {
                    heightButton.setText("Height: " + height +  " cm"); 
                    heightField.setText(height);
                    heightButton.setSize(heightButton.getPreferredSize());
                    client.sendHeight(Integer.parseInt(height));
                }
                addBMRAndBMICheck();
            }
        }
        else if(e.getSource() == weightButton) {
            String weight = JOptionPane.showInputDialog(window, "Enter weight(kg): ", JOptionPane.OK_CANCEL_OPTION);
            if (weight != null) {
                if (!isDouble(weight)) {
                    JOptionPane.showMessageDialog(this, "Weight must be an integer");
                } else {
                    weightButton.setText("Weight: " + weight + " kg");
                    weightField.setText(weight);
                    weightButton.setSize(weightButton.getPreferredSize());
                    client.sendWeight(Double.parseDouble(weight));
                }
                addBMRAndBMICheck();
            }
        }
    }
    
    /**
     * addBMRAndBMICheck
     * Converts input into BMR AND BMI values
     */
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
    
    /**
     * newProfileButton
     * Draws a profile tab button
     * @param name Button text
     * @param x X coordinate of button 
     * @param y Y coordinate of button
     * @param width Width of button
     * @return Designed button
     */
    public JButton newProfileButton(String name, int x, int y, int width) {
        JButton button = new JButton();     
        
        button.setForeground(Color.BLACK);
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
    
    /**
     * newNavBarButton
     * Draws the navigation bar
     * @param name Navigation button text
     * @param x X coordinate of button
     * @param icon Image of button
     * @return designed button
     */
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
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        
        this.add(button);
        return button;
    }
    
    /**
     * isInt
     * Check if it is an integer
     * @param str String of value
     * @return boolean whether or not it is an integer
     */
    public static boolean isInt(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        } catch (NumberFormatException e) {  
            return false;  
        }  
    }
    
    /**
     * isDouble
     * Check if it is a double
     * @param str String of value
     * @return boolean whether or not it is a double
     */
    public static boolean isDouble(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch (NumberFormatException e) {  
            return false;  
        }  
    }
}