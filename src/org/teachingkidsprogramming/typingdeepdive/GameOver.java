package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;

import org.teachingkidsprogramming.typingdeepdive.analytics.GameOverAnalytics;

public class GameOver implements Actor
{
  private DeepDiveTypingGame game;
  public GameOver(DeepDiveTypingGame game)
  {
    this.game = game;
    Sounds.playPlayerDies();
    game.selected = this;
    game.stopTimer();
    doAnalytics(game);
  }
  public void doAnalytics(DeepDiveTypingGame game)
  {
    game.analytics.gameOver = new GameOverAnalytics();
    game.writeAnalytics();
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
