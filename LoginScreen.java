package gritnessApp;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginScreen implements ActionListener{
    private JFrame window, title, text, username, password ;
    GraphicsPanel canvas;
    
    LoginScreen(){
        window = new JFrame("Gritness Login");
        window.setSize(Const.LOGIN_LENGTH, Const.LOGIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        window.add(canvas);
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
