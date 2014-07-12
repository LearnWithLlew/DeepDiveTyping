package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DeepDiveTypingView extends JPanel
{
  private static Image       image;
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
    paintBackground(g);
    paintScores(g);
    for (Actor shark : deepDive.actors)
    {
      shark.paint(g, getSize());
    }
    for (Actor actor : deepDive.actors)
    {
      if (actor instanceof Shark && ((Shark) actor).isSelected())
      {
        ((Shark) actor).paint(g, getSize());
      }
    }
  }
  private void paintBackground(Graphics g)
  {
    g.drawImage(loadImage(), 0, 0, getSize().width, getSize().height, null);
  }
  public static Image loadImage()
  {
    if (image == null)
    {
      image = new ImageIcon(Diver.class.getResource("water.jpg")).getImage();
    }
    return image;
  }
  private void paintScores(Graphics g)
  {
    if (deepDive.score == null) { return; }
    g.setColor(Color.white);
    g.setFont(new Font(Font.SERIF, Font.BOLD, 18));
    Point bottomRight = new Point(getSize().width - 6, getSize().height - 6);
    printText(g, bottomRight, "Sharks Killed: " + deepDive.score.getSharks());
    printText(g, bottomRight, "Round: " + deepDive.score.getBatch());
  }
  public void printText(Graphics g, Point bottomRight, String text)
  {
    Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
    bottomRight.y -= bounds.getHeight();
    g.drawString(text, (int) (bottomRight.x - bounds.getWidth()), (int) (bottomRight.y - bounds.getMaxY()));
  }
}
