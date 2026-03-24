import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundDisplay {
    public static void play(String pathname){
        try {
            AudioInputStream endGameSound = AudioSystem.getAudioInputStream(
                    SoundDisplay.class.getResource(pathname));
            Clip clip = AudioSystem.getClip();
            clip.open(endGameSound);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
