package org.teachingkidsprogramming.typingdeepdive.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.approvaltests.Approvals;
import org.junit.Test;
import org.lambda.functions.Function1;
import org.lambda.query.Query;
import org.teachingkidsprogramming.typingdeepdive.SharkBatch;
import org.teachingkidsprogramming.typingdeepdive.Words;

import com.spun.util.io.FileUtils;

public class WordTest
{
  // @Test
  public void SortWordList() throws Exception
  {
    String[] words = FileUtils.readFromClassPath(Words.class, "words.txt").split("\n");
    Function1<String, Comparable> f1 = new Function1<String, Comparable>()
    {
      @Override
      public Comparable call(String i)
      {
        return i;
      }
    };
    Query.orderBy(words, f1);
    File out = new File("c:\\temp\\words.txt");
    FileWriter writer = new FileWriter(out);
    for (String word : words)
    {
      writer.write(word + "\n");
    }
    writer.close();
  }
  // @Test
  public void testWordsByLength() throws Exception
  {
    HashMap<Integer, ArrayList<String>> words = Words.getWords();
    Approvals.verifyAll("", words.get(2));
  }
  @Test
  public void testUnique() throws Exception
  {
    HashSet<Character> letters = new HashSet<Character>();
    for (int i = 1; i <= 26; i++)
    {
      SharkBatch.getUniqueWord(letters);
    }
    assertEquals(26, letters.size());
  }
}
