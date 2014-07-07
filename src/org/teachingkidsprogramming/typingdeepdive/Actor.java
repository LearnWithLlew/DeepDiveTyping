package org.teachingkidsprogramming.typingdeepdive;

import java.awt.Dimension;
import java.awt.Graphics;

public interface Actor
{
  void advanceClock();
  void processLetter(char letter);
  void paint(Graphics g, Dimension size);
}
