package gritnessApp;


import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

//Jason
public class HistoryTab extends JPanel implements ActionListener{
    JLabel age;
    JFrame window;
    JButton profile, workout, food, social, history;
    JLabel dateDisplay, workoutDisplay, nutritionDisplay;
    JComboBox<String> dateComboBox;
    JTable workoutTable, nutritionTable;
    JScrollPane workoutScrollPane, nutritionScrollPane;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EE yyyy MMMM dd");
    String selectedDate;
    String[] workoutColumn, nutritionColumn;
    Object[][] workoutData, nutritionData;
    final int NUMBER_ROWS = 5;
    final int ROW_HEIGHT = 66;
    final int TABLE_WIDTH = Const.MAIN_LENGTH / 2 - 40;
    final int TABLE_HEIGHT = NUMBER_ROWS * ROW_HEIGHT;
    
    HistoryTab(){
    	
    	
    	
    	dateComboBox = new JComboBox<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 31; i++) {
            Date date = calendar.getTime();
            String dateString = dateFormat.format(date);
            dateComboBox.addItem(dateString);
            dateComboBox.setFont(new Font("Calibri", Font.PLAIN, 25));

            calendar.add(Calendar.DATE, 1);
            if(i == 0) {
            	selectedDate = dateString;
            }
        }
        
        dateComboBox.addActionListener(this);
        int x = (int)((Const.MAIN_LENGTH - 200)/2);
        
        dateComboBox.setBounds(x,35,300,35);
        dateComboBox.setSize(dateComboBox.getPreferredSize());
  
        
        profile = newNavBarButton("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton("History", 250, Const.HISTORY_ICON);
        workout = newNavBarButton("Workout", 500, Const.WORKOUT_ICON);
        food = newNavBarButton("Food", 750, Const.FOOD_ICON);   
        social = newNavBarButton("Social", 1000 ,Const.SOCIAL_ICON);
        history.setBackground(Const.NAV_BAR_COLOUR.brighter());

        dateDisplay = newDisplayLabel(selectedDate, Const.HISTORY_LABEL_FONT,(Const.MAIN_LENGTH - 385)/ 2, 75,400,300 );

        workoutColumn = new String[]{"Column 1", "Column 2"};
        workoutData = new Object[][]{
            {"Text 1", "Text 2"},
            {"Text 4", "Text 5"},
            {"Text 7", "Text 8"},
            {"Text 10", "Text 11"},
            {"Text 12", "Text 13"}
        };
        
        nutritionColumn = new String[] {"", "Total","Goal"};
        nutritionData = new Object[][] {
        	{"","Total","Goal"},
        	{"Protein","",""},
        	{"Carbs","",""},
        	{"Fats","",""},
        	{"Sodium","",""}
        };
        
        nutritionTable = new JTable(nutritionData, nutritionColumn);
        nutritionTable.setBorder(new LineBorder(Color.GRAY, 1));
        
        
        
        workoutTable = newTable(workoutColumn, workoutData,30);
        nutritionTable = newTable(nutritionColumn, nutritionData,TABLE_WIDTH + 40);

        this.add(dateComboBox);
        this.add(dateDisplay);
        this.setVisible(true);
        this.setLayout(null);
        
        
        
    }
    public JTable newTable(String[] columns, Object[][] data, int x) {
    	JTable table = new JTable(data, columns);
    	
    	table.setRowHeight(ROW_HEIGHT);
    	table.setShowHorizontalLines(false);
    	table.setShowVerticalLines(false);
    	table.setBounds(x,225,TABLE_WIDTH,TABLE_HEIGHT);
    	table.setBorder((new LineBorder(Color.GRAY, 1)));
    	table.setBackground(Const.BACKGROUND_COLOUR);
    	
    	JScrollPane scrollPane = new JScrollPane(table);
    	add(scrollPane, BorderLayout.CENTER);
    	
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    	for(int i = 0; i < columns.length; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    	}
    	
    	this.add(table);
    	this.add(scrollPane);
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
        else if(e.getSource() == dateComboBox) {
        	selectedDate = (String)(dateComboBox.getSelectedItem());
        	dateDisplay.setText(selectedDate.replace('-', ' '));
        	dateDisplay.setSize(dateDisplay.getPreferredSize());
        }
    }
    public JLabel newDisplayLabel(String text, Font font, int x, int y, int width, int height) {
    	JLabel label = new JLabel(text);
    	label.setFont(font);
    	label.setFocusable(true);
    	label.setBounds(x,y,width,height);
    	label.setSize(label.getPreferredSize());
    	return label;
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
