package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;

public interface Actor
{
  void advanceClock();
  boolean processLetter(char letter);
  void paint(Graphics g, Dimension size);
}
