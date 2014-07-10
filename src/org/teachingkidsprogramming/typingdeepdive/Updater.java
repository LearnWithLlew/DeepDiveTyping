package org.teachingkidsprogramming.typingdeepdive;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater
{
  private static final String DROPBOX_URL = "https://dl.dropboxusercontent.com/u/21445495/DeepDiveTyping.Latest.jar";
  public static void main(String[] args) throws Exception
  {
    start();
  }
  private static void start() throws Exception
  {
    File file = new File(".DeepDiveTyping.jar");
    downloadFileIfNeeded(file);
    launchFile(file);
    System.out.println("done");
  }
  private static void launchFile(File file)
  {
    launch("java -jar %s", file.getAbsolutePath());
  }
  private static void downloadFileIfNeeded(File jar)
  {
    try
    {
      if (UpdateUtils.isFileIdentical(jar, DROPBOX_URL)) { return; }
      URL website = new URL(DROPBOX_URL);
      ReadableByteChannel rbc = Channels.newChannel(website.openStream());
      FileOutputStream fos = new FileOutputStream(jar, false);
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
      fos.close();
      if (isWindowsEnviroment())
      {
        launch("attrib +H %s", jar.getAbsolutePath());
      }
      System.out.println("downloaded");
    }
    catch (Exception e)
    {
      System.out.println(e);//ignore download issues to play offline
    }
  }
  public static boolean isWindowsEnviroment()
  {
    return "\\".equals(File.separator);
  }
  public static void launch(String commandLine, String... formattingArguments)
  {
    try
    {
      String command = String.format(commandLine, (Object[]) formattingArguments);
      Process exec = Runtime.getRuntime().exec(command);
      //      exec.waitFor();
      //      String string = FileUtils.readStream(exec.getErrorStream());
      //      System.out.println(string);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}
