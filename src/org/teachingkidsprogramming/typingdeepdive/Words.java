package org.teachingkidsprogramming.typingdeepdive;

import com.spun.util.NumberUtils;

public class Words
{
  public static String next()
  {
    String[] words = {
        "Samantha", "Lynn", "Llewellyn", "Jessica", "Brick", "teaching", "kids", "programming", "xenophobe",
        "quintessence"};
    return words[NumberUtils.getRandomInt(0, words.length)];
  }
}
