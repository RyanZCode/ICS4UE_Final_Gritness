// Import Classes
import java.io.File;
import java.io.FileWriter;   
import java.io.PrintWriter;    
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
import java.util.ArrayList;

/** [ReviewMenu.java]
  * Desc: The page that displays the completed selections of cargo and trucks
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class ReviewMenu extends JPanel implements ActionListener {
    // Variables
    private File cargoFile, truckFile;
    private ArrayList<Truck> trucks;
    private ArrayList<Box> cargos;
    private boolean isSubmitted;
    private JLabel title, subtitle, cargoTitle, trucksTitle;
    private JButton compute;
    private JTable cargoList, trucksList;
    private ImageIcon logo;
    
    /**
     * ReviewMenu
     * This constructor creates a new ReviewMenu object.
     */
    public ReviewMenu() {
        // Instantiate objects
        this.cargoFile = new File("cargoInfo.txt");
        this.truckFile = new File("truckInfo.txt");
        this.cargos = new ArrayList<Box>();
        this.trucks = new ArrayList<Truck>();
        this.isSubmitted = false;
        
        this.setBackground(Utility.BACKGROUND);
        
        title = new JLabel("Results:");
        subtitle = new JLabel("Please review your completed choices.");
        cargoTitle = new JLabel("Cargo:");
        trucksTitle = new JLabel("Trucks:");
        compute = new JButton("Compute Placement");
        logo = new ImageIcon("images/logo_filled.png");
        
        // Set boundaries
        title.setBounds(420, 120, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        subtitle.setBounds(320, 150, 500, 50);
        subtitle.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        cargoTitle.setBounds(70, 165, 500, 50);
        cargoTitle.setFont(new Font("Assistant", Font.BOLD, 12));
        trucksTitle.setBounds(70, 415, 500, 50);
        trucksTitle.setFont(new Font("Assistant", Font.BOLD, 12));
        
        compute.setBounds(770, 10, 180, 50);
        compute.setFont(new Font("Assistant", Font.BOLD, 12));
        compute.setBorderPainted(false);
        compute.setOpaque(true);
        compute.setBackground(Utility.BACKGROUND);
        
        // Add action listeners
        compute.addActionListener(this);
        
        // Add all of the components
        this.add(title);
        this.add(subtitle);
        this.add(compute);
        this.add(cargoTitle);
        this.add(trucksTitle);
        
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    /**
     * updateTable
     * This method updates the table
     */
    public void updateTable() {
        int tableHeight = 200;
        // cargo
        Object[] columnsCargo = {"Length (in)","Width (in)","Height (in)","Weight (kg)","Q-ty"};
        cargoList = new JTable();
        int cargoTableY = 200;
        JScrollPane cargoPane = Utility.createTable(cargoList, columnsCargo, cargoFile, cargoTableY, tableHeight);
        this.add(cargoPane);
        
        // truck
        Object[] columnsTruck = {"Length (m)","Width (m)","Height (m)","Carrying Cap. (kg)","Q-ty"};
        trucksList = new JTable();
        int truckTableY = 450;
        JScrollPane truckPane = Utility.createTable(trucksList, columnsTruck, truckFile, truckTableY, tableHeight);
        this.add(truckPane);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Compute the placement
        if (e.getSource() == compute) {
            for (int i = 0 ; i < trucksList.getRowCount() ; i++){
                for (int j = 0; j < Integer.parseInt(trucksList.getValueAt(i, 4).toString()) ; j++){
                    trucks.add(new Truck(Integer.parseInt(trucksList.getValueAt(i, 0).toString()),
                                         Integer.parseInt(trucksList.getValueAt(i, 1).toString()),
                                         Integer.parseInt(trucksList.getValueAt(i, 2).toString()),
                                         Integer.parseInt(trucksList.getValueAt(i, 3).toString())));
                }
            }
            
            for (int i = 0 ; i < cargoList.getRowCount() ; i++){
                for (int j = 0; j < Integer.parseInt(cargoList.getValueAt(i, 4).toString()) ; j++){
                    cargos.add(new Box(Integer.parseInt(cargoList.getValueAt(i, 0).toString()),
                                       Integer.parseInt(cargoList.getValueAt(i, 1).toString()),
                                       Integer.parseInt(cargoList.getValueAt(i, 2).toString()),
                                       Integer.parseInt(cargoList.getValueAt(i, 3).toString())));
                }
            }
            isSubmitted = true;
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
     * getTrucks
     * This method gets the trucks
     * @return An array list of the trucks
     */
    public ArrayList<Truck> getTrucks() {
        return trucks;
    }
    
    /**
     * getCargos
     * This method gets the cargo
     * @return An array list of the cargo
     */
    public ArrayList<Box> getCargos() {
        return cargos;
    }
    
    /**
     * getIsSubmitted
     * This method checks if the review menu is submitted
     * @return An array list of the cargo
     */
    public boolean getIsSubmitted() {
        return isSubmitted;
    }
    
    /**
     * setCargoFile
     * This method sets the cargo file
     * @param cargoFile A File object of the cargo file
     */
    public void setCargoFile(File cargoFile) {
        this.cargoFile = cargoFile;
    }
    
    /**
     * setTruckFile
     * This method sets the truck file
     * @param truckFile A File object of the trucks file
     */
    public void setTruckFile(File truckFile) {
        this.truckFile = truckFile;
    }
}