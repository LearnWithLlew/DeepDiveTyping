package org.teachingkidsprogramming.typingdeepdive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.teachingkidsprogramming.typingdeepdive.Shark.PlayState;

import com.spun.util.NumberUtils;
import com.spun.util.WindowUtils;

public class DeepDiveTypingGame implements KeyListener, PlayStateListener
{
  public JPanel           view   = new DeepDiveTypingView(this);
  public ArrayList<Shark> actors = new ArrayList<Shark>();
  private Timer           timer;
  private void launchWindow()
  {
    startGame();
    WindowUtils.testPanel(view);
  }
  private void startGame()
  {
    addShark();
    startClock();
  }
  public void addShark()
  {
    Shark shark = new Shark(Words.next(), NumberUtils.getRandomInt(1, 1000));
    shark.addListener(this);
    actors.add(shark);
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
    for (Shark shark : actors)
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
    for (Shark shark : actors)
    {
      shark.processLetter(letter);
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
    //processLetter(e.getKeyChar());
  }
  @Override
  public void keyReleased(KeyEvent e)
  {
    //processLetter(e.getKeyChar());
  }
  @Override
  public void playStateChanged(Shark shark)
  {
    PlayState state = shark.getState();
    switch (state)
    {
      case Killed :
        addShark();
        actors.remove(shark);
        break;
      case Killing :
        timer.stop();
      default :
        break;
    }
  }
}
