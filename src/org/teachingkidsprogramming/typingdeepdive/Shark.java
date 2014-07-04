package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.spun.util.ArrayUtils;

public class Shark
{
  public enum PlayState {
    Active, Selected, Killed, Killing
  };
  public static final int              speed     = 1000 / (15 * 25);
  private String                       word;
  private int                          typed     = 0;
  private Dimension                    outer     = new Dimension(200, 50);
  private int                          diver     = 75;
  private Point                        topRight;
  private PlayState                    state;
  private ArrayList<PlayStateListener> listeners = new ArrayList<PlayStateListener>();
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
    int startX = box.width - outer.width;
    int length = startX - diver;
    double xScale = length / 1000.0;
    double yScale = box.getHeight() / 1000.0;
    int xPosition = (int) ((xScale * topRight.x) + diver);
    int yPosition = (int) (yScale * topRight.y);
    g.setColor(Color.WHITE);
    g.drawString(word, xPosition, yPosition);
    g.setColor(Color.RED);
    g.drawString(getCompleted(), xPosition, yPosition);
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
  }
  public void processLetter(char letter)
  {
    if (isFinished()) { return; }
    char currentLetter = word.charAt(typed);
    if (currentLetter == letter)
    {
      setState(PlayState.Selected);
      typed++;
      if (typed == word.length())
      {
        setState(PlayState.Killed);
      }
    }
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
