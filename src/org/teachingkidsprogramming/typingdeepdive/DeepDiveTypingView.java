package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

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
    paintScores(g);
    for (Actor shark : deepDive.actors)
    {
      shark.paint(g, getSize());
    }
  }
  private void paintScores(Graphics g)
  {
    if (deepDive.score == null) { return; }
    String text = "Sharks Killed: " + deepDive.score.getSharks();
    g.setColor(Color.white);
    g.setFont(new Font(Font.SERIF, Font.BOLD, 18));
    Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
    Point border = new Point(6, 6);
    int x = (int) (getSize().width - bounds.getWidth()) - border.x;
    int y = (int) (getSize().height - bounds.getMaxY()) - border.y;
    g.drawString(text, x, y);
  }
}
