// Import classes
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;         
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.io.FileReader;
import javax.swing.JFileChooser;

/** [OptionMenu.java]
  * Desc: The page that allows the user to choose how to import their cargo information
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class OptionMenu extends JPanel implements ActionListener {
    // Variables
    private JLabel title, subtitle, optionFileText, optionDirectText;
    private ImageIcon optionFileImage, optionDirectImage;
    private JButton optionFileBox, optionFileTruck, optionDirect, optionFile;
    private FileWriter cargoFileWriter, truckFileWriter;
    
    /**
     * OptionMenu
     * This constructor creates a new OptionMenu object.
     */
    public OptionMenu() {
        // Instantiate objects
        this.setBackground(Utility.BACKGROUND);
        
        optionFileImage = new ImageIcon("images/file_option.png");
        optionDirectImage = new ImageIcon("images/box_option.png");
        
        title = new JLabel("Start the Process:");
        subtitle = new JLabel("Pick between importing files or directly inputting the dimensions.");
        optionFileText = new JLabel("Import a file with all the information.");
        optionDirectText = new JLabel("Input the information yourself.");
        
        optionFileBox = new JButton("Import Box File");
        optionFileTruck = new JButton("Import Truck File");
        optionDirect = new JButton("Continue to Direct");
        optionFile = new JButton("Complete Imports");
        
        try {
            cargoFileWriter = new FileWriter("cargoInfo.txt", false);
            truckFileWriter = new FileWriter("truckInfo.txt", false);
        } catch (IOException e) {
            System.out.println("There are some IOException: " + e.getMessage());
        }
        
        // Set boundaries
        title.setBounds(Utility.MIDDLE_X-125, Utility.MIDDLE_Y-220, 500, 50);
        title.setFont(new Font("Assistant", Font.BOLD, 25));
        
        subtitle.setBounds(Utility.MIDDLE_X-275, Utility.MIDDLE_Y-190, 700, 50);
        subtitle.setFont(new Font("Assistant", Font.PLAIN, 18));
        
        optionFileText.setBounds(Utility.MIDDLE_X-330, Utility.MIDDLE_Y-100, 1000, 20);
        optionFileText.setFont(new Font("Assistant", Font.BOLD, 15));
        optionDirectText.setBounds(Utility.MIDDLE_X+70, Utility.MIDDLE_Y-100, 1000, 20);
        optionDirectText.setFont(new Font("Assistant", Font.BOLD, 15));
        
        optionFileBox.setBounds(Utility.MIDDLE_X-190, Utility.MIDDLE_Y-50, 120, 20);
        optionFileTruck.setBounds(Utility.MIDDLE_X-190, Utility.MIDDLE_Y-10, 120, 20);
        
        optionDirect.setBounds(Utility.MIDDLE_X+100, Utility.MIDDLE_Y+40, 180, 30);
        optionDirect.setFont(new Font("Assistant", Font.BOLD, 12));
        optionDirect.setBorderPainted(false);
        optionDirect.setOpaque(true);
        optionDirect.setBackground(Utility.BACKGROUND);
        optionFile.setBounds(Utility.MIDDLE_X-250, Utility.MIDDLE_Y+40, 180, 30);
        optionFile.setFont(new Font("Assistant", Font.BOLD, 12));
        optionFile.setBorderPainted(false);
        optionFile.setOpaque(true);
        optionFile.setBackground(Utility.BACKGROUND);
        
        // Add action listeners
        optionFileBox.addActionListener(this);
        optionFileTruck.addActionListener(this);
        optionDirect.addActionListener(this);
        optionFile.addActionListener(this);
        
        // Add all of the components
        this.add(title);
        this.add(subtitle);
        this.add(optionFileText);
        this.add(optionDirectText);
        this.add(optionFileBox);
        this.add(optionFileTruck);
        this.add(optionDirect);
        this.add(optionFile);
        
        this.setLayout(null);
        repaint();
        this.setVisible(true);
    }
    
    @Override 
    public void actionPerformed(ActionEvent e) {
        // If the import box button is chosen, allow the user to select a file containing box information
        if (e.getSource() == optionFileBox) {
            selectFile(cargoFileWriter);
        } 
        // If the import truck button is chosen, allow the user to select a file containing truck information
        else if (e.getSource() == optionFileTruck) { 
            selectFile(truckFileWriter);
        } 
        else {
            // If there was no file selected, keep the files empty
            try {
                cargoFileWriter.close();
                truckFileWriter.close();
            } catch (IOException ex) {
                System.out.println("There are some IOException: " + ex.getMessage());
            }
            
            // If the complete import button is clicked, show the review imported page
            if (e.getSource() == optionFile) {
                Frame.layout.show(Frame.container, "reviewMenu");
            }
            // If the continue to direct button is clicked, show the manual cargo input page
            else if (e.getSource() == optionDirect) {
                Frame.layout.show(Frame.container, "cargoMenu");
            }
        }
    }
    
    /**
     * selectFile
     * This method selects a file and writes it onto a new text file.
     * @param writer A FileWriter object of the writer
     * @exception IOException If stream to file cannot be written to or closed
     */
    public void selectFile(FileWriter writer){
        JFileChooser fileChooser = new JFileChooser(); 
        int response = fileChooser.showOpenDialog(null); //select file to open 
        
        if (response == JFileChooser.APPROVE_OPTION) { 
            try {
                File fileImported = new File(fileChooser.getSelectedFile().getAbsolutePath()); 
                
                // Write the imported file onto a new text file
                FileReader fr = new FileReader(fileImported);
                int i;
                while ((i = fr.read()) != -1) {
                    writer.write((char)i);
                }
                fr.close();
            } catch (IOException ex) {
                System.out.println("There are some IOException: " + ex.getMessage());
            }
            
        }
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        // Draw the rectangle
        g.setColor(Color.white);
        g.fillRect(100, 150, 800, 450);
        // Draw the icons
        g.drawImage(optionFileImage.getImage(), Utility.MIDDLE_X-300, Utility.MIDDLE_Y-50, 100, 100, null);
        g.drawImage(optionDirectImage.getImage(), Utility.MIDDLE_X+80, Utility.MIDDLE_Y-70, 125, 125, null);   
    }
}