package gritnessApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gritnessApp.LoginScreen.GraphicsPanel;

public class InformationTab extends JPanel implements ActionListener{
	
    private JLabel background, quote, text, age,  account, quoteIcon, name, height, weight, calorieGoal;
    private JTextField ageField, nameField, heightField, weightField, calorieField;
    private JPasswordField passwordField;
    private JButton login;
    
	InformationTab(){
		quote = new JLabel("<html> \"When you hit failure, your workout has just begun.\"<br/>- Ronnie Coleman</html>");
		quote.setForeground(Color.white);
		quote.setFont(Const.TEXT_FONT2);
		quote.setBounds(50, 50, Const.MAIN_LENGTH, 150);
		quoteIcon = new JLabel (Const.QUOTE_ICON);
		quoteIcon.setBounds(50,25,50,50);
		
		background = new JLabel(Const.COVER_PHOTO);
        background.setBounds(0,0,1280,720);
		
        text = newInformationPanel("Before we get started, tell us a little about yourself.", 175);

        name = newInformationPanel("Display Name:", 240);
        age = newInformationPanel("Age:", 315);
        height = newInformationPanel("Height (CM):", 390);
        weight = newInformationPanel("Weight (KG):", 465);
        calorieGoal = newInformationPanel("Daily Calorie Goal:", 540);
        
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
	
	public JLabel newInformationPanel(String name, int y) {
		JLabel label = new JLabel(name);
		label.setBounds(50, y, Const.MAIN_LENGTH, 50);
		label.setFont(Const.TEXT_FONT);
		label.setForeground(Color.WHITE);
		this.add(label);
		return label;
	}
	
	public JTextField newTextField(int y){
		JTextField field = new JTextField();
		field.setBounds(50, y, 250, 25);
		field.addActionListener(this);
		field.setFont(Const.FIELD_FONT);
		this.add(field);
		return field;
	}

    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            // Anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
        }
        
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
            Window.layout.show(Window.container, "profile");
        }
		// TODO Auto-generated method stub
		
	}

}
