package org.teachingkidsprogramming.typingdeepdive.tests;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.spun.util.NumberUtils;
import com.spun.util.ObjectUtils;

public class UpdateUtils
{
  public static boolean isFileDifferent(File file, String url)
  {
    long fileSize = file.length();
    long webSize = getFileSize(url);
    return fileSize != webSize;
  }
  private static long getFileSize(String url)
  {
    try
    {
      URL obj = new URL(url);
      URLConnection conn = obj.openConnection();
      Map<String, List<String>> map = conn.getHeaderFields();
      List<String> contentLength = map.get("Content-Length");
      return NumberUtils.load(contentLength.get(0), 0l);
    }
    catch (Exception e)
    {
      throw ObjectUtils.throwAsError(e);
    }
  }
}
