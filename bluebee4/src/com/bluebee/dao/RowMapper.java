package com.bluebee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract interface RowMapper<T>
{
  public abstract T mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException;
}