package com.bluebee.dao;

import java.sql.Connection;

public class JdbcConnectionPoolHelper
{
  private static JdbcConnectionPoolHelper jdbcConnectionPool = new JdbcConnectionPoolHelper();

  private Connection conn = null;

  public static JdbcConnectionPoolHelper getInstance()
  {
    return jdbcConnectionPool;
  }

  public Connection getConnection() {
    return this.conn;
  }

  public void setConnection(Connection connection) {
    this.conn = connection;
  }
}