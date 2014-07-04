package org.teachingkidsprogramming.typingdeepdive.tests;

import org.approvaltests.Approvals;
import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.Shark;

public class SharkTest
{
  @Test
  public void testSharkEatsYou() throws Exception
  {
    Shark shark = new Shark("loser", 200);
    advanceTillEnd(shark);
    Approvals.verify("" + shark);
  }
  private void advanceTillEnd(Shark shark)
  {
    for (int i = 0; i < 1000; i++)
    {
      shark.advanceClock();
    }
  }
  @Test
  public void testFirstLetter() throws Exception
  {
    Shark shark = new Shark("word", 200);
    shark.processLetter('w');
    Approvals.verify("" + shark);
  }
  @Test
  public void testWordCompleted() throws Exception
  {
    Shark shark = new Shark("word", 200);
    PlayStateRecorder listener = new PlayStateRecorder();
    shark.addListener(listener);
    processLetters("word", shark);
    Approvals.verify(shark + "\n" + listener.log);
  }
  private void processLetters(String string, Shark shark)
  {
    for (char c : string.toCharArray())
    {
      shark.processLetter(c);
    }
  }
}
