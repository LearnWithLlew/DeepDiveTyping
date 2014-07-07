package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DeepDiveTypingView extends JPanel
{
  private DeepDiveTypingGame deepDive;
  public DeepDiveTypingView(DeepDiveTypingGame deepDive)
  {
    this.deepDive = deepDive;
    this.setPreferredSize(new Dimension(640, 480));
    this.setBackground(Color.BLUE);
    this.addKeyListener(deepDive);
    this.setFocusable(true);
  }
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    for (Actor shark : deepDive.actors)
    {
      shark.paint(g, getSize());
    }
  }
}
