package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Shark
{
  private String    word     = "Samantha";
  private int       typed    = 0;
  private Dimension outer    = new Dimension(200, 50);
  private int       diver    = 75;
  private Point     topRight = new Point(0, 200);
  public void paint(Graphics g, Dimension box)
  {
    int time = 15;
    double frames = time * 25;
    int startX = box.width - outer.width;
    int length = startX - diver;
    double speed = length / frames;
    double position = speed * topRight.x;
    int sharkHead = (int) (startX - position);
    g.setColor(Color.WHITE);
    g.drawString(word, sharkHead, topRight.y);
    g.setColor(Color.RED);
    g.drawString(word.substring(0, typed), sharkHead, topRight.y);
  }
  public void advanceClock()
  {
    topRight.x++;
  }
  public void processLetter(char letter)
  {
    char currentLetter = word.charAt(typed);
    if (currentLetter == letter)
    {
      typed++;
    }
    System.out.println(String.format("Processing %s vs %s", currentLetter, letter));
  }
}
