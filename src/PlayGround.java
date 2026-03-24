import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class PlayGround {
    private ArrayList<Sprite> environment = new ArrayList<>();

    public PlayGround (String pathName){
        try{
            final Image imageTree = ImageIO.read(getClass().getResource("/tiles/tree.png"));
            final Image imageGrass = ImageIO.read(getClass().getResource("/tiles/grass.png"));
            final Image imageRock = ImageIO.read(getClass().getResource("/tiles/rock.png"));
            final Image imageTrap = ImageIO.read(getClass().getResource("/tiles/trap.png"));
            final Image imageBTrap = ImageIO.read(getClass().getResource("/tiles/bearTrap.png"));
            final Image imageHealthRegen = ImageIO.read(getClass().getResource("/tiles/healthRegen.png"));



            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);
            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);
            final int imageBTrapWidth = imageBTrap.getWidth(null);
            final int imageBTrapHeight = imageBTrap.getHeight(null);
            final int imageHealthRegenWidth = imageHealthRegen.getWidth(null);
            final int imageHealthRegenHeight = imageHealthRegen.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(pathName)));
            String line=bufferedReader.readLine();

            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(ImageIO.read(getClass().getResource("/tiles/tree.png")), columnNumber*imageTreeWidth,
                                    lineNumber*imageTreeHeight, imageTreeWidth, imageTreeHeight));
                        break;
                        case ' ' :
                        case 'G': environment.add(new Sprite( ImageIO.read(getClass().getResource("/tiles/grass.png")), columnNumber*imageGrassWidth,
                                    lineNumber*imageGrassHeight, imageGrassWidth, imageGrassHeight));
                        break;
                        case 'R' : environment.add(new SolidSprite( ImageIO.read(getClass().getResource("/tiles/rock.png")), columnNumber*imageRockWidth,
                                    lineNumber*imageRockHeight, imageRockWidth, imageRockHeight));
                        break;
                        case 'X' : environment.add(new Trap( ImageIO.read(getClass().getResource("/tiles/trap.png")), columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrapWidth, imageTrapHeight, 1));
                        break;
                        case 'B' : environment.add(new Trap( ImageIO.read(getClass().getResource("/tiles/bearTrap.png")), columnNumber*imageBTrapWidth,
                                lineNumber*imageBTrapHeight, imageBTrapWidth, imageBTrapHeight, 4));
                        break;
                        case 'H' : environment.add(new HealTile( ImageIO.read(getClass().getResource("/tiles/healthRegen.png")), columnNumber*imageHealthRegenWidth,
                                lineNumber*imageHealthRegenHeight, imageHealthRegenWidth, imageHealthRegenHeight));
                        }
                        columnNumber++;
                    }
                    columnNumber =0;
                    lineNumber++;
                    line=bufferedReader.readLine();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public ArrayList<Sprite> getSolidSpriteList(){
            ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
            for (Sprite sprite : environment){
                if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
            }
            return solidSpriteArrayList;
        }

        public ArrayList<Displayable> getSpriteList(){
            ArrayList <Displayable> displayableArrayList = new ArrayList<>();
            for (Sprite sprite : environment){
                displayableArrayList.add((Displayable) sprite);
            }
            return displayableArrayList;
        }

}
