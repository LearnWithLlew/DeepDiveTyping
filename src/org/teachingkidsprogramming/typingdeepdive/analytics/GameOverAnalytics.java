package org.teachingkidsprogramming.typingdeepdive.analytics;

public class GameOverAnalytics
{
  private long time;
  public GameOverAnalytics()
  {
    time = System.currentTimeMillis();
  }
  @Override
  public String toString()
  {
    return String.format("{time:%s}", time);
  }
}
