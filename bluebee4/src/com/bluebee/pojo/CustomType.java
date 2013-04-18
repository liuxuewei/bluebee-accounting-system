package com.bluebee.pojo;

public class CustomType
{
  private String typename;
  private double integration = 0.0D;
  private double discount = 0.0D;

  public String getTypename() {
    return this.typename;
  }

  public void setTypename(String typename) {
    if ("null".equals(typename))
      this.typename = "";
    else
      this.typename = typename;
  }

  public double getIntegration()
  {
    return this.integration;
  }

  public void setIntegration(double integration) {
    this.integration = integration;
  }

  public double getDiscount() {
    return this.discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }
}