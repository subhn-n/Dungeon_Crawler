import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {
    private Main main;
    public WelcomeScreen(Main main){
        this.main = main;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);

        JButton start= new JButton("N'appuie pas sur moi");
        start.addActionListener(e ->{

                main.startGame();});
        this.add(start);


    }
}
