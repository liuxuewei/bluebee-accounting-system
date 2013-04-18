package com.bluebee.pojo;

import java.math.BigDecimal;

public class DailyExpenses
{
  private String id;
  private String type;
  private String date;
  private String recorddate;
  private BigDecimal pay;
  private String mode;

  public BigDecimal getPay()
  {
    return this.pay;
  }

  public void setPay(BigDecimal pay) {
    this.pay = pay;
  }

  public String getRecorddate() {
    return this.recorddate;
  }

  public void setRecorddate(String recorddate) {
    this.recorddate = recorddate;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }
  public String getMode() {
    return this.mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
}