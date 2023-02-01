package gritnessApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * [LineGraph.java]
 * Constructs a line graph
 * @author Jason Wu
 * @version 1.0 Jan 24, 2023
 */
public class LineGraph extends Graph{
	/**
	 * LineGraph
	 * @param data Bar graph data
	 * @param xAxisLabel X-Label
	 * @param yAxisLabel Y-Label
	 * @param title Graph title
	 * @param x X-location
	 * @param y Y-location
	 * @param maxValue Maximum graph value
	 */
    public LineGraph(int[] data, String xAxisLabel, String yAxisLabel, String title, int x, int y, int maxValue) {
    	super(data, xAxisLabel, yAxisLabel, title, x, y, maxValue);
    }

    /**
     * draw
     * Draws the line graph
     * @param g Draw
     */
    public void draw(Graphics g) {

        //X-Axis line
        g.setColor(Color.black);
        g.drawLine(x, y, x + AXIS_LENGTH, y);
        
        //Y-Axis line
        g.drawLine(x, y , x, y - AXIS_LENGTH);

        // Draw labels
        g.drawString(xAxisLabel,(2 * x + AXIS_LENGTH) / 2, y + LABEL_DISTANCE_BETWEEN_AXIS);
        g.drawString(yAxisLabel, x - LABEL_DISTANCE_BETWEEN_AXIS - 50, (2 * y - AXIS_LENGTH) / 2);
        g.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
        g.drawString(title, (2 * x + AXIS_LENGTH) / 2 - 35, y - AXIS_LENGTH );
        
        g.setFont(new Font("Bahnschrift", Font.PLAIN, 12));  
        
        //Y-Axis Scale
        for (int i = 0; i <= MAX_VALUE; i++) {
        	if(i % (MAX_VALUE / 10) == 0) {
        		//Numbers
        		g.drawString(Integer.toString(i), x - 35, y - (int) (i * AXIS_LENGTH / MAX_VALUE) + 5);
        		
        		//Lines
        		g.drawLine(x - 5, y - (int) (i * AXIS_LENGTH / MAX_VALUE), x, y - (int) (i * AXIS_LENGTH / MAX_VALUE));
        	}
        }
     // Draw data points
        for (int i = 0; i < data.length - 1; i++) {
        	g.setColor(Color.red);
        	double horizontalSpacing = ((double)AXIS_LENGTH / NUMBER_DAYS);
    		double verticalSpacing = ((double)MAX_VALUE) / AXIS_LENGTH;
    		g.drawLine((int)(x + i * horizontalSpacing), (int)(y - data[i] / verticalSpacing), (int)(x + (i + 1) * horizontalSpacing), (int)(y - data[i + 1] / verticalSpacing));
            
        }
        for(int i = 0; i < 7; i++) {
        	g.setColor(Color.black);
        	g.drawString(DAYS_OF_WEEK[i], x + i * AXIS_LENGTH / 7, y + 15);
        }
    }
}