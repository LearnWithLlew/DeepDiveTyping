package org.teachingkidsprogramming.typingdeepdive.tests;

import java.awt.Dimension;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.DeepDiveTypingGame;
import org.teachingkidsprogramming.typingdeepdive.Shark;

@UseReporter({DiffReporter.class, ClipboardReporter.class})
public class GameBehaviorTest
{
  @Test
  public void testSelectedSharkShows() throws Exception
  {
    DeepDiveTypingGame game = new DeepDiveTypingGame();
    game.view.setPreferredSize(new Dimension(300, 150));
    game.actors.add(new Shark("selected", 50));
    game.actors.add(new Shark("blocking", 100));
    advanceTurns(game, 25 * 10);
    game.processLetter('s');
    Approvals.verify(game.view);
  }
  private void advanceTurns(DeepDiveTypingGame game, int turns)
  {
    for (int i = 1; i <= turns; i++)
    {
      game.advanceClock();
    }
  }
}
