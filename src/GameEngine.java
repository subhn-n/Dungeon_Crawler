import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    private final DynamicSprite hero;
    ArrayList<Sprite> environment = new ArrayList<>();
    private boolean downPressed, leftPressed, upPressed, rightPressed;
    private GameContext context;

    public GameEngine(DynamicSprite hero, GameContext context) {
        this.hero = hero;
        this.context= context;
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void clearSprites(){
        environment.clear();
    }

    public boolean heroTouchesDoor(){
        Door currentDoor = context.getCurrentLevel().getDoor();
        Rectangle2D.Double doorHitbox = new Rectangle2D.Double(currentDoor.getX(), currentDoor.getY(), 62, 74);
        return doorHitbox.intersects(hero.getHitbox());
    }


    @Override
    public void update() {
        Door currentDoor = context.getCurrentLevel().getDoor();
        if (currentDoor.isAnimating()){
            currentDoor.updateAnimation();
            return;
        }


        if (downPressed) hero.moveIfPossible(environment);
        if (leftPressed) hero.moveIfPossible(environment);
        if (upPressed) hero.moveIfPossible(environment);
        if (rightPressed) hero.moveIfPossible(environment);

        if (heroTouchesDoor()){
            currentDoor.startOpening();
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_S:
                hero.setDirection(Direction.SOUTH);
                downPressed = true;
                break;
            case KeyEvent.VK_Q:
                hero.setDirection(Direction.WEST);
                leftPressed = true;
                break;
            case KeyEvent.VK_Z:
                hero.setDirection(Direction.NORTH);
                upPressed = true;
                break;
            case KeyEvent.VK_D:
                hero.setDirection(Direction.EAST);
                rightPressed = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                hero.setDirection(Direction.SOUTH);
                downPressed = false;
                break;
            case KeyEvent.VK_Q:
                hero.setDirection(Direction.WEST);
                leftPressed = false;
                break;
            case KeyEvent.VK_Z:
                hero.setDirection(Direction.NORTH);
                upPressed = false;
                break;
            case KeyEvent.VK_D:
                hero.setDirection(Direction.EAST);
                rightPressed = false;
                break;
        }
    }



}
