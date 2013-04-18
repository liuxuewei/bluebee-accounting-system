package com.bluebee.action;

import com.bluebee.ui.frame.PageContainer;
import com.bluebee.ui.frame.StockPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class StockAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(StockPanel.getInstance());
  }
}