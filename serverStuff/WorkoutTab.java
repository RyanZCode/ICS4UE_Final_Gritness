package gritnessApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WorkoutTab extends JPanel implements ActionListener{
    JLabel myWorkout, timeLabel, workoutTitle;
    JButton profile, workout, beginWorkout, food, social, history, endWorkout, cancelWorkout;
    JTextField workoutName;
    JTable workoutTable;
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String[] workoutColumn;
    Object[][] workoutData;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    DemoMouseListener mouseListener;
    final int NUMBER_ROWS_DISPLAYED = 5;
    final int ROW_HEIGHT = 50;
    final int TABLE_WIDTH = 600;
    final int TABLE_HEIGHT = NUMBER_ROWS_DISPLAYED * ROW_HEIGHT;
    Client client;
    Object[][] clearWorkoutData = new Object[][]{
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    	{"","","",""},
    };
    
    WorkoutTab(Client client){
        this.client = client;
    	
    	workoutTitle = newWordPanel ("WORKOUT NAME:", 100, 50, 400, 50);
    	timeLabel = newWordPanel(hours_string+":"+minutes_string+":"+seconds_string, 600, 20, 700, 100);
    	timeLabel.setFont(Const.TITLE_FONT);
    	workoutName = newWorkoutField(100, 100);	
    	
        beginWorkout = newWorkoutButton ("Begin Workout" , 100, 250, 350, 75);
        endWorkout = newWorkoutButton ("End Workout" , 100, 350, 350, 75);
        cancelWorkout = newWorkoutButton ("Cancel Workout" , 950, 25, 300, 70);
        
        profile = newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social = newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        workout.setBackground(Const.BUTTON_COLOUR2.brighter());

        workoutColumn = new String[]{"ACTIVITY","SETS","REPS","WEIGHT"};
        workoutData = new Object[][]{
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        };

        workoutTable = newTable(workoutColumn, workoutData);
        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBounds(600,225,TABLE_WIDTH,TABLE_HEIGHT);
        workoutTable.addMouseListener(mouseListener);
        
        this.add(scrollPane);
        this.setLayout(null);
    }
    
    public JLabel newWordPanel(String name, int x, int y, int bx, int by) {
		JLabel text = new JLabel(name);
		text.setBounds(x, y, bx, by);
		text.setFont(Const.TEXT_FONT2);
		text.setForeground(Color.BLACK);
		this.add(text);
		return text;
	}
    public JTextField newWorkoutField(int x, int y){
		JTextField field = new JTextField();
		field.setBounds(x, y, 400, 50);
		field.addActionListener(this);
		field.setFont(Const.FIELD_FONT2);
		this.add(field);
		return field;
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
    	button.setHorizontalTextPosition(JButton.CENTER);
    	button.setVerticalTextPosition(JButton.BOTTOM);
    	this.add(button);
    	return button;
    }
    
    public JButton newWorkoutButton(String name, int x, int y, int bx, int by) {
        JButton button = new JButton(name);
        button.setBackground(Const.NAV_BAR_COLOUR);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        button.setFont(Const.BUTTON_FONT);
        button.setBounds(x, y, bx, by);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        this.add(button);
        return button;
    }
    
    public JTable newTable(String[] columns, Object[][] data) {
    	JTable table = new JTable(data, columns);
    	
    	table.setRowHeight(ROW_HEIGHT);
    	table.setFont(Const.SMALLER_FONT);
    	table.setShowHorizontalLines(false);
    	table.setShowVerticalLines(false);
    	table.setBounds(500,225,TABLE_WIDTH,330);
    	table.setBorder((new LineBorder(Const.NAV_BAR_COLOUR, 1)));
    	table.setBackground(Const.BACKGROUND_COLOUR);
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    	centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    	for(int i = 0; i < columns.length; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    	}
    	table.getTableHeader().setFont(Const.TEXT_FONT);
    	
    	this.add(table);

    	return table;
    }
    public class DemoMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int row = workoutTable.rowAtPoint(e.getPoint());
            int col = workoutTable.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
                JPopupMenu popup = new JPopupMenu();
                workoutTable.setComponentPopupMenu(popup);

                popup.show(workoutTable, e.getX(), e.getY());
            }
        }
        public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
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
        else if (e.getSource() == beginWorkout) {
        	timer.start();
        }
        else if (e.getSource() == endWorkout) {
        	if (elapsedTime == 0) {
                JOptionPane.showMessageDialog(this, "You must begin a workout first");
        	} else if (workoutName.getText().contains("$")) {
                JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
        	} else if (workoutName.getText().isBlank()) {
        		JOptionPane.showMessageDialog(this, "You must name this workout before ending");
        	} else if (JOptionPane.showConfirmDialog(this, "End Workout?", "Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        	try {
					JOptionPane.showMessageDialog(this, client.sendWorkoutData(workoutName.getText(), elapsedTime));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	
	        	//send the server data
	        	
	        	resetTimer();
	        	clearWorkout();
	        	workoutName.setText("");
        	}
        }
        else if (e.getSource() == cancelWorkout) {
        	if (elapsedTime == 0) {
                JOptionPane.showMessageDialog(this, "You must begin a workout first");
        	} else if (JOptionPane.showConfirmDialog(this, "Cancel Workout?", "Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        		resetTimer();
            	clearWorkout();
        	}
        }
    }
    
    public void clearWorkout() {
//    	DefaultTableModel model = new DefaultTableModel();
//    	workoutTable.setModel(model);
    	
    	workoutTable.removeAll();
    	
    	workoutColumn = new String[]{"ACTIVITY","SETS","REPS","WEIGHT"};
        workoutData = new Object[][]{
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        	{"","","",""},
        };

        workoutTable = newTable(workoutColumn, workoutData);
        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBounds(600,225,TABLE_WIDTH,TABLE_HEIGHT);
        workoutTable.addMouseListener(mouseListener);
    }
    
    Timer timer = new Timer(1000, new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		elapsedTime=elapsedTime+1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    	}
    });
    
    public void resetTimer() {
    	elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timer.stop();
    }
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
        	
        	super.paintComponent(g);
          
        }
    }
}