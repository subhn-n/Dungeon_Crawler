import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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


    public RenderEngine( DynamicSprite hero, GameContext context){
        renderList = new ArrayList<>();
        this.hero= hero;
        this.context= context;
    }

    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

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

    private void updateFPS(){
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) * 1e-9;
        // sumDelta+= deltaTime;
        frameCount++;
        lastTime= now;
        if (frameCount >= 20){
            fpsDisplay = (int) (1.0/ deltaTime);
          //  sumDelta = 0;
            frameCount= 0;
        }
    }



    private void drawFPS(Graphics2D g2d){
        Font fontFPS = new Font("Arial", Font.BOLD, 26);
        g2d.setFont(fontFPS);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("FPS : " + fpsDisplay, 500, 30);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        updateFPS();
        AffineTransform oldTransform = g2d.getTransform();
        centerOnHero(g2d);
        renderList.forEach(v->v.draw(g2d));
        g2d.setTransform(oldTransform);
        drawFPS(g2d);
    }

}
