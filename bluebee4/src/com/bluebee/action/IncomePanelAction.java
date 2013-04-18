package com.bluebee.action;

import com.bluebee.ui.frame.IncomePanel;
import com.bluebee.ui.frame.PageContainer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class IncomePanelAction extends AbstractAction
{
  public void actionPerformed(ActionEvent e)
  {
    PageContainer.getInstance().setDefaultPage(IncomePanel.getInstance());
  }
}