import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    Timer gameTimer;
    Timer renderTimer;
    Timer physicTimer;
    Level level1;
    Door gate;


    public Main(GameContext context) throws IOException {

        Image[] doorFrames = { ImageIO.read(Main.class.getResource("/door/door_shut.png")),
                ImageIO.read(Main.class.getResource("/door/door_anim1.png")),
                ImageIO.read(Main.class.getResource("/door/door_anim2.png")),
                ImageIO.read(Main.class.getResource("/door/door_open.png")) };

        gate = new Door(doorFrames, context, this);

        level1 = new Level(14*64, 9*64, gate, "RIGHT", "/levels/level1.txt", "LEVEL 1");
        Level level2 = new Level(14*64, 9*64, gate, "RIGHT", "/levels/level2.txt", "LEVEL 2");
        Level level3 = new Level(2*14*64, 9*64, gate, "BOTTOM","/levels/level3.txt", "FINAL LEVEL");

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


        renderEngine = new RenderEngine(hero, context, this);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, context);

        renderTimer = new Timer(50,(time)-> renderEngine.update());
        physicTimer = new Timer(50,(time)-> physicEngine.update());
        gameTimer = new Timer(50,(time)-> {gameEngine.update();
                                                                if (context.isTimeUp())triggerGameOver();
                                                                });

        renderTimer.start();
        physicTimer.start();
        gameTimer.start();

        Arena.getContentPane().add(renderEngine);

        Arena.addKeyListener(gameEngine);


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


    private void playStartSound(String pathname){
        try {
            AudioInputStream endGameSound = AudioSystem.getAudioInputStream(
                    getClass().getResource(pathname));
            Clip clip = AudioSystem.getClip();
            clip.open(endGameSound);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void triggerGameOver(){
        if(gameTimer != null) gameTimer.stop();
        renderEngine.setCurrentState(GameState.GAME_OVER);
        playStartSound("/Son/gameOver.wav");
        renderEngine.repaint();

    }

    public void triggerWinScreen(){
        if(gameTimer != null) gameTimer.stop();
        renderEngine.setCurrentState(GameState.WIN);
        playStartSound("/Son/gameFinished.wav");
        renderEngine.repaint();
    }

    public void resetGame(){
        stopAllTimers();
        context.reset(20);
        renderEngine.setCurrentState(GameState.PLAYING);
        renderEngine.hideRetryButton();
        context.loadLevel(level1);
        gate.updatePositionBasedOnLevel();
        startGame();
        //renderEngine.requestFocusInWindow();
        Arena.requestFocus();

    }

    public void stopAllTimers(){
        if(renderTimer != null) renderTimer.stop();
        if(gameTimer != null) gameTimer.stop();
        if(physicTimer != null) physicTimer.stop();


    }



    public static void main(String[] args) throws Exception{
        GameContext context = new GameContext(600, 625);

        Main main = new Main(context);


    }
}