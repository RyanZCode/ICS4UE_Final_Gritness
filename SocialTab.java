package gritnessApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class SocialTab extends JPanel implements ActionListener{
    JLabel name, calorieGoal, proteinGoal, carbGoal, calories, protein, fats, carbs, fibers;
    JButton profile, workout, food, social, history, addFriend;
    final int TABLE_WIDTH = 350;
    final int NUM_FRIENDS_DISPLAYED = 4;
    final int NAV_BUTTON_HEIGHT = 125;
    final int ROW_HEIGHT = (Const.MAIN_WIDTH  - NAV_BUTTON_HEIGHT) / NUM_FRIENDS_DISPLAYED;
    DemoMouseListener mouseListener;
    String[] friendsColumn;
    Object[][] friendsData;
    JTable friendList;
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
        name.setFont(Const.TITLE_FONT);
        name.setBounds(50,100,700,100);
        
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
    public class DemoMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int row = friendList.rowAtPoint(e.getPoint());
            int col = friendList.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
            	System.out.println(row + " " + col);
            	String selectedFriend = friendList.getValueAt(row, col) + "";
            	name.setText(selectedFriend + "'s Profile");
            	name.setPreferredSize(name.getPreferredSize());
                JPopupMenu popup = new JPopupMenu();
                friendList.setComponentPopupMenu(popup);
//                JOptionPane.showMessageDialog(workoutTable, "Cell contents: " + workoutTable.getValueAt(row, col));
                popup.show(friendList, e.getX(), e.getY());
            }
        }
        public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
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
    }

}