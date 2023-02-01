package gritnessApp;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.Month;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;



/**
 * [HistoryTav.java]
 * This class will draw the history tab
 * @author Jason Wu
 * @author Ryan Zhou
 * @version 1.0 Jan, 2023
 */
public class HistoryTab extends JPanel implements ActionListener{
    JLabel age;
    JFrame window;
    JButton profile, workout, food, social, history;
    JLabel dateLabel, workoutLabel, caloriesLabel;
    JComboBox<String> dateComboBox;
    JTable workoutTable, nutritionTable;
    JScrollPane workoutScrollPane;
    String selectedDate;
    String selectedDateString;
    String[] workoutColumn, nutritionColumn;
    String[][] workoutData, nutritionData;
    final int NUMBER_ROWS_DISPLAYED = 5;
    final int ROW_HEIGHT = 66;
    final int TABLE_WIDTH = Const.MAIN_LENGTH / 2 - 40;
    final int TABLE_HEIGHT = NUMBER_ROWS_DISPLAYED * ROW_HEIGHT;
    Client client;
    
    /**
     * HistoryTab
     * Constructs the history tab of the application
     * @param client User client
     * @throws IOException
     */
    HistoryTab(Client client) throws IOException {
    	this.addComponentListener(new ComponentListenerPanel());
    	this.client = client;
    	
    	String[] split = client.getHistoryTab().split("\\$+");
    	
    	
    	dateComboBox = new JComboBox<>();

        for (int i = 0; i < split.length; i++) {
        	String[] dateSplit = split[i].split("-");
            String dateString = Month.of(Integer.parseInt(dateSplit[1])) + " " + dateSplit[2] + ", " + dateSplit[0];
            dateComboBox.addItem(dateString);
            dateComboBox.setFont(new Font("Calibri", Font.PLAIN, 25));

            if(i == 0) {
            	selectedDate = dateString;
            	selectedDateString = split[i];
            }
        }
        
        dateComboBox.addActionListener(this);
        int x = (int)((Const.MAIN_LENGTH - 300)/2);
        
        dateComboBox.setBounds(x,0,300,35);
        dateComboBox.setSize(dateComboBox.getPreferredSize());  
        
        profile = newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social = newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        history.setBackground(Const.BUTTON_COLOUR2.brighter());       
        
        String[] dayInfo = client.getDayInfo(selectedDateString).split("\\$+");
        String[] workoutDayInfo = client.getWorkoutDayInfo(selectedDateString).split("\\$+");
        
        workoutColumn = new String[]{"Name", "Time"};
        
        workoutData = new String[][] {
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""},
        	{"", ""}
        };
        
        workoutTable = newTable(workoutColumn, workoutData,30);
        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBounds(30,225,TABLE_WIDTH,TABLE_HEIGHT);
        
        for (int i = 0; i < workoutDayInfo.length; i++) {
        	if (i % 2 == 0) {
        		workoutTable.setValueAt(workoutDayInfo[i], i / 2, 0);
        	} else {
        		workoutTable.setValueAt(Math.round((Integer.parseInt(workoutDayInfo[i]) / 60000.0) * 100) / 100.0 + " mins", (int) Math.floor(i / 2), 1);
        	}
        }
        
        this.add(scrollPane, BorderLayout.CENTER);
    	
        nutritionColumn = new String[] {"Macro", "Total"};
        nutritionData = new String[][] {
        	{"Macro", "Total"},
        	{"Calories",dayInfo[0]},
        	{"Protein",dayInfo[1]},
        	{"Carbs",dayInfo[2]},
        	{"Fats",dayInfo[3]}
        };
        
        nutritionTable = new JTable(nutritionData, nutritionColumn);
        nutritionTable.setBorder(new LineBorder(Color.GRAY, 1));
        
        nutritionTable = newTable(nutritionColumn, nutritionData,TABLE_WIDTH + 40);
        
        dateLabel = newDisplayLabel(selectedDate, Const.HISTORY_LABEL_FONT,(Const.MAIN_LENGTH - 500)/ 2, 55,500, 65);
        workoutLabel= newDisplayLabel("Workout", Const.HISTORY_LABEL_FONT, workoutTable.getX() + 75, 130, TABLE_WIDTH - 150, 90);
        caloriesLabel = newDisplayLabel("Nutrition", Const.HISTORY_LABEL_FONT, nutritionTable.getX() + 75, 130, TABLE_WIDTH - 150, 90);
        

        this.add(dateComboBox);
        this.setVisible(true);
        this.setLayout(null);
        
    }
    
    /**
     * ComponentListenerPanel
     * This class will listen for components that are interacted with 
     * @author Ryan Zhou
     * @author Jason Wu
     * @version Jan, 2023
     */
    private class ComponentListenerPanel implements ComponentListener {
    	@Override
    	/**
         * componentShown
         * Detects when a component is show
         * @param client User client
         */
    	public void componentShown(ComponentEvent evt) {
            try {
				updateData();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		@Override
		public void componentResized(ComponentEvent e) {
		}
		@Override
		public void componentMoved(ComponentEvent e) {
		}
		@Override
		public void componentHidden(ComponentEvent e) {
		}
    }
    
    /**
     * updateData
     * updates the nutrition table and the workout table with the information from a given day
     */
    public void updateData() throws IOException {
        String[] dayInfo = client.getDayInfo(selectedDateString).split("\\$+");
        
        nutritionTable.setValueAt(dayInfo[0], 1, 1);
        nutritionTable.setValueAt(dayInfo[1], 2, 1);
        nutritionTable.setValueAt(dayInfo[2], 3, 1);
        nutritionTable.setValueAt(dayInfo[3], 4, 1);
        
        String[] workoutDayInfo = client.getWorkoutDayInfo(selectedDateString).split("\\$+");
        
        for (int i = 0; i < workoutDayInfo.length; i++) {
        	if (i % 2 == 0) {
        		workoutTable.setValueAt(workoutDayInfo[i], i / 2, 0);
        	} else {
        		workoutTable.setValueAt(Math.round((Integer.parseInt(workoutDayInfo[i]) / 60000.0) * 100) / 100.0 + " mins", (int) Math.floor(i / 2), 1);
        	}
        }
    }
    /**
     * newTable
     * Creates a JTable that will store workout and nutritional information
     * @param String[] columns: the column headers
     * @param int X: the x coordinate of the table
     * @param String[][] data: the data of the table
     * @return Designed table
     */
    public JTable newTable(String[] columns, String[][] data, int x) {
    	JTable table = new JTable(data, columns);
    	
    	table.setRowHeight(ROW_HEIGHT);
    	table.setFont(new Font("Calibri", Font.PLAIN, 30));
    	table.setShowHorizontalLines(false);
    	table.setShowVerticalLines(false);
    	table.setBounds(x,225,TABLE_WIDTH,330);
    	
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
        else if(e.getSource() == dateComboBox) {
        	selectedDate = (String)(dateComboBox.getSelectedItem());
        	String[] str = String.valueOf(dateComboBox.getSelectedItem()).split(" ");
        	selectedDateString = str[2] + "-" + numericalMonthValue(str[0]) + "-" + str[1].replaceAll(",", "");

        	dateLabel.setText(selectedDate.replace('-', ' '));
        	dateLabel.setSize(dateLabel.getPreferredSize());
        	try {
				updateData();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
    }
    
    /**
     * newDisplayLabel
     * Creates a JLabel that will display information
     * @param String text: the text to be displayed on the label
     * @param Font font: the font of the label
     * @param int X: the x coordinate of the label
     * @param int Y: the y coordinate of the label]
     * @param int width: the width of the table
     * @param int height: the height of the table
     * @return returns the designed Label
     */
    public JLabel newDisplayLabel(String text, Font font, int x, int y, int width, int height) {
    	JLabel label = new JLabel(text);
    	label.setFont(font);
    	label.setFocusable(true);
    	label.setBounds(x,y,width,height);
    	label.setOpaque(true);
    	label.setBackground(Const.BUTTON_COLOUR);
    	label.setForeground(Color.white);
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalAlignment(JLabel.BOTTOM);
    	this.add(label);
    	return label;
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
     * numericalMonthValue
     * Converts a numerical string representation of a month into the text of the month
     * @param String str: string representation of a month
     * @return String representation
     */
    public String numericalMonthValue(String str) {
    	if (str.equals("JANUARY")) {
    		return "01";
    	} else if (str.equals("FEBRUARY")) {
    		return "02";
    	} else if (str.equals("MARCH")) {
    		return "03";
    	} else if (str.equals("APRIL")) {
    		return "04";
    	} else if (str.equals("MAY")) {
    		return "05";
    	} else if (str.equals("JUNE")) {
    		return "06";
    	} else if (str.equals("JULY")) {
    		return "07";
    	} else if (str.equals("AUGUST")) {
    		return "08";
    	} else if (str.equals("SEPTEMBER")) {
    		return "09";
    	} else if (str.equals("OCTOBER")) {
    		return "10";
    	} else if (str.equals("NOVEMBER")) {
    		return "11";
    	} else if (str.equals("DECEMBER")) {
    		return "12";
    	} else {
    		return "";
    	}
    }
	
}
