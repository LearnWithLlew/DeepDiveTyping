package org.teachingkidsprogramming.typingdeepdive.analytics.tests;

import org.approvaltests.Approvals;
import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.DeepDiveTypingGame;
import org.teachingkidsprogramming.typingdeepdive.GameOver;
import org.teachingkidsprogramming.typingdeepdive.SharkBatch;

public class AnalyticsTest
{
  @Test
  public void testSimpleGame() throws Exception
  {
    DeepDiveTypingGame g = new DeepDiveTypingGame();
    SharkBatch.words = new MockWords("one", "two", "six", "four");
    g.start();
    type(g, "aonetwoboaosi");
    //run till end of game
    advanceUntilEnd(g);
    Approvals.verify(g.analytics);
  }
  private void advanceUntilEnd(DeepDiveTypingGame g)
  {
    while (!(g.actors.get(g.actors.size() - 1) instanceof GameOver))
    {
      g.advanceClock();
    }
  }
  private void type(DeepDiveTypingGame g, String letters)
  {
    for (char c : letters.toCharArray())
    {
      {
        g.advanceClock();
      }
      g.processLetter(c);
    }
  }
}
