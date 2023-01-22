package gritnessApp.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NutritionTab extends JPanel implements ActionListener{
   JLabel age;
    NutritionTab(){
        age = new JLabel("Nutrition");
        age.setFont(Const.TEXT_FONT);
        age.setBounds(50, 50, 500, 500);
        this.add(age);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
