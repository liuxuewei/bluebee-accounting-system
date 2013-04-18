package com.bluebee.dao;

import com.bluebee.pojo.CustomType;

import java.util.List;

public abstract interface CustomtTypeDAO
{
  public abstract void add(CustomType paramCustomType);

  public abstract void update(CustomType paramCustomType);

  public abstract void saveorupdate(CustomType paramCustomType);

  public abstract void addCustomTypes(CustomType[] paramArrayOfCustomType);

  public abstract void updateCustomTypes(CustomType[] paramArrayOfCustomType);

  public abstract void deleteByName(String paramString);

  public abstract CustomType get(String paramString);

  public abstract List getCustomtTypes();
}