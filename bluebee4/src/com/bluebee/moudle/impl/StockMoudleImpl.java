package com.bluebee.moudle.impl;

import com.bluebee.dao.StockDAO;
import com.bluebee.moudle.StockMoudle;
import com.bluebee.pojo.Stock;

import java.math.BigDecimal;
import java.util.List;

public class StockMoudleImpl
  implements StockMoudle
{
  private StockDAO stockDAO;

  public boolean add(Stock stock)
  {
    return this.stockDAO.add(stock);
  }

  public void setStockDAO(StockDAO stockDAO) {
    this.stockDAO = stockDAO;
  }

  public boolean delete(String stockid) {
    return this.stockDAO.deleteById(stockid);
  }

  public List<String> getStockSuggestByCatNo(String catno, int max) {
    return this.stockDAO.getStockSuggestByCatNo(catno, max);
  }

  public List getStockByTaday() {
    return this.stockDAO.getStockByTaday();
  }

  public boolean updateSyAmount(String catno, double ssAmount, String type) {
    return this.stockDAO.updateSyAmount(catno, ssAmount, type);
  }

  public Stock getLastStockByNo(String catno) {
    return this.stockDAO.getLastStockByNo(catno);
  }

  public Stock getStockByNo(String catno) {
    return this.stockDAO.getStockByNo(catno);
  }

  public boolean updateStock(Stock stock) {
    return this.stockDAO.updateStock(stock);
  }

  public double sumStockAmount(String catno) {
    return this.stockDAO.sumStockAmount(catno);
  }

  public double sumStockSyAmount(String catno) {
    return this.stockDAO.sumStockSyAmount(catno);
  }

  public Stock getStockByID(String id) {
    return this.stockDAO.getStockByID(id);
  }

  public List getStockOrderBy(String starttime, String endtime) {
    return this.stockDAO.getStockOrderBy(starttime, endtime);
  }

  public List getStock(int start, int max) {
    return this.stockDAO.getStock(start, max);
  }

  public List getStock() {
    return this.stockDAO.getStock();
  }

  public int getStockSize() {
    return this.stockDAO.getStockSize();
  }

  public List getStockByCatNo(String catno, int start, int max) {
    return this.stockDAO.getStockByCatNo(catno, start, max);
  }

  public int getStockByCatNoSize(String catno) {
    return this.stockDAO.getStockByCatNoSize(catno);
  }

  public List getStockByCatCost(String cost, int start, int max) {
    return this.stockDAO.getStockByCatCost(cost, start, max);
  }

  public int getStockByCatCostSize(String cost) {
    return this.stockDAO.getStockByCatCostSize(cost);
  }

  public int sumStockSyAmount(String catno, String cost, String type, String date, String dateTo) {
    return this.stockDAO.sumStockSyAmount(catno, cost, type, date, dateTo);
  }

  public BigDecimal sumStockCostAmount(String catno, String cost, String type, String date, String dateTo) {
    return this.stockDAO.sumStockCostAmount(catno, cost, type, date, dateTo);
  }

  public BigDecimal sumSyStockCostAmount(String catno, String cost, String type, String date, String dateTo) {
    return this.stockDAO.sumSyStockCostAmount(catno, cost, type, date, dateTo);
  }

  public int getStockByParmSize(String catno, String cost, String type, String date, String dateTo) {
    return this.stockDAO.getStockByParmSize(catno, cost, type, date, dateTo);
  }

  public List getStockByParm(String catno, String cost, String type, String date, String dateTo, int start, int max) {
    return this.stockDAO.getStockByParm(catno, cost, type, date, dateTo, start, max);
  }

  public int getStockOrderSize(String starttime, String endtime) {
    return this.stockDAO.getStockOrderSize(starttime, endtime);
  }

  public List getStockOrderBy(String starttime, String endtime, int start, int max) {
    return this.stockDAO.getStockOrderBy(starttime, endtime, start, max);
  }

  public int getStockAlarmSize(String alarmType, double num) {
    return this.stockDAO.getStockAlarmSize(alarmType, num);
  }

  public List getStockAlarm(String alarmType, double num, int start, int max) {
    return this.stockDAO.getStockAlarm(alarmType, num, start, max);
  }
}