package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.spun.util.ArrayUtils;

public class Shark implements Actor
{
  public enum PlayState {
    Active, Selected, Killed, Killing
  };
  public static final int              speed     = 1000 / (15 * 25);
  private static Image                 image;
  private String                       word;
  private int                          typed     = 0;
  public Dimension                     outer     = new Dimension(160, 70);
  private int                          diver     = 75;
  private Point                        topRight;
  private PlayState                    state;
  private ArrayList<PlayStateListener> listeners = new ArrayList<PlayStateListener>();
  private int                          frames;
  public Shark()
  {
    this("Samantha", 200);
  }
  public Shark(String word, int y)
  {
    this.word = word;
    this.setState(PlayState.Active);
    this.topRight = new Point(1000, y);
  }
  public void paint(Graphics g, Dimension box)
  {
    int startX = box.width;
    int length = startX - diver;
    double xScale = length / 1000.0;
    double yScale = box.getHeight() / 1000.0;
    int xPosition = (int) ((xScale * topRight.x) + diver);
    int yPosition = (int) (yScale * topRight.y);
    drawBody(g, xPosition, yPosition);
    drawText(g, xPosition, yPosition);
  }
  private void drawBody(Graphics g, int xPosition, int yPosition)
  {
    Image image = loadImage();
    int width = image.getWidth(null) / 20;
    int height = image.getHeight(null);
    int start = (frames % 20) * width;
    g.drawImage(image, xPosition, yPosition, xPosition + width, yPosition + height, start, 0, start + width,
        height, null);
  }
  public static Image loadImage()
  {
    if (image == null)
    {
      image = new ImageIcon(Shark.class.getResource("shark_basic_swim.png")).getImage();
    }
    return image;
  }
  public void drawText(Graphics g, int xPosition, int yPosition)
  {
    g.setFont(new Font("Cambria", Font.BOLD, 16));
    FontMetrics fontMetrics = g.getFontMetrics();
    Rectangle2D bounds = fontMetrics.getStringBounds(word, g);
    int xOffset = 6;
    int x = (int) ((outer.width - bounds.getWidth()) / 2) + xOffset;
    int yOffset = -3;
    int y = (int) ((outer.height - bounds.getHeight()) / 2) + fontMetrics.getAscent() + yOffset;
    g.setColor(Color.WHITE);
    g.drawString(word, xPosition + x, yPosition + y);
    g.setColor(Color.RED);
    g.drawString(getCompleted(), xPosition + x, yPosition + y);
  }
  public String getCompleted()
  {
    return word.substring(0, typed);
  }
  public void advanceClock()
  {
    if (isFinished()) { return; }
    topRight.x -= speed;
    if (topRight.x <= 0)
    {
      setState(PlayState.Killing);
    }
    frames++;
  }
  public boolean processLetter(char letter)
  {
    if (isFinished()) { return false; }
    char currentLetter = word.charAt(typed);
    if (currentLetter == letter)
    {
      setState(PlayState.Selected);
      typed++;
      if (typed == word.length())
      {
        setState(PlayState.Killed);
        Sounds.playSharkDies();
      }
      return true;
    }
    if (0 < typed)
    {
      Sounds.playWrongLetter();
    }
    return false;
  }
  public boolean isFinished()
  {
    return ArrayUtils.contains(new PlayState[]{PlayState.Killed, PlayState.Killing}, getState());
  }
  @Override
  public String toString()
  {
    String template = "Shark{word:%s,completed:%s,topRight:%s,state:%s}";
    return String.format(template.replace(',', '\n'), word, getCompleted(), topRight, getState());
  }
  public PlayState getState()
  {
    return state;
  }
  public void addListener(PlayStateListener playStateListener)
  {
    listeners.add(playStateListener);
  }
  private void setState(PlayState state)
  {
    if (this.state != state)
    {
      this.state = state;
      for (PlayStateListener l : listeners)
      {
        l.playStateChanged(this);
      }
    }
  }
}
