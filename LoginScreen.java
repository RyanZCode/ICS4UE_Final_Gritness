package gritnessApp;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JPanel implements ActionListener{
    private JFrame window;
    private JLabel title, text, username, password, account;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton login, register;
    private GraphicsPanel canvas;  

    LoginScreen(){
        window = new JFrame("Gritness Login");
        window.setSize(Const.LOGIN_LENGTH, Const.LOGIN_WIDTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        window.add(canvas);
        
        window.setVisible(true);
        window.setResizable(false);
        window.setLayout(null);
        canvas.setVisible(true);

        title = new JLabel("Welcome to Gritness!");
        text = new JLabel("Sign into your account below:");
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        account = new JLabel("Create an account");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        showPassword = new JCheckBox("Show password");

        login = new JButton("Login");
        register = new JButton("Register");

        login.addActionListener(this);
        showPassword.addActionListener(this);
        register.addActionListener(this);

        title.setBounds(100, 50, 1000, 50);
        title.setFont(new Font("Calibri", Font.BOLD, 32));
        
        text.setBounds(150, 200, 1000, 50);
        text.setFont(new Font("Calibri", Font.BOLD, 16));
        
        username.setBounds(100, 300, 1000, 50);
        password.setBounds(100, 350, 1000, 50);

        canvas.add(title);
        canvas.add(text);
        canvas.add(username);
        canvas.add(password);
        canvas.add(account);
        canvas.add(usernameField);
        canvas.add(passwordField);
        canvas.add(showPassword);
        canvas.add(login);
        canvas.add(register);
        
        repaint();
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

    public static void main(String[] args) {
        LoginScreen login = new LoginScreen();
        login.run();
    }

    private void run() {
        window.repaint();
    }

}
