package org.teachingkidsprogramming.typingdeepdive.analytics;

public class GameAnalytics
{
  private long             startTime;
  public GameOverAnalytics gameOver;
  public GameAnalytics()
  {
    startTime = System.currentTimeMillis();
  }
  @Override
  public String toString()
  {
    return String.format("{startTime:%s,gameOver:%s}", startTime, gameOver);
  }
}
