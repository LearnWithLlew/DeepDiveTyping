package org.teachingkidsprogramming.typingdeepdive.tests;

import org.teachingkidsprogramming.typingdeepdive.PlayStateListener;
import org.teachingkidsprogramming.typingdeepdive.Shark;

public class PlayStateRecorder implements PlayStateListener
{
  public StringBuilder log = new StringBuilder();
  @Override
  public void playStateChanged(Shark shark)
  {
    log.append(shark.getState() + "\n");
  }
}
