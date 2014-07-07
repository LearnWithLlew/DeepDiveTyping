package org.teachingkidsprogramming.typingdeepdive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.teachingkidsprogramming.typingdeepdive.Shark.PlayState;

import com.spun.util.WindowUtils;

public class DeepDiveTypingGame implements KeyListener, PlayStateListener
{
  public JPanel           view   = new DeepDiveTypingView(this);
  public ArrayList<Actor> actors = new ArrayList<Actor>();
  private Timer           timer;
  private Actor           selected;
  private void launchWindow()
  {
    startGame();
    WindowUtils.testPanel(view);
  }
  private void startGame()
  {
    actors.add(new SharkBatch(this));
    startClock();
  }
  private void startClock()
  {
    int delay = 1000 / 25; //milliseconds
    ActionListener taskPerformer = new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        advanceClock();
        view.repaint();
      }
    };
    timer = new Timer(delay, taskPerformer);
    timer.start();
  }
  protected void advanceClock()
  {
    for (Actor shark : actors.toArray(new Actor[0]))
    {
      shark.advanceClock();
    }
  }
  public static void main(String[] args)
  {
    new DeepDiveTypingGame().launchWindow();
  }
  private void processLetter(char letter)
  {
    if (selected != null)
    {
      selected.processLetter(letter);
    }
    for (Actor shark : actors)
    {
      if (selected == null)
      {
        shark.processLetter(letter);
      }
    }
  }
  @Override
  public void keyTyped(KeyEvent e)
  {
    processLetter(e.getKeyChar());
  }
  @Override
  public void keyPressed(KeyEvent e)
  {
    //do nothing
  }
  @Override
  public void keyReleased(KeyEvent e)
  {
    //do nothing
  }
  @Override
  public void playStateChanged(Shark shark)
  {
    PlayState state = shark.getState();
    switch (state)
    {
      case Killed :
        removeShark(shark);
        break;
      case Killing :
        timer.stop();
        break;
      case Selected :
        selected = shark;
        break;
      default :
        break;
    }
  }
  public void removeShark(Shark shark)
  {
    actors.remove(shark);
    selected = null;
    if (actors.isEmpty())
    {
      actors.add(new SharkBatch(this));
    }
  }
}
