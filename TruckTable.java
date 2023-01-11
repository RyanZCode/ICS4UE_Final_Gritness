// Import classes
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.io.BufferedWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JOptionPane;

/** [TruckTable.java]
  * Desc: The page that displays the selection of trucks
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class TruckTable extends JPanel implements ActionListener {
    // Variables
    private JLabel title, subtitle;
    private JButton nextPage, backPage;
    private JTable trucksList;
    private ImageIcon logo;
    private ReviewMenu reviewMenu;
    
    /**
     * TruckTable
     * This constructor creates a new TruckTable object.
     */
    public TruckTable() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        title = new JLabel("Trucks List:");
        subtitle = new JLabel("Here is the inputted truck information.");
        nextPage = new JButton("Next Page");
        backPage = new JButton("Back Page");
        Object[] columns = {"Length (m)","Width (m)","Height (m)","Weight (kg)","Q-ty"};
        trucksList = new JTable();
        File file = new File("truckInfo.txt");
        JScrollPane pane = Utility.createTable(trucksList, columns, file, 200, 500);
        this.add(pane);
        logo = new ImageIcon("images/logo_filled.png");
        
        // Set boundaries and font sizes
        title.setBounds(Utility.MIDDLE_X-80, Utility.MIDDLE_Y-300, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        subtitle.setBounds(Utility.MIDDLE_X-180, Utility.MIDDLE_Y-270, 500, 50);
        subtitle.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        nextPage.setBounds(Utility.MIDDLE_X+350, Utility.MIDDLE_Y-410, 125, 50);
        nextPage.setFont(new Font("Assistant", Font.BOLD, 12));
        nextPage.setBorderPainted(false);
        nextPage.setOpaque(true);
        nextPage.setBackground(Utility.BACKGROUND);
        backPage.setBounds(Utility.MIDDLE_X+200, Utility.MIDDLE_Y-410, 125, 50);
        backPage.setFont(new Font("Assistant", Font.BOLD, 12));
        backPage.setBorderPainted(false);
        backPage.setOpaque(true);
        backPage.setBackground(Utility.BACKGROUND);
        
        // Add action listeners
        nextPage.addActionListener(this);
        backPage.addActionListener(this);
        
        // Add all of the components
        this.add(title);
        this.add(subtitle);
        this.add(nextPage);
        this.add(backPage);
        
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Depending on the button clicked, implement the action pertained to it
        if (e.getSource() == nextPage) {
            Frame.layout.show(Frame.container, "reviewMenu");
        }
        if (e.getSource() == backPage) {
            // Go to the previous truck menu
            Frame.layout.show(Frame.container, "truckMenu");
        }
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        // Draw the long rectangle
        g.setColor(Color.white);
        g.fillRect(0, 0, 1005, 70);
        // Draw the logo
        g.drawImage(logo.getImage(), 25, 0, 95, 70, null); 
    }
    
    // Getters and setters
    /**
     * getReviewMenu
     * This method gets the review menu
     * @return A current ReviewMenu of the review menu
     */
    public ReviewMenu getReviewMenu() {
        return this.reviewMenu;
    }
}