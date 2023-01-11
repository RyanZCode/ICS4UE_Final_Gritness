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

/** [TruckMenu.java]
  * Desc: The page that allows the user to import their truck information
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class TruckMenu extends JPanel implements ActionListener {
    // Variables
    private JLabel title, subtitle, categories, quantityTruck, truckName, truckWeight, truckLength, truckWidth, truckHeight;
    private ImageIcon truck, logo;
    private JTextField quantityTruckField, truckNameField, truckWeightField, truckWidthField, truckHeightField, truckLengthField;
    private JButton custom, category1, category2, category3, newTruck, deleteTruck, saveTruck, nextPage, backPage;
    private JTable trucksList;
    private TruckTable truckList;
    
    /**
     * TruckMenu
     * This constructor creates a new TruckMenu object.
     */
    public TruckMenu() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        
        truck = new ImageIcon("images/truck.png");
        logo = new ImageIcon("images/logo_filled.png");
        
        trucksList = new JTable();
        Object[] columns = {"Length (cm)","Width (cm)","Height (cm)","Carrying Cap. (kg)","Q-ty"};
        Object[] row = new Object[5];
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        trucksList.setModel(model);
        JScrollPane pane = new JScrollPane(trucksList);
        
        title = new JLabel("Truck Parameters:");
        subtitle = new JLabel ("Input the information of your trucks.");
        quantityTruck = new JLabel("Q-ty:");
        truckWeight = new JLabel("Carrying Cap. (kg):");
        truckWidth = new JLabel("Width (cm):");
        truckHeight = new JLabel("Height (cm):");
        truckLength = new JLabel("Length (cm):");
        categories = new JLabel("Truck Sizes:");
        
        quantityTruckField = Utility.createFilteredField();
        truckWeightField = Utility.createFilteredField();
        truckLengthField = Utility.createFilteredField();
        truckHeightField = Utility.createFilteredField();
        truckWidthField = Utility.createFilteredField();
        
        newTruck = new JButton("New");
        deleteTruck = new JButton("Delete");
        saveTruck = new JButton("Save");
        nextPage = new JButton("Next Page");
        backPage = new JButton("Back Page");
        category1 = new JButton("Small");
        category2 = new JButton("Medium");
        category3 = new JButton("Large");
        
        // Set boundaries
        pane.setBounds(Utility.MIDDLE_X-280, Utility.MIDDLE_Y+155, 575, 150);
        
        title.setBounds(Utility.MIDDLE_X-120, Utility.MIDDLE_Y-300, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        
        subtitle.setBounds(Utility.MIDDLE_X-150, Utility.MIDDLE_Y-270, 500, 50);
        subtitle.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        quantityTruck.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y+55, 300, 50);
        truckWeight.setBounds(Utility.MIDDLE_X-300, Utility.MIDDLE_Y+15, 200, 30);
        truckWidth.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-85, 200, 30);
        truckLength.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-140, 200, 30);
        truckHeight.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-35, 200, 30);
        categories.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-185, 200, 30);
        
        quantityTruckField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y+60, 150, 40);
        truckHeightField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-40, 150, 40);
        truckWeightField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y+10, 150, 40);
        truckLengthField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-140, 150, 40);
        truckWidthField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-90, 150, 40);
        
        newTruck.setBounds(Utility.MIDDLE_X-170, Utility.MIDDLE_Y+120, 100, 20);
        deleteTruck.setBounds(Utility.MIDDLE_X-60, Utility.MIDDLE_Y+120, 100, 20);
        saveTruck.setBounds(Utility.MIDDLE_X+50, Utility.MIDDLE_Y+120, 100, 20);
        
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
        
        category1.setBounds(Utility.MIDDLE_X-180, Utility.MIDDLE_Y-180, 100, 20);
        category2.setBounds(Utility.MIDDLE_X-65, Utility.MIDDLE_Y-180, 100, 20);
        category3.setBounds(Utility.MIDDLE_X+50, Utility.MIDDLE_Y-180, 100, 20);
        
        // Add all of the components
        this.add(pane);
        this.add(title);
        this.add(subtitle);
        this.add(quantityTruck);
        this.add(truckHeight);
        this.add(truckWeight);
        this.add(truckWidth);
        this.add(truckLength);
        this.add(quantityTruckField);
        this.add(truckHeightField);
        this.add(truckWeightField);
        this.add(truckWidthField);
        this.add(truckLengthField);
        this.add(newTruck);
        this.add(saveTruck);
        this.add(deleteTruck);
        this.add(nextPage);
        this.add(backPage);
        this.add(categories);
        this.add(category1);
        this.add(category2);
        this.add(category3);
        
        // Add action listeners
        //If the back button is clicked then the user will go back to the previous screen
        backPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.layout.show(Frame.container, "cargoTable");
            }
        });
        // If the save button is clicked, add a new filled row with the information in each field to the model
        saveTruck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (truckLengthField.getText().length() == 0 ||truckWidthField.getText().length() == 0 ||truckHeightField.getText().length() == 0 || truckWeightField.getText().length() == 0 || quantityTruckField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required information");
                }
                else{
                    row[0] = truckLengthField.getText();
                    row[1] = truckWidthField.getText();
                    row[2] = truckHeightField.getText();
                    row[3] = truckWeightField.getText();
                    row[4] = quantityTruckField.getText();
                    // Add each row to the model
                    model.addRow(row);
                }
            }
        });
        // If the delete button is clicked, find the selected row and remove it from the JTable
        deleteTruck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find the selected row
                int i = trucksList.getSelectedRow();
                if (i >= 0) {
                    // Remove from the model
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        // If the new button is clicked, empty every input field
        newTruck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckHeightField.setText("");
                truckLengthField.setText("");
                truckWidthField.setText("");
                truckWeightField.setText("");
                quantityTruckField.setText("");
                
            }
        });
        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);
        // If the next page button is clicked, take the information from the JTable and write it onto the cargoInfo.txt file
        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    File file = new File("truckInfo.txt");
                    BufferedWriter output = new BufferedWriter(new FileWriter(file));
                    
                    // Loop for truckList rows
                    for(int i = 0; i < trucksList.getRowCount(); i++) {
                        // Loop for truckList columns
                        for(int j = 0; j < trucksList.getColumnCount(); j++) {
                            output.write(trucksList.getModel().getValueAt(i, j)+" ");
                        }
                        output.write("\n");
                    }
                    output.close();
                }catch (Exception g) {
                }
                // Change the menu to the trucks list
                TruckTable truckList = new TruckTable();
                Frame.container.add(truckList, "truckList");
                Frame.layout.show(Frame.container, "truckList");
            }
        });
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Depending on the button clicked, set the fields to the given value
        if (e.getSource() == category1) {
            truckHeightField.setText("37");
            truckLengthField.setText("275");
            truckWidthField.setText("164");
            truckWeightField.setText("1050");
        }
        else if (e.getSource() == category2) {
            truckHeightField.setText("74");
            truckLengthField.setText("550");
            truckWidthField.setText("328");
            truckWeightField.setText("2100");
        }
        else if (e.getSource() == category3) {
            truckHeightField.setText("148");
            truckLengthField.setText("1100");
            truckWidthField.setText("656");
            truckWeightField.setText("4200");
        }
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        // Draw the truck
        g.drawImage(truck.getImage(), 600, 210, 400, 250, null);
        // Draw the long rectangle
        g.setColor(Color.white);
        g.fillRect(0, 0, 1005, 70);
        // Draw the logo
        g.drawImage(logo.getImage(), 25, 0, 95, 70, null);   
    }
    
    // Getters and setters
    /**
     * getTrucksList
     * This method gets the list of trucks
     * @return A current TruckTable of the list of trucks
     */
    public TruckTable getTrucksList() {
        return this.truckList;
    }
}