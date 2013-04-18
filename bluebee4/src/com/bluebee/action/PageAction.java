package com.bluebee.action;

import java.awt.event.MouseEvent;

public abstract interface PageAction
{
  public abstract void pageFirst();

  public abstract void pagePrev(int paramInt);

  public abstract void pageNext(int paramInt);

  public abstract void pageLast(int paramInt);

  public abstract void itemStateChanged(int paramInt);

  public abstract void export(MouseEvent paramMouseEvent);
}