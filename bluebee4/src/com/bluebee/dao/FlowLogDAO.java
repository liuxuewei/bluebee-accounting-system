package com.bluebee.dao;

import com.bluebee.pojo.Flowlog;

import java.math.BigDecimal;
import java.util.List;

public abstract interface FlowLogDAO
{
  public abstract boolean add(Flowlog paramFlowlog);

  public abstract boolean add(Flowlog[] paramArrayOfFlowlog);

  public abstract boolean update(Flowlog paramFlowlog);

  public abstract Flowlog getFlowByflowno(String paramString);

  public abstract List getFlowlog();

  public abstract List getFlowlog(String paramString, int paramInt);

  public abstract List getFlowlog(String paramString);

  public abstract List getFlowlogByToday(String paramString);

  public abstract boolean deleteByFlowno(String paramString);

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
}