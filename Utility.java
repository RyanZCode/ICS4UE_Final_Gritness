// Import classes
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.io.BufferedReader;
import javax.swing.JTable;
import java.io.File;
import java.io.FileReader; 
import java.awt.Toolkit;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JTextField;

/** [Utility.java]
  * Desc: The class for the utility elements
  * @author Emily Xie & Ali Asghar Bundookwalla
  * @version Oct 2022
  */

public class Utility {
    // Colors
    public static final Color BACKGROUND = new Color(252, 212, 50);
    
    // Dimensions
    public static final int MIDDLE_X = Frame.WIDTH/2;
    public static final int MIDDLE_Y = (Frame.HEIGHT/2) + 20;
    
    /**
     * createTable
     * This method draws a table
     * @param table A JTable object of the table
     * @param columns An Object array of the columns
     * @param file A File object of the file
     * @param tableY An int of the y placement of the table
     * @param tableHeight An int of the height of the table
     * @return The pane of the table, otherwise null
     * @exception Exception If table cannot be created
     */
    public static JScrollPane createTable(JTable table, Object[] columns, File file, int tableY, int tableHeight) {
        try {
            BufferedReader output = new BufferedReader(new FileReader(file));
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);
            
            Object[] tableLines = output.lines().toArray();
            
            for(int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(" ");
                model.addRow(dataRow);
            }
            table.setModel(model);
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(50, tableY, 900, tableHeight);
            return pane;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    /**
     * createFilteredField
     * This method creates a filter textfield so the user can only type numbers, all other values are disregarded
     * @return The text field
     */
    public static JTextField createFilteredField() {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = 10;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,String str, AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0,fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            public void insertString(FilterBypass fb, int offs, String str,AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0,fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }
}