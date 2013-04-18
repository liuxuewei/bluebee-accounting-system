package com.bluebee.dao.impl;

import com.bluebee.dao.DailyExpensesDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.DailyExpenses;
import com.bluebee.util.DateHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

public class DailyExpensesDAOImpl extends BaseDAO
  implements DailyExpensesDAO
{
  public void add(DailyExpenses dailyExpenses)
  {
    if (dailyExpenses.getId() == null) {
      dailyExpenses.setId(String.valueOf(UUID.randomUUID().toString().replaceAll("-", "")));
    }
    String sql = "INSERT INTO DAILYEXPENSES VALUES ('" + 
      dailyExpenses.getId() + "'," + 
      "'" + dailyExpenses.getType() + "'," + 
      "'" + dailyExpenses.getDate() + "'," + 
      "'" + dailyExpenses.getPay() + "'," + 
      "'" + dailyExpenses.getRecorddate() + "'," + 
      "'" + dailyExpenses.getMode() + "'" + 
      ");";
    try {
      insert(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(DailyExpenses dailyExpenses)
  {
    String sql = "UPDATE DAILYEXPENSES SET ETYPE ='" + dailyExpenses.getType() + "',EDATE ='" + dailyExpenses.getDate() + 
      "',PAY ='" + dailyExpenses.getPay() + 
      "' WHERE ID='" + dailyExpenses.getId() + "'";
    try {
      super.update(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteById(String id)
  {
    String sql = "DELETE FROM DAILYEXPENSES WHERE ID='" + id + "'";
    try {
      super.delete(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List getDailyExpensesByTaday()
  {
    String sql = "SELECT * FROM DAILYEXPENSES WHERE RECORD ='" + 
      DateHelper.getNowDateTime() + "'";
    List list = null;
    try
    {
      list = queryForList(sql, new DailyExpensesRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getList()
  {
    String sql = "SELECT * FROM DAILYEXPENSES ";
    List list = null;
    try
    {
      list = queryForList(sql, new DailyExpensesRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getListSize()
  {
    String sql = "SELECT COUNT(*)  FROM DAILYEXPENSES ";
    int r = 0;
    try {
      r = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public List getList(int start, int limit)
  {
    String sql = "SELECT * FROM DAILYEXPENSES LIMIT  " + limit + " OFFSET " + start;

    List list = null;
    try
    {
      list = queryForList(sql, new DailyExpensesRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getListSize(String stattime, String endTime) {
    String sql = "SELECT COUNT(*)  FROM DAILYEXPENSES where EDATE  between '" + 
      stattime + "' AND '" + endTime + "'";

    int r = 0;
    try
    {
      r = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public List getList(String stattime, String endTime, int start, int limit) {
    String sql = "SELECT * FROM DAILYEXPENSES WHERE EDATE  between '" + 
      stattime + 
      "' AND '" + 
      endTime + 
      "' ORDER BY  EDATE DESC LIMIT " + 
      limit + " OFFSET " + start;

    List list = null;
    try {
      list = queryForList(sql, new DailyExpensesRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getList(String mode, String type, String stattime, String endTime, int start, int limit)
  {
    String sql = "SELECT * FROM DAILYEXPENSES WHERE 1=1  ";
    StringBuffer ss = new StringBuffer();
    if ((type != null) && (type.length() > 0)) {
      ss.append(" and ");
      ss.append("ETYPE='");
      ss.append(type);
      ss.append("' ");
    }
    if ((mode != null) && (mode.length() > 0)) {
      ss.append(" and ");
      ss.append("MODE='");
      ss.append(mode);
      ss.append("' ");
    }
    if ((stattime != null) && (stattime.length() > 0) && (endTime != null) && (endTime.length() > 0)) {
      ss.append(" and ");
      ss.append(" EDATE  between '").append(stattime).append("' AND '").append(endTime).append("' ");
    }

    ss.append(" ORDER BY EDATE DESC LIMIT " + limit + " OFFSET " + start);
    sql = sql + String.valueOf(ss);
    List list = null;
    try
    {
      list = queryForList(sql, new DailyExpensesRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getList(String mode, String type, String stattime, String endTime)
  {
    String sql = "SELECT * FROM DAILYEXPENSES WHERE 1=1  ";
    StringBuffer ss = new StringBuffer();
    if ((type != null) && (type.length() > 0)) {
      ss.append(" and ");
      ss.append("ETYPE='");
      ss.append(type);
      ss.append("' ");
    }
    if ((mode != null) && (mode.length() > 0)) {
      ss.append(" and ");
      ss.append("MODE='");
      ss.append(mode);
      ss.append("' ");
    }
    if ((stattime != null) && (stattime.length() > 0) && (endTime != null) && (endTime.length() > 0)) {
      ss.append(" and ");
      ss.append(" EDATE  between '").append(stattime).append("' AND '").append(endTime).append("' ");
    }

    sql = sql + String.valueOf(ss);
    List list = null;
    try
    {
      list = queryForList(sql, new DailyExpensesRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getListSize(String mode, String type, String stattime, String endTime) {
    String sql = "SELECT COUNT(*)  FROM DAILYEXPENSES where 1=1 ";
    StringBuffer ss = new StringBuffer();
    if ((type != null) && (type.length() > 0)) {
      ss.append(" and ");
      ss.append("ETYPE='");
      ss.append(type);
      ss.append("' ");
    }
    if ((mode != null) && (mode.length() > 0)) {
      ss.append(" and ");
      ss.append("MODE='");
      ss.append(mode);
      ss.append("' ");
    }
    if ((stattime != null) && (stattime.length() > 0) && (endTime != null) && (endTime.length() > 0)) {
      ss.append(" and ");
      ss.append(" EDATE  between '").append(stattime).append("' AND '").append(endTime).append("' ");
    }
    sql = sql + String.valueOf(ss);
    int r = 0;
    try {
      r = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public BigDecimal sumDailyExpensesPay(String mode) {
    String sql = "SELECT SUM(PAY)  FROM DAILYEXPENSES WHERE  MODE='" + mode + "'";
    BigDecimal r = new BigDecimal(0);
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      rs.next();
      r = rs.getBigDecimal(1);
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      super.close(stat, rs, conn);
    }
    return r;
  }

  public BigDecimal sumDailyExpensesPay(String type, String stattime, String endTime)
  {
    return sumDailyExpensesPay(null, type, stattime, endTime);
  }

  public BigDecimal sumDailyExpensesPay(String mode, String type, String stattime, String endTime) {
    String sql = "SELECT SUM(PAY)  FROM DAILYEXPENSES where 1=1 ";

    StringBuffer ss = new StringBuffer();
    if ((type != null) && (type.length() > 0)) {
      ss.append(" and ");
      ss.append("ETYPE='");
      ss.append(type);
      ss.append("' ");
    }
    if ((mode != null) && (mode.length() > 0)) {
      ss.append(" and ");
      ss.append(" MODE='");
      ss.append(mode);
      ss.append("' ");
    }
    if ((stattime != null) && (stattime.length() > 0) && (endTime != null) && (endTime.length() > 0)) {
      ss.append(" and ");
      ss.append(" EDATE  between '").append(stattime).append("' AND '").append(endTime).append("' ");
    }

    sql = sql + String.valueOf(ss);

    BigDecimal r = new BigDecimal(0);
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      rs.next();
      r = rs.getBigDecimal(1);
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      super.close(stat, rs, conn);
    }
    return r;
  }

  public BigDecimal sumDailyExpensesPay(String stattime, String endTime) {
    String sql = "SELECT SUM(PAY)  FROM DAILYEXPENSES where 1=1 ";

    StringBuffer ss = new StringBuffer();

    if ((stattime != null) && (stattime.length() > 0) && (endTime != null) && (endTime.length() > 0)) {
      ss.append(" and ");
      ss.append(" EDATE  between '").append(stattime).append("' AND '").append(endTime).append("' ");
    }
    ss.append(" and ").append("MODE='expenses'");
    sql = sql + String.valueOf(ss);

    BigDecimal r = new BigDecimal(0);
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      rs.next();
      r = rs.getBigDecimal(1);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      super.close(stat, rs, conn);
    }
    return r;
  }
  private class DailyExpensesRowMapper implements RowMapper {
    private DailyExpensesRowMapper() {
    }
    public DailyExpenses mapRow(ResultSet rs, int rowNum) throws SQLException {
      String id = rs.getString("ID");
      String ETYPE = rs.getString("ETYPE");
      String EDATE = rs.getString("EDATE");
      String PAY = rs.getString("PAY");
      String RECORD = rs.getString("RECORD");
      String mode = rs.getString("MODE");

      DailyExpenses dailyExpenses = new DailyExpenses();
      if (id != null) {
        dailyExpenses.setId(id);
      }
      if (ETYPE != null) {
        dailyExpenses.setType(ETYPE);
      }
      if (EDATE != null) {
        dailyExpenses.setDate(EDATE);
      }

      if (PAY != null) {
        dailyExpenses.setPay(new BigDecimal(PAY));
      }
      if (RECORD != null) {
        dailyExpenses.setRecorddate(RECORD);
      }
      if (mode != null) {
        dailyExpenses.setMode(mode);
      }
      return dailyExpenses;
    }
  }
}