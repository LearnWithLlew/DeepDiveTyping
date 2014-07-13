package org.teachingkidsprogramming.typingdeepdive.analytics.tests;

import org.teachingkidsprogramming.typingdeepdive.Words;

public class MockWords extends Words
{
  private String[] words;
  private int      index = 0;
  public MockWords(String... words)
  {
    this.words = words;
  }
  @Override
  public String next(int min, int max)
  {
    return words[index++];
  }
}
