package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;

public class GameOver implements Actor
{
  private DeepDiveTypingGame game;
  public GameOver(DeepDiveTypingGame game)
  {
    this.game = game;
    game.selected = this;
    game.timer.stop();
  }
  @Override
  public void advanceClock()
  {
  }
  @Override
  public boolean processLetter(char letter)
  {
    if (letter == 's')
    {
      game.start();
      game.timer.start();
      return true;
    }
    return false;
  }
  @Override
  public void paint(Graphics g, Dimension size)
  {
    StartGame.paintText(g, size, "Game Over \n re(S)tart Game");
  }
}
