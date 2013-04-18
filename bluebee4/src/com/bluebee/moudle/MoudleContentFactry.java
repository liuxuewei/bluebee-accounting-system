package com.bluebee.moudle;

import com.bluebee.dao.DAOContentFactriy;
import com.bluebee.moudle.impl.ConfigMoudleImpl;
import com.bluebee.moudle.impl.CustomMoudleImpl;
import com.bluebee.moudle.impl.CustomtTypeMoudleImpl;
import com.bluebee.moudle.impl.DailyExpensesMoudleImpl;
import com.bluebee.moudle.impl.FlowLogMoudleImpl;
import com.bluebee.moudle.impl.OptionMoudleImpl;
import com.bluebee.moudle.impl.PatchMoudleImpl;
import com.bluebee.moudle.impl.StockMoudleImpl;
import com.bluebee.moudle.impl.SuppliersMoudleImpl;
import com.bluebee.moudle.impl.UserMoudleIMpl;
import com.bluebee.moudle.impl.UserRightMoudleImpl;

public class MoudleContentFactry
{
  public static StockMoudle getStockMoudle()
  {
    StockMoudle stockMoudle = new StockMoudleImpl();
    stockMoudle.setStockDAO(DAOContentFactriy.getStockDAO());
    return stockMoudle;
  }

  public static FlowLogMoudle getFlowLogMoudle()
  {
    FlowLogMoudle slowLogMoudle = new FlowLogMoudleImpl();
    slowLogMoudle.setFlowLogDAO(DAOContentFactriy.getFlowLogDAO());
    slowLogMoudle.setStockDAO(DAOContentFactriy.getStockDAO());
    return slowLogMoudle;
  }

  public static ConfigMoudle getConfigMoudle() {
    ConfigMoudle slowLogMoudle = new ConfigMoudleImpl();
    slowLogMoudle.setConfigDAO(DAOContentFactriy.getConfigDAO());
    return slowLogMoudle;
  }

  public static OptionMoudle getOptionMoudle() {
    OptionMoudle slowLogMoudle = new OptionMoudleImpl();
    slowLogMoudle.setOptionDAO(DAOContentFactriy.getOptionDAO());
    return slowLogMoudle;
  }

  public static PatchMoudle getPatchMoudleImpl() {
    PatchMoudleImpl patchMoudle = new PatchMoudleImpl();
    patchMoudle.setPatchDAO(DAOContentFactriy.getPatchDAO());
    return patchMoudle;
  }

  public static DailyExpensesMoudle getDailyExpensesMoudle() {
    DailyExpensesMoudle expensesMoudle = new DailyExpensesMoudleImpl();
    expensesMoudle.setDailyExpensesDAO(DAOContentFactriy.getDailyExpensesDAO());
    return expensesMoudle;
  }

  public static CustomMoudle getCustomMoudle() {
    CustomMoudle customMoudle = new CustomMoudleImpl();
    customMoudle.setCustomDAO(DAOContentFactriy.getCustomDAO());
    return customMoudle;
  }

  public static CustomtTypeMoudle getCustomtTypeMoudle() {
    CustomtTypeMoudle customMoudle = new CustomtTypeMoudleImpl();
    customMoudle.setCustomtTypeDAO(DAOContentFactriy.getCustomtTypeDAO());
    return customMoudle;
  }

  public static UserMoudle getUserMoudle() {
    UserMoudle userMoudle = new UserMoudleIMpl();
    userMoudle.setUserDAO(DAOContentFactriy.getUserDAO());
    return userMoudle;
  }

  public static UserRightMoudle getUserRightMoudle() {
    UserRightMoudle userMoudle = new UserRightMoudleImpl();
    userMoudle.setUserRightDAO(DAOContentFactriy.getUserRightDAO());
    return userMoudle;
  }
  public static SuppliersMoudle getSuppliersMoudle() { SuppliersMoudle suppliersMoudle = new SuppliersMoudleImpl();
    suppliersMoudle.setSuppliersDAO(DAOContentFactriy.getSuppliersDAO());
    return suppliersMoudle;
  }
}