package gritnessApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class SocialTab extends JPanel implements ActionListener{
    JLabel name,age, weight, height, BMR, BMI;
    JButton profile, workout, food, social, history, addFriend;
    final int TABLE_WIDTH = 350;
    final int NUM_FRIENDS_DISPLAYED = 4;
    final int NAV_BUTTON_HEIGHT = 125;
    final int ROW_HEIGHT = (Const.MAIN_WIDTH  - NAV_BUTTON_HEIGHT) / NUM_FRIENDS_DISPLAYED;
    DemoMouseListener mouseListener;
    String[] friendsColumn;
    Object[][] friendsData;
    ProfileTab profileTab;
    JTable friendList;
    BarGraph numberWorkoutsGraph;
    LineGraph caloriesGraph;
    int[] numberWorkoutsData, caloriesData;
    SocialTab(){
    	
    	
    	//ngl im not sure what i can design here without interfering with the actual data itself
    	
    	addFriend = new JButton ("Add New Friend");
    	addFriend.addActionListener(this);
    	addFriend.setBounds(50, 50, 350, 65);
    	addFriend.setFont(Const.BUTTON_FONT);
    	addFriend.setBackground(Const.BUTTON_COLOUR);
    	addFriend.setForeground(Color.white);
        addFriend.setFocusable(false);
        
        name = new JLabel("'s Profile");
        name.setFont(Const.PROFILE_BUTTON_FONT);
        name.setBounds(50,115,700,100);
        
        //profileTab = new ProfileTab();
        
        numberWorkoutsData = new int[]{1,3,2,4,2,1,5};
        caloriesData = new int[] {0,0,0,0,0,0,0};
        
        age = newLabel("Age:", 50,205, 145);
        weight = newLabel("Weight:", 295,205,225);
        height = newLabel("Height:", 50,335, 225);
        BMI = newLabel("BMI:", 50,465,145);
        BMR = newLabel("BMR: ", 290, 465, 175);
       
        
        profile =  newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history =  newNavBarButton ("History", 250, Const.HISTORY_ICON);
        workout =  newNavBarButton ("Workout", 500, Const.WORKOUT_ICON);
        food =  newNavBarButton ("Food", 750, Const.FOOD_ICON);
        social =  newNavBarButton ("Social", 1000, Const.SOCIAL_ICON);
        
        mouseListener = new DemoMouseListener();
        friendsColumn = new String[]{""};
        friendsData = new Object[][] {
        	{"Jason"},
        	{"Ryan"},
        	{"Nathan"},
        	{"Justin"},
        	{"Justin"},
        	{"Justin"},
        	{"Justin"},
        	{"Justin"},
        };
        
        friendList = newTable(friendsColumn, friendsData, Const.MAIN_LENGTH - TABLE_WIDTH);
        JScrollPane scrollPane = new JScrollPane(friendList);
        
        scrollPane.setBounds(Const.MAIN_LENGTH - TABLE_WIDTH - 15,0,TABLE_WIDTH,Const.MAIN_WIDTH - 150);
        friendList.addMouseListener(mouseListener);
        
        this.add(scrollPane, BorderLayout.CENTER);
        
        this.add(addFriend);
        this.add(name);
        this.setVisible(true);
        this.setLayout(null);
    }
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        numberWorkoutsGraph = new BarGraph(numberWorkoutsData, "Day", "Calories", "Workouts", 600, 240, 5);
        numberWorkoutsGraph.draw(g);
        
        caloriesGraph = new LineGraph(caloriesData, "Day", "Calories", "Calories", 600, 500, 2500);
        caloriesGraph.draw(g);
    }
    public void updateFriendsProfileInformation(int age, int weight, int height, int BMI, int BMR, int[] calorieData, int[] numberWorkoutsData) {
    	this.age.setText("Age:" + age);
    	this.age.setPreferredSize(this.age.getPreferredSize());
    	
    	this.weight.setText("Weight: " + weight);
    	this.weight.setPreferredSize(this.age.getPreferredSize());
    	
    	this.height.setText("Height: " + height);
    	this.height.setPreferredSize(this.age.getPreferredSize());
    	
    	this.BMI.setText("BMI: " + BMI);
    	this.BMI.setPreferredSize(this.age.getPreferredSize());
    	
    	this.BMR.setText("BMR: " + BMR);
    	this.BMR.setPreferredSize(this.age.getPreferredSize());
    	
    	this.caloriesData = calorieData;
    	this.numberWorkoutsData = numberWorkoutsData;
    	
    	caloriesGraph = new LineGraph(caloriesData, "Day", "Calories", "Calories", 600, 500, 2500);
    	numberWorkoutsGraph = new BarGraph(this.numberWorkoutsData, "Day", "Calories", "Workouts", 600, 240, 5);
    	
    	this.repaint();
    }
    public class DemoMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int row = friendList.rowAtPoint(e.getPoint());
            int col = friendList.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
            	String selectedFriend = friendList.getValueAt(row, col) + "";
            	name.setText(selectedFriend + "'s Profile");
            	name.setPreferredSize(name.getPreferredSize());
            	updateFriendsProfileInformation(13,145,145, 22, 22, new int[] {1500,2000,123,1231,2300,0,0}, new int[] {1,2,3,4,4,5,2});
            }
        }
        public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    }
    public JLabel newLabel(String name, int x, int y, int width) {
    	JLabel button = new JLabel();    	
    	
    	button.setBackground(Const.BACKGROUND_COLOUR);
    	button.setForeground(Color.black);
    	button.setFocusable(false);
    	button.setFont(Const.PROFILE_BUTTON_FONT);
    	button.setText(name);
    	button.setBounds(x,y,width,70);
        
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
    public JTable newTable(String[] columns, Object[][] data, int x) {
    	JTable table = new JTable(data, columns);
    	
    	table.setRowHeight(ROW_HEIGHT);
    	table.setFont(new Font("Calibri", Font.PLAIN, 30));
    	table.setShowHorizontalLines(true);
    	table.setShowVerticalLines(false);
    	table.setBorder((new LineBorder(Color.GRAY, 1)));
    	table.setBackground(Const.BACKGROUND_COLOUR);
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    	centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    	for(int i = 0; i < columns.length; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    	}
    	
    	this.add(table);

    	return table;
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
        else if(e.getSource() == addFriend) {
        	String friendsName = JOptionPane.showInputDialog(this,
                    "Enter Friend's Username: ", null);
        	
        }
    }

}