package com.bluebee.dao.impl;

import com.bluebee.dao.RowMapper;
import com.bluebee.dao.UserRightDAO;
import com.bluebee.pojo.UserRight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRightDAOImpl extends BaseDAO
  implements UserRightDAO
{
  public void add(UserRight userRight)
  {
    String sql = "INSERT INTO USERRIGHTS VALUES ('" + userRight.getUserName() + "','" + userRight.getRight() + "');";

    boolean isin = false;
    Statement stat = null;
    try
    {
      insert(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public UserRight get(String username) {
    String sql = "SELECT * FROM USERRIGHTS WHERE USERNAME ='" + username + "'";
    UserRight users = null;
    try
    {
      users = (UserRight)queryForObject(sql, new UserRightRowMapper());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return users;
  }

  public void update(UserRight userRight) {
    String sql = "UPDATE USERRIGHTS SET RIGHTS ='" + userRight.getRight() + "' WHERE USERNAME  ='" + userRight.getUserName() + "'";
    try {
      update(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteById(String username) {
    String sql = "DELETE FROM USERRIGHTS WHERE USERNAME='" + username + "'";
    try {
      delete(sql);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private class UserRightRowMapper implements RowMapper {
    private UserRightRowMapper() {
    }

    public UserRight mapRow(ResultSet rs, int rowNum) throws SQLException { String USERNAME = rs.getString("USERNAME");
      String RIGHTS = rs.getString("RIGHTS");

      UserRight user = new UserRight();

      if (USERNAME != null) {
        user.setUserName(USERNAME);
      }
      if (RIGHTS != null) {
        user.setRight(RIGHTS);
      }
      return user;
    }
  }
}