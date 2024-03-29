package gritnessApp;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

/**
 * [Const.java]
 * Gritness App Constants
 * @author Jason Wu
 * @author Justin Zhou
 * @author Nathan Kong
 * @author Ryan Zhou
 * @version 1.0 Jan 24, 2023
 */
public class Const {
	
	//final integers
    public static final int MAIN_LENGTH = 1280;
    public static final int MAIN_WIDTH = 720;
    public static final int LOGIN_LENGTH = 500;
    public static final int LOGIN_WIDTH = 500;
    public static final int NAV_Y = 570;
    public static final int NAV_WIDTH = 125;
    public static final int NAV_LENGTH = 270;
    
    //final colours
    public static final Color NAV_BAR_COLOUR = new Color(80, 120, 160);
    public static final Color BUTTON_COLOUR = new Color(100, 140, 180);
    public static final Color BUTTON_COLOUR2 = new Color(98, 121, 144);
    public static final Color BACKGROUND_COLOUR = new Color(255, 255, 255);
    public static final Color BACKGROUND_COLOUR2 = new Color(41,43,45);
    
    //final fonts
    public static final Font BUTTON_FONT = new Font("Bahnschrift", Font.PLAIN, 35);
    public static final Font BUTTON_FONT2 = new Font("Bahnschrift", Font.PLAIN, 25);
    public static final Font SMALLER_FONT = new Font("Bahnschrift", Font.PLAIN, 16);
    public static final Font TEXT_FONT = new Font("Bahnschrift", Font.PLAIN, 25);
    public static final Font TEXT_FONT2 = new Font("Bahnschrift", Font.PLAIN, 30);
    public static final Font SUBTITLE_FONT = new Font("Bahnschrift", Font.PLAIN, 30);
    public static final Font TITLE_FONT = new Font("Calibri", Font.BOLD, 45);
    public static final Font COVER_FONT = new Font("Bahnschrift", Font.BOLD, 55);
    public static final Font PROFILE_BUTTON_FONT = new Font("Calibri", Font.PLAIN, 45);
    public static final Font HISTORY_LABEL_FONT = new Font("Calibri", Font.PLAIN, 55);
    public static final Font FIELD_FONT = new Font("Bahnschrift", Font.PLAIN, 18);
    public static final Font FIELD_FONT2 = new Font("Bahnschrift", Font.PLAIN, 40);
    
    //final images
    public static final ImageIcon PROFILE_ICON = new ImageIcon("person.png");
    public static final ImageIcon HISTORY_ICON = new ImageIcon("clock.png");
    public static final ImageIcon WORKOUT_ICON = new ImageIcon("barbell.png");
    public static final ImageIcon QUOTE_ICON = new ImageIcon("quote.png");
    public static final ImageIcon FOOD_ICON = new ImageIcon("food.png");
    public static final ImageIcon SOCIAL_ICON = new ImageIcon("social.png");
    public static final ImageIcon PROFILE_PIC = new ImageIcon("profilepic.png");
    public static final ImageIcon COVER_PHOTO = new ImageIcon("gritnesscover.jpg");
    public static final ImageIcon APP_ICON = new ImageIcon("gritnessicon.png");
}