package com.bluebee.dao;

import com.bluebee.pojo.User;

import java.util.List;

public abstract interface UserDAO
{
  public abstract void add(User paramUser);

  public abstract User getUserByid(String paramString);

  public abstract User getUserByName(String paramString);

  public abstract void updateUser(User paramUser);

  public abstract void deleteUser(String paramString);

  public abstract List getUser(String paramString);
}