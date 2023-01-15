package gritnessApp;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProfileTab extends JPanel implements ActionListener{
    private JFrame window;
    private GraphicsPanel canvas;
    
    ProfileTab(){
        window = new JFrame("Profile");
        window.setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        window.add(canvas);
        
        window.setVisible(true);
        window.setResizable(false);
        window.setLayout(null);
        canvas.setVisible(true);
    }
    
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
