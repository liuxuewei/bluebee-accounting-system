package com.bluebee.dao.impl;

import com.bluebee.dao.ConfigDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConfigDAOImpl extends BaseDAO
  implements ConfigDAO
{
  public boolean addConfigs(Config[] configs)
  {
    boolean isin = false;
    String[] strings = new String[configs.length];
    for (int i = 0; i < configs.length; i++) {
      Config config = configs[i];
      if ((config != null) && (!"".equals(config.getValue().trim()))) {
        String sql = "INSERT INTO CONFIG VALUES ('" + config.getKey() + "'," + 
          "'" + config.getValue() + "'" + 
          ");";
        Config configold = getConfig(config.getKey());
        if (configold != null) {
          sql = " UPDATE CONFIG SET  TEXTVALUE ='" + config.getValue() + "'  WHERE KEYID ='" + config.getKey() + "'";
        }
        strings[i] = sql;
      }
    }
    try {
      batchUpdate(strings);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public boolean saveOrUpdateConfig(Config config) {
    Config oldconf = getConfig(config.getKey());
    if (oldconf != null)
      updateConfig(config);
    else {
      addConfig(config);
    }
    return true;
  }

  public boolean updateConfig(Config config) {
    String sql = "UPDATE CONFIG SET TEXTVALUE ='" + config.getValue() + "' WHERE KEYID='" + config.getKey() + "'";
    boolean isin = false;
    try {
      super.update(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public boolean deleteConfig(String key) {
    String sql = "DELETE  FROM CONFIG WHERE KEYID ='" + key + "'";
    boolean isin = false;
    try {
      super.delete(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return isin;
  }

  public boolean addConfig(Config config) {
    String sql = "INSERT INTO CONFIG VALUES ('" + config.getKey() + "'," + 
      "'" + config.getValue() + "'" + 
      ");";
    boolean isin = false;
    try
    {
      super.insert(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return isin;
  }

  public Config getConfig(String key)
  {
    String sql = "SELECT * FROM CONFIG WHERE KEYID ='" + key + "'";

    Config config = null;
    try {
      config = (Config)super.queryForObject(sql, new ConfigRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return config;
  }

  public List getConfigs() {
    String sql = "SELECT * FROM FLOW_LOG ";

    List list = null;
    try {
      list = super.queryForList(sql, new ConfigRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  private class ConfigRowMapper implements RowMapper {
    private ConfigRowMapper() {
    }
    public Config mapRow(ResultSet rs, int rowNum) throws SQLException { String catno = rs.getString("KEYID");
      String amount = rs.getString("TEXTVALUE");

      Config config = new Config();
      if (catno != null) {
        config.setKey(catno);
      }
      if (amount != null) {
        config.setValue(amount);
      }
      return config;
    }
  }
}