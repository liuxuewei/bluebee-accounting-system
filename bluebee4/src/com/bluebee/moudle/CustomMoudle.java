package com.bluebee.moudle;

import com.bluebee.dao.CustomDAO;
import com.bluebee.pojo.Custom;

import java.util.List;

public abstract interface CustomMoudle
{
  public abstract void setCustomDAO(CustomDAO paramCustomDAO);

  public abstract Custom getCustomById(String paramString);

  public abstract Custom add(Custom paramCustom);

  public abstract Custom update(Custom paramCustom);

  public abstract boolean delete(String paramString);

  public abstract List getCustom(String paramString1, String paramString2, String paramString3);

  public abstract int getCustomsSize();

  public abstract List getCustoms(int paramInt1, int paramInt2);

  public abstract List getCustoms(String paramString, int paramInt);

  public abstract List getCustoms();
}