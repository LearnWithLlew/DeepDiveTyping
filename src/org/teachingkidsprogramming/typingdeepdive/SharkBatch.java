package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.spun.util.NumberUtils;

public class SharkBatch implements Actor
{
  private DeepDiveTypingGame                 game;
  private HashMap<Integer, ArrayList<Shark>> sharks = new HashMap<Integer, ArrayList<Shark>>();
  private int                                frames;
  public SharkBatch(DeepDiveTypingGame game)
  {
    this.game = game;
    this.game.score.scoreNewBatch();
    addSharks(3 + game.score.getBatch());
    Sounds.playDiveUnderwater();
  }
  private void addSharks(int number)
  {
    int spacing = (900 - 50) / number;
    HashSet<Character> letters = new HashSet<Character>();
    for (int i = 0; i < number; i++)
    {
      int y = 50 + (spacing * i) + NumberUtils.getRandomInt(-50, 50);
      String word = getUniqueWord(letters);
      Shark shark = new Shark(word, y);
      add(sharks, NumberUtils.getRandomInt(1, 75), shark);
    }
  }
  public static String getUniqueWord(HashSet<Character> letters)
  {
    String word;
    do
    {
      word = Words.next(2, 7);
    }
    while (letters.contains(word.charAt(0)));
    letters.add(word.charAt(0));
    return word;
  }
  public static <K, V> void add(HashMap<K, ArrayList<V>> map, K frame, V shark)
  {
    if (!map.containsKey(frame))
    {
      map.put(frame, new ArrayList<V>());
    }
    map.get(frame).add(shark);
  }
  @Override
  public void advanceClock()
  {
    frames++;
    if (sharks.containsKey(frames))
    {
      for (Shark s : sharks.get(frames))
      {
        s.addListener(game);
        game.actors.add(s);
      }
      sharks.remove(frames);
    }
    if (sharks.isEmpty())
    {
      game.actors.remove(this);
    }
  }
  @Override
  public boolean processLetter(char letter)
  {
    return false;
  }
  @Override
  public void paint(Graphics g, Dimension size)
  {
  }
}
