package com.bluebee.ui.frame;

public abstract interface IPage
{
  public abstract String getPageId();

  public abstract String getPageName();

  public abstract void disposePage();

  public abstract boolean isDefaultPage();

  public abstract void setDefaultPage(boolean paramBoolean);
}