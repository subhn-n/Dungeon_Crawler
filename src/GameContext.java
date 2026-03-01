import java.util.ArrayList;

public class GameContext{
    private final int windowWidth;
    private final int windowHeight;
    private Level currentLevel;
    private ArrayList<Level> levels = new ArrayList<>();
    private int currentLevelIndex = 0;
    private int mapWidth;
    private int mapHeight;

    public GameContext(int w, int h){
        this.windowWidth = w;
        this.windowHeight = h;
    }

    public void loadLevel (Level level){
        this.mapWidth = level.getMapWidth();
        this.mapHeight = level.getMapHeight();
        this.currentLevel = level;


    }

    public String loadNextLevel(){
        currentLevelIndex ++;
        if (currentLevelIndex < levels.size()){
            Level next = levels.get(currentLevelIndex);
            this.loadLevel(next);
            return next.getPath();
        }
        return null;
    }

    public void setLevels(ArrayList<Level> levels) {this.levels = levels;}

    public Level getCurrentLevel() {return currentLevel;}

    public int getWindowWidth() {return windowWidth;}

    public int getWindowHeight() {return windowHeight;}

    public int getMapWidth() {return mapWidth;}

    public int getMapHeight() {return mapHeight;}

    }

