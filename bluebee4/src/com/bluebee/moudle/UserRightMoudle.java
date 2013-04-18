package com.bluebee.moudle;

import com.bluebee.dao.UserRightDAO;
import com.bluebee.pojo.UserRight;

public abstract interface UserRightMoudle
{
  public abstract void setUserRightDAO(UserRightDAO paramUserRightDAO);

  public abstract void add(UserRight paramUserRight);

  public abstract UserRight get(String paramString);

  public abstract void update(UserRight paramUserRight);

  public abstract void delete(String paramString);
}