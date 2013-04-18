package com.bluebee.moudle;

import com.bluebee.dao.StockDAO;
import com.bluebee.pojo.Stock;

import java.math.BigDecimal;
import java.util.List;

public abstract interface StockMoudle
{
  public abstract void setStockDAO(StockDAO paramStockDAO);

  public abstract boolean add(Stock paramStock);

  public abstract boolean delete(String paramString);

  public abstract List getStockByTaday();

  public abstract boolean updateSyAmount(String paramString1, double paramDouble, String paramString2);

  public abstract Stock getLastStockByNo(String paramString);

  public abstract Stock getStockByNo(String paramString);

  public abstract Stock getStockByID(String paramString);

  public abstract boolean updateStock(Stock paramStock);

  public abstract double sumStockAmount(String paramString);

  public abstract double sumStockSyAmount(String paramString);

  public abstract List getStockOrderBy(String paramString1, String paramString2);

  public abstract List getStock();

  public abstract List<String> getStockSuggestByCatNo(String paramString, int paramInt);

  public abstract List getStock(int paramInt1, int paramInt2);

  public abstract int getStockSize();

  public abstract int sumStockSyAmount(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract int getStockByParmSize(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract List getStockByParm(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2);

  public abstract int getStockByCatNoSize(String paramString);

  public abstract List getStockByCatNo(String paramString, int paramInt1, int paramInt2);

  public abstract List getStockByCatCost(String paramString, int paramInt1, int paramInt2);

  public abstract int getStockByCatCostSize(String paramString);

  public abstract BigDecimal sumStockCostAmount(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract BigDecimal sumSyStockCostAmount(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract int getStockOrderSize(String paramString1, String paramString2);

  public abstract List getStockOrderBy(String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract int getStockAlarmSize(String paramString, double paramDouble);

  public abstract List getStockAlarm(String paramString, double paramDouble, int paramInt1, int paramInt2);
}