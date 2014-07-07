package org.teachingkidsprogramming.typingdeepdive;

import com.spun.util.NumberUtils;
import com.spun.util.io.FileUtils;

public class Words
{
  private static String[] words;
  /**
   * @return
   */
  public static String next()
  {
    String[] words = getWords();
    return words[NumberUtils.getRandomInt(0, words.length)];
  }
  public static String[] getWords()
  {
    if (words == null)
    {
      words = FileUtils.readFromClassPath(Words.class, "words.txt").split("\n");
    }
    return words;
  }
}
