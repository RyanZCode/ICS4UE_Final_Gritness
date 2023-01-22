package gritnessApp.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileTab extends JPanel implements ActionListener{
    private GraphicsPanel canvas;
    private JLabel age, height, bmi, bmr, name;
    
    ProfileTab(){    	
    	this.setVisible(true);
    	
        canvas = new GraphicsPanel();
        canvas.setLayout(null);
        
        age = new JLabel("Profile");
        age.setFont(Const.TEXT_FONT);
        age.setBounds(50, 50, 500, 500);
        this.add(age);
        
        height = new JLabel("Height");
        bmi = new JLabel("BMI");
        bmr = new JLabel("BMR");
    }	
    
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            
            // Anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
            
            g.setColor(Color.white);

        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {  	
        
    }

}
