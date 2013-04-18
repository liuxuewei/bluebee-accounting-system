package com.bluebee.util;

public class NumberUtils
{
  public static boolean isNumber(String str)
  {
    if ((str == null) || (str.trim().length() == 0))
      return false;
    try {
      Integer.parseInt(str.trim());
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}