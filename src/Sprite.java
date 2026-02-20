import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Sprite implements Displayable {
    protected BufferedImage image;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public Sprite( BufferedImage image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);

    }

    public Rectangle2D.Double getHitbox(){
        return new Rectangle2D.Double(x, y, width, height);
    }
}
