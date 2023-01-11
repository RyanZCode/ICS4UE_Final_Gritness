// Import classes
import java.io.File;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;

/** [CargoTable.java]
  * Desc: The page that displays the selection of cargo
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class CargoTable extends JPanel implements ActionListener {
    // Variables
    private JLabel title, subtitle;
    private JButton nextPage, backPage;
    private JTable cargoList;
    private ImageIcon logo;
    
    /**
     * CargoTable
     * This constructor creates a new CargoTable object.
     */
    public CargoTable() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        title = new JLabel("Cargo List:");
        subtitle = new JLabel("Here is the inputted cargo information.");
        nextPage = new JButton("Next Page");
        backPage = new JButton("Back Page");
        Object[] columns = {"Length (in)","Width (in)","Height (in)","Weight (kg)","Q-ty"};
        cargoList = new JTable();
        File file = new File("cargoInfo.txt");
        JScrollPane pane = Utility.createTable(cargoList, columns, file, 200, 500);
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
        
        // Add all the components
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
            // Go to the truck menu
            Frame.layout.show(Frame.container, "truckMenu");
        }
        if (e.getSource() == backPage) {
            // Go to the previous cargo menu
            Frame.layout.show(Frame.container, "cargoMenu");
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
}