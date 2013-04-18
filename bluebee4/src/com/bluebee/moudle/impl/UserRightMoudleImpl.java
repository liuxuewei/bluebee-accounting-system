package com.bluebee.moudle.impl;

import com.bluebee.dao.UserRightDAO;
import com.bluebee.moudle.UserRightMoudle;
import com.bluebee.pojo.UserRight;

public class UserRightMoudleImpl
  implements UserRightMoudle
{
  private UserRightDAO userRightDAO;

  public void setUserRightDAO(UserRightDAO iuserRightDAO)
  {
    this.userRightDAO = iuserRightDAO;
  }

  public void add(UserRight userRight) {
    this.userRightDAO.add(userRight);
  }

  public UserRight get(String username) {
    return this.userRightDAO.get(username);
  }

  public void update(UserRight userRight) {
    this.userRightDAO.update(userRight);
  }

  public void delete(String username) {
    this.userRightDAO.deleteById(username);
  }
}