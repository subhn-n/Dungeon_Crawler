import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    JFrame Arena;
    RenderEngine renderEngine;
    PhysicEngine physicEngine;
    GameEngine gameEngine;

    public Main() {
        Arena = new JFrame("Echappe-toi si tu peux !");
        Arena.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Arena.setSize(400, 600);

        WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        Arena.add(welcomeScreen);
        Arena.setVisible(true);
    }

        public void startGame(){


        DynamicSprite hero = null;
        try {
            hero = new DynamicSprite(
                    ImageIO.read(new File("./tiles/heroTileSheetLowRes.png")), 200,300,48,50);
            hero.setDirection(Direction.SOUTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());


        renderTimer.start();
        physicTimer.start();
        gameTimer.start();

        Arena.getContentPane().add(renderEngine);

//        Sprite test = null;
//        try {
//            test = new Sprite(
//                    ImageIO.read(new File("./tiles/tree.png")),200,300,64,64);
//            renderEngine.addToRenderList(test);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }




        Arena.addKeyListener(gameEngine);


//        SolidSprite testSprite = null;
//        try {
//            testSprite = new SolidSprite(ImageIO.read(new File("./tiles/rock.png")),250,300,64,64);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        renderEngine.addToRenderList(testSprite);


//        ArrayList<Sprite> decor= new ArrayList<>();
//        decor.add(testSprite);
//        physicEngine.setEnvironment(new ArrayList<Sprite>(decor));
//    }

    PlayGround playGround = new PlayGround("./tiles/level1.txt");
    for (Displayable d: playGround.getSpriteList()){
        renderEngine.addToRenderList(d);
    }

    physicEngine.setEnvironment(playGround.getSolidSpriteList());
    renderEngine.addToRenderList(hero);
    physicEngine.addMovingSpriteList(hero);

    Arena.setVisible(true);
    Arena.requestFocus();


    }

    public static void main(String[] args) throws Exception{
        Main main = new Main();


    }
}