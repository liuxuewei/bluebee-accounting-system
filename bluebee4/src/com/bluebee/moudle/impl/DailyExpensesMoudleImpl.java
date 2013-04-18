package com.bluebee.moudle.impl;

import com.bluebee.dao.DailyExpensesDAO;
import com.bluebee.moudle.DailyExpensesMoudle;
import com.bluebee.pojo.DailyExpenses;

import java.math.BigDecimal;
import java.util.List;

public class DailyExpensesMoudleImpl
  implements DailyExpensesMoudle
{
  private DailyExpensesDAO dailyExpensesDAO;

  public void setDailyExpensesDAO(DailyExpensesDAO dailyExpensesDAO)
  {
    this.dailyExpensesDAO = dailyExpensesDAO;
  }

  public void add(DailyExpenses dailyExpenses) {
    this.dailyExpensesDAO.add(dailyExpenses);
  }

  public List getDailyExpensesByTaday() {
    return this.dailyExpensesDAO.getDailyExpensesByTaday();
  }

  public void update(DailyExpenses dailyExpenses) {
    this.dailyExpensesDAO.update(dailyExpenses);
  }

  public void deleteById(String id) {
    this.dailyExpensesDAO.deleteById(id);
  }

  public List getList() {
    return this.dailyExpensesDAO.getList();
  }

  public int getListSize() {
    return this.dailyExpensesDAO.getListSize();
  }

  public List getList(int start, int limit) {
    return this.dailyExpensesDAO.getList(start, limit);
  }

  public int getListSize(String stattime, String endTime) {
    return this.dailyExpensesDAO.getListSize(stattime, endTime);
  }

  public List getList(String stattime, String endTime, int start, int limit) {
    return this.dailyExpensesDAO.getList(stattime, endTime, start, limit);
  }

  public List getList(String mode, String type, String stattime, String endTime, int start, int limit) {
    return this.dailyExpensesDAO.getList(mode, type, stattime, endTime, start, limit);
  }

  public int getListSize(String mode, String type, String stattime, String endTime) {
    return this.dailyExpensesDAO.getListSize(mode, type, stattime, endTime);
  }

  public List getList(String mode, String type, String stattime, String endTime) {
    return this.dailyExpensesDAO.getList(mode, type, stattime, endTime);
  }

  public BigDecimal sumDailyExpensesPay(String mode) {
    return this.dailyExpensesDAO.sumDailyExpensesPay(mode);
  }

  public BigDecimal sumDailyExpensesPay(String type, String stattime, String endTime) {
    return this.dailyExpensesDAO.sumDailyExpensesPay(type, stattime, endTime);
  }

  public BigDecimal sumDailyExpensesPay(String mode, String type, String stattime, String endTime) {
    return this.dailyExpensesDAO.sumDailyExpensesPay(mode, type, stattime, endTime);
  }

  public BigDecimal sumDailyExpensesPay(String stattime, String endTime) {
    return this.dailyExpensesDAO.sumDailyExpensesPay(stattime, endTime);
  }
}