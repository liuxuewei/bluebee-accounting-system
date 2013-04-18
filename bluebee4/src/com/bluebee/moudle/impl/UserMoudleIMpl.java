package com.bluebee.moudle.impl;

import com.bluebee.dao.UserDAO;
import com.bluebee.moudle.UserMoudle;
import com.bluebee.pojo.User;

import java.util.List;

public class UserMoudleIMpl
  implements UserMoudle
{
  private UserDAO userDAO;

  public void setUserDAO(UserDAO userDAO)
  {
    this.userDAO = userDAO;
  }

  public void add(User user) {
    this.userDAO.add(user);
  }

  public User getUserByid(String id) {
    return this.userDAO.getUserByid(id);
  }
  public User getUserByName(String name) { return this.userDAO.getUserByName(name); }

  public void updateUser(User user)
  {
    this.userDAO.updateUser(user);
  }

  public void deleteUser(String id) {
    this.userDAO.deleteUser(id);
  }

  public List getUser(String type) {
    return this.userDAO.getUser(type);
  }
}