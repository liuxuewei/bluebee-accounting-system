package com.bluebee.moudle.impl;

import com.bluebee.dao.CustomtTypeDAO;
import com.bluebee.moudle.CustomtTypeMoudle;
import com.bluebee.pojo.CustomType;

import java.util.List;

public class CustomtTypeMoudleImpl
  implements CustomtTypeMoudle
{
  private CustomtTypeDAO customtTypeDAO;

  public void setCustomtTypeDAO(CustomtTypeDAO customtTypeDAO)
  {
    this.customtTypeDAO = customtTypeDAO;
  }

  public void add(CustomType customType) {
    this.customtTypeDAO.add(customType);
  }

  public void update(CustomType customType) {
    this.customtTypeDAO.update(customType);
  }

  public void saveorupdate(CustomType customType) {
    this.customtTypeDAO.update(customType);
  }

  public void addCustomTypes(CustomType[] customType) {
    this.customtTypeDAO.addCustomTypes(customType);
  }

  public void updateCustomTypes(CustomType[] customType) {
    this.customtTypeDAO.updateCustomTypes(customType);
  }

  public void delete(String name) {
    this.customtTypeDAO.deleteByName(name);
  }

  public CustomType get(String name) {
    return this.customtTypeDAO.get(name);
  }

  public List getCustomtTypes() {
    return this.customtTypeDAO.getCustomtTypes();
  }
}