package org.teachingkidsprogramming.typingdeepdive.tests;

import org.approvaltests.Approvals;
import org.teachingkidsprogramming.typingdeepdive.DeepDiveTypingGame;
import org.teachingkidsprogramming.typingdeepdive.Shark;

public class DeepDiveTypingGameTest
{
  //@Test
  public void testStartingShark() throws Exception
  {
    Shark shark = new Shark("Samantha", 200);
    DeepDiveTypingGame game = new DeepDiveTypingGame();
    game.actors.add(shark);
    Approvals.verify(game.view);
  }
}
