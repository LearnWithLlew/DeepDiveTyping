package org.teachingkidsprogramming.typingdeepdive.tests;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.Test;

public class UpdaterTests
{
  @Test
  public void headers() throws Exception
  {
  }
  @Test
  public void testIsFileDifferent() throws Exception
  {
    boolean b = UpdateUtils.isFileDifferent(new File("C:\\temp\\builds\\DeepDiveTyping.jar"),
        "https://dl.dropboxusercontent.com/u/21445495/DeepDiveTyping.jar");
    assertFalse(b);
  }
}
