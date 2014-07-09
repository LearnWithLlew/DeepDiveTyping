package org.teachingkidsprogramming.typingdeepdive.tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.Shark;

@UseReporter({DiffReporter.class, ClipboardReporter.class})
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
  @Test
  public void testSharkDesign() throws Exception
  {
    Shark shark = new Shark("legitimate", 0);
    processLetters("legi", shark);
    Approvals.verify(new SharkView(shark));
  }
}

class SharkView extends JPanel
{
  private Shark shark;
  public SharkView(Shark shark)
  {
    this.shark = shark;
    this.setPreferredSize(shark.outer);
    this.setBackground(Color.BLUE);
  }
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    shark.paint(g, new Dimension(0, getSize().height));
  }
}