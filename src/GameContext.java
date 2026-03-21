import java.util.ArrayList;

public class GameContext{
    private final int windowWidth;
    private final int windowHeight;
    private Level currentLevel;
    private ArrayList<Level> levels = new ArrayList<>();
    private int currentLevelIndex = 0;
    private int mapWidth;
    private int mapHeight;
    private int remainingSeconds =10;
    private boolean isTimeUp = false;

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

    public void decreaseSeconds(){
        if (remainingSeconds > 0) {
            remainingSeconds--;
        }else{
            isTimeUp= true;
        }
    }

    public String timeIntoString(){
        int minutes = (remainingSeconds / 60);
        int seconds= (remainingSeconds %60);
        return String.format("%02d:%02d", minutes,seconds);
    }

    public boolean isTimeUp(){
        return isTimeUp;
    }

    public void reset(int minutes){
        this.remainingSeconds= 60*minutes;
        this.isTimeUp= false;
        this.currentLevelIndex=0;
    }


    public void setLevels(ArrayList<Level> levels) {this.levels = levels;}

    public Level getCurrentLevel() {return currentLevel;}

    public int getWindowWidth() {return windowWidth;}

    public int getWindowHeight() {return windowHeight;}

    public int getMapWidth() {return mapWidth;}

    public int getMapHeight() {return mapHeight;}

    public int getRemainingSeconds() {return remainingSeconds;}


}

