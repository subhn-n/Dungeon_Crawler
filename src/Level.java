public class Level {
    private int mapWidth;
    private int mapHeight;
    private Door door;
    private String exitSide ="RIGHT";
    private String path;

    public Level(int mapWidth, int mapHeight, Door door, String exitSide, String path) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.door = door;
        this.exitSide= exitSide;
        this.path= path;
    }


    public Door getDoor() {return door;}

    public String getExitSide() {return exitSide;}

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {return mapHeight;}

    public String getPath() {return path;}

}
