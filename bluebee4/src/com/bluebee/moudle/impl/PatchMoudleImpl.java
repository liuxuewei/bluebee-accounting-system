package com.bluebee.moudle.impl;

import com.bluebee.dao.PatchDAO;
import com.bluebee.moudle.PatchMoudle;

public class PatchMoudleImpl
  implements PatchMoudle
{
  private PatchDAO patchDAO;

  public void setPatchDAO(PatchDAO patchDAO)
  {
    this.patchDAO = patchDAO;
  }

  public void patch(float oldversion, float newversion) {
    this.patchDAO.patch(oldversion, newversion);
  }
}