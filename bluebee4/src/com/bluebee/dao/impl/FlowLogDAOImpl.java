package com.bluebee.dao.impl;

import com.bluebee.dao.FlowLogDAO;
import com.bluebee.dao.RowMapper;
import com.bluebee.pojo.Flowlog;
import com.bluebee.util.DateHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.h2.jdbc.JdbcSQLException;

public class FlowLogDAOImpl extends BaseDAO
  implements FlowLogDAO
{
  public boolean deleteByFlowno(String flowno)
  {
    String sql = "DELETE FROM FLOW_LOG WHERE FLOWNO='" + flowno + "';";
    boolean isin = false;
    try {
      delete(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public boolean add(Flowlog flowlog) {
    if (flowlog.getFlowno() == null) {
      flowlog.setFlowno(String.valueOf(UUID.randomUUID().toString().replaceAll("-", "")));
    }
    String sql = "INSERT INTO FLOW_LOG VALUES ('" + 
      flowlog.getFlowno() + "'," + 
      "'" + flowlog.getCatno() + "'," + 
      "'" + flowlog.getAmount() + "'," + 
      "'" + flowlog.getSellprice() + "'," + 
      "'" + flowlog.getLrprice() + "'," + 
      "'" + flowlog.getCostprice() + "'," + 
      "'" + flowlog.getType() + "'," + 
      "'" + flowlog.getDate() + "'," + 
      "'" + flowlog.getStockname() + "'," + 
      "'" + flowlog.getFlowflag() + "'," + 
      "'" + flowlog.getRecorddate() + "'," + 
      "'" + flowlog.getCustomNo() + "'," + 
      "'" + flowlog.getCustomName() + "'" + 
      ");";

    boolean isin = false;
    try {
      insert(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public boolean add(Flowlog[] flowlogs) {
    boolean isin = false;
    try
    {
      String[] strings = new String[flowlogs.length];
      for (int i = 0; i < flowlogs.length; i++) {
        Flowlog flowlog = flowlogs[i];
        if (flowlog.getFlowno() == null) {
          flowlog.setFlowno(String.valueOf(UUID.randomUUID().toString().replaceAll("-", "")));
        }
        String sql = "INSERT INTO FLOW_LOG VALUES ('" + 
          flowlog.getFlowno() + "'," + 
          "'" + flowlog.getCatno() + "'," + 
          "'" + flowlog.getAmount() + "'," + 
          "'" + flowlog.getSellprice() + "'," + 
          "'" + flowlog.getLrprice() + "'," + 
          "'" + flowlog.getCostprice() + "'," + 
          "'" + flowlog.getType() + "'," + 
          "'" + flowlog.getDate() + "'," + 
          "'" + flowlog.getStockname() + "'," + 
          "'" + flowlog.getFlowflag() + "'," + 
          "'" + flowlog.getRecorddate() + "'," + 
          "'" + flowlog.getCustomNo() + "'," + 
          "'" + flowlog.getCustomName() + "'" + 
          ");";

        strings[i] = sql;
      }
      batchUpdate(strings);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public Flowlog getFlowByflowno(String flowno)
  {
    String sql = "SELECT * FROM FLOW_LOG WHERE FLOWNO='" + flowno + "';";

    Flowlog stock = null;
    try
    {
      stock = (Flowlog)queryForObject(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stock;
  }

  public boolean update(Flowlog flowlog) {
    boolean isin = false;
    String sql = "UPDATE FLOW_LOG SET CATNO ='" + flowlog.getCatno() + "', COSTPRICE='" + flowlog.getCostprice() + "',AMOUNT ='" + flowlog.getAmount() + "',SELLPRICE ='" + flowlog.getSellprice() + 
      "',LRPRICE ='" + flowlog.getLrprice() + 
      "' WHERE FLOWNO='" + flowlog.getFlowno() + "'";
    try
    {
      update(sql);
      isin = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public List getStatistical(String starttime, String endtime, String type)
  {
    List list = null;
    if ((starttime != null) && (endtime != null)) {
      String sql = "SELECT * FROM FLOW_LOG WHERE DATETIMED  between '" + starttime + "' AND '" + endtime + "'";

      if ((!"".equals(type)) && (type != null)) {
        sql = sql + " and STOCKTYPE='" + type + "'";
      }
      try
      {
        list = queryForList(sql, new FlowlogRowMapper());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return list;
  }

  public List getFlowlogByToday(String type)
  {
    String sql = "SELECT * FROM FLOW_LOG WHERE RECORD ='" + DateHelper.getNowDateTime() + "' AND FLOWFLAG='" + type + "'";

    List list = null;
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getFlowlog(String cartno)
  {
    String sql = "SELECT * FROM FLOW_LOG WHERE CATNO ='" + cartno + "'";

    List list = null;
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getFlowlog(String cartno, int size) {
    Connection conn = super.getConnection();

    cartno = sqlStr(cartno);

    String sql = "SELECT * FROM FLOW_LOG WHERE CATNO LIKE '" + cartno + 
      "%' LIMIT " + size;

    Statement stat = null;
    ResultSet rs = null;
    List list = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      list = new ArrayList();
      while (rs.next()) {
        String catnoname = rs.getString("CATNO");
        list.add(catnoname);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      super.close(stat, rs, conn);
    }
    return list;
  }

  public List getFlowlog() {
    String sql = "SELECT * FROM FLOW_LOG ;";
    List list = null;
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStatisticalSize(String starttime, String endtime, String type) {
    String sql = "SELECT   COUNT(*) FROM FLOW_LOG WHERE DATETIMED  between '" + starttime + "' AND '" + endtime + "'";

    if ((!"".equals(type)) && (type != null)) {
      sql = sql + " and STOCKTYPE='" + type + "'";
    }

    int list = 0;
    try
    {
      list = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStatistical(String starttime, String endtime, String type, int start, int max)
  {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT * FROM FLOW_LOG WHERE DATETIMED");
    sql.append("  between '").append(starttime).append("' AND '").append(endtime).append("'");
    if ((!"".equals(type)) && (type != null)) {
      sql.append(" AND ").append("STOCKTYPE='").append(type).append("'");
    }

    sql.append(" LIMIT ").append(max).append(" OFFSET ").append(start);

    List list = null;
    try
    {
      list = queryForList(String.valueOf(sql), new FlowlogRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public BigDecimal sumCostPrice(String starttime, String endtime, String type) {
    Connection conn = super.getConnection();

    StringBuffer sql = new StringBuffer();
    sql.append("SELECT SUM(COSTPRICE * AMOUNT) FROM FLOW_LOG  WHERE DATETIMED ");
    sql.append("  between '").append(starttime).append("' AND '").append(endtime).append("'");
    if ((!"".equals(type)) && (type != null)) {
      sql.append(" AND ").append("STOCKTYPE='").append(type).append("'");
    }

    BigDecimal list = new BigDecimal(0);
    Statement stat = null;
    ResultSet resultSet = null;
    try {
      stat = conn.createStatement();
      resultSet = stat.executeQuery(String.valueOf(sql));
      resultSet.next();
      list = resultSet.getBigDecimal(1);
      if (list != null)
        list = list.setScale(2, 4);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      close(stat, resultSet, conn);
    }
    return list;
  }

  public BigDecimal sumLrPrice(String starttime, String endtime, String type) {
    Connection conn = super.getConnection();

    StringBuffer sql = new StringBuffer();
    sql.append("SELECT SUM(LRPRICE) FROM FLOW_LOG  WHERE DATETIMED ");
    sql.append("  between '").append(starttime).append("' AND '").append(endtime).append("'");
    if ((!"".equals(type)) && (type != null)) {
      sql.append(" AND ").append("STOCKTYPE='").append(type).append("'");
    }

    BigDecimal list = new BigDecimal(0);
    Statement stat = null;
    ResultSet resultSet = null;
    try {
      stat = conn.createStatement();
      resultSet = stat.executeQuery(String.valueOf(sql));
      resultSet.next();
      list = resultSet.getBigDecimal(1);
      if (list != null)
        list = list.setScale(2, 4);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      close(stat, resultSet, conn);
    }
    return list;
  }

  public BigDecimal sumFlowPrice(String starttime, String endtime, String type) {
    Connection conn = super.getConnection();

    StringBuffer sql = new StringBuffer();
    sql.append("SELECT SUM(SELLPRICE * AMOUNT) FROM FLOW_LOG  WHERE DATETIMED ");
    sql.append("  between '").append(starttime).append("' AND '").append(endtime).append("'").append(" and AMOUNT >0");
    if ((!"".equals(type)) && (type != null)) {
      sql.append(" AND ").append("STOCKTYPE='").append(type).append("'");
    }
    StringBuffer sql_1 = new StringBuffer();
    sql_1.append("SELECT SUM(SELLPRICE * AMOUNT) FROM FLOW_LOG  WHERE DATETIMED ");
    sql_1.append("  between '").append(starttime).append("' AND '").append(endtime).append("'").append(" and AMOUNT <0");
    if ((!"".equals(type)) && (type != null)) {
      sql_1.append(" AND ").append("STOCKTYPE='").append(type).append("'");
    }

    BigDecimal sum = new BigDecimal(0);
    Statement stat = null;
    ResultSet resultSet = null;
    try {
      stat = conn.createStatement();
      resultSet = stat.executeQuery(String.valueOf(sql));
      resultSet.next();
      sum = resultSet.getBigDecimal(1);
      if (sum != null) {
        sum = sum.setScale(2, 4);
      }

      resultSet = stat.executeQuery(String.valueOf(sql_1));
      resultSet.next();
      BigDecimal sumb = resultSet.getBigDecimal(1);
      if ((sumb != null) && (sum != null)) {
        sum = sum.subtract(sumb);
        sum = sum.setScale(2, 4);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(stat, resultSet, conn);
    }
    return sum;
  }

  public int sumFlow(String catno, String flowflag) {
    String sql = "SELECT SUM(AMOUNT)  FROM FLOW_LOG  WHERE CATNO ='" + catno + "' and FLOWFLAG='" + flowflag + "'";
    int r = 0;
    try {
      r = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  private List getResultset(ResultSet rs) throws SQLException {
    List list = new ArrayList();

    while (rs.next()) {
      Flowlog flowlog = new Flowlog();
      try {
        String flowno = rs.getString("FLOWNO");

        flowlog.setFlowno(flowno);
        String CUSTOMID = rs.getString("CUSTOMID");

        flowlog.setCustomNo(CUSTOMID);
        String CUSTOMNAME = rs.getString("CUSTOMNAME");
        flowlog.setCustomName(CUSTOMNAME);
      }
      catch (JdbcSQLException localJdbcSQLException) {
      }
      String catno = rs.getString("CATNO");
      String amount = rs.getString("AMOUNT");

      String sellprice = rs.getString("SELLPRICE");
      String lrprice = rs.getString("LRPRICE");
      String costprice = rs.getString("COSTPRICE");
      String stockytpe = rs.getString("STOCKTYPE");
      String datetime = rs.getString("DATETIMED");
      String STOCKNAME = rs.getString("STOCKNAME");

      if (catno != null) {
        flowlog.setCatno(catno);
      }
      if (amount != null) {
        flowlog.setAmount(Double.parseDouble(amount));
      }

      if (sellprice != null) {
        flowlog.setSellprice(new BigDecimal(sellprice));
      }

      if (lrprice != null) {
        flowlog.setLrprice(new BigDecimal(lrprice));
      }

      if (costprice != null) {
        flowlog.setCostprice(new BigDecimal(costprice));
      }

      if (stockytpe != null) {
        flowlog.setType(stockytpe);
      }
      if (datetime != null) {
        flowlog.setDate(datetime);
      }
      if (STOCKNAME != null) {
        flowlog.setStockname(STOCKNAME);
      }
      try
      {
        String FLOWFLAG = rs.getString("FLOWFLAG");
        String RECORD = rs.getString("RECORD");

        if (FLOWFLAG != null) {
          flowlog.setFlowflag(FLOWFLAG);
        }
        if (RECORD != null) {
          flowlog.setRecorddate(RECORD);
        }
      }
      catch (JdbcSQLException localJdbcSQLException1)
      {
      }
      list.add(flowlog);
    }
    return list;
  }

  public int getFlowLogOrderSize(String starttime, String endtime)
  {
    String sql = "SELECT CATNO ,sum(AMOUNT) AS AMOUNT,sum(LRPRICE ) AS LRPRICE ,STOCKNAME ,STOCKTYPE FROM FLOW_LOG WHERE DATETIMED  between '" + starttime + "' AND '" + endtime + "' GROUP BY CATNO ORDER BY sum(AMOUNT) DESC";

    List list = new ArrayList();
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list.size();
  }

  public List getFlowLogOrderBy(String starttime, String endtime, int start, int max) {
    String sql = "SELECT CATNO ,sum(AMOUNT) AS AMOUNT,sum(LRPRICE ) AS LRPRICE ,STOCKNAME ,STOCKTYPE FROM FLOW_LOG WHERE DATETIMED  between '" + starttime + "' AND '" + endtime + "' GROUP BY CATNO ORDER BY sum(AMOUNT) DESC LIMIT " + 
      max + " OFFSET " + start;

    List list = null;
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getFlowLogOrderBy(String starttime, String endtime) {
    String sql = "SELECT CATNO ,sum(AMOUNT) AS AMOUNT,sum(LRPRICE ) AS LRPRICE ,STOCKNAME ,STOCKTYPE FROM FLOW_LOG GROUP BY CATNO ORDER BY sum(AMOUNT) DESC  ";
    List list = null;
    try
    {
      list = queryForList(sql, new FlowlogRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getFlowLogKeHLogSize(String starttime, String endtime, String cno, String cName) {
    StringBuffer sqlbur = new StringBuffer();
    sqlbur.append("SELECT  COUNT(*) FROM FLOW_LOG WHERE 1=1 ");

    if ((starttime != null) && (!"".equals(starttime)) && (endtime != null) && (!"".equals(endtime))) {
      sqlbur.append(" AND ");
      sqlbur.append(" DATETIMED  between '").append(starttime).append("' AND '").append(endtime).append("'");
    }
    if ((cno != null) && (!"".equals(cno))) {
      sqlbur.append(" AND ");
      sqlbur.append(" CUSTOMID='");
      sqlbur.append(cno);
      sqlbur.append("'");
    }
    if ((cName != null) && (!"".equals(cName))) {
      sqlbur.append(" AND ");
      sqlbur.append(" CUSTOMNAME !='");
      sqlbur.append(cName);
      sqlbur.append("'");
    }

    sqlbur.append(" AND ");
    sqlbur.append(" CUSTOMID !=''");

    int list = 0;
    try
    {
      list = getTotalRow(String.valueOf(sqlbur));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getFlowLogKeHLog(String starttime, String endtime, String cno, String cName, int start, int max)
  {
    StringBuffer sqlbur = new StringBuffer();
    sqlbur.append("SELECT * FROM FLOW_LOG WHERE 1=1 ");

    if ((starttime != null) && (!"".equals(starttime)) && (endtime != null) && (!"".equals(endtime))) {
      sqlbur.append(" AND ");
      sqlbur.append(" DATETIMED  between '").append(starttime).append("' AND '").append(endtime).append("'");
    }
    if ((cno != null) && (!"".equals(cno))) {
      sqlbur.append(" AND ");
      sqlbur.append(" CUSTOMID='");
      sqlbur.append(cno);
      sqlbur.append("'");
    }
    if ((cName != null) && (!"".equals(cName))) {
      sqlbur.append(" AND ");
      sqlbur.append(" CUSTOMNAME !='");
      sqlbur.append(cName);
      sqlbur.append("'");
    }

    sqlbur.append(" AND ");
    sqlbur.append(" CUSTOMID !=''");
    if (max > 0) {
      sqlbur.append(" LIMIT ").append(max).append(" OFFSET ").append(start);
    }

    List list = null;
    try
    {
      list = queryForList(String.valueOf(sqlbur), new FlowlogRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  private class FlowlogRowMapper implements RowMapper {
    private FlowlogRowMapper() {
    }
    public Flowlog mapRow(ResultSet rs, int rowNum) throws SQLException { Flowlog flowlog = new Flowlog();
      try {
        String flowno = rs.getString("FLOWNO");

        flowlog.setFlowno(flowno);
        String CUSTOMID = rs.getString("CUSTOMID");

        flowlog.setCustomNo(CUSTOMID);
        String CUSTOMNAME = rs.getString("CUSTOMNAME");
        flowlog.setCustomName(CUSTOMNAME);
      }
      catch (JdbcSQLException localJdbcSQLException) {
      }
      String catno = rs.getString("CATNO");
      String amount = rs.getString("AMOUNT");

      String sellprice = rs.getString("SELLPRICE");
      String lrprice = rs.getString("LRPRICE");
      String costprice = rs.getString("COSTPRICE");
      String stockytpe = rs.getString("STOCKTYPE");
      String datetime = rs.getString("DATETIMED");
      String STOCKNAME = rs.getString("STOCKNAME");

      if (catno != null) {
        flowlog.setCatno(catno);
      }
      if (amount != null) {
        flowlog.setAmount(Double.parseDouble(amount));
      }

      if (sellprice != null) {
        flowlog.setSellprice(new BigDecimal(sellprice));
      }

      if (lrprice != null) {
        flowlog.setLrprice(new BigDecimal(lrprice));
      }

      if (costprice != null) {
        flowlog.setCostprice(new BigDecimal(costprice));
      }

      if (stockytpe != null) {
        flowlog.setType(stockytpe);
      }
      if (datetime != null) {
        flowlog.setDate(datetime);
      }
      if (STOCKNAME != null) {
        flowlog.setStockname(STOCKNAME);
      }
      try
      {
        String FLOWFLAG = rs.getString("FLOWFLAG");
        String RECORD = rs.getString("RECORD");

        if (FLOWFLAG != null) {
          flowlog.setFlowflag(FLOWFLAG);
        }
        if (RECORD != null)
          flowlog.setRecorddate(RECORD);
      }
      catch (JdbcSQLException localJdbcSQLException1)
      {
      }
      return flowlog;
    }
  }
}