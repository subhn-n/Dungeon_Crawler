import java.awt.*;
import java.awt.image.BufferedImage;

public class Door implements Displayable {
    private Image[] doorFrames;
    private int x, y;
    private GameContext context;
    private int currentFrames;
    private  Boolean isAnimating = false;
    private int timerCount;
    private final int TICKS_PER_FRAME = 3;
    private Main main;

    public Door(Image[] doorFrames, GameContext context, Main main) {
        this.doorFrames = doorFrames;
        this.context = context;
        this.main = main;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}

    public Boolean isAnimating() {return isAnimating;}

    public void startOpening(){
        this.isAnimating = true;
    }

    public void updatePositionBasedOnLevel(){
        Level currentLevel = context.getCurrentLevel();
        String side = currentLevel.getExitSide();

        if (side.equals("RIGHT")){
            this.x = currentLevel.getMapWidth() -62;
            this.y = currentLevel.getMapHeight() /2 -62;
        }else if (side.equals("BOTTOM")){
            this.x = currentLevel.getMapWidth()-62;
            this.y= currentLevel.getMapHeight() -74;
        }

    }


    public void triggerLevelChange(){
        String nextLevelPath = context.loadNextLevel();
        if (nextLevelPath!= null){
            main.loadLevel(nextLevelPath);
            updatePositionBasedOnLevel();
        }

        this.currentFrames= 0;
        this.isAnimating=false;
    }


    public void updateAnimation(){
        if(isAnimating){
            timerCount++;
            if (timerCount >= TICKS_PER_FRAME){
                timerCount= 0;
                currentFrames++;
                if (currentFrames >= doorFrames.length){
                currentFrames= doorFrames.length-1;
                isAnimating= false;
                triggerLevelChange();
                }

            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(doorFrames[currentFrames], this.x, this.y, null);
    }

}
