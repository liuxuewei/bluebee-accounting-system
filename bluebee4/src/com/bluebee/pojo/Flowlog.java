package com.bluebee.pojo;

import java.math.BigDecimal;

public class Flowlog
{
  private String flowno;
  private String catno;
  private BigDecimal sellprice;
  private double amount;
  private BigDecimal costprice = new BigDecimal(0);
  private BigDecimal lrprice = new BigDecimal(0);
  private String type;
  private String date;
  private String stockname;
  private String flowflag;
  private String recorddate;
  private String customName = "";
  private String customNo = "";

  public String getFlowno() {
    return this.flowno;
  }

  public void setFlowno(String flowno) {
    this.flowno = flowno;
  }

  public String getCatno() {
    return this.catno;
  }

  public void setCatno(String catno) {
    this.catno = catno;
  }

  public BigDecimal getSellprice() {
    return this.sellprice;
  }

  public void setSellprice(BigDecimal sellprice) {
    this.sellprice = sellprice;
  }

  public BigDecimal getLrprice() {
    return this.lrprice;
  }

  public void setLrprice(BigDecimal lrprice)
  {
    this.lrprice = lrprice.setScale(2, 4);
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public BigDecimal getCostprice() {
    return this.costprice;
  }

  public void setCostprice(BigDecimal costprice) {
    this.costprice = costprice.setScale(2, 4);
  }

  public String getStockname() {
    return this.stockname;
  }

  public void setStockname(String stockname) {
    if ("null".equals(stockname))
      this.stockname = "";
    else
      this.stockname = stockname;
  }

  public String getFlowflag()
  {
    return this.flowflag;
  }

  public void setFlowflag(String flowflag) {
    this.flowflag = flowflag;
  }

  public String getRecorddate() {
    return this.recorddate;
  }
  public void setRecorddate(String recorddate) {
    this.recorddate = recorddate;
  }

  public String getCustomNo() {
    return this.customNo;
  }

  public void setCustomNo(String customNo) {
    if ("null".equals(this.stockname))
      this.customNo = "";
    else
      this.customNo = customNo;
  }

  public String getCustomName()
  {
    return this.customName;
  }

  public void setCustomName(String customName) {
    if ("null".equals(customName))
      this.customName = customName;
    else
      this.customName = customName;
  }
}