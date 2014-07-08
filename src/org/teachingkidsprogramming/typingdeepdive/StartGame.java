package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class StartGame implements Actor
{
  private DeepDiveTypingGame game;
  public StartGame(DeepDiveTypingGame game)
  {
    this.game = game;
    preloadImages();
  }
  public void preloadImages()
  {
    Shark.loadImage();
    Diver.loadImage();
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
      return true;
    }
    return false;
  }
  @Override
  public void paint(Graphics g, Dimension size)
  {
    paintText(g, size, "(S)tart Game");
  }
  public static void paintText(Graphics g, Dimension size, String text)
  {
    g.setColor(Color.white);
    g.setFont(new Font(Font.SERIF, Font.BOLD, 28));
    Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
    int x = (int) ((size.width - bounds.getWidth()) / 2);
    int y = (int) ((size.height - bounds.getHeight()) / 2);
    g.drawString(text, x, y);
  }
}
