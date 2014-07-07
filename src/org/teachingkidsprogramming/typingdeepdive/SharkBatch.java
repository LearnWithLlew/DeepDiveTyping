package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.spun.util.NumberUtils;

public class SharkBatch implements Actor
{
  private DeepDiveTypingGame                 game;
  private HashMap<Integer, ArrayList<Shark>> sharks = new HashMap<Integer, ArrayList<Shark>>();
  private int                                frames;
  public SharkBatch(DeepDiveTypingGame game)
  {
    this.game = game;
    addSharks(6);
    game.addDiver();
  }
  private void addSharks(int number)
  {
    int spacing = (900 - 50) / number;
    for (int i = 0; i < number; i++)
    {
      int y = 50 + (spacing * i) + NumberUtils.getRandomInt(-50, 50);
      Shark shark = new Shark(Words.next(), y);
      add(NumberUtils.getRandomInt(1, 75), shark);
    }
  }
  private void add(int frame, Shark shark)
  {
    if (!sharks.containsKey(frame))
    {
      sharks.put(frame, new ArrayList<Shark>());
    }
    sharks.get(frame).add(shark);
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
