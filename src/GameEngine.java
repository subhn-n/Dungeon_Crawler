import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    private final DynamicSprite hero;
    ArrayList<Sprite> environment = new ArrayList<>();
    private boolean downPressed, leftPressed, upPressed, rightPressed;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
        if (downPressed) hero.moveIfPossible(environment);
        if (leftPressed) hero.moveIfPossible(environment);
        if (upPressed) hero.moveIfPossible(environment);
        if (rightPressed) hero.moveIfPossible(environment);

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
