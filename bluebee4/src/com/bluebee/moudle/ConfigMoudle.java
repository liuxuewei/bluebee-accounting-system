package com.bluebee.moudle;

import com.bluebee.dao.ConfigDAO;
import com.bluebee.pojo.Config;

import java.util.List;

public abstract interface ConfigMoudle
{
  public abstract void setConfigDAO(ConfigDAO paramConfigDAO);

  public abstract boolean addConfigs(Config[] paramArrayOfConfig);

  public abstract boolean saveOrUpdateConfig(Config paramConfig);

  public abstract boolean addConfig(Config paramConfig);

  public abstract boolean deleteConfig(String paramString);

  public abstract Config getConfig(String paramString);

  public abstract List getConfigs();
}