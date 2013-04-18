package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.SellOrderpane;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class SellOrderAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(SellOrderpane.getInstance());
  }
}