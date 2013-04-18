package com.bluebee;

import com.bluebee.moudle.MoudleContentFactry;

public class Patch
{
  public static void pachExexcute(float oldversion, float newversion)
  {
    MoudleContentFactry.getPatchMoudleImpl().patch(oldversion, newversion);
  }
}