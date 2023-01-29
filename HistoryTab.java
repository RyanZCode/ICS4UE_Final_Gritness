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
    JLabel dateLabel, workoutLabel, caloriesLabel;
    JComboBox<String> dateComboBox;
    JTable workoutTable, nutritionTable;
    JScrollPane workoutScrollPane, nutritionScrollPane;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EE MMMM dd yyyy");
    String selectedDate;
    String[] workoutColumn, nutritionColumn;
    Object[][] workoutData, nutritionData;
    DemoMouseListener mouseListener;
    final int NUMBER_ROWS_DISPLAYED = 5;
    final int ROW_HEIGHT = 66;
    final int TABLE_WIDTH = Const.MAIN_LENGTH / 2 - 40;
    final int TABLE_HEIGHT = NUMBER_ROWS_DISPLAYED * ROW_HEIGHT;
    
    HistoryTab(){    	
    	
    	mouseListener = new DemoMouseListener();
    	
    	dateComboBox = new JComboBox<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 59; i++) {
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
        int x = (int)((Const.MAIN_LENGTH - 300)/2);
        
        dateComboBox.setBounds(x,0,300,35);
        dateComboBox.setSize(dateComboBox.getPreferredSize());
  
        
        profile = newNavBarButton("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton("History", 250, Const.HISTORY_ICON);
        workout = newNavBarButton("Workout", 500, Const.WORKOUT_ICON);
        food = newNavBarButton("Food", 750, Const.FOOD_ICON);   
        social = newNavBarButton("Social", 1000 ,Const.SOCIAL_ICON);
        history.setBackground(Const.NAV_BAR_COLOUR.brighter());

        
        
        
        workoutColumn = new String[]{""};
        workoutData = new Object[][]{
        	{"Lat Pulldown"},
            {"1. 135 x 10lbs"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 10"},
            {"Text 12"}
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
        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBounds(30,225,TABLE_WIDTH,TABLE_HEIGHT);
        workoutTable.addMouseListener(mouseListener);
        
        this.add(scrollPane, BorderLayout.CENTER);
        
        nutritionTable = newTable(nutritionColumn, nutritionData,TABLE_WIDTH + 40);
        
        
        dateLabel = newDisplayLabel(selectedDate, Const.HISTORY_LABEL_FONT,(Const.MAIN_LENGTH - 500)/ 2, 55,500, 65);
        workoutLabel= newDisplayLabel("Workout", Const.HISTORY_LABEL_FONT, workoutTable.getX() + 75, 130, TABLE_WIDTH - 150, 90);
        caloriesLabel = newDisplayLabel("Nutrition", Const.HISTORY_LABEL_FONT, nutritionTable.getX() + 75, 130, TABLE_WIDTH - 150, 90 );
        
        
        this.addMouseListener(mouseListener);
        this.add(dateComboBox);
        this.setVisible(true);
        this.setLayout(null);
        
        
        
    }
    public JTable newTable(String[] columns, Object[][] data, int x) {
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
    
    
    public void mouseClicked(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		System.out.println("a");
    	}
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
        	dateLabel.setText(selectedDate.replace('-', ' '));
        	dateLabel.setSize(dateLabel.getPreferredSize());
        }
    }
    public class DemoMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int row = workoutTable.rowAtPoint(e.getPoint());
            int col = workoutTable.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
                JPopupMenu popup = new JPopupMenu();
                workoutTable.setComponentPopupMenu(popup);
//                JOptionPane.showMessageDialog(workoutTable, "Cell contents: " + workoutTable.getValueAt(row, col));
                popup.show(workoutTable, e.getX(), e.getY());
            }
        }
        public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    }
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