import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite{
    protected boolean IsWalking = true;
    protected double speed = 20;
    protected final int spriteSheetNumberOfColumn = 10;

    protected int timeBetweenFrame = 200;
    protected Direction direction;

    protected double lifePoint;
    protected  double defensePoint;

    public DynamicSprite(BufferedImage image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){
        switch (direction){
            case SOUTH -> {
                this.y += speed;
                break;}
            case WEST -> {
                this.x -= speed;
                break;
            }
            case NORTH -> {
                this.y -= speed;
                break;
            }
            case EAST -> {
                this.x += speed;
                break;
            }

        }

    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){
        double nextPosX= x, nextPosY = y;
        switch (direction){
            case SOUTH -> nextPosY += speed;
            case WEST -> nextPosX -= speed;
            case NORTH -> nextPosY -= speed;
            case EAST -> nextPosX += speed;
        }

        Rectangle2D.Double hitbox = new Rectangle2D.Double(nextPosX, nextPosY, width, height);
        for (Sprite s : environment) {
            if((s instanceof SolidSprite) && (s!= this)){
                if(((SolidSprite)s).getHitbox().intersects(hitbox)){
                    return false;
                }

            }
        }
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }


    @Override
    public void draw(Graphics g){
        int index = (int) ((System.currentTimeMillis()/timeBetweenFrame)% spriteSheetNumberOfColumn );
        int attitude = direction.getFrameLineNumber();
        g.drawImage(image, (int) x, (int) y, (int) (x+ width), (int) (y+height),
                (int) (index*width),(int) (attitude*height), (int) ((index+1)*width), (int) ((attitude+1)*height), null );

    }

}
