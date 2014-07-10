package org.teachingkidsprogramming.typingdeepdive;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.teachingkidsprogramming.typingdeepdive.tests.UpdateUtils;

import com.spun.util.ObjectUtils;

public class Updater
{
  private static final String DROPBOX_URL = "https://dl.dropboxusercontent.com/u/21445495/DeepDiveTyping.jar";
  public static void main(String[] args) throws Exception
  {
    start();
  }
  private static void start() throws Exception
  {
    File file = new File(".DeepDiveTyping.jar");
    if (UpdateUtils.isFileDifferent(file, DROPBOX_URL))
    {
      downloadFile(file);
      System.out.println("downloaded");
    }
    launchFile(file);
    System.out.println("done");
  }
  private static void launchFile(File file)
  {
    launch("java -jar %s", file.getAbsolutePath());
  }
  private static void downloadFile(File jar) throws Exception
  {
    URL website = new URL(DROPBOX_URL);
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream(jar, false);
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    fos.close();
    launch("attrib +H %s", jar.getAbsolutePath());
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
      ObjectUtils.throwAsError(e);
    }
  }
}
