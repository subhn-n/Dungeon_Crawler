import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite{
    protected boolean IsWalking = true;
    protected double speed = 15;
    protected final int spriteSheetNumberOfColumn = 10;

    protected int timeBetweenFrame = 200;
    protected Direction direction;

    protected int maxHealth = 5;
    protected  int currentHealth = 5;
    protected boolean isInvulnerable = false;
    protected long invulnerabilityStartTime = 0;
    protected final long INVULNERABILITY_DURATION = 2000;
    private Main main;



    public DynamicSprite(BufferedImage image, double x, double y, double width, double height, Main main) {
        super(image, x, y, width, height);
        this.main= main;
    }

    //public double getX(){return this.x;}

   // public double getY(){return this.y;}

    public void setX(int x){this.x = x;}
    public void setY(int x){this.y = y;}

    public int getCurrentHealth() {return currentHealth;}

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

        Rectangle2D.Double hitbox = new Rectangle2D.Double(nextPosX, nextPosY, this.width, this.height);
        for (Sprite s : environment) {
            if((s instanceof SolidSprite) && (s!= this)){
                if(((SolidSprite)s).getHitbox().intersects(hitbox)){
                    if(s instanceof  Trap) {
                        int damageToApply = ((Trap) s).getDamage();
                        this.handleTrapCollision(damageToApply, environment);
                        return true;
                    }
                    if(s instanceof  HealTile) {
                        this.currentHealth = 5;
                        SoundDisplay.play("Son/healthRecharge.wav");
                        environment.remove(s);
                        main.removeSprite(s);
                        return true;
                    }
                    return false;
                }

            }
        }
        return true;
    }


    private void handleTrapCollision(int damageToApply, ArrayList<Sprite> environment){
        if(!(this.isInvulnerable)){
            this.takeDamage(damageToApply);
            SoundDisplay.play("/Son/ough.wav");
            double reculDistance = 60;
            double testX = x;
            double testY = y;

            switch(direction) {
                case NORTH -> testY += reculDistance;
                case SOUTH -> testY -= reculDistance;
                case EAST -> testX -= reculDistance;
                case WEST -> testX += reculDistance;
            }

            if(isPositionFree(testX, testY, environment)){
                this.x= testX;
                this.y= testY;
            }
        }
    }

    private boolean isPositionFree(double tx, double ty, ArrayList<Sprite> environment){
        Rectangle2D.Double testHitbox = new Rectangle2D.Double(tx, ty, width, height);
        for(Sprite s: environment){
            if(s instanceof  SolidSprite && !(s instanceof Trap) && s!= this){
                if (((SolidSprite) s).getHitbox().intersects(testHitbox))return false;
            }
        }
        return true;
    }


    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    public void takeDamage(int damage){
        if (!isInvulnerable && currentHealth > 0){
            this.currentHealth-= damage;
            this.isInvulnerable= true;
            if (this.currentHealth<0)currentHealth=0;
            this.invulnerabilityStartTime = System.currentTimeMillis();
            switch(currentHealth){
                case 4 -> System.out.println("PV Restants: "+ currentHealth );
                case 3 -> System.out.println("PV Restants: "+ currentHealth + ", FAIS GAFFE !");
                case 2 -> System.out.println("PV Restants: "+ currentHealth + ", T'es bientôt mort si tu n'avais pas remarqué !");
                case 1 -> System.out.println("PV Restants: "+ currentHealth + ", T'es foutu, je t'avais dit que tu ne pouvais pas t'échapper");


            }
        }
    }


    private boolean shouldBeDrawn(){
        if(!isInvulnerable)return true;
        long durationNoHit = System.currentTimeMillis() - invulnerabilityStartTime;
        if(durationNoHit > INVULNERABILITY_DURATION){
            isInvulnerable = false;
            return true;
        }
        return
                (durationNoHit / 300)%2 == 0;

    }


    @Override
    public void draw(Graphics g){
        if(!shouldBeDrawn())return;
        int index = (int) ((System.currentTimeMillis()/timeBetweenFrame)% spriteSheetNumberOfColumn );
        int attitude = direction.getFrameLineNumber();
        g.drawImage(image, (int) x, (int) y, (int) (x+ width), (int) (y+height),
                (int) (index*width),(int) (attitude*height), (int) ((index+1)*width), (int) ((attitude+1)*height), null );

    }


}
