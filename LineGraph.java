package gritnessApp;
import java.awt.*;
import javax.swing.*;

public class LineGraph extends JPanel {
    private int[] data;
    private String xAxisLabel;
    private String yAxisLabel;
    private String title;
    private int x, y;
    final int AXIS_LENGTH = 250;
    final int LABEL_DISTANCE_BETWEEN_AXIS = 35;
    final int MAX_VALUE = 2500;
    final int NUMBER_DAYS = 7;
    final String[] DAYS_OF_WEEK = {"S","M","T","W","T","F","S"};
    
    
    public LineGraph(int[] data, String xAxisLabel, String yAxisLabel, String title, int x, int y) {
        this.data = data;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.title = title;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw x and y axis
        // x= 50, y = 250
        
        //X-Axis line
        g.drawLine(x, y, x + AXIS_LENGTH, y);
        
        //Y-Axis line
        g.drawLine(x, y , x, y - AXIS_LENGTH);

        // Draw data points
        for (int i = 0; i < data.length - 1; i++) {
            g.drawLine(x + i*(AXIS_LENGTH / NUMBER_DAYS), y - data[i] / 10, x + (i+1)*(AXIS_LENGTH / NUMBER_DAYS), y - data[i+1] / 10);
        }

        // Draw labels
        g.drawString(xAxisLabel,(2 * x + AXIS_LENGTH) / 2, y + LABEL_DISTANCE_BETWEEN_AXIS);
        g.drawString(yAxisLabel, x - LABEL_DISTANCE_BETWEEN_AXIS - 50, (2 * y - AXIS_LENGTH) / 2);
        g.setFont(new Font("dialog", Font.PLAIN, 25));
        g.drawString(title, (2 * x + AXIS_LENGTH) / 2 - 35, y - AXIS_LENGTH );
        
        g.setFont(new Font("dialog", Font.PLAIN, 12));
        
        
        //Y-Axis Scale
        for (int i = 0; i <= MAX_VALUE; i++) {
        	if(i % (MAX_VALUE / 10) == 0) {
        		//Numbers
        		g.drawString(Integer.toString(i), x - 35, y - (int) (i * AXIS_LENGTH / MAX_VALUE) + 5);
        		
        		//Lines
        		g.drawLine(x - 5, y - (int) (i * AXIS_LENGTH / MAX_VALUE), x, y - (int) (i * AXIS_LENGTH / MAX_VALUE));
        	}
        }
        
        for(int i = 0; i < 7; i++) {
        	g.drawString(DAYS_OF_WEEK[i], x + i * AXIS_LENGTH / 7, y + 15);
        }
    }

    public static void main(String[] args) {
        int[] data = {450, 300, 1500, 1770, 690, 580, 280};

        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LineGraph(data, "Day", "Calories", "Calories", 150, 350));
        frame.setVisible(true);
    }
}
