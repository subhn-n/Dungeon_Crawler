import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen extends JPanel {
    private Main main;
    private Image background;


    public WelcomeScreen(Main main){
        this.main = main;
        try{
            background = ImageIO.read(getClass().getResource("/background/dungeonWelcome.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY);

        JLabel title = new JLabel("ECHAPPE TOI SI TU PEUX !");

        Font gameFont = null;
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/Font/PressStart2P-Regular.ttf")).deriveFont(25f);

            title.setFont(gameFont);
        }catch (Exception e){
            e.printStackTrace();
        }

        title.setForeground(new Color(100, 255, 100));
        //title.setForeground(new Color(120, 255, 120));

        //title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton start= new JButton(" >   START     ");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.GREEN);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        start.setPreferredSize(new Dimension(80, 60));
        start.setFont(gameFont.deriveFont(18f));



        Timer blinkTimer = new Timer(500, e -> {title.setVisible(!title.isVisible());
                                                                                                      });
        blinkTimer.start();



        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        //this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(start);
        this.add(Box.createVerticalGlue());


        start.addActionListener(e ->
        {   SoundDisplay.play("/Son/startGame.wav");
            blinkTimer.stop();
            getParent().remove(this);
            main.startGame();
        });


    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (background!= null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
