package gritnessApp;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * [Graph.java]
 * Constructs a graph
 * @author Jason Wu
 * @version 1.0 Jan 24, 2023
 */
public class Graph extends JPanel {
	
    int[] data;
    String xAxisLabel;
    String yAxisLabel;
    String title;
    int x, y;
    final int AXIS_LENGTH = 200;
    final int LABEL_DISTANCE_BETWEEN_AXIS = 35;
    int MAX_VALUE = 5;
    final int NUMBER_DAYS = 7;
    final String[] DAYS_OF_WEEK = {"S","M","T","W","T","F","S"};

    /**
     * Graph
     * @param data Graph data
     * @param xAxisLabel X-Axis Label
     * @param yAxisLabel Y-Axis Label
     * @param title Graph Title
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param maxValue Maximum graph value
     */
    public Graph(int[] data, String xAxisLabel, String yAxisLabel, String title, int x, int y, int maxValue) {
        this.data = data;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.title = title;
        this.MAX_VALUE = maxValue;
        this.x = x;
        this.y = y;
    }

    /**
     * paintComponent
     * Graphics
     */
    public void paintComponent(Graphics g) {
    }

}
