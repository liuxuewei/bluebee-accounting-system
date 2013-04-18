package com.bluebee;

public class Version
{
  private static Version version = new Version();

  public static Version getInstance() {
    return version;
  }

  public String getVersion()
  {
    return "1.4";
  }

  public String getBuild() {
    return "(build100924)Beta°æ";
  }
}