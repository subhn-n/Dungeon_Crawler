import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    JFrame Arena;
    RenderEngine renderEngine;
    PhysicEngine physicEngine;
    GameEngine gameEngine;
    DynamicSprite hero;
    GameContext context;

    public Main(GameContext context) throws IOException {

        Image[] doorFrames = { ImageIO.read(Main.class.getResource("/door/door_shut.png")),
                ImageIO.read(Main.class.getResource("/door/door_anim1.png")),
                ImageIO.read(Main.class.getResource("/door/door_anim2.png")),
                ImageIO.read(Main.class.getResource("/door/door_open.png")) };

        Door gate = new Door(doorFrames, context, this);

        Level level1 = new Level(14*64, 9*64, gate, "RIGHT", "/levels/level1.txt");
        Level level2 = new Level(14*64, 9*64, gate, "RIGHT", "/levels/level2.txt");
        Level level3 = new Level(2*14*64, 9*64, gate, "BOTTOM","/levels/level3.txt");

        ArrayList<Level> levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        context.setLevels(levels);
        context.loadLevel(level1);
        gate.updatePositionBasedOnLevel();



        this.context= context;

        Arena = new JFrame("Echappe-toi si tu peux !");
        Arena.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Arena.setSize(context.getWindowWidth(), context.getWindowHeight());

        WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        Arena.add(welcomeScreen);
        Arena.setVisible(true);
    }


        public void startGame(){


        hero = null;
        try {
            hero = new DynamicSprite(
                    ImageIO.read(getClass().getResource("/tiles/heroTileSheetLowRes.png")), 200,300,48,50);
            hero.setDirection(Direction.SOUTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        renderEngine = new RenderEngine(hero, context);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, context);

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



//    PlayGround playGround = new PlayGround("/levels/level1.txt");
//    for (Displayable d: playGround.getSpriteList()){
//        renderEngine.addToRenderList(d);
//    }
//
//    physicEngine.setEnvironment(playGround.getSolidSpriteList());
//    gameEngine.setEnvironment(playGround.getSolidSpriteList());
//
//    renderEngine.addToRenderList(hero);
//    physicEngine.addMovingSpriteList(hero);
//
//    Arena.setVisible(true);
//    Arena.requestFocus();

        loadLevel("/levels/level1.txt");
            Arena.setVisible(true);
            Arena.requestFocus();

        }


    public void loadLevel(String pathname){
        renderEngine.clearSprites();
        physicEngine.clearSprites();
        //gameEngine.clearSprites();

        PlayGround playGround = new PlayGround(pathname);
        for (Displayable d: playGround.getSpriteList()){
            renderEngine.addToRenderList(d);
        }

        physicEngine.setEnvironment(playGround.getSolidSpriteList());
        gameEngine.setEnvironment(playGround.getSolidSpriteList());

        Door gate = context.getCurrentLevel().getDoor();
        renderEngine.addToRenderList(gate);

        renderEngine.addToRenderList(hero);
        physicEngine.addMovingSpriteList(hero);

        hero.setX(50);
        hero.setY(50);
    }



    public static void main(String[] args) throws Exception{
        GameContext context = new GameContext(600, 625);
//        Image[] doorFrames = { ImageIO.read(Main.class.getResource("/door/door_shut.png")),
//                ImageIO.read(Main.class.getResource("/door/door_anim1.png")),
//                ImageIO.read(Main.class.getResource("/door/door_anim2.png")),
//                ImageIO.read(Main.class.getResource("/door/door_open.png")) };
//
//        Door gate = new Door(doorFrames, context, null);
//
//        Level level1 = new Level(14*64, 9*64, gate, "RIGHT");
//        Level level2 = new Level(14*64, 9*64, gate, "RIGHT");
//        Level level3 = new Level(14*64, 9*64, gate, "BOTTOM");
//
//        ArrayList<Level> levels = new ArrayList<>();
//        levels.add(level1);
//        levels.add(level2);
//        levels.add(level3);
//
//        context.setLevels(levels);
//        context.loadLevel(level1);
//        gate.updatePositionBasedOnLevel();


        Main main = new Main(context);


    }
}