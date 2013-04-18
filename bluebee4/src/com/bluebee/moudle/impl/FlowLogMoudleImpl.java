package com.bluebee.moudle.impl;

import com.bluebee.dao.FlowLogDAO;
import com.bluebee.dao.StockDAO;
import com.bluebee.moudle.FlowLogMoudle;
import com.bluebee.pojo.Flowlog;

import java.math.BigDecimal;
import java.util.List;

public class FlowLogMoudleImpl
  implements FlowLogMoudle
{
  private FlowLogDAO flowLogDAO;
  private StockDAO stockDAO;

  public Flowlog getFlowByflowno(String flowno)
  {
    return this.flowLogDAO.getFlowByflowno(flowno);
  }

  public List getFlowlogByToday(String type) {
    return this.flowLogDAO.getFlowlogByToday(type);
  }

  public boolean delete(String flowno, String catno, double amount) {
    this.stockDAO.updateSyAmount(catno, amount, "+");
    return this.flowLogDAO.deleteByFlowno(flowno);
  }

  public boolean delete(String flowno) {
    return this.flowLogDAO.deleteByFlowno(flowno);
  }

  public boolean update(Flowlog flowlog, String type) {
    if (type != null) {
      this.stockDAO.updateSyAmount(flowlog.getCatno(), flowlog.getAmount(), type);
    }

    return this.flowLogDAO.update(flowlog);
  }

  public boolean add(Flowlog flowlog) {
    return this.flowLogDAO.add(flowlog);
  }

  public boolean add(Flowlog[] flowlogs) {
    return this.flowLogDAO.add(flowlogs);
  }

  public List getFlowlog(String cartno, int size) {
    return this.flowLogDAO.getFlowlog(cartno, size);
  }

  public List getFlowlog(String cartno) {
    return this.flowLogDAO.getFlowlog(cartno);
  }

  public void setFlowLogDAO(FlowLogDAO flowLogDAO) {
    this.flowLogDAO = flowLogDAO;
  }

  public void setStockDAO(StockDAO stockDAO) {
    this.stockDAO = stockDAO;
  }

  public List getStatistical(String starttime, String endtime, String type) {
    return this.flowLogDAO.getStatistical(starttime, endtime, type);
  }

  public int getStatisticalSize(String starttime, String endtime, String type) {
    return this.flowLogDAO.getStatisticalSize(starttime, endtime, type);
  }

  public List getStatistical(String starttime, String endtime, String type, int start, int max) {
    return this.flowLogDAO.getStatistical(starttime, endtime, type, start, max);
  }

  public BigDecimal sumCostPrice(String starttime, String endtime, String type) {
    return this.flowLogDAO.sumCostPrice(starttime, endtime, type);
  }

  public BigDecimal sumLrPrice(String starttime, String endtime, String type) {
    return this.flowLogDAO.sumLrPrice(starttime, endtime, type);
  }

  public BigDecimal sumFlowPrice(String starttime, String endtime, String type) {
    return this.flowLogDAO.sumFlowPrice(starttime, endtime, type);
  }

  public int sumFlow(String catno, String flowflag) {
    return this.flowLogDAO.sumFlow(catno, flowflag);
  }

  public int getFlowLogOrderSize(String starttime, String endtime) {
    return this.flowLogDAO.getFlowLogOrderSize(starttime, endtime);
  }

  public List getFlowLogOrderBy(String starttime, String endtime, int start, int max) {
    return this.flowLogDAO.getFlowLogOrderBy(starttime, endtime, start, max);
  }

  public List getFlowLogOrderBy(String starttime, String endtime) {
    return this.flowLogDAO.getFlowLogOrderBy(starttime, endtime);
  }

  public int getFlowLogKeHLogSize(String starttime, String endtime, String cno, String cName) {
    return this.flowLogDAO.getFlowLogKeHLogSize(starttime, endtime, cno, cName);
  }

  public List getFlowLogKeHLog(String starttime, String endtime, String cno, String cName, int start, int max) {
    return this.flowLogDAO.getFlowLogKeHLog(starttime, endtime, cno, cName, start, max);
  }
  public List getFlowLogKeHLog(String starttime, String endtime, String cno, String cName) { return this.flowLogDAO.getFlowLogKeHLog(starttime, endtime, cno, cName, -1, -1); }

}