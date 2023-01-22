package gritnessApp;
import java.awt.*;
import javax.swing.*;

public class Graph extends JPanel {
    int[] data;
    String xAxisLabel;
    String yAxisLabel;
    String title;
    int x, y;
    final int AXIS_LENGTH = 250;
    final int LABEL_DISTANCE_BETWEEN_AXIS = 35;
    int MAX_VALUE = 5;
    final int NUMBER_DAYS = 7;
    final String[] DAYS_OF_WEEK = {"S","M","T","W","T","F","S"};

    public Graph(int[] data, String xAxisLabel, String yAxisLabel, String title, int x, int y, int maxValue) {
        this.data = data;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.title = title;
        this.MAX_VALUE = maxValue;
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
    }

}
