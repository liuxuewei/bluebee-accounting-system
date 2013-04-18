package com.bluebee.dao.impl;

import com.bluebee.dao.RowMapper;
import com.bluebee.dao.StockDAO;
import com.bluebee.pojo.Stock;
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

public class StockDAOImpl extends BaseDAO
  implements StockDAO
{
  public boolean deleteById(String stockid)
  {
    String sql = "DELETE FROM STOCK WHERE ID='" + stockid + "'";
    boolean isin = true;
    try {
      delete(sql);
    } catch (SQLException e) {
      isin = false;
      e.printStackTrace();
    }
    return isin;
  }

  public boolean updateStock(Stock stock)
  {
    boolean isin = false;
    String sql = "UPDATE STOCK SET AMOUNT ='" + 
      stock.getAmount() + "'," + 
      "COSTPRICE ='" + stock.getCostprice() + "'," + 
      "SELLPRICE ='" + stock.getSellprice() + "'," + 
      "DATETIMED ='" + stock.getDate() + "'," + 
      "COSTTOTAL ='" + stock.getTotal() + "'," + 
      "SYAMOUNT ='" + stock.getSyamount() + "'," + 
      "STOCKTYPE ='" + stock.getType() + "'," + 
      "STOCKFLAG ='" + stock.getStockFlag() + "'," + 
      "COLOR ='" + stock.getColor() + "'," + 
      "SPECIF ='" + stock.getSpecif() + "'," + 
      "RECORD ='" + stock.getRecorddate() + "'," + 
      "SUPPLIERSNAME ='" + stock.getSuppliers() + "'" + 
      " WHERE ID='" + stock.getId() + "'";
    try
    {
      update(sql);
    } catch (SQLException e) {
      isin = false;
      e.printStackTrace();
    }
    return isin;
  }

  public boolean updateSyAmount(String catno, double ssAmount, String type)
  {
    Stock stock = getStockByNo(catno);
    if (stock != null) {
      double syAmount = 0.0D;
      String sql = null;
      if ("+".equals(type)) {
        syAmount = stock.getSyamount() + ssAmount;
        if (syAmount > stock.getAmount()) {
          double am = stock.getAmount() + ssAmount;

          sql = "UPDATE STOCK SET SYAMOUNT ='" + syAmount + 
            "', AMOUNT='" + am + "' WHERE ID='" + stock.getId() + "'";
        } else {
          sql = "UPDATE STOCK SET SYAMOUNT ='" + syAmount + 
            "' WHERE ID='" + stock.getId() + "'";
        }
      } else {
        if (stock.getSyamount() == 0.0D) {
          syAmount = 0.0D;
        } else if (stock.getSyamount() > 0.0D) {
          syAmount = stock.getSyamount() - ssAmount;
          if (syAmount < 0.0D) {
            syAmount = 0.0D;
          }
        }
        sql = "UPDATE STOCK SET SYAMOUNT ='" + syAmount + 
          "' WHERE ID='" + stock.getId() + "'";
      }

      boolean isin = false;
      try {
        update(sql);
        isin = true;
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return isin;
    }

    return true;
  }

  public List getStockOrderBy(String starttime, String endtime)
  {
    String sql = "SELECT * FROM STOCK WHERE DATETIMED  between '" + 
      starttime + 
      "' AND '" + 
      endtime + 
      "' AND SYAMOUNT < AMOUNT ORDER BY  SYAMOUNT ASC ,DATETIMED DESC";

    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStockOrderSize(String starttime, String endtime) {
    Connection conn = super.getConnection();
    String sql = "SELECT COUNT(*)  FROM STOCK WHERE DATETIMED  between '" + 
      starttime + "' AND '" + endtime + "' AND SYAMOUNT < AMOUNT ";
    int list = 0;
    try {
      list = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStockOrderBy(String starttime, String endtime, int start, int max)
  {
    Connection conn = super.getConnection();
    String sql = "SELECT * FROM STOCK WHERE DATETIMED  between '" + 
      starttime + 
      "' AND '" + 
      endtime + 
      "' AND SYAMOUNT < AMOUNT ORDER BY  SYAMOUNT ASC ,DATETIMED DESC LIMIT " + 
      max + " OFFSET " + start;

    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStockSize() {
    String sql = "SELECT COUNT(*)  FROM STOCK WHERE SYAMOUNT > 0";

    int r = 0;
    try
    {
      r = getTotalRow(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public List getStock()
  {
    String sql = "SELECT * FROM STOCK WHERE SYAMOUNT > 0";
    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStock(int start, int max)
  {
    String sql = "SELECT * FROM STOCK WHERE SYAMOUNT > 0 LIMIT  " + max + 
      " OFFSET " + start;
    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public Stock getStockByNo(String catno) {
    String sql = "SELECT * FROM STOCK WHERE CATNO='" + catno + "';";

    Stock stock = null;
    try {
      stock = (Stock)queryForObject(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stock;
  }

  public Stock getStockByID(String id) {
    String sql = "SELECT * FROM STOCK WHERE ID='" + id + "';";
    Stock stock = null;
    try {
      stock = (Stock)queryForObject(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stock;
  }

  public Stock getLastStockByNo(String catno)
  {
    String sql = "SELECT * FROM STOCK WHERE CATNO='" + catno + 
      "' AND SYAMOUNT >0 ORDER BY  DATETIMED ASC ;";

    Stock stock = null;
    try {
      stock = (Stock)queryForObject(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stock;
  }

  public List<String> getStockSuggestByCatNo(String catno, int max) {
    Connection conn = super.getConnection();
    catno = sqlStr(catno);
    String sql = "SELECT * FROM STOCK WHERE CATNO LIKE '" + catno + "%' LIMIT " + max;

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
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(stat, rs, conn);
    }
    return list;
  }

  public int sumStockSyAmount(String catno, String cost, String type, String date, String dateTo)
  {
    StringBuffer sqltemp = new StringBuffer();
    sqltemp.append("SELECT SUM (SYAMOUNT) FROM STOCK WHERE 1=1 ");
    if (catno != null) {
      sqltemp.append(" and CATNO='");
      sqltemp.append(catno);
      sqltemp.append("'");
    }
    if (cost != null) {
      sqltemp.append(" and COSTPRICE='");
      sqltemp.append(cost);
      sqltemp.append("'");
    }
    if (type != null) {
      sqltemp.append(" and STOCKTYPE='");
      sqltemp.append(type);
      sqltemp.append("'");
    }
    if (date != null) {
      sqltemp.append(" and DATETIMED>='");
      sqltemp.append(date);
      sqltemp.append("'");
    }
    if (dateTo != null) {
      sqltemp.append(" and DATETIMED<='");
      sqltemp.append(dateTo);
      sqltemp.append("'");
    }
    sqltemp.append(" and  SYAMOUNT > 0");
    int r = 0;
    try {
      r = getTotalRow(String.valueOf(sqltemp));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return r;
  }

  public BigDecimal sumStockCostAmount(String catno, String cost, String type, String date, String dateTo)
  {
    StringBuffer sqltemp = new StringBuffer();
    sqltemp.append("SELECT SUM(COSTTOTAL) FROM STOCK WHERE 1=1 ");
    if (catno != null) {
      sqltemp.append(" and CATNO='");
      sqltemp.append(catno);
      sqltemp.append("'");
    }
    if (cost != null) {
      sqltemp.append(" and COSTPRICE='");
      sqltemp.append(cost);
      sqltemp.append("'");
    }
    if (type != null) {
      sqltemp.append(" and STOCKTYPE='");
      sqltemp.append(type);
      sqltemp.append("'");
    }
    if (date != null) {
      sqltemp.append(" and DATETIMED>='");
      sqltemp.append(date);
      sqltemp.append("'");
    }
    if (dateTo != null) {
      sqltemp.append(" and DATETIMED<='");
      sqltemp.append(dateTo);
      sqltemp.append("'");
    }

    BigDecimal r = new BigDecimal(0);
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(String.valueOf(sqltemp));
      rs.next();
      r = rs.getBigDecimal(1);
      if (r != null)
        r = r.setScale(2, 4);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      close(stat, rs, conn);
    }
    return r;
  }

  public BigDecimal sumSyStockCostAmount(String catno, String cost, String type, String date, String dateTo)
  {
    StringBuffer sqltemp = new StringBuffer();
    sqltemp.append("SELECT  SUM(SYAMOUNT * COSTPRICE ) FROM STOCK WHERE 1=1 ");
    if (catno != null) {
      sqltemp.append(" and CATNO='");
      sqltemp.append(catno);
      sqltemp.append("'");
    }
    if (cost != null) {
      sqltemp.append(" and COSTPRICE='");
      sqltemp.append(cost);
      sqltemp.append("'");
    }
    if (type != null) {
      sqltemp.append(" and STOCKTYPE='");
      sqltemp.append(type);
      sqltemp.append("'");
    }
    if (date != null) {
      sqltemp.append(" and DATETIMED>='");
      sqltemp.append(date);
      sqltemp.append("'");
    }
    if (dateTo != null) {
      sqltemp.append(" and DATETIMED<='");
      sqltemp.append(dateTo);
      sqltemp.append("'");
    }

    sqltemp.append(" and  SYAMOUNT > 0");

    BigDecimal r = new BigDecimal(0);
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(String.valueOf(sqltemp));
      rs.next();
      r = rs.getBigDecimal(1);
      if (r != null)
        r = r.setScale(2, 4);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } finally {
      close(stat, rs, conn);
    }
    return r;
  }

  public double sumStockAmount(String catno) {
    String sql = "SELECT SUM(AMOUNT)  FROM STOCK  WHERE CATNO ='" + catno + "'";

    double r = 0.0D;
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      rs.next();
      r = rs.getDouble(1);
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(stat, rs, conn);
    }
    return r;
  }

  public double sumStockSyAmount(String catno)
  {
    String sql = "SELECT SUM(SYAMOUNT)  FROM STOCK  WHERE CATNO ='" + catno + "'";
    double r = 0.0D;
    Connection conn = super.getConnection();
    Statement stat = null;
    ResultSet rs = null;
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      rs.next();
      r = rs.getDouble(1);
    }
    catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(stat, rs, conn);
    }
    return r;
  }

  public boolean add(Stock stock) {
    boolean isin = false;
    if (stock.getId() == null) {
      String id = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
      stock.setId(id);
    }
    String sql = "INSERT INTO STOCK VALUES ('" + stock.getId() + "'," + "'" + 
      stock.getCatno() + "'," + "'" + stock.getAmount() + "'," + 
      "'" + stock.getSyamount() + "'," + "'" + 
      stock.getCostprice() + "'," + "'" + 
      stock.getSellprice() + "'," + 
      "'" + stock.getType() + "'," + 
      "'" + stock.getDate() + "'," + 
      "'" + stock.getTotal() + "'," + 
      "'" + stock.getStockname() + "'," + 
      "'" + stock.getStockFlag() + "'," + 
      "'" + stock.getColor() + "'," + 
      "'" + stock.getSpecif() + "'," + 
      "'" + stock.getRecorddate() + "'," + 
      "'" + stock.getSuppliers() + "'" + 
      ");";

    Statement stat = null;
    try
    {
      insert(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return isin;
  }

  public int getStockByCatNoSize(String catno)
  {
    String sql = "SELECT COUNT(*) FROM STOCK WHERE CATNO ='" + catno + "'";

    int list = 0;
    try
    {
      list = getTotalRow(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStockByCatCostSize(String cost) {
    String sql = "SELECT COUNT(*) FROM STOCK WHERE COSTPRICE ='" + cost + 
      "'";

    int list = 0;
    try
    {
      list = getTotalRow(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStockByCatNo(String catno, int start, int max) {
    String sql = "SELECT * FROM STOCK WHERE CATNO ='" + catno + "' LIMIT " + 
      max + " OFFSET " + start;

    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStockByCatCost(String cost, int start, int max)
  {
    String sql = "SELECT * FROM STOCK WHERE COSTPRICE ='" + cost + 
      "' LIMIT " + max + " OFFSET " + start;

    List list = null;
    try
    {
      list = queryForList(sql, new StockRowMapper());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStockByParmSize(String catno, String cost, String type, String date, String dateTo) {
    StringBuffer sqltemp = new StringBuffer();
    sqltemp.append("SELECT COUNT(*) FROM STOCK WHERE 1=1 ");
    if (catno != null) {
      sqltemp.append(" and CATNO='");
      sqltemp.append(catno);
      sqltemp.append("'");
    }
    if (cost != null) {
      sqltemp.append(" and COSTPRICE='");
      sqltemp.append(cost);
      sqltemp.append("'");
    }
    if (type != null) {
      sqltemp.append(" and STOCKTYPE='");
      sqltemp.append(type);
      sqltemp.append("'");
    }
    if (date != null) {
      sqltemp.append(" and DATETIMED>='");
      sqltemp.append(date);
      sqltemp.append("'");
    }
    if (dateTo != null) {
      sqltemp.append(" and DATETIMED<='");
      sqltemp.append(dateTo);
      sqltemp.append("'");
    }
    int list = 0;
    try
    {
      list = getTotalRow(String.valueOf(sqltemp));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStockByParm(String catno, String cost, String type, String date, String dateTo, int start, int max)
  {
    StringBuffer sqltemp = new StringBuffer();
    sqltemp.append("SELECT * FROM STOCK WHERE 1=1 ");
    if (catno != null) {
      sqltemp.append(" and CATNO='");
      sqltemp.append(catno);
      sqltemp.append("'");
    }
    if (cost != null) {
      sqltemp.append(" and COSTPRICE='");
      sqltemp.append(cost);
      sqltemp.append("'");
    }
    if (type != null) {
      sqltemp.append(" and STOCKTYPE='");
      sqltemp.append(type);
      sqltemp.append("'");
    }
    if (date != null) {
      sqltemp.append(" and DATETIMED>='");
      sqltemp.append(date);
      sqltemp.append("'");
    }

    if (dateTo != null) {
      sqltemp.append(" and DATETIMED<='");
      sqltemp.append(dateTo);
      sqltemp.append("'");
    }

    String sql = String.valueOf(sqltemp.append(" ORDER BY DATETIMED DESC LIMIT " + max + " OFFSET " + start));
    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getStockByTaday()
  {
    Connection conn = super.getConnection();
    String sql = "SELECT * FROM STOCK WHERE DATETIMED ='" + 
      DateHelper.getNowDateTime() + "'";
    List list = null;
    try {
      list = queryForList(sql, new StockRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getStockAlarmSize(String alarmType, double num)
  {
    String sql = "SELECT  COUNT(*)  from (SELECT CATNO,sum(SYAMOUNT)  SYAMOUNT,COSTPRICE ,STOCKTYPE, DATETIMED ,RECORD ,STOCKFLAG ,COLOR ,SPECIF FROM STOCK   GROUP BY CATNO) where SYAMOUNT " + alarmType + num;
    int totalRow = 0;
    try
    {
      totalRow = getTotalRow(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return totalRow;
  }

  public List getStockAlarm(String alarmType, double num, int start, int max) {
    String sql = "SELECT *  from (SELECT CATNO,sum(SYAMOUNT)  SYAMOUNT,STOCKNAME,COSTPRICE ,STOCKTYPE, DATETIMED ,RECORD ,STOCKFLAG ,COLOR ,SPECIF FROM STOCK   GROUP BY CATNO) where SYAMOUNT " + alarmType + num + " ORDER BY DATETIMED DESC LIMIT " + max + " OFFSET " + start;
    List list = null;
    try {
      list = queryForList(sql, new StockRowAlarmMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  private class StockRowAlarmMapper
    implements RowMapper
  {
    private StockRowAlarmMapper()
    {
    }

    public Stock mapRow(ResultSet rs, int rowNum)
      throws SQLException
    {
      String catno = rs.getString("CATNO");
      String syamount = rs.getString("SYAMOUNT");
      String costprice = rs.getString("COSTPRICE");
      String stockytpe = rs.getString("STOCKTYPE");
      String datetime = rs.getString("DATETIMED");
      String STOCKNAME = rs.getString("STOCKNAME");
      Stock stock = new Stock();
      if (catno != null) {
        stock.setCatno(catno);
      }

      if (syamount != null) {
        stock.setSyamount(Double.parseDouble(syamount));
      }
      if (costprice != null) {
        stock.setCostprice(new BigDecimal(costprice));
      }

      if (stockytpe != null) {
        stock.setType(stockytpe);
      }
      if (datetime != null) {
        stock.setDate(datetime);
      }

      if (STOCKNAME != null)
        stock.setStockname(STOCKNAME);
      try
      {
        String COLOR = rs.getString("COLOR");
        String SPECIF = rs.getString("SPECIF");
        String STOCKFLAG = rs.getString("STOCKFLAG");
        String RECORD = rs.getString("RECORD");
        String SUPPLIERSNAME = rs.getString("SUPPLIERSNAME");
        if (RECORD != null) {
          stock.setRecorddate(RECORD);
        }
        if (STOCKFLAG != null) {
          stock.setStockFlag(STOCKFLAG);
        }
        if (COLOR != null) {
          stock.setColor(COLOR);
        }
        if (SPECIF != null) {
          stock.setSpecif(SPECIF);
        }
        if (SUPPLIERSNAME != null)
          stock.setSuppliers(SUPPLIERSNAME);
      }
      catch (JdbcSQLException localJdbcSQLException) {
      }
      return stock;
    }
  }

  private class StockRowMapper
    implements RowMapper
  {
    private StockRowMapper()
    {
    }

    public Stock mapRow(ResultSet rs, int rowNum)
      throws SQLException
    {
      String id = rs.getString("ID");
      String catno = rs.getString("CATNO");
      String amount = rs.getString("AMOUNT");
      String syamount = rs.getString("SYAMOUNT");
      String costprice = rs.getString("COSTPRICE");
      String sellprice = rs.getString("SELLPRICE");
      String stockytpe = rs.getString("STOCKTYPE");
      String datetime = rs.getString("DATETIMED");
      String costtotal = rs.getString("COSTTOTAL");
      String STOCKNAME = rs.getString("STOCKNAME");

      Stock stock = new Stock();
      if (id != null) {
        stock.setId(id);
      }
      if (catno != null) {
        stock.setCatno(catno);
      }
      if (amount != null) {
        stock.setAmount(Double.parseDouble(amount));
      }
      if (syamount != null) {
        stock.setSyamount(Double.parseDouble(syamount));
      }
      if (costprice != null) {
        stock.setCostprice(new BigDecimal(costprice));
      }
      if (sellprice != null) {
        stock.setSellprice(new BigDecimal(sellprice));
      }

      if (stockytpe != null) {
        stock.setType(stockytpe);
      }
      if (datetime != null) {
        stock.setDate(datetime);
      }
      if (costtotal != null) {
        stock.setTotal(new BigDecimal(costtotal));
      }
      if (STOCKNAME != null)
        stock.setStockname(STOCKNAME);
      try
      {
        String COLOR = rs.getString("COLOR");
        String SPECIF = rs.getString("SPECIF");
        String STOCKFLAG = rs.getString("STOCKFLAG");
        String RECORD = rs.getString("RECORD");
        String SUPPLIERSNAME = rs.getString("SUPPLIERSNAME");

        if (RECORD != null) {
          stock.setRecorddate(RECORD);
        }
        if (STOCKFLAG != null) {
          stock.setStockFlag(STOCKFLAG);
        }
        if (COLOR != null) {
          stock.setColor(COLOR);
        }
        if (SPECIF != null) {
          stock.setSpecif(SPECIF);
        }
        if (RECORD != null) {
          stock.setRecorddate(RECORD);
        }
        if (SUPPLIERSNAME != null)
          stock.setSuppliers(SUPPLIERSNAME);
      }
      catch (JdbcSQLException localJdbcSQLException) {
      }
      return stock;
    }
  }
}