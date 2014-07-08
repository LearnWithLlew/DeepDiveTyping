package org.teachingkidsprogramming.typingdeepdive.tests;

import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.Sounds;

public class SoundDemo
{
  @Test
  public void testWrongLetter() throws Exception
  {
    //Sounds.playDiveUnderwater();
    // Sounds.playWrongLetter();
    Sounds.playPlayerDies();
    //Sounds.playSharkDies();
    Thread.sleep(2000);
  }
}
