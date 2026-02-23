public class GameContext{
    private final int windowWidth;
    private final int windowHeight;

    private int mapWidth;
    private int mapHeight;

    public GameContext(int w, int h){
        this.windowWidth = w;
        this.windowHeight = h;
    }

    public void LoadLevel (Level level){
        this.mapWidth = level.getMapWidth();
        this.mapHeight = level.getMapHeight();
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
