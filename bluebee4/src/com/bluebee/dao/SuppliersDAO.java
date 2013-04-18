package com.bluebee.dao;

import com.bluebee.pojo.Suppliers;

import java.util.List;

public abstract interface SuppliersDAO
{
  public abstract void insertSuppliers(Suppliers paramSuppliers);

  public abstract void deleteSuppliers(String paramString);

  public abstract void updateSuppliers(Suppliers paramSuppliers);

  public abstract Suppliers getSuppliersByNo(String paramString);

  public abstract List<String> getSuppliersByName(String paramString, int paramInt);

  public abstract List getSuppliers(int paramInt1, int paramInt2);

  public abstract List getSuppliers();

  public abstract int getSuppliersSize();

  public abstract List getSuppliers(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract int getSuppliersSize(String paramString1, String paramString2, String paramString3);
}