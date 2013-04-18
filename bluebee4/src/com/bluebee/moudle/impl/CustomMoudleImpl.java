package com.bluebee.moudle.impl;

import com.bluebee.dao.CustomDAO;
import com.bluebee.moudle.CustomMoudle;
import com.bluebee.pojo.Custom;

import java.util.List;

public class CustomMoudleImpl
  implements CustomMoudle
{
  private CustomDAO customDAO;

  public void setCustomDAO(CustomDAO customDAO)
  {
    this.customDAO = customDAO;
  }

  public Custom getCustomById(String id) {
    return this.customDAO.getCustomById(id);
  }

  public Custom add(Custom custom) {
    return this.customDAO.add(custom);
  }

  public Custom update(Custom custom) {
    return this.customDAO.update(custom);
  }

  public boolean delete(String id) {
    return this.customDAO.deleteById(id);
  }

  public List getCustom(String id, String name, String telephone) {
    return this.customDAO.getCustom(id, name, telephone);
  }

  public int getCustomsSize() {
    return this.customDAO.getCustomsSize();
  }

  public List getCustoms(int start, int max) {
    return this.customDAO.getCustoms(start, max);
  }
  public List getCustoms(String value, int max) {
    return this.customDAO.getCustoms(value, max);
  }
  public List getCustoms() {
    return this.customDAO.getCustoms();
  }
}