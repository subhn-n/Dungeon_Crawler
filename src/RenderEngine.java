import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {
    private List<Displayable> renderList;
    private DynamicSprite hero;
    private GameContext context;
    private long lastTime = System.nanoTime();
    //private double sumDelta = 0;
    private int frameCount = 0;
    private int fpsDisplay = 0;
    private GameState currentState = GameState.PLAYING;
    private Main main;
    private JButton retryButton;
    private ImageIcon retryIcon;


    public RenderEngine( DynamicSprite hero, GameContext context, Main main){
        renderList = new ArrayList<>();
        this.hero= hero;
        this.context= context;
        this.main = main;

        this.setLayout(new BorderLayout());
        try{
            BufferedImage img = ImageIO.read(getClass().getResource("/tiles/retryGreen.png"));
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();

            int desiredWidth = 50;
            int desiredHeight = (int)(desiredWidth*((double)originalHeight / originalWidth));
            Image scaledImage = img.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            retryIcon = new ImageIcon(scaledImage);
            retryButton= new JButton(retryIcon);
            //retryButton.setMargin(new Insets(0, 0, 0, 0));
            //.setPreferredSize(new Dimension(desiredWidth, desiredHeight));
        }catch (Exception e){
            System.out.println("Erreur chargement logo retry");
            retryButton= new JButton("RETRY");
        }
        retryButton.setContentAreaFilled(false);
        retryButton.setBorderPainted(false);
        retryButton.setFocusPainted(false);
        retryButton.setOpaque(false);
        retryButton.setPreferredSize(new Dimension(50, 50));
        retryButton.setVisible(false);
        retryButton.addActionListener(e -> main.resetGame());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 30));
        buttonPanel.add(retryButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    public void setRenderList(List<Displayable> renderList) {this.renderList = renderList;}

    public void addToRenderList(Displayable d){
        renderList.add(d);
    }

    public void clearSprites(){
        this.renderList.clear();
    }

    @Override
    public void update(){
        repaint();
    }


    private void centerOnHero(Graphics2D g2d){
        int mapWidth= context.getMapWidth();
        int mapHeight= context.getMapHeight();

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        int camX = (int) ((screenWidth/2) - hero.getX());
        int camY = (int) ((screenHeight/2) - hero.getY());

        if (camX > 0) camX = 0;
        if (camY > 0) camY = 0;


        int minX = - (mapWidth - screenWidth);
        int minY = -(mapHeight - screenHeight);

        if (camX < minX) camX = minX;
        if (camY < minY) camY = minY;

        g2d.translate(camX, camY);
    }

    private void updateFPS_Timer(){
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) * 1e-9;
        // sumDelta+= deltaTime;
        frameCount++;
        lastTime= now;
        if (frameCount >= 20) {
            fpsDisplay = (int) (1.0 / deltaTime);
            context.decreaseSeconds();
            //  sumDelta = 0;
            frameCount = 0;
        }

    }


    private void drawFPS(Graphics2D g2d){
        Font fontFPS = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(fontFPS);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("FPS : " + fpsDisplay, 500, 30);
    }


    private void drawLevelName(Graphics2D g2d){
        Level currentLevel = context.getCurrentLevel();
        String levelName= currentLevel.getName();
        if (levelName.equals("FINAL LEVEL")){
            try {
                Font gameFont = Font.createFont(Font.TRUETYPE_FONT,
                        getClass().getResourceAsStream("/Font/Another Danger - Demo.otf")).deriveFont(25f);
                g2d.setFont(gameFont);
                g2d.setColor(Color.ORANGE);
                g2d.drawString(levelName, 20, 30);
            } catch (Exception e) {
                g2d.setFont(new Font("Serif", Font.BOLD, 25));
                g2d.setColor(Color.ORANGE);
                g2d.drawString(levelName, 20, 30);
            }

        } else {
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                g2d.drawString(levelName, 20, 30);
            }

    }

    private void drawTimer(Graphics2D g2d){
        //context.decreaseSeconds();
        g2d.setColor(context.getRemainingSeconds()<=60?Color.RED:Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("TIME : " + context.timeIntoString(), 250, 30);
    }

    private void drawEndScreen(Graphics2D g2d){
        String imagePath = (currentState == GameState.WIN?"/background/winPage.png":"/background/gameOverPage2.png");
        Image endImage = null;
        try {
            endImage = ImageIO.read(getClass().getResource(imagePath));
            g2d.drawImage(endImage,0,0, getWidth(), getHeight(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void hideRetryButton(){
        if (retryButton != null){
            retryButton.setVisible(false);
        }
        this.revalidate();
        this.repaint();
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
       if(currentState == GameState.PLAYING){
        //super.paint(g);
        drawTimer(g2d);
        updateFPS_Timer();
        AffineTransform oldTransform = g2d.getTransform();
        centerOnHero(g2d);
        renderList.forEach(v->v.draw(g2d));
        g2d.setTransform(oldTransform);
        drawFPS(g2d);
        drawLevelName(g2d);
        drawTimer(g2d);
        retryButton.setVisible(false);
       }
       else{
           drawEndScreen(g2d);
           if(!retryButton.isVisible()){
               retryButton.setVisible(true);
           //    this.revalidate();
           }
       }
    }

}
