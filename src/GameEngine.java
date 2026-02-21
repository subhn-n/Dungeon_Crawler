import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    private final DynamicSprite hero;
    ArrayList<Sprite> environment = new ArrayList<>();

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_S:
                hero.setDirection(Direction.SOUTH);
                hero.moveIfPossible(environment);
                break;
            case KeyEvent.VK_Q:
                hero.setDirection(Direction.WEST);
                hero.moveIfPossible(environment);
                break;
            case KeyEvent.VK_Z:
                hero.setDirection(Direction.NORTH);
                hero.moveIfPossible(environment);
                break;
            case KeyEvent.VK_D:
                hero.setDirection(Direction.EAST);
                hero.moveIfPossible(environment);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }



}
