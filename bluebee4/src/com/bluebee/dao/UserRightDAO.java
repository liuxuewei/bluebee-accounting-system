package com.bluebee.dao;

import com.bluebee.pojo.UserRight;

public abstract interface UserRightDAO
{
  public abstract void add(UserRight paramUserRight);

  public abstract UserRight get(String paramString);

  public abstract void update(UserRight paramUserRight);

  public abstract void deleteById(String paramString);
}