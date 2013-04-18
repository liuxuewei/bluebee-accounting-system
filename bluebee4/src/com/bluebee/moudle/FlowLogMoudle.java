package com.bluebee.moudle;

import com.bluebee.dao.FlowLogDAO;
import com.bluebee.dao.StockDAO;
import com.bluebee.pojo.Flowlog;

import java.math.BigDecimal;
import java.util.List;

public abstract interface FlowLogMoudle
{
  public abstract void setFlowLogDAO(FlowLogDAO paramFlowLogDAO);

  public abstract void setStockDAO(StockDAO paramStockDAO);

  public abstract boolean add(Flowlog paramFlowlog);

  public abstract boolean add(Flowlog[] paramArrayOfFlowlog);

  public abstract Flowlog getFlowByflowno(String paramString);

  public abstract List getFlowlogByToday(String paramString);

  public abstract boolean update(Flowlog paramFlowlog, String paramString);

  public abstract boolean delete(String paramString1, String paramString2, double paramDouble);

  public abstract boolean delete(String paramString);

  public abstract List getFlowlog(String paramString, int paramInt);

  public abstract List getFlowlog(String paramString);

  public abstract List getStatistical(String paramString1, String paramString2, String paramString3);

  public abstract int getStatisticalSize(String paramString1, String paramString2, String paramString3);

  public abstract List getStatistical(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract BigDecimal sumCostPrice(String paramString1, String paramString2, String paramString3);

  public abstract BigDecimal sumLrPrice(String paramString1, String paramString2, String paramString3);

  public abstract BigDecimal sumFlowPrice(String paramString1, String paramString2, String paramString3);

  public abstract int sumFlow(String paramString1, String paramString2);

  public abstract int getFlowLogOrderSize(String paramString1, String paramString2);

  public abstract List getFlowLogOrderBy(String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract List getFlowLogOrderBy(String paramString1, String paramString2);

  public abstract int getFlowLogKeHLogSize(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List getFlowLogKeHLog(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2);

  public abstract List getFlowLogKeHLog(String paramString1, String paramString2, String paramString3, String paramString4);
}