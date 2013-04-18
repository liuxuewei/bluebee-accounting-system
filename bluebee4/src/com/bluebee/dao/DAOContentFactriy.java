package com.bluebee.dao;

import com.bluebee.dao.impl.ConfigDAOImpl;
import com.bluebee.dao.impl.CustomDAOImpl;
import com.bluebee.dao.impl.CustomtTypeDAOImpl;
import com.bluebee.dao.impl.DailyExpensesDAOImpl;
import com.bluebee.dao.impl.FlowLogDAOImpl;
import com.bluebee.dao.impl.OptionDAOImpl;
import com.bluebee.dao.impl.PatchDAOImpl;
import com.bluebee.dao.impl.StockDAOImpl;
import com.bluebee.dao.impl.SuppliersDAOImpl;
import com.bluebee.dao.impl.UserDAOImpl;
import com.bluebee.dao.impl.UserRightDAOImpl;

public class DAOContentFactriy
{
  public static StockDAO getStockDAO()
  {
    return new StockDAOImpl();
  }

  public static FlowLogDAO getFlowLogDAO()
  {
    return new FlowLogDAOImpl();
  }

  public static ConfigDAO getConfigDAO()
  {
    return new ConfigDAOImpl();
  }

  public static OptionDAO getOptionDAO()
  {
    return new OptionDAOImpl();
  }

  public static PatchDAO getPatchDAO()
  {
    return new PatchDAOImpl();
  }

  public static DailyExpensesDAO getDailyExpensesDAO()
  {
    return new DailyExpensesDAOImpl();
  }

  public static CustomDAO getCustomDAO()
  {
    return new CustomDAOImpl();
  }

  public static CustomtTypeDAO getCustomtTypeDAO()
  {
    return new CustomtTypeDAOImpl();
  }

  public static UserDAO getUserDAO()
  {
    return new UserDAOImpl();
  }

  public static UserRightDAO getUserRightDAO()
  {
    return new UserRightDAOImpl();
  }
  public static SuppliersDAO getSuppliersDAO() {
    return new SuppliersDAOImpl();
  }
}