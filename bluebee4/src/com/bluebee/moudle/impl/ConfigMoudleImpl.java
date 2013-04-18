package com.bluebee.moudle.impl;

import com.bluebee.dao.ConfigDAO;
import com.bluebee.moudle.ConfigMoudle;
import com.bluebee.pojo.Config;

import java.util.List;

public class ConfigMoudleImpl
  implements ConfigMoudle
{
  private ConfigDAO configDAO;

  public void setConfigDAO(ConfigDAO configDAO)
  {
    this.configDAO = configDAO;
  }

  public List getConfigs() {
    return this.configDAO.getConfigs();
  }

  public boolean saveOrUpdateConfig(Config config) {
    return this.configDAO.saveOrUpdateConfig(config);
  }

  public Config getConfig(String key) {
    return this.configDAO.getConfig(key);
  }

  public boolean addConfig(Config config) {
    return this.configDAO.addConfig(config);
  }

  public boolean deleteConfig(String key) {
    return this.configDAO.deleteConfig(key);
  }

  public boolean addConfigs(Config[] config) {
    return this.configDAO.addConfigs(config);
  }
}