package stud.ntnu.idatt1005.pantrypal.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundPlayer {
  public enum Sound {
    DEFAULT,
    DELETE
  }

  public static void playSound(Sound sound) {
    if (sound == Sound.DELETE) {
      playSound(deleteSoundPath);
    } else {
      playSound(defaultSoundPath);
    }
  }

  private static final String defaultSoundPath = "src/main/resources/sound/defaultSound.wav";
  private static final String deleteSoundPath = "src/main/resources/sound/deleteSound.mp3";
  private static void playSound(String path) {
    Media sound = new Media(new File(path).toURI().toString());
    MediaPlayer player= new MediaPlayer(sound);
    player.play();
  }
}
