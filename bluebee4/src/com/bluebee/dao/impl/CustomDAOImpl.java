package com.bluebee.dao.impl;

import com.bluebee.dao.CustomDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.Custom;
import com.bluebee.util.DateHelper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomDAOImpl extends BaseDAO
  implements CustomDAO
{
  public Custom getCustomById(String id)
  {
    String sql = "SELECT * FROM CUSTOM WHERE CUSTOMID='" + id + "';";
    Custom custom = null;
    try {
      custom = (Custom)queryForObject(sql, new CustomRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return custom;
  }

  public Custom add(Custom custom) {
    custom.setRegDate(DateHelper.getNowDateTime());
    String sql = "INSERT INTO CUSTOM VALUES ('" + custom.getId() + "'," + 
      "'" + custom.getName() + "'," + 
      "'" + custom.getType() + "'," + "'" + 
      custom.getSex() + "'," + 
      "'" + custom.getBirthday() + "'," + 
      "'" + custom.getIntegration() + "'," + 
      "'" + custom.getAmount() + "'," + 
      "'" + custom.getFrequency() + "'," + 
      "'" + custom.getTelephone() + "'," + 
      "'" + custom.getAddress() + "'," + 
      "'" + custom.getNote() + "'," + 
      "'" + custom.getRegDate() + "'" + 
      ");";
    try {
      insert(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return custom;
  }

  public Custom update(Custom custom) {
    if ((custom.getRegDate() == null) || ("".equals(custom.getRegDate()))) {
      custom.setRegDate(DateHelper.getNowDateTime());
    }
    String sql = "UPDATE CUSTOM SET CUSTOMNAME ='" + 
      custom.getName() + "'," + 
      "CUSTOMTYPE ='" + custom.getType() + "'," + 
      "SEX ='" + custom.getSex() + "'," + 
      "BIRTHDAY ='" + custom.getBirthday() + "'," + 
      "AMOUNT ='" + custom.getAmount() + "'," + 
      "INTEGRATION ='" + custom.getIntegration() + "'," + 
      "FREQUENCY ='" + custom.getFrequency() + "'," + 
      "TELEPHONE ='" + custom.getTelephone() + "'," + 
      "ADDRESS ='" + custom.getAddress() + "'," + 
      "NODTE ='" + custom.getNote() + "'," + 
      "REGDATE ='" + custom.getRegDate() + "'" + 
      " WHERE CUSTOMID='" + custom.getId() + "'";
    try {
      update(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return custom;
  }

  public boolean deleteById(String id) {
    String sql = "DELETE FROM CUSTOM WHERE CUSTOMID='" + id + "'";
    try {
      delete(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public List getCustom(String id, String name, String telephone)
  {
    String sql = "SELECT * FROM CUSTOM WHERE CUSTOMID LIKE'%" + id + "%' or CUSTOMNAME LIKE'%" + name + "%' or TELEPHONE LIKE'%" + telephone + "%';";
    List list = null;
    try {
      list = queryForList(sql, new CustomRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getCustomsSize() {
    String sql = "SELECT COUNT(*)  FROM CUSTOM ";
    int r = 0;
    try {
      r = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public List getCustoms(int start, int max) {
    String sql = "SELECT * FROM CUSTOM  LIMIT " + max + " OFFSET " + start;
    List list = null;
    try {
      list = queryForList(sql, new CustomRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getCustoms(String value, int max) {
    value = sqlStr(value);
    String sql = "SELECT * FROM CUSTOM WHERE CUSTOMID LIKE '%" + value + "%' or CUSTOMNAME LIKE '%" + value + "%' or TELEPHONE LIKE'%" + value + "%' LIMIT " + max;

    List list = null;
    try {
      list = queryForList(sql, new CustomRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getCustoms()
  {
    String sql = "SELECT * FROM CUSTOM ";
    List list = null;
    try {
      list = queryForList(sql, new CustomRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  private class CustomRowMapper implements RowMapper {
    private CustomRowMapper() {
    }
    public Custom mapRow(ResultSet rs, int rowNum) throws SQLException { String id = rs.getString("CUSTOMID");
      String CUSTOMNAME = rs.getString("CUSTOMNAME");
      String CUSTOMTYPE = rs.getString("CUSTOMTYPE");
      String SEX = rs.getString("SEX");
      String BIRTHDAY = rs.getString("BIRTHDAY");
      String AMOUNT = rs.getString("AMOUNT");
      String FREQUENCY = rs.getString("FREQUENCY");
      String TELEPHONE = rs.getString("TELEPHONE");
      String ADDRESS = rs.getString("ADDRESS");
      String INTEGRATION = rs.getString("INTEGRATION");
      String NODTE = rs.getString("NODTE");
      String REGDATE = rs.getString("REGDATE");
      Custom custom = new Custom();
      if (id != null) {
        custom.setId(id);
      }
      if (CUSTOMNAME != null) {
        custom.setName(CUSTOMNAME);
      }
      if (CUSTOMTYPE != null) {
        custom.setType(CUSTOMTYPE);
      }
      if (SEX != null) {
        custom.setSex(SEX);
      }
      if (BIRTHDAY != null) {
        custom.setBirthday(BIRTHDAY);
      }
      if (AMOUNT != null) {
        custom.setAmount(new BigDecimal(AMOUNT));
      }
      if (FREQUENCY != null) {
        custom.setFrequency(Integer.parseInt(FREQUENCY));
      }
      if (INTEGRATION != null) {
        custom.setIntegration(Double.parseDouble(INTEGRATION));
      }
      if (TELEPHONE != null) {
        custom.setTelephone(TELEPHONE);
      }
      if (ADDRESS != null) {
        custom.setAddress(ADDRESS);
      }
      if (NODTE != null) {
        custom.setNote(NODTE);
      }
      if (REGDATE != null) {
        custom.setRegDate(REGDATE);
      }
      return custom;
    }
  }
}