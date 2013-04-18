package com.bluebee.dao;

import com.bluebee.pojo.Config;

import java.util.List;

public abstract interface ConfigDAO
{
  public abstract boolean addConfigs(Config[] paramArrayOfConfig);

  public abstract boolean addConfig(Config paramConfig);

  public abstract boolean deleteConfig(String paramString);

  public abstract boolean saveOrUpdateConfig(Config paramConfig);

  public abstract Config getConfig(String paramString);

  public abstract List getConfigs();
}