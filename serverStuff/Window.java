package gritnessApp;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;
/**
 * [Window.java]
 * Window constructor class for application
 * @author Nathan Kong
 * @version 1.0 Jan 24, 2023
 */
public class Window extends JFrame{
    public static Container container;
    public static CardLayout layout;
    /**
     * NutritionTab
     * Constructs the window used for the application
     */
    Window(){
        setTitle("GRITNESS");
        setResizable(false);
        setSize(Const.MAIN_LENGTH, Const.MAIN_WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(Const.APP_ICON.getImage());
        //Use CardLayout for switching tabs 
        container = getContentPane();
        layout = new CardLayout();
        container.setLayout(layout);
    }
}