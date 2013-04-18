package com.bluebee.pojo;

import java.math.BigDecimal;

public class Custom
{
  private String id;
  private String name;
  private String type;
  private String sex;
  private String birthday = "0000-01-01";
  private BigDecimal amount = new BigDecimal(0);
  private int frequency;
  private String telephone;
  private String address;
  private String note;
  private String regDate;
  private double integration = 0.0D;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirthday() {
    return this.birthday;
  }

  public void setBirthday(String birthday) {
    if ("null".equals(birthday))
      this.birthday = "";
    else
      this.birthday = birthday;
  }

  public BigDecimal getAmount()
  {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public int getFrequency() {
    return this.frequency;
  }

  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  public String getTelephone()
  {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    if ("null".equals(telephone))
      this.telephone = "";
    else
      this.telephone = telephone;
  }

  public String getAddress()
  {
    return this.address;
  }

  public void setAddress(String address) {
    if ("null".equals(address))
      this.address = "";
    else
      this.address = address;
  }

  public String getNote()
  {
    return this.note;
  }

  public void setNote(String note) {
    if ("null".equals(note))
      this.note = "";
    else
      this.note = note;
  }

  public String getRegDate()
  {
    return this.regDate;
  }

  public void setRegDate(String regDate) {
    this.regDate = regDate;
    if ("null".equals(regDate))
      this.regDate = "";
    else
      this.regDate = regDate;
  }

  public double getIntegration()
  {
    return this.integration;
  }

  public void setIntegration(double integration) {
    this.integration = integration;
  }

  public String toString() {
    return getId() + "," + getName() + "," + getTelephone() + "," + getType() + "," + getIntegration();
  }
}