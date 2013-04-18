package com.bluebee.moudle.impl;

import com.bluebee.dao.SuppliersDAO;
import com.bluebee.moudle.SuppliersMoudle;
import com.bluebee.pojo.Suppliers;

import java.util.List;

public class SuppliersMoudleImpl
  implements SuppliersMoudle
{
  private SuppliersDAO suppliersDAO = null;

  public void setSuppliersDAO(SuppliersDAO suppliersDAO) {
    this.suppliersDAO = suppliersDAO;
  }

  public void insertSuppliers(Suppliers suppliers) {
    this.suppliersDAO.insertSuppliers(suppliers);
  }

  public void deleteSuppliers(String suppliersno) {
    this.suppliersDAO.deleteSuppliers(suppliersno);
  }

  public void updateSuppliers(Suppliers suppliers) {
    this.suppliersDAO.updateSuppliers(suppliers);
  }
  public List<String> getSuppliersByName(String name, int max) {
    return this.suppliersDAO.getSuppliersByName(name, max);
  }
  public List getSuppliers() {
    return this.suppliersDAO.getSuppliers();
  }
  public List getSuppliers(int start, int max) {
    return this.suppliersDAO.getSuppliers(start, max);
  }
  public int getSuppliersSize() {
    return this.suppliersDAO.getSuppliersSize();
  }

  public Suppliers getSuppliersByNo(String no) {
    return this.suppliersDAO.getSuppliersByNo(no);
  }

  public List getSuppliers(String name, String concat, String phone, int start, int max) {
    return this.suppliersDAO.getSuppliers(name, concat, phone, start, max);
  }

  public int getSuppliersSize(String name, String concat, String phone) {
    return this.suppliersDAO.getSuppliersSize(name, concat, phone);
  }
}