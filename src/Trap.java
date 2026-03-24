import java.awt.image.BufferedImage;

public class Trap  extends SolidSprite{
    private int damage;

    public Trap(BufferedImage image, double x, double y, double width, double height, int damage) {
        super(image, x, y, width, height);
        this.damage= damage;
    }

    public int getDamage() {return damage;}
}
