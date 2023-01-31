package gritnessApp;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame{
    public static Container container;
    public static CardLayout layout;
    
    Window(){
        setTitle("GRITNESS");
        setResizable(false);
        setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(Const.APP_ICON.getImage());
        container = getContentPane();
        layout = new CardLayout();
        container.setLayout(layout);
    }
}