package gritnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * [InformationTab.java]
 * This tab takes in all of the information
 * @author Ryan Zhou
 * @author Justin Zhou
 * @version 1.0 Jan 24, 2023
 */
public class InformationTab extends JPanel implements ActionListener{
	Client client;
    private JLabel background, quote, quoteIcon;
    private JTextField ageField, nameField, heightField, weightField, calorieField;
    private JButton login;
    
    /**
     * InformationTab
     * Constructs the information tab
     * @param client User client
     */
	InformationTab(Client client){
		//draws buttons
		this.client = client;
		quote = new JLabel("<html> \"When you hit failure, your workout has just begun.\"<br/>- Ronnie Coleman</html>");
		quote.setForeground(Color.white);
		quote.setFont(Const.TEXT_FONT2);
		quote.setBounds(50, 50, Const.MAIN_LENGTH, 150);
		quoteIcon = new JLabel (Const.QUOTE_ICON);
		quoteIcon.setBounds(50,25,50,50);
		
		background = new JLabel(Const.COVER_PHOTO);
        background.setBounds(0,0,1280,720);
		
        newInformationPanel("Before we get started, tell us a little about yourself.", 175);

        newInformationPanel("Display Name:", 240);
        newInformationPanel("Age:", 315);
        newInformationPanel("Height (CM):", 390);
        newInformationPanel("Weight (KG):", 465);
        newInformationPanel("Daily Calorie Goal:", 540);
        
        nameField = newTextField(285);
        ageField = newTextField(360);
        heightField = newTextField(435);
        weightField = newTextField(510);
        calorieField = newTextField(585);
        
        login = new JButton("BEGIN YOUR FITNESS JOURNEY.");
        login.setFont(Const.BUTTON_FONT2);
        login.setForeground(Color.WHITE);
        login.setBackground(Const.BUTTON_COLOUR);
        login.setBounds(700, 450, 450, 100);
        login.addActionListener(this); 
        login.setFocusable(false);
        login.setBorderPainted(false);

        this.add(quote);
        this.add(quoteIcon);
        this.add(login);
        this.add(background);
        this.setLayout(null);
        this.setVisible(true);
        
    }
	
	/**
	 * newInformationPanel
	 * Constructs a label 
	 * @param name Displayed text
	 * @param y Y coordinate of text
	 * @return designed label
	 */
	public JLabel newInformationPanel(String name, int y) {
		JLabel label = new JLabel(name);
		label.setBounds(50, y, Const.MAIN_LENGTH, 50);
		label.setFont(Const.TEXT_FONT);
		label.setForeground(Color.WHITE);
		this.add(label);
		return label;
	}
	
	/**
	 * newTextField
	 * Constructs a blank to fill in 
	 * @param y Y coordinate of box
	 * @return designed text box
	 */
	public JTextField newTextField(int y){
		JTextField field = new JTextField();
		field.setBounds(50, y, 250, 25);
		field.addActionListener(this);
		field.setFont(Const.FIELD_FONT);
		this.add(field);
		return field;
	}

	/**
     * actionPerformed
     * Records all button clicks
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String nameText;
			String ageText;
			String heightText;
			String weightText;
			String calorieText;
			
			nameText = nameField.getText();
			ageText = ageField.getText();
			heightText = heightField.getText();
			weightText = weightField.getText();
			calorieText = calorieField.getText();
			
			//input restrictions
			if (nameText.contains("$")) {
				JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
			} else if (!isInt(ageText)) {
				JOptionPane.showMessageDialog(this, "Age must be an integer");
			} else if (!isInt(heightText)) {
				JOptionPane.showMessageDialog(this, "Height must be an integer");
			} else if (!isDouble(weightText)) {
				JOptionPane.showMessageDialog(this, "Weight must be a number");
			} else if (!isInt(calorieText)) {
				JOptionPane.showMessageDialog(this, "Calorie goal must be an integer");
			} else {
				if (!nameText.isBlank() && !ageText.isBlank() && !heightText.isBlank() 
						&& !weightText.isBlank() && !calorieText.isBlank()){
					
					//converts input
					client.sendName(nameText);
					client.sendAge(Integer.parseInt(ageText));
					client.sendHeight(Integer.parseInt(heightText));
					client.sendWeight(Double.parseDouble(weightText));
					client.sendCalorieGoal(Integer.parseInt(calorieText));
					
					ProfileTab profile;
					SocialTab social;
					NutritionTab nutrition;
					
					//navigation bar
					try {
						profile = new ProfileTab(client);
						Window.container.add(profile, "profile");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
	                HistoryTab history = new HistoryTab();
	                Window.container.add(history, "history");
	                
					try {
						nutrition = new NutritionTab(client);
						Window.container.add(nutrition, "nutrition");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					WorkoutTab workout = new WorkoutTab(client);
	                Window.container.add(workout, "workout");
	                
					try {
						social = new SocialTab(client);
						Window.container.add(social, "social");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
		            Window.layout.show(Window.container, "profile");
				} else {
		        	JOptionPane.showMessageDialog(this, "Please fill in all fields");
		        }
			}
        }		
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
