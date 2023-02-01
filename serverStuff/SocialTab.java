package gritnessApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;

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
import javax.swing.table.DefaultTableModel;

/**
 * [SocialTab.java]
 * Social tab of the application
 * @author Jason Wu 
 * @author Ryan Zhou
 * @author Nathan Kong
 * @author Justin Zhou
 * @version 1.2 Jan 24, 2023
 */
public class SocialTab extends JPanel implements ActionListener{
    JLabel name,age, weight, height, BMR, BMI;
    JButton profile, workout, food, social, history, addFriend;
    final int TABLE_WIDTH = 350;
    final int NUM_FRIENDS_DISPLAYED = 4;
    final int NAV_BUTTON_HEIGHT = 125;
    final int ROW_HEIGHT = (Const.MAIN_WIDTH  - NAV_BUTTON_HEIGHT) / NUM_FRIENDS_DISPLAYED;
    DemoMouseListener mouseListener;
    String[] friendsColumn;
    String[][] friendsData;
    ProfileTab profileTab;
    JTable friendList;
    BarGraph numberWorkoutsGraph;
    LineGraph caloriesGraph;
    int[] numberWorkoutsData, caloriesData;
    Client client;
    int friendsNum;
    JScrollPane scrollPane;

    /**
     * SocialTab
     * Constructs the social tab of the application
     * @param client
     * @throws IOException
     */
    SocialTab(Client client) throws IOException {
        this.client = client;
        String usernames = client.getFriendUsernames();
        String[] split = usernames.split("\\$+");
        if (!usernames.isBlank()) {
            updateInfo(split, split[0]);
        } else {
            friendsNum = 0;
        }
        this.addComponentListener(new ComponentListenerPanel());
        //Add new friend button
        addFriend = new JButton ("Add New Friend");
        addFriend.addActionListener(this);
        addFriend.setBounds(50, 50, 350, 65);
        addFriend.setFont(Const.BUTTON_FONT);
        addFriend.setBackground(Const.BUTTON_COLOUR);
        addFriend.setForeground(Color.white);
        addFriend.setFocusable(false);

        //Add nav bar buttons
        profile = newNavBarButton ("Profile", 0, Const.PROFILE_ICON);
        history = newNavBarButton ("History", 256, Const.HISTORY_ICON);
        workout = newNavBarButton ("Workout", 512, Const.WORKOUT_ICON);
        food = newNavBarButton ("Food", 768, Const.FOOD_ICON);
        social = newNavBarButton ("Social", 1024, Const.SOCIAL_ICON);
        social.setBackground(Const.BUTTON_COLOUR2.brighter());

        this.add(addFriend);

        this.setVisible(true);
        this.setLayout(null);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Draw graphs when friends are added
        if (friendsNum > 0) {
            numberWorkoutsGraph = new BarGraph(numberWorkoutsData, "Day", "Calories", "Workouts", 600, 240, 5);
            numberWorkoutsGraph.draw(g);

            caloriesGraph = new LineGraph(caloriesData, "Day", "Calories", "Calories", 600, 500, 2500);
            caloriesGraph.draw(g);
        }
    }
    /**
     * updateFriendsProfileInformation
     * Updates profile information of friends when called
     * @param friendUsername
     * @throws IOException
     */
    public void updateFriendsProfileInformation(String friendUsername) throws IOException {
        String friendData = client.getFriendData(friendUsername);
        String[] split = friendData.split("\\$+");

        this.name.setText(split[0] + "'s Profile");
        this.name.setPreferredSize(getPreferredSize());

        this.age.setText("Age:" + split[1]);
        this.age.setPreferredSize(this.age.getPreferredSize());

        this.weight.setText("Weight: " + split[2]);
        this.weight.setPreferredSize(this.age.getPreferredSize());

        this.height.setText("Height: " + split[3]);
        this.height.setPreferredSize(this.age.getPreferredSize());

        this.BMI.setText("BMI: " + split[4]);
        this.BMI.setPreferredSize(this.age.getPreferredSize());

        this.BMR.setText("BMR: " + split[5]);
        this.BMR.setPreferredSize(this.age.getPreferredSize());

        numberWorkoutsData = getGraphData(client.getProfileWorkoutNumHistory(friendUsername));
        caloriesData = getGraphData(client.getProfileCalHistory(friendUsername));

        caloriesGraph = new LineGraph(caloriesData, "Day", "Calories", "Calories", 600, 500, 2500);
        numberWorkoutsGraph = new BarGraph(this.numberWorkoutsData, "Day", "Calories", "Workouts", 600, 240, 5);

        this.repaint();
    }
    /**
     * DemoMouseListener
     * MouseListener for when a friend is clicked, updates their information
     * @author 
     */
    public class DemoMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            int row = friendList.rowAtPoint(e.getPoint());
            int col = friendList.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
                String selectedFriend = friendList.getValueAt(row, col) + "";
                try {
                    updateFriendsProfileInformation(selectedFriend);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
    /**
     * newLabel
     * Creates a custom text label 
     * @param name Text to be displayed
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width of the label
     * @return custom label
     */
    public JLabel newLabel(String name, int x, int y, int width) {
        JLabel button = new JLabel();    	

        button.setBackground(Const.BACKGROUND_COLOUR);
        button.setForeground(Color.black);
        button.setFocusable(false);
        button.setFont(Const.BUTTON_FONT);
        button.setText(name);
        button.setBounds(x,y,width,70);

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
     * newTable
     * Creates a custom JTable
     * @param columns
     * @param data
     * @param x
     * @return custom JTable
     */
    public JTable newTable(String[] columns, String[][] data, int x) {
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
    /**
     * actionPerformed
     * ActionListener for buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Nav bar action listeners
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
        //Action listener when new friend button is clicked
        else if(e.getSource() == addFriend) {
            String friendsName = JOptionPane.showInputDialog(this,
                    "Enter Friend's Username: ", null);
            //Error messages
            if (friendsName != null) {
                if (friendsName.contains("$")) {
                    JOptionPane.showMessageDialog(this, "The use of the $ character is not permitted");
                } else if (friendsName.equals(client.getUsername())) { 
                    JOptionPane.showMessageDialog(this, "Cannot add yourself as a friend");
                    //Try sending username to database to add friend
                } else if (!friendsName.isBlank()) {
                    String serverMessage = null;
                    try {
                        serverMessage = client.sendFriendRequest(friendsName);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //Successfully added friend
                    if (serverMessage.equals("success")) {
                        JOptionPane.showMessageDialog(this, "Friend added");
                        if (friendsNum == 0) {
                            try {
                                updateInfo(client.getFriendUsernames().split("\\$+"), friendsName);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            addFriendToList(friendsName);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, serverMessage);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Username must contain characters");
                }
            }
        }
    }
    /**
     * updateInfo
     * Create new scrollable friends list
     * @param split
     * @param friendUsername Username of the friend to load
     * @throws IOException
     */
    public void updateInfo(String[] split, String friendUsername) throws IOException {   	
        friendsNum = split.length;
        friendsData = new String[friendsNum][1];

        for (int i = 0; i < friendsNum; i++) {
            friendsData[i][0] = split[i];
        }

        mouseListener = new DemoMouseListener();
        friendsColumn = new String[]{""};
        friendList = newTable(friendsColumn, friendsData, Const.MAIN_LENGTH - TABLE_WIDTH);
        scrollPane = new JScrollPane(friendList);

        scrollPane.setBounds(Const.MAIN_LENGTH - TABLE_WIDTH - 15,0,TABLE_WIDTH,Const.MAIN_WIDTH - 150);
        friendList.addMouseListener(mouseListener);

        this.add(scrollPane, BorderLayout.CENTER);

        loadFriendData(friendUsername);
        this.add(name);
    }
    /**
     * loadFriendData
     * Retrieves friend data from database
     * @param friendUsername Username of the friend to load
     * @throws IOException
     */
    public void loadFriendData(String friendUsername) throws IOException {
        String friendData = client.getFriendData(friendUsername);
        String[] split = friendData.split("\\$+");

        name = new JLabel(split[0] + "'s Profile");
        name.setFont(Const.PROFILE_BUTTON_FONT);
        name.setBounds(50,115,700,100);

        numberWorkoutsData = getGraphData(client.getProfileWorkoutNumHistory(friendUsername));
        caloriesData = getGraphData(client.getProfileCalHistory(friendUsername));

        age = newLabel("Age: " + split[1], 50,205, 145);
        weight = newLabel("Weight: " + Math.floor(Double.parseDouble(split[2])) + "kg", 235,205,250);
        height = newLabel("Height: " + Math.floor(Double.parseDouble(split[3])) + "cm", 50,335, 280);
        BMI = newLabel("BMI: " + Math.floor(Double.parseDouble(split[4])), 50,465,200);
        BMR = newLabel("BMR: " + Math.floor(Double.parseDouble(split[5])), 290, 465, 200);
    }
    /**
     * addFriendToList 
     * Adds friend to scrollable friend list
     * @param username Friend username
     */
    public void addFriendToList(String username) {
        DefaultTableModel model = new DefaultTableModel(friendsData, friendsColumn);
        model.addRow(new String[]{username});
        String[][] updatedData = new String[friendsData.length + 1][1];
        updatedData[friendsData.length] = new String[] {username};
        friendsData = updatedData;
        friendList.setModel(model);
        friendList.revalidate();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        friendList.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }
    /**
     * getGraphData
     * converts String data from the server into an array which will be used to generate a graph
     * @param data
     * @return Graph
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
            //Separate into days
            switch(dayOfWeek) {
            case "SUNDAY":{
                graphData[0] = calories;
                break;
            }case "MONDAY":{
                graphData[1] = calories;
                break;
            }case "TUESDAY":{
                graphData[2] = calories;
                break;
            }case "WEDNESDAY":{
                graphData[3] = calories;
                break;
            }case "THURSDAY":{
                graphData[4] = calories;
                break;
            }case "FRIDAY":{
                graphData[5] = calories;
                break;
            }case "SATURDAY":{
                graphData[6] = calories;
                break;
            }
            }
        }
        return graphData;
    }
    
    private class ComponentListenerPanel implements ComponentListener {
    	@Override
    	public void componentShown(ComponentEvent evt) {
            try {
            	String usernames = client.getFriendUsernames();
                String[] split = usernames.split("\\$+");
                if (!usernames.isBlank()) {
                    updateFriendsProfileInformation(split[0]);
                } else {
                    friendsNum = 0;
                }
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
}
