package com.bluebee.dao.impl;

import com.bluebee.dao.CustomtTypeDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.CustomType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomtTypeDAOImpl extends BaseDAO
  implements CustomtTypeDAO
{
  public void add(CustomType customType)
  {
    String sql = "INSERT INTO CUSTOMTYPE VALUES ('" + customType.getTypename() + "'," + 
      "'" + customType.getDiscount() + "'," + 
      "'" + customType.getIntegration() + "'" + 
      ");";
    try
    {
      super.insert(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(CustomType customType)
  {
    String sql = "UPDATE CUSTOMTYPE SET INTEGRATION ='" + 
      customType.getIntegration() + "'," + 
      "DISCOUNT ='" + customType.getDiscount() + "'" + 
      " WHERE CUSTOMNAME='" + customType.getTypename() + "'";
    try {
      super.update(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void saveorupdate(CustomType customType)
  {
  }

  public void addCustomTypes(CustomType[] customTypes) {
    for (int i = 0; i < customTypes.length; i++) {
      CustomType customType = customTypes[i];
      if ((customType != null) && 
        (customType.getTypename() != null) && (customType.getIntegration() > 0.0D)) {
        CustomType old = get(customType.getTypename());
        if (old == null)
          add(customType);
        else
          update(customType);
      }
    }
  }

  public void updateCustomTypes(CustomType[] customType)
  {
  }

  public CustomType get(String name)
  {
    String sql = "SELECT * FROM CUSTOMTYPE WHERE CUSTOMNAME='" + name + "';";
    CustomType customType = null;
    try {
      customType = (CustomType)super.queryForObject(sql, new CustomTypeRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return customType;
  }

  public void deleteByName(String name) {
    String sql = "DELETE FROM CUSTOMTYPE WHERE CUSTOMNAME='" + name + "'";
    try {
      super.delete(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List getCustomtTypes()
  {
    String sql = "SELECT * FROM CUSTOMTYPE ";
    List list = null;
    try
    {
      list = queryForList(sql, new CustomTypeRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  private class CustomTypeRowMapper implements RowMapper {
    private CustomTypeRowMapper() {
    }

    public CustomType mapRow(ResultSet rs, int rowNum) throws SQLException {
      String CUSTOMNAME = rs.getString("CUSTOMNAME");
      double INTEGRATION = rs.getDouble("INTEGRATION");
      double DISCOUNT = rs.getDouble("DISCOUNT");
      CustomType customType = new CustomType();
      if (CUSTOMNAME != null) {
        customType.setTypename(CUSTOMNAME);
      }
      customType.setIntegration(INTEGRATION);
      customType.setDiscount(DISCOUNT);
      return customType;
    }
  }
}