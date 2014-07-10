package org.teachingkidsprogramming.typingdeepdive;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UpdateUtils
{
  public static boolean isFileIdentical(File file, String url)
  {
    long fileSize = file.length();
    long webSize = getFileSize(url);
    return fileSize == webSize;
  }
  private static long getFileSize(String url)
  {
    try
    {
      URL obj = new URL(url);
      URLConnection conn = obj.openConnection();
      Map<String, List<String>> map = conn.getHeaderFields();
      List<String> contentLength = map.get("Content-Length");
      return Long.parseLong(contentLength.get(0));
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}
