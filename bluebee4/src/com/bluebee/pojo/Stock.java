package com.bluebee.pojo;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Stock
{
  private static DecimalFormat format = new DecimalFormat("###,####.00");
  private String id;
  private String catno;
  private String stockname;
  private double amount;
  private double syamount;
  private BigDecimal costprice = new BigDecimal(0);
  private BigDecimal sellprice = new BigDecimal(0);
  private String type;
  private String date;
  private BigDecimal total = new BigDecimal(0);
  private String stockFlag;
  private String recorddate;
  private String color = "";
  private String specif = "";
  private String suppliers = null;

  public String getStockname() {
    return this.stockname;
  }

  public void setStockname(String stockname) {
    if ("null".equals(stockname))
      this.stockname = "";
    else
      this.stockname = stockname;
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCatno() {
    return this.catno;
  }

  public void setCatno(String catno) {
    this.catno = catno;
  }

  public BigDecimal getTotal() {
    return this.total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total.setScale(2, 4);
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getSellprice() {
    return this.sellprice;
  }

  public void setSellprice(BigDecimal sellprice) {
    this.sellprice = sellprice.setScale(2, 4);
  }

  public BigDecimal getCostprice() {
    return this.costprice;
  }

  public void setCostprice(BigDecimal costprice) {
    this.costprice = costprice.setScale(2, 4);
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getSyamount() {
    return this.syamount;
  }

  public void setSyamount(double syamount) {
    this.syamount = syamount;
  }

  public String getStockFlag() {
    return this.stockFlag;
  }

  public void setStockFlag(String stockFlag) {
    this.stockFlag = stockFlag;
  }

  public String getRecorddate() {
    return this.recorddate;
  }

  public void setRecorddate(String recorddate) {
    this.recorddate = recorddate;
  }

  public String getColor()
  {
    return this.color;
  }

  public void setColor(String color) {
    if ("null".equals(color))
      this.color = "";
    else
      this.color = color;
  }

  public String getSpecif()
  {
    return this.specif;
  }

  public String getSuppliers() {
    return this.suppliers;
  }

  public void setSuppliers(String suppliers) {
    if ("null".equals(suppliers))
      this.suppliers = "";
    else
      this.suppliers = suppliers;
  }

  public void setSpecif(String specif)
  {
    if ("null".equals(specif))
      this.specif = "";
    else
      this.specif = specif;
  }

  public static void main(String[] dd)
  {
    String ddd = "2607.39";
    double dddd = 2607.3899999999999D;
    DecimalFormat df = new DecimalFormat("##.00");
    System.out.println(df.format(dddd));
    BigDecimal ff = new BigDecimal(ddd);
    BigDecimal ffddd = ff.setScale(2, 4);
    System.out.println(ffddd);
    DecimalFormat format = new DecimalFormat("###,####.00");

    System.out.println(format.format(111111123456.12672D));
  }
}