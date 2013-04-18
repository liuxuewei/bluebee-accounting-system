package com.bluebee.dao.impl;

import com.bluebee.dao.JdbcConnectionPoolHelper;
import com.bluebee.dao.JdbcTemplate;
import com.bluebee.dao.RowMapper;
import com.bluebee.dao.StatementCallback;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO
{
  private JdbcTemplate jdbcTemplate;

  public Connection getConnection()
  {
    return JdbcConnectionPoolHelper.getInstance().getConnection();
  }

  public String sqlStr(String value) {
    if (value == null) {
      return null;
    }

    StringBuffer ans = new StringBuffer();
    String ch = "";
    for (int cnt = 0; cnt < value.length(); cnt++) {
      ch = value.substring(cnt, cnt + 1);
      if (ch.equals("'"))
        ans.append("''");
      else {
        ans.append(ch);
      }
    }
    return ans.toString();
  }

  public int[] batchUpdate(final String[] sql)
    throws SQLException
  {
    return (int[])execute(new StatementCallback()
    {
      private String currSql;

      public int[] doInStatement(Statement stmt)
        throws SQLException
      {
        int[] rowsAffected = new int[sql.length];
        for (int i = 0; i < sql.length; i++) {
          this.currSql = sql[i];
          if (!stmt.execute(sql[i]))
            rowsAffected[i] = stmt.getUpdateCount();
          else {
            throw new SQLException("Invalid batch SQL statement: " + sql[i]);
          }
        }
        return rowsAffected;
      }
    });
  }

  public int execute(final String sql)
    throws SQLException
  {
    return ((Integer)execute(new StatementCallback()
    {
      public Integer doInStatement(Statement stmt)
        throws SQLException
      {
        return Integer.valueOf(stmt.executeUpdate(sql));
      }
    })).intValue();
  }

  public <T> Object execute(StatementCallback<T> action)
    throws SQLException
  {
    Object result = null;
    Statement stmt = null;
    Connection conn = null;
    try {
      conn = getConnection();
      conn.setAutoCommit(false);
      stmt = conn.createStatement();
      result = action.doInStatement(stmt);
      conn.commit();
      return result;
    } catch (SQLException ex) {
      if (conn != null) {
        conn.rollback();
      }
      ex.printStackTrace();
      throw new SQLException(ex.getMessage());
    } finally {
      close(stmt, null, conn);
    }
  }

  public int update(final String sql)
    throws SQLException
  {
    return ((Integer)execute(new StatementCallback()
    {
      public Integer doInStatement(Statement stmt)
        throws SQLException
      {
        return Integer.valueOf(stmt.executeUpdate(sql));
      }
    })).intValue();
  }

  public int insert(final String sql)
    throws SQLException
  {
    return ((Integer)execute(new StatementCallback()
    {
      public Integer doInStatement(Statement stmt)
        throws SQLException
      {
        return Integer.valueOf(stmt.executeUpdate(sql));
      }
    })).intValue();
  }

  public int delete(final String sql)
    throws SQLException
  {
    return ((Integer)execute(new StatementCallback()
    {
      public Integer doInStatement(Statement stmt)
        throws SQLException
      {
        return Integer.valueOf(stmt.executeUpdate(sql));
      }
    })).intValue();
  }

  public <T> Object queryForObject(final String sql, final RowMapper<T> rowMapper)
    throws SQLException
  {
    return execute(new StatementCallback()
    {
      public T doInStatement(Statement stmt)
        throws SQLException
      {
        ResultSet rs = null;
        try {
          rs = stmt.executeQuery(sql);
          int rowNum = 0;
          if (rs.next()) {
            return rowMapper.mapRow(rs, rowNum);
          }
          return null;
        }
        finally {
          BaseDAO.this.close(rs);
        }
      }
    });
  }

  public boolean query(final String sql)
    throws SQLException
  {
    Boolean aBoolean = (Boolean)execute(new StatementCallback()
    {
      public Boolean doInStatement(Statement stmt)
        throws SQLException
      {
        ResultSet rs = stmt.executeQuery(sql);
        return Boolean.valueOf(rs.next());
      }
    });
    return aBoolean.booleanValue();
  }

  public int getTotalRow(String sql)
    throws SQLException
  {
    RowMapper totalRow = new RowMapper() {
      public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Integer.valueOf(rs.getInt(1));
      }
    };
    Object number = queryForObject(sql, totalRow);

    return number != null ? ((Integer)number).intValue() : 0;
  }

  public List queryForList(final String sql, final RowMapper rowMapper)
    throws SQLException
  {
    return (List)execute(new StatementCallback()
    {
      public List doInStatement(Statement stmt)
        throws SQLException
      {
        List results = new ArrayList();
        ResultSet rs = stmt.executeQuery(sql);
        int rowNum = 0;
        while (rs.next()) {
          results.add(rowMapper.mapRow(rs, rowNum++));
        }
        return results;
      }
    });
  }

  public void close(PreparedStatement ps, ResultSet rs, Connection conn)
  {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        System.out.println("preparedStatement 关闭失败");
        e.printStackTrace();
      }
    }
    close(rs, conn);
  }

  public void close(Statement ps, ResultSet rs, Connection conn)
  {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    close(rs, conn);
  }

  public void close(ResultSet rs, Connection conn)
  {
    close(rs);
  }

  public void close(ResultSet rs)
  {
    if (rs != null)
      try {
        rs.close();
      } catch (SQLException e) {
        System.out.println("ResultSet 关闭失败");
        e.printStackTrace();
      }
  }
}