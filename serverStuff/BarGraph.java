package gritnessApp;
import java.awt.*;
import javax.swing.*;

public class BarGraph extends Graph{    
    BarGraph(int[] data, String xAxisLabel, String yAxisLabel, String title, int x, int y, int maxValue) {
    	super(data, xAxisLabel, yAxisLabel, title, x, y, maxValue);
    }
    
    
    public void draw(Graphics g) {
    	
    	for (int i = 0; i < data.length; i++) {
        	
        	g.setColor(Color.red);
        	if(data[i] != 0) {
        		 g.fillRect(x + i* (AXIS_LENGTH / NUMBER_DAYS) , y -  data[i] * (AXIS_LENGTH / MAX_VALUE), 10, data[i] * (AXIS_LENGTH / MAX_VALUE));
        	}
           
        }
    	
    	
    	// Draw x and y axis
        //X-Axis line
    	g.setColor(Color.black);
        g.drawLine(x, y, x + AXIS_LENGTH, y);
        
        //Y-Axis line
        g.drawLine(x, y , x, y - AXIS_LENGTH);

        // Draw data bars
        

        // Draw labels
        g.drawString(xAxisLabel,(2 * x + AXIS_LENGTH) / 2, y + LABEL_DISTANCE_BETWEEN_AXIS);
        g.drawString(yAxisLabel, x - LABEL_DISTANCE_BETWEEN_AXIS - 50, (2 * y - AXIS_LENGTH) / 2);
        g.setFont(new Font("dialog", Font.PLAIN, 25));
        g.drawString(title, (2 * x + AXIS_LENGTH) / 2 - 35, y - AXIS_LENGTH );
        
        g.setFont(new Font("dialog", Font.PLAIN, 12));
        
        
        //Y-Axis Scale
        for (int i = 0; i <= MAX_VALUE; i++) {
        	//Numbers
    		g.drawString(Integer.toString(i), x - 35, y - (int) (i * AXIS_LENGTH / MAX_VALUE) + 5);
    		
    		//Lines
    		g.drawLine(x - 5, y - (int) (i * AXIS_LENGTH / MAX_VALUE), x, y - (int) (i * AXIS_LENGTH / MAX_VALUE));
        }
        
        for(int i = 0; i < 7; i++) {
        	g.drawString(DAYS_OF_WEEK[i], x + i * AXIS_LENGTH / 7, y + 15);
        }
    }
}
