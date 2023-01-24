package gritnessApp;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Window extends JFrame{
    public static Container container;
    public static CardLayout layout;
    public NavigationBar nav;

    Window(){
        setTitle("GRITNESS");
        setResizable(false);
        setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        container = getContentPane();
        layout = new CardLayout();
        container.setLayout(layout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
}