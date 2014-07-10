package org.teachingkidsprogramming.typingdeepdive.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.teachingkidsprogramming.typingdeepdive.UpdateUtils;

public class UpdaterTests
{
  @Test
  public void headers() throws Exception
  {
  }
  @Test
  public void testIsFileDifferent() throws Exception
  {
    boolean b = UpdateUtils.isFileIdentical(new File("C:\\temp\\builds\\DeepDiveTyping.jar"),
        "https://dl.dropboxusercontent.com/u/21445495/DeepDiveTyping.jar");
    assertTrue(b);
  }
}
