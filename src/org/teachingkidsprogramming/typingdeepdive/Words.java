package org.teachingkidsprogramming.typingdeepdive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.lambda.functions.Function1;
import org.lambda.query.Query;

import com.spun.util.NumberUtils;
import com.spun.util.ObjectUtils;
import com.spun.util.StringUtils;

public class Words
{
  private static HashMap<Integer, ArrayList<String>> words;
  public String next(int min, int max)
  {
    HashMap<Integer, ArrayList<String>> words = getWords();
    ArrayList<ArrayList<String>> possible = new ArrayList<ArrayList<String>>();
    for (Integer key : words.keySet())
    {
      if (min <= key && key <= max)
      {
        possible.add(words.get(key));
      }
    }
    Function1<ArrayList<String>, Integer> added = new Function1<ArrayList<String>, Integer>()
    {
      public Integer call(ArrayList<String> i)
      {
        return i.size();
      }
    };
    int total = Query.sum(possible, added).intValue();
    int index = NumberUtils.getRandomInt(0, total);
    for (ArrayList<String> w : words.values())
    {
      if (index < w.size()) { return w.get(index); }
      index -= w.size();
    }
    return null;
  }
  public static HashMap<Integer, ArrayList<String>> getWords()
  {
    if (words == null)
    {
      words = importWords();
    }
    return words;
  }
private static HashMap<Integer, ArrayList<String>> importWords() throws Error {
	boolean finnish = true;
	return finnish ? importFinnishWords() :  importEnglishWords();
}
private static HashMap<Integer, ArrayList<String>> importFinnishWords() throws Error {
	HashMap<Integer, ArrayList<String>> words2 = new HashMap<Integer, ArrayList<String>>();
      importWords(words2, "words/words_suomi.txt");
	return words2;
}
private static HashMap<Integer, ArrayList<String>> importEnglishWords() throws Error {
	HashMap<Integer, ArrayList<String>> words2 = new HashMap<Integer, ArrayList<String>>();
	importWords(words2, "words/words.txt");
	importWords(words2, "words/fun_words.txt");
	importWords(words2, "words/punctuation.txt");
	return words2;
}
  public static void importWords(HashMap<Integer,ArrayList<String>> words2, String filename) throws Error
  {
    InputStream resourceAsStream = Words.class.getResourceAsStream(filename);
    BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
    try
    {
      while (reader.ready())
      {
        String word = reader.readLine().trim();
        if (!StringUtils.isEmpty(word))
        {
          SharkBatch.add(words2, word.length(), word);
        }
      }
      reader.close();
    }
    catch (IOException e)
    {
      ObjectUtils.throwAsError(e);
    }
  }
}
