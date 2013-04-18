package com.bluebee.dao.impl;

import com.bluebee.dao.OptionDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.Option;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.h2.jdbc.JdbcSQLException;

public class OptionDAOImpl extends BaseDAO
  implements OptionDAO
{
  public boolean addOption(Option option)
  {
    String sql = "INSERT INTO OPTIONS VALUES ('" + option.getId() + "'," + 
      "'" + option.getText() + "'," + "'" + option.getType() + "'" + 
      ");";

    boolean isin = true;
    try
    {
      insert(sql);
    } catch (SQLException e) {
      isin = false;
      e.printStackTrace();
    }
    return isin;
  }

  public boolean deleteOption(String id)
  {
    String sql = "DELETE FROM OPTIONS WHERE KEYID='" + id + "'";
    boolean isin = true;
    try {
      delete(sql);
    } catch (SQLException e) {
      isin = false;
      e.printStackTrace();
    }
    return isin;
  }

  public List getOption()
  {
    String sql = "SELECT * FROM OPTIONS ";

    List list = null;
    try {
      list = queryForList(sql, new OptionRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getOption(String type) {
    String sql = "SELECT * FROM OPTIONS WHERE OTYPE='" + type + "'";
    List list = null;
    try {
      list = queryForList(sql, new OptionRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  private class OptionRowMapper implements RowMapper {
    private OptionRowMapper() {
    }
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      Option stock = new Option();
      String id = rs.getString("KEYID");
      String text = rs.getString("TEXTVALUE");
      try {
        String OTYPE = rs.getString("OTYPE");
        if (OTYPE != null)
          stock.setType(OTYPE);
      }
      catch (JdbcSQLException localJdbcSQLException) {
      }
      if (id != null) {
        stock.setId(id);
      }
      if (text != null) {
        stock.setText(text);
      }
      return stock;
    }
  }
}