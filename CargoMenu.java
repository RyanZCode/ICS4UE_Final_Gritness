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

/** [CargoMenu.java]
  * Desc: The page that allows the user to import their cargo information
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class CargoMenu extends JPanel implements ActionListener {
    // Variables
    private JLabel title, subtitle, categories, quantity, weight, length, width, height, desc;
    private ImageIcon box, logo;
    private JTextField quantityField, weightField, widthField, heightField, lengthField;
    private JButton custom, categoryLight, categoryMedium, categoryHeavy, empty, delete, save, nextPage;
    private JTable cargoList;
    
    /**
     * CargoMenu
     * This constructor creates a new CargoMenu object.
     */
    public CargoMenu() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        
        box = new ImageIcon("images/box.png");
        logo = new ImageIcon("images/logo_filled.png");
        
        cargoList = new JTable();
        Object[] columns = {"Length (in)","Width (in)","Height (in)","Weight (kg)","Q-ty"};
        Object[] row = new Object[5];
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        cargoList.setModel(model);
        JScrollPane pane = new JScrollPane(cargoList);
        
        title = new JLabel("Cargo Parameters:");
        subtitle = new JLabel("Input the information of your cargo.");
        quantity = new JLabel("Q-ty:");
        weight = new JLabel("Weight (kg):");
        width = new JLabel("Width (in):");
        height = new JLabel("Height (in):");
        length = new JLabel("Length (in):");
        categories = new JLabel("Box Sizes:");
        
        quantityField = Utility.createFilteredField();
        weightField = Utility.createFilteredField();
        lengthField = Utility.createFilteredField();
        heightField = Utility.createFilteredField();
        widthField = Utility.createFilteredField();
        
        empty = new JButton("New");
        delete = new JButton("Delete");
        save = new JButton("Save");
        nextPage = new JButton("Next Page");
        categoryLight = new JButton("Small");
        categoryMedium = new JButton("Medium");
        categoryHeavy = new JButton("Large");
        
        // Set boundaries
        pane.setBounds(Utility.MIDDLE_X-280, Utility.MIDDLE_Y+155, 575, 150);
        
        title.setBounds(Utility.MIDDLE_X-120, Utility.MIDDLE_Y-300, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        
        subtitle.setBounds(Utility.MIDDLE_X-150, Utility.MIDDLE_Y-270, 500, 50);
        subtitle.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        quantity.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y+55, 300, 50);
        weight.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y+15, 200, 30);
        width.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-85, 200, 30);
        length.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-140, 200, 30);
        height.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-35, 200, 30);
        categories.setBounds(Utility.MIDDLE_X-270, Utility.MIDDLE_Y-180, 200, 30);
        
        quantityField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y+60, 150, 40);
        heightField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-40, 150, 40);
        weightField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y+10, 150, 40);
        lengthField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-140, 150, 40);
        widthField.setBounds(Utility.MIDDLE_X-185, Utility.MIDDLE_Y-90, 150, 40);
        
        empty.setBounds(Utility.MIDDLE_X-170, Utility.MIDDLE_Y+120, 100, 20);
        delete.setBounds(Utility.MIDDLE_X-60, Utility.MIDDLE_Y+120, 100, 20);
        save.setBounds(Utility.MIDDLE_X+50, Utility.MIDDLE_Y+120, 100, 20);
        
        nextPage.setBounds(Utility.MIDDLE_X+350, Utility.MIDDLE_Y-410, 125, 50);
        nextPage.setFont(new Font("Assistant", Font.BOLD, 12));
        nextPage.setBorderPainted(false);
        nextPage.setOpaque(true);
        nextPage.setBackground(Utility.BACKGROUND);
        
        categoryLight.setBounds(Utility.MIDDLE_X-180, Utility.MIDDLE_Y-180, 100, 20);
        categoryMedium.setBounds(Utility.MIDDLE_X-65, Utility.MIDDLE_Y-180, 100, 20);
        categoryHeavy.setBounds(Utility.MIDDLE_X+50, Utility.MIDDLE_Y-180, 100, 20);
        
        // Add all of the components
        this.add(title);
        this.add(subtitle);
        this.add(quantity);
        this.add(height);
        this.add(weight);
        this.add(width);
        this.add(length);
        this.add(quantityField);
        this.add(heightField);
        this.add(weightField);
        this.add(widthField);
        this.add(lengthField);
        this.add(empty);
        this.add(save);
        this.add(delete);
        this.add(nextPage);
        this.add(categories);
        this.add(categoryLight);
        this.add(categoryMedium);
        this.add(categoryHeavy);
        this.add(pane);
        
        // Add action listeners
        // If the save button is clicked, add a new filled row with the information in each field to the model
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure that every text field is filled
                if (lengthField.getText().length() == 0 || widthField.getText().length() == 0 ||heightField.getText().length() == 0 || weightField.getText().length() == 0 || quantityField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required information");
                } else {
                    row[0] = lengthField.getText();
                    row[1] = widthField.getText();
                    row[2] = heightField.getText();
                    row[3] = weightField.getText();
                    row[4] = quantityField.getText();
                    // Add each row to the model
                    model.addRow(row);
                }
            }
        });
        // If the delete button is clicked, find the selected row and remove it from the JTable
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find the selected row
                int i = cargoList.getSelectedRow();
                if (i >= 0) {
                    // Remove from the model
                    model.removeRow(i);
                }
                else{
                    System.out.println("Deleting error");
                }
            }
        });
        // If the new button is clicked, empty every input field
        empty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightField.setText("");
                lengthField.setText("");
                widthField.setText("");
                weightField.setText("");
                quantityField.setText("");
            }
        });
        categoryLight.addActionListener(this);
        categoryMedium.addActionListener(this);
        categoryHeavy.addActionListener(this);
        // If the next page button is clicked, take the information from the JTable and write it onto the cargoInfo.txt file
        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("cargoInfo.txt");
                    BufferedWriter output = new BufferedWriter(new FileWriter(file));
                    
                    // Loop for cargoList rows
                    for(int i = 0; i < cargoList.getRowCount(); i++) {
                        // Loop for cargoList columns
                        for(int j = 0; j < cargoList.getColumnCount(); j++) {
                            output.write(cargoList.getModel().getValueAt(i, j)+" ");
                        }
                        output.write("\n");
                    }
                    DefaultTableModel model = (DefaultTableModel) cargoList.getModel();
                    output.close();
                } 
                catch (Exception g) {
                }
                // Change the menu to the cargo list
                CargoTable cargoList = new CargoTable();
                Frame.container.add(cargoList, "cargoList");
                Frame.layout.show(Frame.container, "cargoList");
            }
        });
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Depending on the button clicked, set the fields to the given value
        if (e.getSource() == categoryLight) {
            heightField.setText("16");
            lengthField.setText("12");
            widthField.setText("12");
        }
        if (e.getSource() == categoryMedium) {
            heightField.setText("18");
            lengthField.setText("16");
            widthField.setText("18");
        }
        if (e.getSource() == categoryHeavy) {
            heightField.setText("18");
            lengthField.setText("18");
            widthField.setText("24");
        }
    }
  
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        // Draw the box
        g.drawImage(box.getImage(), 630, 250, 250, 250, null);
        // Draw the long rectangle
        g.setColor(Color.white);
        g.fillRect(0, 0, 1005, 70);
        // Draw the logo
        g.drawImage(logo.getImage(), 25, 0, 95, 70, null);   
    }
}