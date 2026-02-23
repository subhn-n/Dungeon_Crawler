import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {
    private List<Displayable> renderList;
    private DynamicSprite hero;
    private GameContext context;


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

    @Override
    public void update(){
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

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

        // AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(camX, camY);
        renderList.forEach(v->v.draw(g2d));
    }

}
