package com.bluebee.util;

import java.io.File;

public class UpdateTools
{
  public static void main(String[] args)
  {
    File file = new File("./update.exe");

    File ffjzxt = new File("./sysff.exe");

    String ffjzxtpath = ffjzxt.getAbsolutePath();
    if (file.exists()) {
      ffjzxt.delete();
      file.renameTo(new File("./sysff.exe"));
    }Process process;
    try {
      process = Runtime.getRuntime().exec(ffjzxtpath);
    }
    catch (Throwable localThrowable)
    {
    }
  }
}