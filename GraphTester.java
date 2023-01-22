package gritnessApp;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphTester {
	JFrame window;
	GraphTester(){
		window = new JFrame();
		Panel panel = new Panel();
		window.setSize(600,500);
		window.add(panel);
		window.setVisible(true);
	}
    public class Panel extends JPanel{
    	BarGraph barGraph;
    	LineGraph lineGraph;
    	int[] data = {1, 2, 3, 4, 3, 2, 3};
    	Panel(){
            setFocusable(true);
            requestFocusInWindow();
        }
    	
    	@Override
    	public void paintComponent(Graphics g){ 
    		super.paintComponent(g);
    		int[] data = {1, 2, 3, 4, 3, 2, 1};
            barGraph = new BarGraph(data, "Day", "Calories", "Calories", 150, 350, 5);
            barGraph.draw(g);
           
            //Uncomment to draw the line graph
            
//            int[] data = {450, 300, 1500, 1770, 690, 580, 280};
//            lineGraph = new LineGraph(data, "Day", "Calories", "Calories", 150, 350, 2500);
//            lineGraph.draw(g);
         
           
        }
    }
    public void run() {
    	window.repaint();
    }
    public static void main(String[] args) {
    	GraphTester test = new GraphTester();
    	test.run();
    }
}
