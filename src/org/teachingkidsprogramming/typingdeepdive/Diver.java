package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Diver implements Actor
{
  private static Image image;
  private int          frames;
  @Override
  public void advanceClock()
  {
    frames++;
  }
  @Override
  public boolean processLetter(char letter)
  {
    return false;
  }
  @Override
  public void paint(Graphics g, Dimension size)
  {
    int imageCount = 8;
    Image image = loadImage();
    int width = image.getWidth(null) / imageCount;
    int height = image.getHeight(null);
    int start = ((frames / 4) % imageCount) * width;
    int y = (size.height - height) / 2;
    g.drawImage(image, 0, y, width, y + height, start, 0, start + width, height, null);
  }
  public static Image loadImage()
  {
    if (image == null)
    {
      image = new ImageIcon(Diver.class.getResource("diver.png")).getImage();
    }
    return image;
  }
}
