package org.teachingkidsprogramming.typingdeepdive;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.spun.util.MySystem;

public class Sounds
{
  public static void playWrongLetter()
  {
    playSoundFile("sounds/wrong_letter.wav");
  }
  public static void playSharkDies()
  {
    playSoundFile("sounds/shark_dies.wav");
  }
  public static void playDiveUnderwater()
  {
    playSoundFile("sounds/going_underwater.wav");
  }
  public static void playPlayerDies()
  {
    playSoundFile("sounds/player_about_to_die.wav");
    playSoundFile("sounds/player_died.wav");
  }
  public static void playSoundFile(String soundFile)
  {
    try
    {
      InputStream soundRaw = Sounds.class.getResourceAsStream(soundFile);
      BufferedInputStream sound = new BufferedInputStream(soundRaw);
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
    }
    catch (Exception e)
    {
      MySystem.warning(e);
      // do nothing
    }
  }
}
